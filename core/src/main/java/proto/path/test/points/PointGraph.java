package proto.path.test.points;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.List;

public class PointGraph {
    private final Array<Point> points = new Array<>();

    public void addPoint (List<String> positionStrings) {
        for (String positionString : positionStrings) {
            addPoint(positionString);
        }
    }

    public void addPoint (String positionString) {
        //Если некотороя точка не соответствует формату, она не добавляется
        if (!checkPositionString(positionString)) {
            return;
        }
        Point point = new Point(positionString);
        points.add(point);
    }

    public void addPoint (Vector2 position) {
        Point point = new Point(position);
        points.add(point);
    }

    //Формат строки, по которому можно создать точку: "X:Y", где X, Y - вещественные числа
    public static boolean checkPositionString (String positionString) {
        String[] coorsString = positionString.split(":");
        if (coorsString.length != 2) {
            return false;
        }
        return isNumeric(coorsString[0]) && isNumeric(coorsString[1]);
    }

    private static boolean isNumeric (String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public void render (ShapeDrawer shapeDrawer) {
        for (Point point : points) {
            point.render(shapeDrawer);
        }
    }

    public Array<Point> getPoints () {
        return points;
    }

    @Override
    public String toString () {
        StringBuilder pointGraph = new StringBuilder();
        for (Point point : points) {
            pointGraph.append(point.toString()).append("\n");
        }
        return pointGraph.toString();
    }
}
