# Warnsdorff's Algorithm
## Intro
A **knight's tour** is a sequence of moves of a knight on a chessboard such that the knight visits every square only once. If the knight ends on a square that is one knight's move from the beginning square (so that it could tour the board again immediately, following the same path), the tour is closed, otherwise it is open.

We can solve the **knight's tour** problem using warnsdorff's algorithm, which states that:-

- We can start from any initial position of the knight on the board.
- We can always move to an adjacent, unvisited square with minimal degree(minimum number of unvisited adjacent).

## Sample Run

| Symbol | Meaning           |
| :----: | ----------------- |
|   0    | Unvisited Cell    |
|   1    | Visited Cell      |
|   2    | Knight's Position |

## Fun Fact
On an _8 × 8 board_, there are exactly _26,534,728,821,064 directed closed tours_ (i.e. two tours along the same path that travel in opposite directions are counted separately, as are rotations and reflections). The number of _undirected closed tours is half this number_, since every tour can be traced in reverse!

## Facing a Issue
If you are in this situation _first and foremost_ Don't panic, I'm here to help you get over it. Simply click [this](https://github.com/yestab335/knights-tour/issues) and properly state your issue (be as verbose as you can be), After that sit tight and relax while I fix the issue.

## Want to Contribute
Contributions are welcome! If you have suggestions, bug fixes, or improvements, feel free to open an issue or submit a pull request.