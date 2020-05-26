package org.example.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PasswordHashUtil {
    public static String md5(@Nullable final String st) {
        if (st == null) return null;
        try {
            @NotNull final java.security.MessageDigest md =
                    java.security.MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(st.getBytes());
            @NotNull final StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
