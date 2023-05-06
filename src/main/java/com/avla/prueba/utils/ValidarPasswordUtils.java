package com.avla.prueba.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarPasswordUtils {

	 private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

	    public boolean validate(String password) {
	        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
	    }
	
}
