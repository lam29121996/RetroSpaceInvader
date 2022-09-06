package student.demo;

import game.v2.Console;
import game.v2.Game;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class GameDemo extends Game {

    /*
    You can declare any data fields here for your game as usual.
     */
    //Game Setting
    public static final int width = 1920;
    public static final int height = 1080;
    private static final Image meunBackground = Console.loadImage("/student/demo/img/meunbackground.png");
    private static final Image gameBackground = Console.loadImage("/student/demo/img/gamebackground.png");
    public static int framePerSecond = 100;
    //Menu Object
    private static final Image startImageOne = Console.loadImage("/student/demo/img/start_1.png");
    private static final Image startImageTwo = Console.loadImage("/student/demo/img/start_2.png");
    private static Image[] startImage = {startImageOne, startImageTwo};
    private static final Image pauseImageOne = Console.loadImage("/student/demo/img/pause_1.png");
    private static final Image pauseImageTwo = Console.loadImage("/student/demo/img/pause_2.png");
    private static Image[] pauseImage = {pauseImageOne, pauseImageOne};
    private static final Image replayImageOne = Console.loadImage("/student/demo/img/replay_1.png");
    private static final Image replayImageTwo = Console.loadImage("/student/demo/img/replay_2.png");
    private static Image[] replayImage = {replayImageOne, replayImageOne};
    private static final Image nextImageOne = Console.loadImage("/student/demo/img/next_1.png");
    private static final Image nextImageTwo = Console.loadImage("/student/demo/img/next_2.png");
    private static Image[] nextImage = {nextImageOne, nextImageOne};
    private static final Image successImageOne = Console.loadImage("/student/demo/img/success_1.png");
    private static final Image successImageTwo = Console.loadImage("/student/demo/img/success_2.png");
    private static Image[] successImage = {successImageOne, successImageTwo};

    //Game Parameter
    public static int difficulty = 0;
    public static final int gapBetweenAlien = 70;
    public static final int gapBetweenBlock = 164;
    public static int numberOfFriendlyFire = 2;
    public static int numberOfIncomingFire = 2;
    public static int numberOfBlock = 4;
    public static int highestScore;
    public static int score = 0;
    public static boolean nextStage = false;
    public static boolean allCompleted = false;
    public static boolean gameOver = false;
    //Hidden Game Setting
    public static int leftEdgeOfSafeArea = 150;
    public static int rightEdgeOfSafeArea = width - 150;
    public static int topEdgeOfSafeArea = 150;
    public static int bottomEdgeOfSafeArea = height - 150;
    private static int counter = 0;
    public static int imageSwitch;
    public static int countDeathAlien = 0;
    public static int countDeathUfo = 0;
    public static int countDeathEnemy = countDeathAlien + countDeathUfo;
    //Create Object
    public static Audio enemyDead;
    public static Audio spaceshipDead;
    public static Audio spaceshipShoot;
    public static Audio backgroundMusic;
    public static Button start;
    public static Button pause;
    public static Button replay;
    public static Button next;
    public static Button success;
    public static Spaceship spaceship;
    public static Alien[][] alien;
    public static Ufo[] ufo;
    public static FriendlyFire[] friendlyFire;
    public static IncomingFire[] incomingFire;
    public static Block[] block;
    //I/O
    private static final File data = new File("./src/student/demo/data.txt");

    private static void initialization() {
        enemyDead = new Audio("/student/demo/audio/enemy_dead.wav");
        spaceshipDead = new Audio("/student/demo/audio/spaceship_dead.wav");
        spaceshipShoot = new Audio("/student/demo/audio/spaceship_shoot.wav");
        backgroundMusic = new Audio("/student/demo/audio/Fantasia_On_Greensleeves.wav");
        start = new Button(startImage, 150, 50, 885, 400, false);
        pause = new Button(pauseImage, 150, 50, 885, 400, false);
        replay = new Button(replayImage, 150, 50, 885, 400, false);
        next = new Button(nextImage, 150, 50, 885, 400, false);
        success = new Button(successImage, 150, 50, 885, 400, false);
        spaceship = new Spaceship(48, 48, (int) (Math.random() * (rightEdgeOfSafeArea - leftEdgeOfSafeArea)) + leftEdgeOfSafeArea, bottomEdgeOfSafeArea, 3);
        alien = new Alien[5][11];
        for (int i = 0; i < alien.length; i++) {
            for (int j = 0; j < alien[i].length; j++) {
                alien[i][j] = new Alien(64, 64, (difficulty * 2), 20, (leftEdgeOfSafeArea + (gapBetweenAlien * j)), (topEdgeOfSafeArea + (gapBetweenAlien * i)), 1);
            }
        }
        ufo = new Ufo[1];
        ufo[0] = new Ufo(128, 128, ((difficulty * 2) + 3), 0, (int) (Math.random() * width), 40, 1);
        friendlyFire = new FriendlyFire[numberOfFriendlyFire];
        for (int i = 0; i < numberOfFriendlyFire; i++) {
            friendlyFire[i] = new FriendlyFire(4, 10, 0, 20, 0, 0, 0);
        }
        incomingFire = new IncomingFire[numberOfIncomingFire];
        for (int i = 0; i < incomingFire.length; i++) {
            incomingFire[i] = new IncomingFire(4, 10, 0, 2, width, 600, 1);
        }
        block = new Block[numberOfBlock];
        for (int i = 0; i < block.length; i++) {
            block[i] = new Block(200, 50, ((i * 200) + ((i + 1) * gapBetweenBlock) + leftEdgeOfSafeArea), 780, 5);
        }
        countDeathAlien = 0;
        countDeathUfo = 0;
    }

    private static void record() {
        if (highestScore >= score) {
            try {
                Scanner sc = new Scanner(data);
                while (sc.hasNextLine()) {
                    String temp = sc.nextLine();
                    highestScore = Integer.valueOf(temp);
                }
            } catch (IOException e) {
            }
        } else {
            highestScore = score;
            try {
                try (PrintWriter pw = new PrintWriter(new FileOutputStream(data))) {
                    String temp = Integer.toString(highestScore);
                    pw.write(temp);
                }
            } catch (IOException e) {
            }
        }
    }

    /*
    Main method
     */
    public static void main(String[] args) {
        initialization();
        backgroundMusic.play(true);
        /*
        Customize the console window per your need but do not show it yet.
         */
        Console.getInstance()
                .setTitle("RetroSpaceInvader")
                .setWidth(width)
                .setHeight(height)
                .setTheme(Console.Theme.DARK);
        /*
        Similar to the Console class, use the chaining setters to configure the game. Call start() at the end of
        the chain to start the game loop.
         */
        new GameDemo()
                .setFps(framePerSecond) // set frame rate
                .setShowFps(true) // set to display fps on screen
                .setBackground(meunBackground) // set background image
                .start(); // start game loop
    }

    /**
     * **********************************************************************************************
     * There are three abstract methods must be overridden: protected abstract
     * void cycle(); protected abstract void keyPressed(KeyEvent e); protected
     * abstract void mouseClicked(MouseEvent e);
     */
    /*
    Called regularly at predefined frame rate (fps).
    This callback is used to program what to do in each cycle, i.e. a particular frame. 
    For example, if you have set a frame rate at 50, this method will be invoked approximately 
    50 times every second. At the end of each cycle, Console.update() is called implicitly to 
    flush the drawings from buffer to screen.
     */
    @Override
    public void cycle() {
        //Record
        record();
        //Start
        if (start.getDeterminant()) {
            difficulty++;
            start.setDeterminant(false);
        }
        //Pause
        if (!pause.getDeterminant()) {
            counter = ++counter % 20;
            imageSwitch = counter / 10;
        }
        //Replay
        if (replay.getDeterminant()) {
            int temp = spaceship.getNumberOfLife();
            initialization();
            if (temp > 1) {
                spaceship.setNumberOfLife(temp);
            } else {
                spaceship.setNumberOfLife(1);
            }
            replay.setDeterminant(false);
        }
        //Next
        if (next.getDeterminant()) {
            difficulty++;
            numberOfIncomingFire++;
            int temp = spaceship.getNumberOfLife();
            initialization();
            spaceship.setNumberOfLife(temp);
            next.setDeterminant(false);
        }
        //Success
        if (success.getDeterminant()) {
            difficulty = 0;
            initialization();
            success.setDeterminant(false);
        }
        //Start Stage
        if (difficulty == 0) {
            start.draw();
        } else {
            //NextStage Condition
            if (countDeathEnemy == ((alien.length * alien[0].length) + ufo.length) && difficulty < 10) {
                nextStage = true;
            }
            //All Completed Condition
            if (countDeathEnemy == ((alien.length * alien[0].length) + ufo.length) && difficulty == 10) {
                allCompleted = true;
            }
            //GameOver Condition
            if (spaceship.getNumberOfLife() == 0) {
                gameOver = true;
            }
            //Drawing Game Stage's Meun
            setBackground(gameBackground);
            if (pause.getDeterminant()) {
                pause.draw();
            }
            if (nextStage) {
                next.draw();
            }
            if (allCompleted) {
                success.draw();
            }
            if (gameOver) {
                replay.draw();
            }
            countDeathEnemy = countDeathAlien + countDeathUfo;
            //Drawing Game Messages
            String difficultyDisplay = "Level: " + difficulty;
            Console.getInstance().drawText(20, 40, difficultyDisplay);
            String numberOfLifeDisplay = "Remain Life: " + spaceship.getNumberOfLife();
            Console.getInstance().drawText(170, 40, numberOfLifeDisplay);
            String highestScoreDisplay = "Highest Score: " + highestScore;
            Console.getInstance().drawText(320, 40, highestScoreDisplay);
            String scoreDisplay = "Score: " + score;
            Console.getInstance().drawText(470, 40, scoreDisplay);
            if (countDeathEnemy == 56) {
                String missionDisplay = "Mission Complete!";
                Console.getInstance().drawText(20, 80, missionDisplay);
            }
            //Drawing Game Object
            spaceship.draw();
            for (int i = 0; i < numberOfFriendlyFire; i++) {
                friendlyFire[i].draw(friendlyFire[i].getWidth());
            }
            for (int i = 0; i < numberOfIncomingFire; i++) {
                incomingFire[i].draw(alien[0][0].getWidth());
            }
            for (int i = 0; i < alien.length; i++) {
                for (int j = 0; j < alien[i].length; j++) {
                    alien[i][j].draw();
                    alien[i][j].setXSpeed((int) (countDeathEnemy / 5) + 1);
                }
            }
            for (int i = 0; i < ufo.length; i++) {
                ufo[i].draw();
            }
            for (int i = 0; i < block.length; i++) {
                block[i].draw();
            }
            //Pause Condition
            if (pause.getDeterminant()) {
                Console.pause(1);
            } else {
                spaceship.contactDetection(incomingFire);
                for (int i = 0; i < numberOfFriendlyFire; i++) {
                    friendlyFire[i].update();
                }
                for (int i = 0; i < numberOfIncomingFire; i++) {
                    incomingFire[i].renew();
                    incomingFire[i].update();
                }
                for (int i = 0; i < alien.length; i++) {
                    for (int j = 0; j < alien[i].length; j++) {
                        alien[i][j].positionUpdate((leftEdgeOfSafeArea + (j * gapBetweenAlien)), (rightEdgeOfSafeArea - ((alien[i].length - 1 - j) * gapBetweenAlien)));
                        alien[i][j].contactDetection(friendlyFire);
                    }
                }
                for (int i = 0; i < ufo.length; i++) {
                    ufo[i].positionUpdate(0, width);
                    ufo[i].contactDetection(friendlyFire);
                }
                for (int i = 0; i < block.length; i++) {
                    block[i].contactDetection(friendlyFire, incomingFire);
                }
            }
        }
    }

    @Override
    protected void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                spaceship.update(ke);
                break;
            case KeyEvent.VK_RIGHT:
                spaceship.update(ke);
                break;
            case KeyEvent.VK_SPACE:
                int count = 0;
                for (int i = 0; i < friendlyFire.length; i++) {
                    if (friendlyFire[i].getYCoordinate() > spaceship.getYCoordinate() - gapBetweenAlien) {
                        count++;
                    }
                }
                if (count == 0) {
                    for (int i = 0; i < numberOfFriendlyFire; i++) {
                        if (friendlyFire[i].getYCoordinate() <= 0) {
                            friendlyFire[i].renew();
                            break;
                        }
                    }
                }
                count = 0;
                break;
            case KeyEvent.VK_ENTER:
                if (difficulty == 0) {
                    difficulty++;
                }
                if (difficulty > 0 && nextStage) {
                    nextStage = false;
                    next.setDeterminant(true);
                    countDeathEnemy = 0;
                }
                if (difficulty == 10 && allCompleted) {
                    allCompleted = false;
                    success.setDeterminant(true);
                }
                break;
            case KeyEvent.VK_P:
                if (difficulty > 0) {
                    pause.setDeterminant(!pause.getDeterminant());
                }
                break;
            case KeyEvent.VK_R:
                if (difficulty > 0 && gameOver) {
                    gameOver = false;
                    replay.setDeterminant(true);
                }
        }
    }

    @Override
    protected void mouseClicked(MouseEvent me) {
        System.out.println("Click on (" + me.getX() + "," + me.getY() + ")");
        if (difficulty == 0 && me.getX() >= start.getXCoordinate() && me.getX() <= (start.getXCoordinate() + start.getWidth()) && me.getY() >= start.getYCoordinate() && me.getY() <= (start.getYCoordinate() + start.getHeight())) {
            start.setDeterminant(true);
        }
        if (gameOver && me.getX() >= replay.getXCoordinate() && me.getX() <= (replay.getXCoordinate() + replay.getWidth()) && me.getY() >= replay.getYCoordinate() && me.getY() <= (replay.getYCoordinate() + replay.getHeight())) {
            gameOver = false;
            replay.setDeterminant(true);
        }
        if (nextStage && me.getX() >= next.getXCoordinate() && me.getX() <= (next.getXCoordinate() + next.getWidth()) && me.getY() >= next.getYCoordinate() && me.getY() <= (next.getYCoordinate() + next.getHeight())) {
            nextStage = false;
            next.setDeterminant(true);
            countDeathEnemy = 0;
        }
        if (allCompleted && me.getX() >= success.getXCoordinate() && me.getX() <= (success.getXCoordinate() + success.getWidth()) && me.getY() >= success.getYCoordinate() && me.getY() <= (success.getYCoordinate() + success.getHeight())) {
            allCompleted = false;
            success.setDeterminant(true);
        }
    }
}
