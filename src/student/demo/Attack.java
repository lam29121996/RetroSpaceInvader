package student.demo;

/**
 *
 * @author KelvinLam
 */
public abstract class Attack {

    int width = 4;
    int height = 10;
    int xSpeed = 0;
    int ySpeed = 2;
    int xCoordinate;
    int yCoordinate;
    int numberOfLife;

    public abstract void update();

    public abstract void draw(int widthOfSource);
    
    public abstract void renew();
}
