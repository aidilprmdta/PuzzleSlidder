package sample.util;

import java.util.Map;

public class imageRepository {

    private static final Map<Integer, String> gridImageMap = Map.of(
            3, "/images/level1.jpg",
            4, "/images/level2.jpg",
            5, "/images/level3.jpg"
    );

    public static String getImageByGrid(int gridSize) {
        return gridImageMap.get(gridSize);
    }
}
