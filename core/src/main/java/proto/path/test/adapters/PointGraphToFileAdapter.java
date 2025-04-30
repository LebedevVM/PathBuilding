package proto.path.test.adapters;

import proto.path.test.points.PointGraph;

import java.io.FileWriter;

public abstract class PointGraphToFileAdapter {
    public static void uploadPointGraph (PointGraph pointGraph, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(pointGraph.toString());
            writer.flush();
        } catch(Exception ignored){}
    }
}
