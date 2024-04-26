package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.Predator;

public class PredatorSpawnAction extends SpawnAction<Predator> {
    public PredatorSpawnAction() {
        super.countTypeOnMap = 20;
    }

    protected Predator spawnEntity(Coordinates coordinates) {
        return new Predator(coordinates, 10);
    }
}
