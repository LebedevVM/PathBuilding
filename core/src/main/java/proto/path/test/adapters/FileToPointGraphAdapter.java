package proto.path.test.adapters;

import proto.path.test.points.PointGraph;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FileToPointGraphAdapter {
    public static PointGraph getPointGraphFromFile (String fileName) {
        PointGraph pointGraph = new PointGraph();
        try {
            List<String> strings = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
            pointGraph.addPoint(strings);
        }
        catch (Exception ignored) {}
        return pointGraph;
    }
}
