package com.business.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: lxliuxuan
 * Date: 2018/09/03
 */

public class RandomUtil {
    private static final String CHALLENGE_SEED = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMBER_SEED = "0123456789";
    private static final String CHARACTER_SEED = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public RandomUtil() {
    }

    public static String getRandomChallenge(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(getRandomInt(0, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length() - 1)));
        }

        return sb.toString();
    }

    public static String getRandomCharacter(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(getRandomInt(0, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length() - 1)));
        }

        return sb.toString();
    }

    public static String getRandomNumberAndCharacter(int numLength, int characterLength) {
        List<Character> list = new ArrayList();

        int i;
        for(i = 0; i < characterLength - 1; ++i) {
            list.add("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(getRandomInt(0, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length() - 1)));
        }

        for(i = 0; i < numLength; ++i) {
            list.add("0123456789".charAt(getRandomInt(0, "0123456789".length() - 1)));
        }

        Collections.shuffle(list);
        Character[] objects = (Character[])list.toArray(new Character[0]);
        StringBuffer sb = new StringBuffer();
        sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(getRandomInt(0, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length() - 1)));
        Character[] var5 = objects;
        int var6 = objects.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Character character = var5[var7];
            sb.append(character);
        }

        return sb.toString();
    }

    public static String getRandomNumber(int length) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            sb.append("0123456789".charAt(getRandomInt(0, "0123456789".length() - 1)));
        }

        return sb.toString();
    }

    public static int getRandomInt(int min, int max) {
        return (int)(Math.random() * (double)max + (double)min);
    }

}
