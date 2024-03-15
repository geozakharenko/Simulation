package com.zakharenko.Enities;

import com.zakharenko.Coordinates;
import com.zakharenko.FinderAlgorithm.BreadthFirstSearch;
import com.zakharenko.Map;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    @Override
    public void makeMove(Coordinates start, Map map) {
        List<Coordinates> path = BreadthFirstSearch.get(start, map);
        if (path.size() > 1) {
            int amountOfSpeed = Math.min(getSpeed(), path.size() - 1);
            Coordinates to = path.get(amountOfSpeed);
            if (to == null || map.getEntity(to) instanceof Herbivore) return;
            if (map.getEntity(to) instanceof Grass) {
                ate(start, to, map);
                return;
            }
            map.moveEntity(start, to);
        }
            famine();
            if (getHealth() <= 0) map.removeEntity(start);

    }
}