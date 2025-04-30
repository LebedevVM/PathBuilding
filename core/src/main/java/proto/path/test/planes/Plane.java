package proto.path.test.planes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import proto.path.test.Starter;
import proto.path.test.points.Point;
import proto.path.test.points.PointGraph;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Plane {
    private Vector2 position;
    private Vector2 displacement; //Вектор движения самолёта
    private Vector2 direction; //Вектор направления, к которому самолёт должен повернуть

    private final float speed = 30;
    private final float angularSpeed = 100;

    private final Queue<Point> pathPoints = new Queue<>();

    private final PlaneStorage planeStorage;

    public Plane (PlaneStorage planeStorage, PointGraph pointGraph) {
        this.planeStorage = planeStorage;
        for (Point point : pointGraph.getPoints()) {
            pathPoints.addLast(point);
        }
        //Если количество точек траектории меньше двух, то самолёту некуда лететь
        if (pathPoints.size < 2) {
            endJourney();
            return;
        }
        position = new Vector2(pathPoints.removeFirst().getPosition());
        direction = new Vector2(pathPoints.first().getPosition()).sub(position);
        displacement = new Vector2(direction);
    }

    public void render (ShapeDrawer shapeDrawer, Batch batch, float delta) {
        if (pathPoints.size == 0) {
            endJourney();
            return;
        }
        move(delta);
        checkCollision();
        shapeDrawer.filledCircle(position, 2, Color.GREEN);
        Starter.font.draw(batch, (int) position.x + ":" + (int) position.y, position.x-2, position.y-2);
    }

    private void checkCollision () {
        if (pathPoints.first().checkCollision(position)) {
            pathPoints.removeFirst();
        }
    }

    //Самолёт летит к следующей точке, поворачиваясь к ней с угловой скоростью angularSpeed
    private void move (float delta) {
        direction.set(pathPoints.first().getPosition()).sub(position);
        displacement.setLength(speed * delta);
        if (direction.angleDeg(displacement) > 180) {
            displacement.rotateDeg(-angularSpeed*delta);
        }
        if (direction.angleDeg(displacement) < 180) {
            displacement.rotateDeg(angularSpeed*delta);
        }
        position.add(displacement);
    }

    private void endJourney () {
        planeStorage.removePlane(this);
    }
}
