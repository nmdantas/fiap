package br.com.fiap.educalab.shared;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedContent {
    private static final Set<String> PACKAGE_BLACK_LIST = new HashSet<>();

    public static void addToBlackList(String packageName) {
        PACKAGE_BLACK_LIST.add(packageName);
    }

    public static void removeFromBlackList(String packageName) {
        PACKAGE_BLACK_LIST.remove(packageName);
    }

    public static boolean shouldBlock(String packageName) {
        return PACKAGE_BLACK_LIST.contains(packageName);
    }
}
