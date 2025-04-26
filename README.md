# Snake Game in Java
This is a classic Snake game implemented in Java. The game is built using Swing for the graphical user interface and Java AWT for event handling. The game is played on a game panel within a frame and includes all the basic mechanics of the traditional Snake game.

## Features
- **Simple Gameplay:** Navigate the snake around the screen to collect food, growing longer with each food item eaten.

- **Game Over:** The game ends if the snake collides with the walls or itself.

- **Randomized Food:** The food items appear randomly on the screen after each one is eaten.

- **Smooth Controls:** Control the snake with the arrow keys or WASD.

## Requirements
Java 8 or higher

## Installation
#### Clone this repository:

```bash
git clone https://github.com/your-username/snake-game.git
```
#### Navigate into the project folder:

```bash
cd Snake-Game
```
#### Compile and run the program:

```bash
javac GameFrame.java GamePanel.java
java GameFrame
```
## Files
- GameFrame.java - The JFrame window that holds the game panel.
  
- GamePanel.java - The JPanel where the game mechanics are handled, including the action listener for snake movement.
  
- SnakeGame.java - The main class that runs the game.

## Gameplay
1. Use the arrow keys to move the snake in the four directions (up, down, left, right).
2. The snake will grow longer each time it eats food.
3. The game ends when the snake collides with itself or the boundaries of the screen.

## Contributions
Feel free to fork the repo, make changes, and submit pull requests. Contributions are welcome!

## License
This project is licensed under the MIT License - see the LICENSE file for details.

