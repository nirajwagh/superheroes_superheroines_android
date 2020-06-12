package com.theminimaldeveloper.superheroes;
import android.util.Log;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateMD5Hash{




        public static String digest(String hashme) {


            Charset UTF_8 = StandardCharsets.UTF_8;


        byte[] input = hashme.getBytes(UTF_8);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);


        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(String.format("%02x", b));
        }


        return sb.toString();


    }

}

