# Warnsdorff's Algorithm (Python)

## Overview
A **knight's tour** is a sequence of moves of a knight on a chessboard such that the knight visits every square only once. If the knight ends on a square that is one knight's move from the beginning square (so that it could tour the board again immediately, following the same path), the tour is closed, otherwise it is open.

## Files
* **main.py**: The entry point of the application. This file contains the main logic and flow of the program.

* **board.py**: This file manages the board functionalities. It includes methods to initialize, update, and display the board.

* **requirements.txt**: Lists the Python dependencies required to run the project. These can be installed using `pip`.

## Python Build

```
$ pip install -r requirements.txt
```

## Dependencies
This project requires the follow Python packages:

* `windows-curses`: Required for Windows systems to support curses functionality.

* `mypy`: A static type checker for Python.

* `colorama`: Provides color and formatting for terminal output.

These dependecies are listed in the `requirements.txt` file and can be installed using the command provided above.

## Usage
To run the application, execute the following command:

```zsh
python main.py
```

## Contributing
Contributions are welcome! If you have suggestions, bug fixes, or improvements, feel free to open an issue or submit a pull request.
