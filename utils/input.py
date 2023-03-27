from colorama import Fore
from os import system, name

def clear() -> None:
  # Clears The Console
  
  # For Windows
  if name == 'nt':
    _ = system('cls')
  
  # For Mac And Linux
  else:
    _ = system('clear')

def print_dummy_board() -> None:
  # Print A Dummy Board With Indexes For All The Cells
  j: int = 0

  for i in range(9):
    if i != 0:
      print(str(j) + '   ' + Fore.YELLOW + '|   |   |   |   |   |   |   |   |')
      print('    ' + Fore.YELLOW + '---------------------------------')
      j += 1
    else:
      print('      ' + '0   1   2   3   4   5   6   7\n')
      print('    ' + Fore.YELLOW + '---------------------------------')
  print('\n')

def validate(pos: str) -> bool:
  # Check If User's Input Is Valid
  try:
    row, col = list(map(int, pos.split(',')))
    
    if not 0 <= row <= 7 or not 0 <= col <= 7:
      raise ValueError()
  except:
    return False
  return True