package org.example.util;

public class CryptographicUtils {

    public static int generateFourDigitNumber() {
        final int length = 9;
        int randNum = 0;
        while (randNum < Math.pow(10, length - 1)) {
            randNum = (int) Math.floor(Math.random() * Math.pow(10, length));
        }

        return randNum;
    }
}
