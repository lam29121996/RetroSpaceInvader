/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package student.demo;

import game.v2.Console;
import java.awt.Image;

/**
 *
 * @author KelvinLam
 */
public class Alien extends Enemy {

    private Image alienModelOne = Console.loadImage("/student/demo/img/invader64_1.png");
    private Image alienModelTwo = Console.loadImage("/student/demo/img/invader64_2.png");
    private Image[] alienModel = {alienModelOne, alienModelTwo};
    private int width = 64;
    private int height = 64;
    private int xSpeed = 1;
    private int ySpeed = 1;
    private int xCoordinate = 300;
    private int yCoordinate = 520;
    private int numberOfLife = 1;
    private int direction = (int) (Math.random());
    private int score = 10;
    private int floatCount = 0;

    public Alien(int width, int height, int xSpeed, int ySpeed, int xCoordinate, int yCoordinate, int numberOfLife) {
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.numberOfLife = numberOfLife;
    }

    public Image getAlienModelOne() {
        return alienModelOne;
    }

    public void setAlienModelOne(Image alienModelOne) {
        this.alienModelOne = alienModelOne;
    }

    public Image getAlienModelTwo() {
        return alienModelOne;
    }

    public void setAlienModelTwo(Image alienModelTwo) {
        this.alienModelTwo = alienModelTwo;
    }

    public Image[] getImage() {
        return alienModel;
    }

    public void setImage(Image[] alienModel) {
        this.alienModel = alienModel;
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
        if (xCoordinate <= leftEdgeOfSafeArea + xSpeed && direction == 1) {
            xCoordinate = leftEdgeOfSafeArea;
            direction = 0;
            yCoordinate += ySpeed;
        }
        if (xCoordinate >= rightEdgeOfSafeArea - width - xSpeed && direction == 0) {
            xCoordinate = rightEdgeOfSafeArea - width;
            direction = 1;
            yCoordinate += ySpeed;
        }
        if (direction == 0) {
            xCoordinate += xSpeed;
            floatCount++;
            if (floatCount > 40) {
                floatCount = 0;
            } else if (floatCount <= 20) {
                yCoordinate++;
            } else if (floatCount >= 20) {
                yCoordinate--;
            }
        }
        if (direction == 1) {
            xCoordinate -= xSpeed;
            floatCount++;
            if (floatCount > 40) {
                floatCount = 0;
            } else if (floatCount < 20) {
                yCoordinate++;
            } else if (floatCount > 20) {
                yCoordinate--;
            }

        }
        System.out.println(floatCount);
    }

    @Override
    public void draw() {
        if (numberOfLife > 0) {
            Console.getInstance().drawImage(xCoordinate, yCoordinate, alienModel[GameDemo.imageSwitch]);
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
                                GameDemo.countDeathAlien++;
                            }
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            GameDemo.score += score;
                            GameDemo.enemyDead.play(false);
                            break;
                        }
                        if ((friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) <= (xCoordinate + width) && (friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            if (numberOfLife == 0) {
                                GameDemo.countDeathAlien++;
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
                                GameDemo.countDeathAlien++;
                            }
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            GameDemo.score += score;
                            GameDemo.enemyDead.play(false);
                            break;
                        }
                        if ((friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) <= (xCoordinate + width) && (friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            if (numberOfLife == 0) {
                                GameDemo.countDeathAlien++;
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
