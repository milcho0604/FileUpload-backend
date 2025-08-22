package com.fileblocker.flow.util;

public final class ExtensionUtils {
    private ExtensionUtils(){}

    public static String normalize(String raw){
        if(raw == null) return null;
        String s = raw.trim().toLowerCase();
        while(s.startsWith(".")) s = s.substring(1);
        return s;
    }

    public static boolean isValid(String normalized){
        if(normalized == null || normalized.isBlank()) return false;
        if(normalized.length() > 20) return false;
        return normalized.matches("^[a-z0-9]+$");
    }
}
