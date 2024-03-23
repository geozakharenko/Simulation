package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.Rock;

public class RockSpawnAction extends SpawnAction<Rock> {
    public RockSpawnAction() {
        super.countTypeOnMap = 130;
    }

    protected Rock spawnEntity(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}

