package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.Grass;

public class GrassSpawnAction extends SpawnAction<Grass> {
    public GrassSpawnAction() {
        super.countTypeOnMap = 110;
    }

    protected Grass spawnEntity(Coordinates coordinates) {
        return new Grass(coordinates);
    }
}
