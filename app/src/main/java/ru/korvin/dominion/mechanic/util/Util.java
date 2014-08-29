package ru.korvin.dominion.mechanic.util;

import java.util.Random;

public class Util {
    public static final Random random = new Random();

    public static int getRandomValue(int initiateValue, int from, int to, int countAttemt, int minValue, int maxValue) {
        int result = initiateValue;
        for (int i = 0; i < countAttemt; i++) {
            result += (random.nextInt(to - from) + from) / countAttemt;
        }
        if (result < minValue) result = minValue;
        if (result > maxValue) result = maxValue;
        return result;
    }
}
