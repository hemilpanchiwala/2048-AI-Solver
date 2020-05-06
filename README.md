# 2048-AI-Solver
A Java-based Artificial Intelligence Solver for the game 2048. It works on a simple Monte-Carlo heuristic algorithm. This project is made for exploring how Monte Carlo methods work.

# Present Java Classes

- Board.java
	- This class contains the code for the moving the pieces, calculating the score of the player, knowing the game status after taking actions, etc.
- Game.java
	- This class contains the code for playing the game and knowing the accuracy of the AI Solver by playing it for specific times.
- Solver.java
	- This class contains the main logic for solving the game. It contains the methods determining the best possible move at any specific state of the game.
- Direction.java
	- This enum class contains the available direction moves for the player.
- GameStatus.java
	- This enum class contains the possible status for the game i.e. WIN, LOSE, etc.

# Alpha-Beta Pruning Algorithm

This algorithm is used for determining the best step to take at every stage of the game. It is simply an expansion of minimax algorithm, that reduces the computation by a huge factor. It cuts off the branches in the game tree which need not to be searched because there already exists a move which is more better. It is named Alpha-Beta pruning as it uses two extra parameters along with the minimax algorithm namely alpha and beta.

- The alpha parameter keeps the track of maximizer at a certain level and it's above.
- While on the other hand, the beta parameter keeps the track of minimizer at a certain level, and it's above.

<div align="center"><img src = "https://media.geeksforgeeks.org/wp-content/uploads/MIN_MAX3.jpg" /></div>


## Minimax Algorithm

It is a kind of backtracking algorithm used to determine the most optimal move for a player, assuming the opponent plays optimally. In the minimax algorithm, two players are called maximizer and minimizer. The maximizer tries to get the highest possible score while the minimizer oppositely tries to get the lowest possible score.
<div align="center"><img src= "https://i.ibb.co/zPyM9xt/minmax1.jpg" /></div>
