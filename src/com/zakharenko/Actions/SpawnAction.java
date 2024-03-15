package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.*;
import com.zakharenko.Map;

import java.util.Random;

import static com.zakharenko.Map.MAP_HEIGHT;
import static com.zakharenko.Map.MAP_WEIGHT;

public class SpawnAction {
    static Random random = new Random();

    public static Coordinates getEmptyPlaceRandom(Map map){
        while (true) {
            Coordinates coordinates = new Coordinates(
                    random.nextInt(MAP_HEIGHT + 1),//Y
                    random.nextInt(MAP_WEIGHT + 1));//X
            if (map.isPlaceEmpty(coordinates)) return coordinates;
        }
    }

    public static void fillMapWithCreaturesRandom(Map map){

        //Replace
        for (int rock = 0; rock < 40; rock++) {
            Coordinates coordinates = getEmptyPlaceRandom(map);
            map.setEntity(coordinates, new Rock(coordinates));
        }
        for (int grass = 0; grass < 30; grass++) {
            Coordinates coordinates = getEmptyPlaceRandom(map);
            map.setEntity(coordinates, new Grass(coordinates));
        }
        for (int herbivore = 0; herbivore < 10; herbivore++) {
            Coordinates coordinates = getEmptyPlaceRandom(map);
            map.setEntity(coordinates, new Herbivore(coordinates,2,40));
        }
        for (int predator = 0; predator < 5; predator++) {
            Coordinates coordinates = getEmptyPlaceRandom(map);
            map.setEntity(coordinates, new Predator(coordinates,1,50,10));
        }
    }
}