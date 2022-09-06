package student.demo;

/**
 *
 * @author KelvinLam
 */
public abstract class Enemy {

    int width;
    int height;
    int xSpeed;
    int ySpeed;
    int xCoordinate;
    int yCoordinate;
    int numberOfLife;

    public abstract void positionUpdate(int leftEdgeOfSafeArea, int rightEdgeOfSafeArea);
    
    public abstract void draw();
    
    public abstract void contactDetection(FriendlyFire[] friendlyFire);
}
