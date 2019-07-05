package com.example.lenovo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Global {
        private static Global instance;


    // Global variable
        private String username;

        // Restrict the constructor from being instantiated
        private Global(){}

        public void setData(String d){
            this.username=d;
        }
        public String getData(){
            return this.username;
        }

        public static synchronized Global getInstance(){
            if(instance==null){
                instance=new Global();
            }
            return instance;
        }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    }

