# Hunt-The-Wumpus-Game

## Abstract
The project’s main goal is to implement the classic game Hunt The Wumpus. The data structure “Graph”, which consists of vertices and each of them could be connected to four directions, is applied to implement the game. Vertices are served as rooms in the game. The `Hunter` and the `Wumpus` are randomly placed in two of the vertices. Actions of the `Hunter` is controlled by keyboard and the rooms are set to be red if the `Wumpus` is less than two rooms away, in which the distance is represented as the “cost” in the program calculated by Dijkstra's algorithm (shortest distance).
 
## Solution
### 1) Graph Class
This class holds a very important method addEdge() that will enable the paths(mutual connections) between the rooms so that the user will be able to control the Hunter moving around the rooms. It also has the shortestPath() method that counts the cost of different vertices, providing the information needed to change the room’s color.

### 2) Hunter and Wumpus Class
Both of them have methods that can return and change their locations. The most important part to my understanding is the boolean fields that tell whether they are alive, visible. By working together with setXXX() and getXXX() methods that process both fields, their conditions can be told and updated, enabling the game to work.

### 3) Main gameplay Implementation (HuntTheWumpus Class)
I would say the most tricky part in the class is connecting all the rooms(vertices) together. Two double loops are used to reach that end and I spent a lot of time trying to figure out what index do I need to use inside the loops. I finally made it by drawing a picture of the graph and titled the rows and columns to see the underlying process I need to write. Besides, A very straightforward structure of implementing the game, which I learned from Alice’s thought, is used to follow the rules of the game. Separating the game into four stages: {normal, shoot, quit, gameover} represented by Enum, rules are written respectively according to different stages of the game. 
 
## Results
Watch the `Example.mov` to watch how I play the game.
 
## Extensions
 1) I drew pictures to represent different states of Wumpus and Hunter
 
 2) Reset button and Quit button are implemented to restart or quit the game
 
 
## Acknowledgments
I would like to give great thanks to Alice Zhang and Shailin for helping me overcome problems while doing the project, as well as inspiring me about ways to implement the game. I could hardly finish the project on my own without their help. I also want to thank Prof. Layton, Prof. Bruce, TA Jackie, TA Prashant, Xiaoyue, Zejun, Kai, Silpian, Briana, Ben, and Qianhan.
