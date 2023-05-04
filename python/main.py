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
  # Directions The Knight Can Move On The Chessboard
  dx: List[int] = [1, 2, 2, 1, -1, -2, -2, -1]
  dy: List[int] = [-2, -1, 1, 2, 2, 1, -1, -2]

  for _ in range(64):
    board[krow][kcol] = 1
    # Priority Queue Of Avialable Neighbors
    pq: List[Tuple[int, int]] = []

    # Degree Of Neighbors
    for i in range(8):
      nrow: int = krow + dx[i]
      ncol: int = kcol + dy[i]

      if 0 <= nrow <= 7 and 0 <= ncol <= 7 and board[nrow][ncol] == 0:
        # Count The Available Neighbors Of The Neighbor
        count = 0
        for j in range(8):
          nnrow: int = nrow + dx[j]
          nncol: int = ncol + dy[j]

          if 0 <= nnrow <= 7 and 0 <= nncol <= 7 and board[nnrow][nncol] == 0:
            count += 1
        heappush(pq, (count, i))
    
    if len(pq) > 0:
      # Knight's Next Position
      (p, m) = heappop(pq)
      krow += dx[m]
      kcol += dy[m]
      board[krow][kcol] = 2
      update_board(stdscr, cursor, board)
    else:
      board[krow][kcol] = 1
      update_board(stdscr, cursor, board) 

def visualize(stdscr, pos: Tuple[int, int]) -> None:
  # Clear The Window
  stdscr.clear()

  # Hide The Cursor
  curses.curs_set(0)

  # COLORS
  # Boundry
  curses.init_pair(1, curses.COLOR_YELLOW, curses.COLOR_BLACK)

  # Visitied Cell
  curses.init_pair(2, curses.COLOR_GREEN, curses.COLOR_BLACK)

  # Knight's Cell
  curses.init_pair(3, curses.COLOR_RED, curses.COLOR_BLACK)

  # Unvisited Cell
  curses.init_pair(4, curses.COLOR_WHITE, curses.COLOR_BLACK)

  # Progress Bar
  curses.init_pair(5, curses.COLOR_MAGENTA, curses.COLOR_BLACK)
  # END OF COLORS

  # Get Max X And Y Co-ordinates Of The Window
  max_y, max_x = stdscr.getmaxyx()

  # 8 x 8 Chess Board
  board: List[List[int]] = [[0] * 8 for _ in range(8)]

  # Initialize The Cursor
  cursor: Cursor = Cursor(max_x, max_y)

  # Place The Knight On The Board
  board[pos[0]][pos[1]] = 2

  # Print The Chess Board
  print_board(stdscr, cursor, board, sleep_value=2.0, initialize=True)

  # Start The Warnsdorff Algorithm
  algorithm(stdscr, cursor, board, pos[0], pos[1])

  sleep(5.0)

def main() -> None:
  # Initialize Colorama
  init(autoreset=True)

  while True:
    clear()

    # Print Dummy Chess Board To Help User Decide Knight's Position
    print_dummy_board()

    pos = input('knight\'s position (row, col): ')

    # Check User's Input
    if validate(pos):
      break
    else:
      print(Fore.RED + 'Invalid, Try Again.')
      sleep(1)
  
  # Start Visualization
  curses.wrapper(visualize, tuple(map(int, pos.split(','))))

  clear()

if __name__ == '__main__':
  # Get The Size Of The Terminal
  size = os.get_terminal_size()

  if size[0] < 55 or size[1] < 25:
    sys.exit('Maximize your terminal window. (Req: column=55, lines=25)')

  main()