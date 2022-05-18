package cn.qa.sean.utils;

import java.util.Locale;

public class FormatString {


        public static String getStringWithoutSpaceLowerCase(String string) {
            return string.toLowerCase(Locale.ROOT).replaceAll("\\s+", "");
        }

        public static Integer getIntFromString(String string) {
            return Integer.parseInt(string.toLowerCase(Locale.ROOT).replaceAll("[^\\d]", ""));
        }
    }


