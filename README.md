# Bubble-Blast

> This is the project I've created  to pass the Programming Exam at Università degli Studi di Milano (UniMI) - SSRI.

# The game
A playing field is initialized randomly with bubbles that can be found in 3 states:
1- the bubble needs 3 increments to burst
2- 2 increments are enough to make it burst
3- one increment is enough to burst the bubble
the status 0 indicates the absence of the bubble.
When a bubble bursts it spreads in 4 directions (up, down, right and left) increasing the first bubble it encounters in each direction.
The goal of the game is to pop all the bubbles in the minimum number of moves.

# Minimum number of increments
The count of the minimum increments is performed by executing a dfs along the tree of possible bets, considering all possible game scenarios.
