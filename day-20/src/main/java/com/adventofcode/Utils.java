package com.adventofcode;

public class Utils {

    public static byte charToByte(char c) {
        return switch (c) {
            case '.' -> 0;
            case '#' -> 1;
            default -> throw new IllegalArgumentException("Unexpected char " + c);
        };
    }

    public static void main(String[] args) {

        System.out.println(charToByte('#'));
        System.out.println(charToByte('.'));
        System.out.println(charToByte('\n'));
    }
}
