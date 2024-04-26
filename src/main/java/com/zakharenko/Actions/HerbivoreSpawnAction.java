package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.Herbivore;

public class HerbivoreSpawnAction extends SpawnAction<Herbivore> {
    public HerbivoreSpawnAction() {
        super.countTypeOnMap = 46;
    }

    protected Herbivore spawnEntity(Coordinates coordinates) {
        return new Herbivore(coordinates);
    }
}
