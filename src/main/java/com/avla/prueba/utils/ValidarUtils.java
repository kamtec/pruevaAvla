package com.avla.prueba.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ValidarUtils {

	 private static final String PASSWORD_PATTERN ="^(?=.*[A-Z])(?=.*\\d).+$";

	    public boolean validate(String password) {
	        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
	    }
	    
	    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	    public boolean ValidateEmail(String email) {
	        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	
}
