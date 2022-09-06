package student.demo;

import game.v2.Console;
import java.awt.Image;
import java.awt.event.KeyEvent;

/**
 *
 * @author KelvinLam
 */
public class Spaceship {

    private Image spaceshipModelOne = Console.loadImage("/student/demo/img/spaceship_1.png");
    private Image[] spaceshipModel = {spaceshipModelOne, spaceshipModelOne};
    private final int oneStep = (GameDemo.rightEdgeOfSafeArea - GameDemo.leftEdgeOfSafeArea) / 100;
    private int width = 48;
    private int height = 48;
    private int xCoordinate = 300;
    private int yCoordinate = 520;
    private int numberOfLife = 3;
    private int invincibleTime = 2;
    private int invincibleFrame = 0;

    public Spaceship(int width, int height, int xCoordinate, int yCoordinate, int numberOfLife) {
        this.width = width;
        this.height = height;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.numberOfLife = numberOfLife;
    }

    public Image getSpaceshipModelOne() {
        return spaceshipModelOne;
    }

    public void setSpaceshipModelOne(Image spaceshipModelOne) {
        this.spaceshipModelOne = spaceshipModelOne;
    }

    public Image[] getImage() {
        return spaceshipModel;
    }

    public void setImage(Image[] spaceshipModel) {
        this.spaceshipModel = spaceshipModel;
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

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public int getNumberOfLife() {
        return numberOfLife;
    }

    public void setNumberOfLife(int numberOfLife) {
        this.numberOfLife = numberOfLife;
    }

    public int getInvincibleTime() {
        return invincibleTime;
    }

    public void setInvincibleTime(int invincibleTime) {
        this.invincibleTime = invincibleTime;
    }

    public void update(KeyEvent ke) {
        if (numberOfLife > 0 && !GameDemo.pause.getDeterminant()) {
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                if (xCoordinate < GameDemo.leftEdgeOfSafeArea + oneStep) {
                    xCoordinate = GameDemo.leftEdgeOfSafeArea;
                } else {
                    xCoordinate -= oneStep;
                }
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (xCoordinate + width > GameDemo.rightEdgeOfSafeArea - oneStep) {
                    xCoordinate = GameDemo.rightEdgeOfSafeArea - width;
                } else {
                    xCoordinate += oneStep;
                }
            }
        }
    }

    public void draw() {
        if (numberOfLife > 0) {
            if (invincibleFrame > 0) {
                invincibleFrame--;
                if (GameDemo.imageSwitch == 0) {
                    Console.getInstance().drawImage(xCoordinate, yCoordinate, spaceshipModel[GameDemo.imageSwitch]);
                }
            } else {
                Console.getInstance().drawImage(xCoordinate, yCoordinate, spaceshipModel[GameDemo.imageSwitch]);
            }
        }
    }

    public void contactDetection(IncomingFire[] incomingFire) {
        if (numberOfLife > 0 && invincibleFrame == 0) {
            for (int i = 0; i < incomingFire.length; i++) {
                if (incomingFire[i].getNumberOfLife() > 0) {
                    if (incomingFire[i].getYCoordinate() >= (yCoordinate + height) && incomingFire[i].getYCoordinate() <= yCoordinate) {
                        if (incomingFire[i].getXCoordinate() <= (xCoordinate + width) && incomingFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            invincibleFrame = (invincibleTime * GameDemo.framePerSecond);
                            GameDemo.spaceshipDead.play(false);
                            break;
                        }
                        if ((incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) <= (xCoordinate + width) && (incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            invincibleFrame = (invincibleTime * GameDemo.framePerSecond);
                            GameDemo.spaceshipDead.play(false);
                            break;
                        }
                    }
                    if ((incomingFire[i].getYCoordinate() + incomingFire[i].getHeight()) >= yCoordinate && (incomingFire[i].getYCoordinate() + incomingFire[i].getHeight()) <= (yCoordinate + height)) {
                        if (incomingFire[i].getXCoordinate() <= (xCoordinate + width) && incomingFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            invincibleFrame = (invincibleTime * GameDemo.framePerSecond);
                            GameDemo.spaceshipDead.play(false);
                            break;
                        }
                        if ((incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) <= (xCoordinate + width) && (incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            invincibleFrame = (invincibleTime * GameDemo.framePerSecond);
                            GameDemo.spaceshipDead.play(false);
                            break;
                        }
                    }
                }
            }
        }
    }
}
