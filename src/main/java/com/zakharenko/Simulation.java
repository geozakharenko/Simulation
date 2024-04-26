package com.zakharenko;

import com.zakharenko.Actions.*;
import com.zakharenko.Enities.Grass;
import com.zakharenko.Enities.Herbivore;
import com.zakharenko.Enities.Predator;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final Map map = new Map();
    private final MapRenderer renderer = new MapRenderer();
    private final MoveAction moveAction = new MoveAction();
    private static int countIteration = 1;

    public void nextTurn() {
        moveAction.perform(map);
        renderer.render(map);
        checkForLivingCreatures(map);
        addGrassIfNecessary(map);
    }

    private void checkForLivingCreatures(Map map) {
        if (map.getEntitiesOfType(Herbivore.class).isEmpty()) {
            System.out.println("Живых травоядных не осталось. Победа хищников");
            System.exit(0);
        }
        if (map.getEntitiesOfType(Predator.class).isEmpty()) {
            System.out.println("Живых хищников не осталось. Победа травоядных");
            System.exit(0);
        }
    }

    private void addGrassIfNecessary(Map map) {
        while (map.getEntitiesOfType(Grass.class).size() < 60) {
            Coordinates coordinates = map.getEmptyPlaceRandom();
            map.setEntity(coordinates, new Grass(coordinates));
        }
    }

    public int getNumberOfIteration() {
        return countIteration++;
    }

    private List<SpawnAction<?>> getInitActions() {
        List<SpawnAction<?>> initActions = new ArrayList<>();
        initActions.add(new GrassSpawnAction());
        initActions.add(new HerbivoreSpawnAction());
        initActions.add(new PredatorSpawnAction());
        initActions.add(new RockSpawnAction());
        return initActions;
    }

    protected void initWorld() {
        for (SpawnAction<?> spawnAction : getInitActions())
            spawnAction.perform(map);
        System.out.println("СГЕНЕРИРОВАННАЯ КАРТА");
        renderer.render(map);
    }
}
