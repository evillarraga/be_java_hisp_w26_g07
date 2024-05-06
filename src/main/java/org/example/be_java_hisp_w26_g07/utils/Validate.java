package org.example.be_java_hisp_w26_g07.utils;

public class Validate {

    public static String stringWhioutSpecialCharacters = "^[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$";

    public static String getStringWhioutSpecialCharacters() {
        return stringWhioutSpecialCharacters;
    }

}
