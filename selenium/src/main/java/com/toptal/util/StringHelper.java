package com.toptal.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author askeledzija
 */
public class StringHelper {

    public static String getSubstringByPattern(String str, String pattern_string) throws CSTException {
        String result;
        try {
            Matcher m  = Pattern.compile(pattern_string).matcher(str);
            //noinspection ResultOfMethodCallIgnored
            m.find();
            result = m.group(0);
        } catch (IllegalStateException e){
            throw new CSTException("Can't find matching group: "+pattern_string);
        }
        return result;
    }

    public static String get_csrf_token(String str) throws CSTException {

        return getSubstringByPattern(str, "\\w{40}");
    }
}
