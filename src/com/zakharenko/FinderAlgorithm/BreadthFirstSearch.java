package com.zakharenko.FinderAlgorithm;

import com.zakharenko.Coordinates;
import com.zakharenko.Enities.*;
import com.zakharenko.Map;
import java.util.*;

import static com.zakharenko.Map.MAP_HEIGHT;
import static com.zakharenko.Map.MAP_WEIGHT;

public class BreadthFirstSearch {
    static boolean isPredator;

    public static List<Coordinates> get(Coordinates startNode, Map map) {
        if (map.getEntity(startNode) instanceof Predator) isPredator = true;
        List<Coordinates> shortestPathToResult = findShortestPathToResult(startNode, map);
        isPredator = false;
        return shortestPathToResult;
    }

    private static List<Coordinates> findShortestPathToResult(Coordinates startNode, Map map) {
        Queue<Coordinates> queue = new LinkedList<>();// queue for BFS
        java.util.Map<Coordinates, Coordinates> parentMap = new HashMap<>(); //Map for restore path
        Set<Coordinates> list = new HashSet<>(); // visited Nodes
        Coordinates resultNode = null;

        queue.add(startNode);

        while (!queue.isEmpty()) {
            list.add(queue.element());
            Coordinates current = queue.remove();

            if (!(map.isPlaceEmpty(current)) && IsItPurpose(current, map)) {
                resultNode = current;
                break;
            }

            List<Coordinates> nodesAround = getNodesWithinBorders(current, map);

            for (Coordinates node : nodesAround) {
                if (!(list.contains(node) || queue.contains(node))) {
                    parentMap.put(node, current);
                    queue.offer(node);
                    list.add(node);
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

    public static List<Coordinates> getNodesWithinBorders(Coordinates current, Map map) {
        List<Coordinates> nodesAround = new ArrayList<>();
        int cordY = current.getCordY();
        int cordX = current.getCordX();

        if (!(cordY - 1 < 0) && ItIsNotACreatureOrRock(cordY - 1, cordX, map))
            nodesAround.add(new Coordinates(cordY - 1, cordX));
        if (!(cordX + 1 > MAP_WEIGHT) && ItIsNotACreatureOrRock(cordY, cordX + 1, map))
            nodesAround.add(new Coordinates(cordY, cordX + 1));
        if (!(cordY + 1 > MAP_HEIGHT) && ItIsNotACreatureOrRock(cordY + 1, cordX, map))
            nodesAround.add(new Coordinates(cordY + 1, cordX));
        if (!(cordX - 1 < 0) && ItIsNotACreatureOrRock(cordY, cordX - 1, map))
            nodesAround.add(new Coordinates(cordY, cordX - 1));
        return nodesAround;
    }

    private static boolean IsItPurpose(Coordinates node, Map map) {
        if (isPredator) return map.getEntity(node) instanceof Herbivore;
        return map.getEntity(node) instanceof Grass;
    }

    private static boolean ItIsNotACreatureOrRock(int y, int x, Map map) {
        Entity entity = map.getEntity(new Coordinates(y, x));
        if (isPredator) return !(entity instanceof Predator | entity instanceof Rock);
        return !(entity instanceof Creature | entity instanceof Rock);
    }
}
