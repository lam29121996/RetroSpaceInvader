/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package student.demo;

import game.v2.Console;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author KelvinLam
 */
public class FriendlyFire extends Attack {

    private Image laserModel;
    private Color color;
    private int width = 4;
    private int height = 10;
    private int xSpeed = 0;
    private int ySpeed = 2;
    private int xCoordinate;
    private int yCoordinate;
    private int numberOfLife = 1;

    public FriendlyFire(int width, int height, int xSpeed, int ySpeed, int xCoordinate, int yCoordinate, int numberOfLife) {
        this.width = width;
        this.height = height;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.numberOfLife = numberOfLife;
    }

    public Image getImage() {
        return laserModel;
    }

    public void setImage(Image laserModel) {
        this.laserModel = laserModel;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void update() {
        xCoordinate += xSpeed;
        yCoordinate -= ySpeed;
        if (numberOfLife == 0) {
            yCoordinate = 0;
        }
    }

    @Override
    public void draw(int widthOfSource) {
        if (numberOfLife > 0) {
            Console.getInstance().drawRectangle(xCoordinate, yCoordinate, width, height, Color.yellow);
        }
    }

    @Override
    public void renew() {
        if (GameDemo.spaceship.getNumberOfLife() > 0 && !GameDemo.pause.getDeterminant()) {
            if (yCoordinate <= 0) {
                xCoordinate = GameDemo.spaceship.getXCoordinate() + (GameDemo.spaceship.getWidth() / 2) - (width / 2);
                yCoordinate = GameDemo.spaceship.getYCoordinate();
                numberOfLife = 1;
                GameDemo.spaceshipShoot.play(false);
            }
        }
    }
}
