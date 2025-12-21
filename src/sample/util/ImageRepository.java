package sample.util;

import java.util.Map;

public class ImageRepository {

    private static final Map<Integer, String> gridImageMap = Map.of(
            4, "/resources/images/level1.png",
            5, "/resources/images/level2.jpg",
            6, "/resources/images/level3.jpg",
            7, "/resources/images/level4.png",
            8, "/resources/images/level5.jpg",
            9, "/resources/images/level6.png",
            10, "/resources/images/level7.jpg",
            11, "/resources/images/level8.jpg",
            12, "/resources/images/level9.jpg"
    );

    public static String getImageByGrid(int gridSize) {
        return gridImageMap.get(gridSize);
    }
}
