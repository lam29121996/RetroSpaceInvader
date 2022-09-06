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
public class Button {

    private Image[] buttonModel;
    private int width = 200;
    private int height = 50;
    private int xCoordinate = 400;
    private int yCoordinate = 300;
    private boolean determinant = false;

    public Button(Image[] buttonModel, int width, int height, int xCoordinate, int yCoordinate, boolean determinant) {
        this.buttonModel = buttonModel;
        this.width = width;
        this.height = height;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.determinant = determinant;
    }

    public Image[] getImage() {
        return buttonModel;
    }

    public void setImage(Image[] buttonModel) {
        this.buttonModel = buttonModel;
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

    public boolean getDeterminant() {
        return determinant;
    }

    public void setDeterminant(boolean determinant) {
        this.determinant = determinant;
    }

    public void draw() {
        Console.getInstance().drawImage(xCoordinate, yCoordinate, buttonModel[GameDemo.imageSwitch]);
    }
}
