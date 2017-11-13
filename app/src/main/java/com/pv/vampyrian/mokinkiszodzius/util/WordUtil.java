package com.pv.vampyrian.mokinkiszodzius.util;

import java.util.Random;

public class WordUtil {

    public static int getNext(int currentId, int maxSize, boolean random) {
        if (maxSize < 1) {
            return 0;
        }
        if (random) {
            currentId = new Random().nextInt(maxSize);
        } else {
            currentId++;
            if (currentId >=maxSize) {
                currentId = 0;
            }
        } return currentId;
    }
}
