package com.company.javabussimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Simulation {
    public int passengerDurationMin = 300;
    public int passengerDurationMax = 800;

    private static final Random globalRandom = new Random(System.currentTimeMillis());

    public synchronized int getRandomIntInRangeEnclosed(int min, int max){
        return globalRandom.nextInt((max - min + 1) + min);
    }

    public static void main(String[] args) {

    }
}
