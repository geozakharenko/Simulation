package com.zakharenko;

import com.zakharenko.Enities.*;

import java.util.HashMap;

public class Map {
    public static final int MAP_HEIGHT = 9;
    public static final int MAP_WEIGHT = 64;

    HashMap<Coordinates, Entity> entities = new HashMap<>();

    public boolean isPlaceEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }
    public void removeEntity(Coordinates coordinates){
        entities.remove(coordinates);
    }
    public void moveEntity(Coordinates from, Coordinates to){
        Entity entity = getEntity(from);
        entities.remove(from);
        setEntity(to, entity);
    }

    //КАК РАБОТАЕТ ЭТОТ КОД
    public <T> HashMap<Coordinates, T> getEntitiesOfType(Class<T> typeEntity) {
        HashMap<Coordinates, T> result = new HashMap<>();
        for (java.util.Map.Entry<Coordinates, Entity> entry : entities.entrySet()) {
            if (typeEntity.isInstance(entry.getValue())) {
                //noinspection unchecked
                result.put(entry.getKey(), (T) entry.getValue());
            }
        }
        return result;
    }

}
