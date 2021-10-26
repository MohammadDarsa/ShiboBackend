package com.example.shibo.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        Pattern p = Pattern.compile("/(?=.*\\d)(?=.*[a-z])(?=.*[!@#$%=._])(?=.*[A-Z]).{8,}/");
        if(s == null) return false;
        Matcher m = p.matcher(s);
//      return m.matches();
        return true;
    }

}
