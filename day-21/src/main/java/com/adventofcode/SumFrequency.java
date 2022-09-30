package com.adventofcode;

public class SumFrequency {

    private final static int DICE_SIDES = 3;

    public static void main(String[] args) {

        int[] frequencies = new int[DICE_SIDES * 3];
        for (int i = 1; i <= DICE_SIDES; i++) {
            for (int j = 1; j <= DICE_SIDES; j++) {
                for (int k = 1; k <= DICE_SIDES; k++) {
                    int sum = i + j + k;
                    frequencies[sum - 1]++;
                }
            }
        }

        System.out.println("Sum frequencies:");
        for (int i = 0; i < frequencies.length; i++) {
            int sum = i + 1;
            int frequency = frequencies[i];
            if (frequency != 0) {
                System.out.println(sum + " -> " + frequency);
            }
        }
    }
}
