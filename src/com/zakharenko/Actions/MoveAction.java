package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.Creature;
import com.zakharenko.Map;

public class MoveAction {
    public static void makeMoveWithCreatures(Map map) {
        //Check One more
        for (java.util.Map.Entry<Coordinates, Creature> entry : map.getEntitiesOfType(Creature.class).entrySet()) {
            Creature creature = entry.getValue();
            creature.makeMove(entry.getKey(), map);
        }
    }
}
