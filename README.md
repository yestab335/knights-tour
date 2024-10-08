# Warnsdorff's Algorithm

<div align="center">
  <img src="./images/visualizer.gif" alt="visualizer" />
  <p align="center">
    <strong>Fig.1 Algorithm Visualization</strong>
  </p>
</div>

## Python Build

```
$ pip install -r requirements.txt
```

## Intro

A **knight's tour** (See `Fig.2`) is a sequence of moves of a knight on a chessboard such that the knight visits every square only once. If the knight ends on a square that is one knight's move from the beginning square (so that it could tour the board again immediately, following the same path), the tour is closed, otherwise it is open.

<div align="center">
  <img src="https://github.com/yestab335/knights-tour/blob/readme-update/.github/knight-tour.gif" alt="sample knight's tour" width="50%" />
  <p align="center">
    <strong>Fig.2 Sample Knight's Tour</strong>
  </p>
</div>

We can solve the **knight's tour** problem using warnsdorff's algorithm, which states that:-

- We can start from any initial position of the knight on the board.
- We can always move to an adjacent, unvisited square with minimal degree(minimum number of unvisited adjacent).

## Sample Run

`Fig.1` demonstrates a sample run of the visualizer when knight is placed at `0, 0`, you can find other samples [here](https://github.com/yestab335/knights-tour/blob/main/SAMPLES.md).

| Symbol | Meaning           |
| :----: | ----------------- |
|   0    | Unvisited Cell    |
|   1    | Visited Cell      |
|   2    | Knight's Position |

## Fun Fact

On an _8 × 8 board_, there are exactly _26,534,728,821,064 directed closed tours_ (i.e. two tours along the same path that travel in opposite directions are counted separately, as are rotations and reflections). The number of _undirected closed tours is half this number_, since every tour can be traced in reverse!

## Facing a Issue

If you are in this situation _first and foremost_ Don't panic :cry: I'm here to help you get over it. Simply click [this](https://github.com/yestab335/knights-tour/issues) and properly state your issue (be as verbose as you can be), After that sit tight and watch :movie_camera: the movie you have been postponing for so long while I :construction_worker: fix the issue.

## Want to Contribute

I will be glad :smiley: to work with you on a new idea or fixing a invisible bug :bug: or if you have already done the work :hammer: just create a pull request and I will merge it asap.