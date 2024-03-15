package com.zakharenko;

import com.zakharenko.Enities.Entity;

public class MapRenderer {
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE
    public static final String RESET = "\033[0m"; // Text Reset

    public void render(Map map) {
        for (int height = Map.MAP_HEIGHT; height >= 0; height--) {
            String line = "";
            for (int weight = Map.MAP_WEIGHT; weight >= 0; weight--) {
                Coordinates coordinates = new Coordinates(height, weight);
                if (map.isPlaceEmpty(coordinates)) {
                    line += getSpriteForEmptySquare();
                } else {
                    line += getEntitySprite(map.getEntity(coordinates));
                }
            }
            line += RESET;
            System.out.println(line);
        }
    }

    public String colorizeSprite(String sprite) {
        String result = sprite;
        result = WHITE_BACKGROUND_BRIGHT + result;
        return result;
    }

    private String getEntitySprite(Entity entity) {

        return colorizeSprite(selectUnicodeSpriteForEntity(entity));
    }

    private String selectUnicodeSpriteForEntity(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Rock":
                return "\uD83E\uDEA8";
            case "Grass":
                return "\uD83C\uDF3F";
            case "Herbivore":
                return "\uD83D\uDC30";
            case "Predator":
                return "\uD83D\uDC3A";
        }
        return "";
    }

    private String getSpriteForEmptySquare() {
        return colorizeSprite("▫️");
    }
}
