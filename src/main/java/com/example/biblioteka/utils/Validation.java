package com.example.biblioteka.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    //Kuriami REGEX patternai username, password ir tt... kas reikalinga
    public static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]{5,13}$";
    public static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9!@#$%]{5,13}$";
    public static final String EMAIL_REGEX_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    public static final String REGUSERNAME_REGEX_PATTERN = "^[A-Za-z0-9]{5,}$";
    public static final String REGPASSWORD_REGEX_PATTERN = "^[A-Za-z0-9!@#$%]{5,}$";


    //Kuriami metodai username, password ir t.t... tinkamumui patikrinti

    public static boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}