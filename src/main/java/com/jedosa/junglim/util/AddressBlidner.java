package com.jedosa.junglim.util;

public class AddressBlidner {
    public static String blindAsStar(String address) {
        // 서울 마포구 성암로 301
        // 서울 마포구 성암로 ***
        int streetCharacterIndex = address.lastIndexOf("로");
        String raw = address.substring(0, streetCharacterIndex+1);
        String blind = address.substring(streetCharacterIndex+1).replaceAll("[^\\s]", "*");
        return raw + blind;
    }
}
