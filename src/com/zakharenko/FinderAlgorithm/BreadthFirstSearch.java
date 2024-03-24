package com.zakharenko.FinderAlgorithm;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.*;
import com.zakharenko.Map;

import java.util.*;

import static com.zakharenko.Map.MAP_HEIGHT;
import static com.zakharenko.Map.MAP_WEIGHT;

public class BreadthFirstSearch {
    static Class<? extends Entity> victim;

    public static List<Coordinates> get(Coordinates startNode, Map map, Class<? extends Entity> victim) {
        BreadthFirstSearch.victim = victim;
        return findShortestPathToResult(startNode, map);
    }

    private static List<Coordinates> findShortestPathToResult(Coordinates startNode, Map map) {
        Queue<Coordinates> queue = new LinkedList<>();
        java.util.Map<Coordinates, Coordinates> parentMap = new HashMap<>();
        Set<Coordinates> visitedList = new HashSet<>();
        Coordinates resultNode = null;

        queue.add(startNode);

        while (!queue.isEmpty()) {
            visitedList.add(queue.element());
            Coordinates current = queue.remove();

            if (!(map.isPlaceEmpty(current)) && map.getEntity(current).getClass() == victim) {
                resultNode = current;
                break;
            }

            List<Coordinates> nodesAround = getNodesWithinBorders(current, map);

            for (Coordinates node : nodesAround) {
                if (!(visitedList.contains(node) || queue.contains(node))) {
                    parentMap.put(node, current);
                    queue.offer(node);
                    visitedList.add(node);
                }
            }
        }
        List<Coordinates> path = new ArrayList<>();

        while (resultNode != null) {
            path.add(resultNode);
            resultNode = parentMap.get(resultNode);
        }
        Collections.reverse(path);
        return path;
    }

    private static List<Coordinates> getNodesWithinBorders(Coordinates current, Map map) {
        List<Coordinates> nodesAround = new ArrayList<>();
        int cordY = current.getCordY();
        int cordX = current.getCordX();

        if (!(cordY - 1 < 0) && ItIsNotABarrier(cordY - 1, cordX, map))
            nodesAround.add(new Coordinates(cordY - 1, cordX));
        if (!(cordX + 1 > MAP_WEIGHT) && ItIsNotABarrier(cordY, cordX + 1, map))
            nodesAround.add(new Coordinates(cordY, cordX + 1));
        if (!(cordY + 1 > MAP_HEIGHT) && ItIsNotABarrier(cordY + 1, cordX, map))
            nodesAround.add(new Coordinates(cordY + 1, cordX));
        if (!(cordX - 1 < 0) && ItIsNotABarrier(cordY, cordX - 1, map))
            nodesAround.add(new Coordinates(cordY, cordX - 1));
        return nodesAround;
    }

    private static boolean ItIsNotABarrier(int y, int x, Map map) {
        Entity entity = map.getEntity(new Coordinates(y, x));
        if (victim == Herbivore.class) return !(entity instanceof Predator || entity instanceof Rock ||
                entity instanceof Grass);
        return !(entity instanceof Creature || entity instanceof Rock);
    }
}
