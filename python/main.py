import os
import sys
import curses
from typing import List, Tuple
from colorama import init, Fore
from time import sleep
from heapq import heappush, heappop
from utils.cursor import Cursor
from utils.input import print_dummy_board, clear, validate
from utils.board import print_board, update_board

def algorithm(stdscr, cursor: Cursor, board: List[List[int]], krow: int, kcol: int) -> None:
  # WARNSDORFF'S ALGORITHM IMPLEMENTATION
  # Directions the knight can move on the chessboard
  dx: List[int] = [1, 2, 2, 1, -1, -2, -2, -1]
  dy: List[int] = [-2, -1, 1, 2, 2, 1, -1, -2]

  for _ in range(64):
    board[krow][kcol] = 1
    # Priority queue of avialable neighbors
    pq: List[Tuple[int, int]] = []

    # Degree of neighbors
    for i in range(8):
      nrow: int = krow + dx[i]
      ncol: int = kcol + dy[i]

      if 0 <= nrow <= 7 and 0 <= ncol <= 7 and board[nrow][ncol] == 0:
        # Count the available neightbors of the neighbor
        count = 0
        for j in range(8):
          nnrow: int = nrow + dx[j]
          nncol: int = ncol + dy[j]

          if 0 <= nnrow <= 7 and 0 <= nncol <= 7 and board[nnrow][nncol] == 0:
            count += 1
        heappush(pq, (count, i))
    
    if len(pq) > 0:
      # Knight's next position
      (p, m) = heappop(pq)
      krow += dx[m]
      kcol += dy[m]
      board[krow][kcol] = 2
      update_board(stdscr, cursor, board)
    else:
      board[krow][kcol] = 1
      update_board(stdscr, cursor, board) 

def visualize(stdscr, pos: Tuple[int, int]) -> None:
  # Clear the window
  stdscr.clear()

  # Hide the cursor
  curses.curs_set(0)

  # COLORS
  # Boundry
  curses.init_pair(1, curses.COLOR_YELLOW, curses.COLOR_BLACK)

  # Visitied cell
  curses.init_pair(2, curses.COLOR_GREEN, curses.COLOR_BLACK)

  # Knight's cell
  curses.init_pair(3, curses.COLOR_RED, curses.COLOR_BLACK)

  # Unvisited cell
  curses.init_pair(4, curses.COLOR_WHITE, curses.COLOR_BLACK)

  # Progress bar
  curses.init_pair(5, curses.COLOR_MAGENTA, curses.COLOR_BLACK)

  # Get max X and Y co-ordinates of the window
  max_y, max_x = stdscr.getmaxyx()

  # 8 x 8 chess board
  board: List[List[int]] = [[0] * 8 for _ in range(8)]

  # Initialize the cursor
  cursor: Cursor = Cursor(max_x, max_y)

  # Place the knight on the board
  board[pos[0]][pos[1]] = 2

  # Print the chess board
  print_board(stdscr, cursor, board, sleep_value=2.0, initialize=True)

  # Start the Warnsdorff Algorithm
  algorithm(stdscr, cursor, board, pos[0], pos[1])

  sleep(5.0)

def main() -> None:
  # Initialize colorama
  init(autoreset=True)

  while True:
    clear()

    # Print dummy chess board to help user decide knight's position
    print_dummy_board()

    pos = input('knight\'s position (row, col): ')

    # Check user's input
    if validate(pos):
      break
    else:
      print(Fore.RED + 'Invalid, Try Again.')
      sleep(1)
  
  # Start visualization
  curses.wrapper(visualize, tuple(map(int, pos.split(','))))

  clear()

if __name__ == '__main__':
  # Get the size of the terminal
  size = os.get_terminal_size()

  if size[0] < 55 or size[1] < 25:
    sys.exit('Maximize your terminal window. (Req: column=55, lines=25)')

  main()
