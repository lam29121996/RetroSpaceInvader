/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.demo;

import game.v2.Console;
import java.awt.Image;

/**
 *
 * @author KelvinLam
 */
public class Block {

    private Image blockModelOne = Console.loadImage("/student/demo/img/block_1.png");
    private Image blockModelTwo = Console.loadImage("/student/demo/img/block_2.png");
    private Image blockModelThree = Console.loadImage("/student/demo/img/block_3.png");
    private Image blockModelFour = Console.loadImage("/student/demo/img/block_4.png");
    private Image blockModelFive = Console.loadImage("/student/demo/img/block_5.png");
    private Image[] blockModel = {blockModelOne, blockModelTwo, blockModelThree, blockModelFour, blockModelFive};
    private int width = 200;
    private int height = 40;
    private int xCoordinate = 355;
    private int yCoordinate = 780;
    private int numberOfLife = 5;

    public Block(int width, int height, int xCoordinate, int yCoordinate, int numberOfLife) {
        this.width = width;
        this.height = height;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.numberOfLife = numberOfLife;
    }

    public Image getBlockModelOne() {
        return blockModelOne;
    }

    public void setBlockModelOne(Image blockModelOne) {
        this.blockModelOne = blockModelOne;
    }

    public Image getBlockModelTwo() {
        return blockModelTwo;
    }

    public void setBlockModelTwo(Image blockModelTwo) {
        this.blockModelTwo = blockModelTwo;
    }

    public Image getBlockModelThree() {
        return blockModelThree;
    }

    public void setBlockModelThree(Image blockModelThree) {
        this.blockModelThree = blockModelThree;
    }

    public Image getBlockModelFour() {
        return blockModelFour;
    }

    public void setBlockModelFour(Image blockModelFour) {
        this.blockModelFour = blockModelFour;
    }

    public Image getBlockModelFive() {
        return blockModelFive;
    }

    public void setBlockModelFive(Image blockModelFive) {
        this.blockModelFive = blockModelFive;
    }

    public Image[] getImage() {
        return blockModel;
    }

    public void setImage(Image[] blockModel) {
        this.blockModel = blockModel;
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

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getNumberOfLife() {
        return numberOfLife;
    }

    public void setNumberOfLife(int numberOfLife) {
        this.numberOfLife = numberOfLife;
    }

    public void draw() {
        if (numberOfLife > 0) {
            Console.getInstance().drawImage(xCoordinate, yCoordinate, blockModel[numberOfLife - 1]);
        }
    }

    public void contactDetection(FriendlyFire[] friendlyFire, IncomingFire[] incomingFire) {
        if (numberOfLife > 0) {
            for (int i = 0; i < friendlyFire.length; i++) {
                if (friendlyFire[i].getNumberOfLife() > 0) {
                    if (friendlyFire[i].getYCoordinate() <= (yCoordinate + height) && friendlyFire[i].getYCoordinate() >= yCoordinate) {
                        if (friendlyFire[i].getXCoordinate() <= (xCoordinate + width) && friendlyFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            break;
                        }
                        if ((friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) <= (xCoordinate + width) && (friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            break;
                        }
                    }
                    if ((friendlyFire[i].getYCoordinate() + friendlyFire[i].getHeight()) <= (yCoordinate + height) && (friendlyFire[i].getYCoordinate() + friendlyFire[i].getHeight() >= yCoordinate)) {
                        if (friendlyFire[i].getXCoordinate() <= (xCoordinate + width) && friendlyFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            break;
                        }
                        if ((friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) <= (xCoordinate + width) && (friendlyFire[i].getXCoordinate() + friendlyFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.friendlyFire[i].setNumberOfLife(0);
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < incomingFire.length; i++) {
                if (incomingFire[i].getNumberOfLife() > 0) {
                    if (incomingFire[i].getYCoordinate() >= (yCoordinate + height) && incomingFire[i].getYCoordinate() <= yCoordinate) {
                        if (incomingFire[i].getXCoordinate() <= (xCoordinate + width) && incomingFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            yCoordinate += 10;
                            break;
                        }
                        if ((incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) <= (xCoordinate + width) && (incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            yCoordinate += 10;
                            break;
                        }
                    }
                    if ((incomingFire[i].getYCoordinate() + incomingFire[i].getHeight()) >= yCoordinate && (incomingFire[i].getYCoordinate() + incomingFire[i].getHeight()) <= (yCoordinate + height)) {
                        if (incomingFire[i].getXCoordinate() <= (xCoordinate + width) && incomingFire[i].getXCoordinate() >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            yCoordinate += 10;
                            break;
                        }
                        if ((incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) <= (xCoordinate + width) && (incomingFire[i].getXCoordinate() + incomingFire[i].getWidth()) >= xCoordinate) {
                            numberOfLife--;
                            GameDemo.incomingFire[i].setNumberOfLife(0);
                            yCoordinate += 10;
                            break;
                        }
                    }
                }
            }
        }
    }
}
