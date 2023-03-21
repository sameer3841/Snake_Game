import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int dotsEaten;
    int dotX;
    int dotY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(new Color(206, 86, 12));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame(){
        newDot();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        if(running){
            /*
            for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
                g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
            }
            The code above shows the grid of the map. Don't need this grid, so it is converted into a comment.
             */
            g.setColor(Color.yellow);
            g.fillOval(dotX,dotY,UNIT_SIZE,UNIT_SIZE);

            for(int i = 0; i < bodyParts;i++) {
                if (i == 0) {
                    g.setColor(new Color(156, 11, 11));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    //g.setColor(new Color(75, 6, 6));
                    //The line below gives snake darker but colorful body parts except the head of the snake
                    g.setColor(new Color(random.nextInt(155),random.nextInt(155),random.nextInt(155)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(new Color(121, 21, 127));
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + dotsEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: " + dotsEaten)) / 2, g.getFont().getSize());

        } else {
            GameOver(g);
        }

    }

    public void newDot(){
        //spawns yellow dot
        dotX = random.nextInt(SCREEN_WIDTH/UNIT_SIZE) * UNIT_SIZE;
        dotY = random.nextInt(SCREEN_HEIGHT/UNIT_SIZE) * UNIT_SIZE;

    }

    public void move(){
        for(int i = bodyParts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction) {
            //Up
            case 'U' -> y[0] = y[0] - UNIT_SIZE;

            //down
            case 'D' -> y[0] = y[0] + UNIT_SIZE;

            //left
            case 'L' -> x[0] = x[0] - UNIT_SIZE;

            //right
            case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }
    }

    public void checkDot(){
        //if snake finds dot, snake grows one block longer, score increase by 1, and dot respawns in a new location
        if((x[0] == dotX) && (y[0] == dotY)){
            bodyParts++;
            dotsEaten++;
            newDot();
        }
    }

    public void checkCollisions() {
        //checks if head touches body
        for(int i = bodyParts; i > 0; i--){
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                break;
            }
        }
        //These if statements are for the borders of the game screen
        //left border
        if(x[0] < 0){
            running = false;
        }
        //right border
        if(x[0] > SCREEN_WIDTH){
            running = false;
        }
        //top border
        if(y[0] < 0){
            running = false;
        }
        //bottom border
        if(y[0] > SCREEN_HEIGHT){
            running = false;
        }
        //timer stops if snake touches body or any border of screen
        if(!running) {
            timer.stop();
        }
    }

    public void GameOver(Graphics g){
        //Score text
        g.setColor(new Color(121, 21, 127));
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + dotsEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score: " + dotsEaten)) / 2, g.getFont().getSize());
        //Game over text
        g.setColor(new Color(156, 11, 11));
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over!",(SCREEN_WIDTH - metrics2.stringWidth("Game Over!")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkDot();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){

                //Controls are WASD!!!
                //Goes left
                case KeyEvent.VK_A:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                //Goes right
                case KeyEvent.VK_D:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                //Goes up
                case KeyEvent.VK_W:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                //Goes down
                case KeyEvent.VK_S:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                //Restarts the game
                case KeyEvent.VK_SPACE:
                    dotsEaten = 0;
                    new GameFrame();
            }
        }
    }
}