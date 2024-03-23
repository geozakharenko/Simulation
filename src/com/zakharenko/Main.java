package com.zakharenko;

import java.util.Scanner;

public class Main {
    private static final String START_MENU_ITEM = "1";
    private static final String TURN_SIM = "2";
    private static final String EXIT = "3";
    private static final int PAUSE_SIM = 1;
    private static final int CONTINUE_SIM = 2;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.initWorld();
        while (true) {
            System.out.println("Выберите действие: 1 - Авто-запуск симуляции. 2 - Сделать итерацию. 3 - Выйти");
            switch (scanner.next()) {
                case START_MENU_ITEM:
                    int userInput = CONTINUE_SIM;
                    while (true) {
                        userInput = inputInSimulation(userInput);
                        if (userInput == PAUSE_SIM) break;
                        System.out.println("Номер итерации: #" + simulation.getNumberOfIteration() +
                                ". 1 - Приостановка симуляции");
                        simulation.nextTurn();
                    }
                    break;
                case TURN_SIM:
                    System.out.println("Номер итерации: #" + simulation.getNumberOfIteration() + ".");
                    simulation.nextTurn();
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
        }
    }

    private static int inputInSimulation(int current) {
        try {
            Thread.sleep(500);
            if (System.in.available() > 0) {
                if (scanner.nextInt() == PAUSE_SIM) return PAUSE_SIM;
                return current;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return current;
    }
}
