package com.olamaps.consumermaps.utility;

import org.springframework.http.HttpStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static Integer statusCodeMatchFinder(String str){
        Pattern pattern = Pattern.compile("\\b\\d{3}\\b");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            return Integer.parseInt(matcher.group());
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
