package proto.path.test.planes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import proto.path.test.points.PointGraph;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class PlaneStorage {
    private final Array<Plane> planes = new Array<>();

    public void addPlane (PointGraph pointGraph) {
        planes.add(new Plane(this, pointGraph));
    }

    public void removePlane (Plane plane) {
        planes.removeValue(plane, true);
    }

    public void render (ShapeDrawer shapeDrawer, Batch batch, float delta) {
        for (Plane plane : planes) {
            plane.render(shapeDrawer, batch, delta);
        }
    }
}
