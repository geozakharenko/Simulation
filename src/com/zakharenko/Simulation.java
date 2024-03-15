package com.zakharenko;

import com.zakharenko.Actions.MoveAction;
import com.zakharenko.Actions.SpawnAction;

import java.util.Scanner;

public class Simulation {
    private final Map map = new Map();
    private final MapRenderer renderer = new MapRenderer();
    private boolean isPaused = false;


    public void initWorld() {
        SpawnAction.fillMapWithCreaturesRandom(map);
        System.out.println("СГЕНЕРИРОВАННАЯ КАРТА");
        renderer.render(map);
    }

    public void nextTurn() {
        MoveAction.makeMoveWithCreatures(map);
        renderer.render(map);

    }

    public void startSimulation() {
        //Create Threads
        //Scanner scanner = new Scanner(System.in);
        int countTurns = 1;

        while (true) {
            //String input = scanner.nextLine().toLowerCase();
            switch ("") {
                case "": {
                    if (isPaused) {
                        System.out.println(countTurns);
                        nextTurn();
                        countTurns++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        pauseSimulation();
                    }
                    break;
                }
                case "e": System.exit(0);
                default : printIncorrectInputInfo();
            }
        }
    }

    public void pauseSimulation() {
        this.isPaused = true;
    }
    private static void printIncorrectInputInfo() {
        System.out.println("Вы ввели другой символ");
        System.out.println();
    }
}
