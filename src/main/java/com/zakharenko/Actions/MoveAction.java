package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.Creature;
import com.zakharenko.Map;

public class MoveAction extends Action {
    @Override
    public void perform(Map map) {
        for (java.util.Map.Entry<Coordinates, Creature> entry : map.getEntitiesOfType(Creature.class).entrySet()) {
            Creature creature = entry.getValue();
            creature.makeMove(entry.getKey(), map);
        }
    }
}
