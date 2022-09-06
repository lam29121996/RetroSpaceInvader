package student.demo;

import game.v2.Console;
import java.awt.Image;

/**
 *
 * @author KelvinLam
 */
public class Ufo extends Enemy {

    private Image ufoModelOne = Console.loadImage("/student/demo/img/invader_1.png");
    private Image ufoModelTwo = Console.loadImage("/student/demo/img/invader_2.png");
    private Image[] ufoModel = {ufoModelOne, ufoModelTwo};
    private int appearFrequency = 5;
    private int width = 128;
    private int height = 128;
    private int xSpeed = 2;
    private int ySpeed = 0;
    private int xCoordinate = 300;
    private int yCoordinate = 0;
    private int numberOfLife = 1;
    private int direction = (int) (Math.random() * 2);
    private int score = 100;

    public Ufo(int width, int height, int xSpeed, int ySpeed, int xCoordinate, int yCoordinate, int numberOfLife) {
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.numberOfLife = numberOfLife;
    }

    public Image getUfoModelOne() {
        return ufoModelOne;
    }

    public void setUfoModelOne(Image ufoModelOne) {
        this.ufoModelOne = ufoModelOne;
    }

    public Image getUfoModelTwo() {
        return ufoModelTwo;
    }

    public void setUfoModelTwo(Image ufoModelTwo) {
        this.ufoModelTwo = ufoModelTwo;
    }

    public Image[] getUfoModel() {
        return ufoModel;
    }

    public void setUfoModel(Image[] ufoModel) {
        this.ufoModel = ufoModel;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getNumberOfLife() {
        return numberOfLife;
    }

    public void setNumberOfLife(int numberOfLife) {
        this.numberOfLife = numberOfLife;
    }

    @Override
    public void positionUpdate(int leftEdgeOfSafeArea, int rightEdgeOfSafeArea) {
        if (xCoordinate <= leftEdgeOfSafeArea - width - xSpeed && direction == 1) {
            xCoordinate = leftEdgeOfSafeArea - width - (xSpeed * appearFrequency * GameDemo.framePerSecond);
            direction = 0;
        }
        if (xCoordinate >= rightEdgeOfSafeArea + xSpeed && direction == 0) {
            xCoordinate = rightEdgeOfSafeArea + (xSpeed * appearFrequency * GameDemo.framePerSecond);
            direction = 1;
        }
        if (direction == 0) {
            xCoordinate += xSpeed;
        }
        if (direction == 1) {
            xCoordinate -= xSpeed;
        }
    }

    @Override
    public void draw() {
        if (numberOfLife > 0) {
            Console.getInstance().drawImage(xCoordinate, yCoordinate, ufoModel[GameDemo.imageSwitch]);
        }
    }

    @Override
    public void contactDetection(FriendlyFire[] friendlyFire) {
        if (numberOfLife > 0) {
            for (int i = 0; i < friendlyFire.length; i++) {
                if (friendlyFire[i].getNumberOfLife() > 0) {
                    if (friendlyFire[i].getYCoordinate() <= (yCoordinate + height) && friendlyFire[i].getYCoordinate() >= yCoordinate) {
                        if (friendlyFire[i].getXCoordinate() <= (xCoordinate + width) && friendlyFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            if (numberOfLife == 0) {
                                GameDemo.countDeathUfo++;
                                GameDemo.numberOfFriendlyFire++;
                                GameDemo.friendlyFire = new FriendlyFire[GameDemo.numberOfFriendlyFire];
                                for (int k = 0; k < GameDemo.numberOfFriendlyFire; k++) {
                                    GameDemo.friendlyFire[k] = new FriendlyFire(4, 10, 0, 20, 0, 0, 0);
                                }
                            }
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            GameDemo.score += score;
                            GameDemo.enemyDead.play(false);
                            break;
                        }
                        if ((friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) <= (xCoordinate + width) && (friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            if (numberOfLife == 0) {
                                GameDemo.countDeathUfo++;
                                GameDemo.numberOfFriendlyFire++;
                                GameDemo.friendlyFire = new FriendlyFire[GameDemo.numberOfFriendlyFire];
                                for (int k = 0; k < GameDemo.numberOfFriendlyFire; k++) {
                                    GameDemo.friendlyFire[k] = new FriendlyFire(4, 10, 0, 20, 0, 0, 0);
                                }
                            }
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            GameDemo.score += score;
                            GameDemo.enemyDead.play(false);
                            break;
                        }
                    }
                    if ((friendlyFire[i].getYCoordinate() + friendlyFire[i].getHeight()) <= (yCoordinate + height) && (friendlyFire[i].getYCoordinate() + friendlyFire[i].getHeight() >= yCoordinate)) {
                        if (friendlyFire[i].getXCoordinate() <= (xCoordinate + width) && friendlyFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            if (numberOfLife == 0) {
                                GameDemo.countDeathUfo++;
                                GameDemo.numberOfFriendlyFire++;
                                GameDemo.friendlyFire = new FriendlyFire[GameDemo.numberOfFriendlyFire];
                                for (int k = 0; k < GameDemo.numberOfFriendlyFire; k++) {
                                    GameDemo.friendlyFire[k] = new FriendlyFire(4, 10, 0, 20, 0, 0, 0);
                                }
                            }
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            GameDemo.score += score;
                            GameDemo.enemyDead.play(false);
                            break;
                        }
                        if ((friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) <= (xCoordinate + width) && (friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            if (numberOfLife == 0) {
                                GameDemo.countDeathUfo++;
                                GameDemo.numberOfFriendlyFire++;
                                GameDemo.friendlyFire = new FriendlyFire[GameDemo.numberOfFriendlyFire];
                                for (int k = 0; k < GameDemo.numberOfFriendlyFire; k++) {
                                    GameDemo.friendlyFire[k] = new FriendlyFire(4, 10, 0, 20, 0, 0, 0);
                                }
                            }
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            GameDemo.score += score;
                            GameDemo.enemyDead.play(false);
                            break;
                        }
                    }
                }
            }
        }
    }
}
