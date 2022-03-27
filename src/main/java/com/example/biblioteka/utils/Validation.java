package com.example.biblioteka.utils;

import javafx.scene.shape.PathElement;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    //Kuriami REGEX patternai username, password ir tt... kas reikalinga
    public static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]{4,13}$";
    public static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9!@#$%]{4,13}$";
    public static final String EMAIL_REGEX_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
    public static final String TITLE_REGEX_PATTERN = "^[A-Za-z0-9 ]{5,25}$";
    public static final String ID_REGEX_PATTERN = "^[0-9]{1,13}$";
    public static final String ISBN_REGEX_PATTERN = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$";
    public static final String SUMMARY_REGEX_PATTERN = "^[\\w.-]+$";

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

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean isValidTitle(String title) {
        Pattern pattern = Pattern.compile(TITLE_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(title);
        return matcher.find();
    }

    public static boolean isValidId(String id) {
        Pattern pattern = Pattern.compile(ID_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }

    public static boolean isValidISBN(String ISBN) {
        Pattern pattern = Pattern.compile(ISBN_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(ISBN);
        return matcher.find();
    }

    public static boolean isValidSummary(String summary) {
        Pattern pattern = Pattern.compile(SUMMARY_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(summary);
        return matcher.find();
    }
}