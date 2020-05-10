<div style="text-align: center;"><h1>2048-AI-Solver</h1></div>  
<div align="center">  
<img src="https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/cover.jpg?token=AKD26V4QPCORL6VWW2MMWRK6XRHFG" height = 600 width=500 />  
</div>  
  
A Java-based Artificial Intelligence Solver for the game [2048](https://play2048.co/). It works on a simple Monte-Carlo heuristic algorithm. This project is made for learning about how Monte Carlo methods work. Currently, this project only contains the command line interface which will soon be updated.  
  
# Present Java Classes  
  
- Board.java  
   - This class contains the code for the moving the pieces, calculating the score of the player, knowing the game status after taking actions, etc.  
- Game.java  
   - This class contains the code for playing the game and knowing the win-rate of the AI Solver by playing it for specific times.  
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
  
<div align="center"><img src = "https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/alpha_beta.jpg?token=AKD26VYTKS6ABETPUEHILQK6XRDJ2" /></div>  
  
  
## Minimax Algorithm  
  
It is a kind of backtracking algorithm used to determine the most optimal move for a player, assuming the opponent plays optimally. In the minimax algorithm, two players are called maximizer and minimizer. The maximizer tries to get the highest possible score while the minimizer oppositely tries to get the lowest possible score.  
<div align="center"><img src= "https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/minmax.jpg?token=AKD26VYNQGOX7KOZJZHKBKS6XRDGQ" /></div>  
  
# Want to Play?  
Just run the `Game.java` class and you get the menu for the available commands. And following the instructions, you can play the game.  
  
### Available Controls:  
- `W` - Moving all tiles UP  
- `A` - Moving all tiles LEFT  
- `S` - Moving all tiles DOWN  
- `D` - Moving all tiles RIGHT  
- `h` - Autoplay the move  
- `q` - Bored!! Quit the game.  
  
# Screenshots (CLI version)  
  
<div align = "center">   
<table>  
<tr>  
<td>  
<div align="center"><code>Menu</code></div>  
<br>  
<div style="text-align: center;"><img src="https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/menu.png" height=220/></div>  
</td>  
<td>  
<div align="center"><code>Game Controls</code></div>  
<br>  
<div style="text-align: center;"><img src="https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/play.png" height=250 width = 280/></div>  
</td>  
<td>  
<div align="center"><code>Playing Game with suggestions by AI Solver</code></div>  
<br>  
<div style="text-align: center;"><img src="https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/play2.png" height = 330 width = 250/></div>  
</td>  
</tr>  
<tr>  
<td>  
<div align="center"><code>Win-Rate</code></div>  
<br>  
<div style="text-align: center;"><img src="https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/accuracy2.png" height=270/> </div>  
</td>  
<td>  
<div align="center"><code>Auto-Play</code></div>  
<br>  
<div style="text-align: center;"><img src="https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/autoplay.png" height=250 width = 280/></div>  
</td>  
<td>  
<div align="center"><code>Game Over</code></div>  
<br>  
<div style="text-align: center;"><img src="https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/gameover.png" height = 200 width = 250/> </div>  
</td>  
</tr>  
</table>  
</div>  

# Heuristic Function
This solver uses two heuristics for determining the best possible value for a state. These heuristics include 

- Using the weight matrix
	- This heuristic provides different weights to the tiles of the board.
	- A single corner is given a higher weight as in most of the games the bigger tiles tends to be directed toward the corners only.
	- The weight matrix used in this solver is
	<center><img src = "https://raw.githubusercontent.com/hemilpanchiwala/2048-AI-Solver/master/screenshots/weight_matrix.png" /></center>
	
	- The `score` is found out by summation of the multiplication of each tiles of the board with its corresponding weights.

- Clustering
	- This heuristic acts as a `penalty` for any specific state of game.
	- It calculates the absolute difference of values of a tile with all its adjacent tiles which determines the density of the scattered states.
	- If the tiles are more scattered, then the penalty becomes high which makes the state less preferable.

Hence, the heuristic function is defined as the `score - penalty due to clustering`.
  
# Win-Rate  
The win-rate of the algorithm highly depends on the depth of the tree. With the depth 7 and though time of about half an minute, it achieves winning rate of about 80 - 90% of the games. This can be made even higher increasing the depth of the tree.
