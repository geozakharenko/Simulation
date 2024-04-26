package com.zakharenko.Actions;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.*;
import com.zakharenko.Map;

public abstract class SpawnAction<T extends Entity> extends Action {
    protected int countTypeOnMap;

    @Override
    public void perform(Map map) {
        int currentRate = 0;
        while (currentRate < countTypeOnMap) {
            Coordinates coordinates = map.getEmptyPlaceRandom();
            map.setEntity(coordinates, spawnEntity(coordinates));
            currentRate++;
        }
    }

    protected abstract T spawnEntity(Coordinates coordinates);
}