package proto.path.test.points;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Point {
    private final Vector2 position;

    public Point (String positionString) {
        String[] coorsString = positionString.split(":");
        position = new Vector2(Float.parseFloat(coorsString[0]), Float.parseFloat(coorsString[1]));
    }

    public Point (Vector2 position) {
        this.position = position;
    }

    public boolean checkCollision (Vector2 collisionPosition) {
        return position.dst(collisionPosition) < 1f;
    }

    public void render (ShapeDrawer shapeDrawer) {
        shapeDrawer.filledCircle(position, 1, Color.CYAN);
    }

    public Vector2 getPosition () {
        return position;
    }

    @Override
    public String toString () {
        return position.x + ":" + position.y;
    }
}
