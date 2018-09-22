package br.com.fiap.educalab.shared;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SharedContent {
    private static final Set<String> PACKAGE_BLACK_LIST = new HashSet<>();
    
    private static Date expireDate = null;

    public static void addToBlackList(String packageName) {
        PACKAGE_BLACK_LIST.add(packageName);
    }

    public static void removeFromBlackList(String packageName) {
        PACKAGE_BLACK_LIST.remove(packageName);
    }

    public static boolean shouldBlock(String packageName) {
        return PACKAGE_BLACK_LIST.contains(packageName);
    }
    
    public static Date getExpireDate() {
        return expireDate;
    }
    
    public static void setExpireDate(Date date) {
        expireDate = date;
    }
    
    public static boolean expired() {
        return expireDate != null && expireDate.before(new Date());
    }
}
