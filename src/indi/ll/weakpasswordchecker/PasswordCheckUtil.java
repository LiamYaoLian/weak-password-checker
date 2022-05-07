package indi.ll.weakpasswordchecker;

import java.util.Arrays;
import java.util.List;

public class PasswordCheckUtil {
	
    public static void main(String[] args) {
    	// empty
    	//String pwd = "";
    	
    	// empty
    	//String pwd = null;
    	
    	// too short
        //String pwd = "123"; 
    	
    	// too long
        //String pwd = "1234567891011121314151617181920";
    	
    	// no letter
    	//String pwd = "12345678";
    	
    	// no upper case
    	//String pwd = "a12345678";
    	
    	// should not contain >= 4 characters of a horizonal key sequential
    	//String pwd = "aA12345678";
    	
    	// should not contain >= 4 characters of a horizonal key sequential
    	//String pwd = "aA1234ljy";
    	
    	// should not contain >= 4 characters of a slant key sequential
    	//String pwd = "aA1qazljy";
    	
    	// should not contain >= 4 characters of a - z or z - a
    	//String pwd = "abcdEfgHij1";
    	
    	// should not contain >= 4 of the same character
    	//String pwd = "aA1111111111";
    	
    	// password too simple
    	//String pwd = "Trustno1";
    	
    	// ok
    	//String pwd = "Trustno12";
        //System.out.println(evalPassword(pwd));
        
    }

    /**
     * @param password
     * @return ok -> true
     */
    public static boolean checkPasswordLength(String password, String max, String min) {

        // if we did not configure MAX_LENGTH in "PasswordCheckConfig.java", only need to check MIN_LENGTH
        if ("".equals(max) && password.length() >= Integer.parseInt(min)) { return true; }

        if ("".equals(max) && password.length() < Integer.parseInt(min)) { return false; }
        
        if (password.length() >= Integer.parseInt(min)
                    && password.length() <= Integer.parseInt(max)) { return true; }

        return false;
    }

    /**
     * 
     * @param password
     * @return yes -> true
     */
    public static boolean checkContainDigit(String password) {
        char[] chPass = password.toCharArray();

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isDigit(chPass[i])) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * 
     * @param password
     * @return yes -> true
     */
    public static boolean checkContainLetter(String password) {
        char[] chPass = password.toCharArray();


        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLetter(chPass[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * 
     * @param password
     * @return yes -> true
     */
    public static boolean checkContainLowerCase(String password) {
        char[] chPass = password.toCharArray();

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLowerCase(chPass[i])) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * 
     * @param password
     * @return yes -> true
     */
    public static boolean checkContainUpperCase(String password) {

        char[] chPass = password.toCharArray();

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isUpperCase(chPass[i])) {
                return true;
            }
        }

        return false;

    }

    /**
     * 
     * @param password
     * @return contains -> true
     */
    public static boolean checkContainSpecialChar(String password) {

        char[] chPass = password.toCharArray();

        for (int i = 0; i < chPass.length; i++) {
            if (PasswordCheckConfig.SPECIAL_CHAR.indexOf(chPass[i]) != -1) {
                return true;
            }
        }

        return false;
    }
         
    /**
     * 
     * @param password
     * @return contains -> true
     */
    public static boolean checkKeySequential(String password, String[] keyboardArr, String limitKeyNum) {
    	
    	String transPwd = new String(password).toLowerCase();
    	
    	int keyLimit = Integer.parseInt(limitKeyNum);
    	    	
    	for (int i = 0; i <= transPwd.length() - keyLimit; i++) {
    		
    		String pwdSnippet = transPwd.substring(i, i + keyLimit);
    		
        	for (String configStr : keyboardArr) {        		
        		String reversedConfigStr = new StringBuffer(configStr).reverse().toString();
        		if (configStr.indexOf(pwdSnippet) != -1 || reversedConfigStr.indexOf(pwdSnippet) != -1) {
        			return true;
        		}
        	}
    	}
    	
    	return false;
    }
    

    /**
     * 
     * @param password
     * @return contains -> true
     */
    public static boolean checkSequentialChars(String password, String limitStr) {

    	int limit = Integer.parseInt(limitStr);
        char[] pwdCharArr = new String(password).toLowerCase().toCharArray();

        for (int i = 0; i + limit <= password.length(); i++) {
            int normal_count = 0;
            int reversed_count = 0;
            for (int j = 0; j < limit - 1; j++) {
                if (pwdCharArr[i + j + 1] - pwdCharArr[i + j] != 1) { continue; }
                if (++normal_count == limit - 1) { return true; }

                if (pwdCharArr[i + j] - pwdCharArr[i + j + 1] != 1) { continue; } 
                if (++reversed_count == limit - 1) { return true; }
            }
        }
        return false;
    }

    /**
     * 
     * @param password
     * @return contains -> true
     */
    public static boolean checkSequentialSameChars(String password, String limitStr) {
        char[] pwdCharArr = password.toCharArray();
        int limit = Integer.parseInt(limitStr);
        
        for (int i = 0; i + limit <= password.length(); i++) {
            int count = 0;
            for (int j = 0; j < limit - 1; j++) {
                if (pwdCharArr[i + j] != pwdCharArr[i + j + 1]) { continue; }
                if (++count == limit - 1) { return true; }
            }
        }
        return false;
    }

    
    /**
     * 
     * @param password
     * @return contains -> true
     */
    public static boolean checkSimpleWord(String password) {
    	List<String> simpleWords = Arrays.asList(PasswordCheckConfig.SIMPLE_WORDS);
        return simpleWords.contains(password.toLowerCase());
    }

    /**
     * Evaluate the password
     * 
     * @param password
     * @return msg
     */
    public static String evalPassword(String password) {
        if (password == null || "".equals(password)) {
            return "empty";
        }

        if (PasswordCheckConfig.CHECK_PASSWORD_LENGTH == PasswordCheckConfig.Option.ENABLE && !checkPasswordLength(password, PasswordCheckConfig.MAX_LENGTH, PasswordCheckConfig.MIN_LENGTH)) { 
        	String msg = "Password length should be >= " + PasswordCheckConfig.MIN_LENGTH + ". ";
        	msg = (!PasswordCheckConfig.MAX_LENGTH.equals("")) ? msg + "Password length should not be > " + PasswordCheckConfig.MAX_LENGTH + ". " : msg;
        	return msg;
        }

        if (PasswordCheckConfig.CHECK_CONTAIN_DIGIT == PasswordCheckConfig.Option.ENABLE && !checkContainDigit(password)) { 
        	return "not contain digit"; 
        }

        if (PasswordCheckConfig.CHECK_CONTAIN_LETTER  == PasswordCheckConfig.Option.ENABLE && !checkContainLetter(password)) { 
        	return "not contain letter"; 
        }
        
        if (PasswordCheckConfig.CHECK_LOWER_CASE == PasswordCheckConfig.Option.ENABLE && !checkContainLowerCase(password)) { 
        	return "not contain lower case"; 
        }

        if (PasswordCheckConfig.CHECK_UPPER_CASE == PasswordCheckConfig.Option.ENABLE && !checkContainUpperCase(password)) { 
        	return "not contain upper case"; 
        }

        if (PasswordCheckConfig.CHECK_CONTAIN_SPECIAL_CHAR  == PasswordCheckConfig.Option.ENABLE && !checkContainSpecialChar(password)) { 
        	return "not contain special character"; 
        }

        if (PasswordCheckConfig.CHECK_HORIZONTAL_KEY_SEQUENTIAL == PasswordCheckConfig.Option.ENABLE
            && checkKeySequential(password, PasswordCheckConfig.KEYBOARD_HORIZONTAL_ARR, PasswordCheckConfig.LIMIT_HORIZONTAL_KEY_NUM)) {
                return "should not contain >= " + PasswordCheckConfig.LIMIT_HORIZONTAL_KEY_NUM + " characters of a horizonal key sequential";
        }

        if (PasswordCheckConfig.CHECK_SLANT_KEY_SEQUENTIAL  == PasswordCheckConfig.Option.ENABLE 
            && checkKeySequential(password, PasswordCheckConfig.KEYBOARD_SLANT_ARR, PasswordCheckConfig.LIMIT_SLANT_KEY_NUM)) {
        	return "should not contain >= " + PasswordCheckConfig.LIMIT_SLANT_KEY_NUM + " characters of a slant key sequential";
        }

        if (PasswordCheckConfig.CHECK_SEQUENTIAL_CHAR == PasswordCheckConfig.Option.ENABLE && checkSequentialChars(password, PasswordCheckConfig.LIMIT_SEQUENTIAL_CHAR_NUM)) { 
        	return "should not contain >= " + PasswordCheckConfig.LIMIT_SEQUENTIAL_CHAR_NUM + " characters of a - z or z - a";
        }

        if (PasswordCheckConfig.CHECK_SEQUENTIAL_SAME_CHAR == PasswordCheckConfig.Option.ENABLE && checkSequentialSameChars(password, PasswordCheckConfig.LIMIT_SEQUENTIAL_SAME_CHAR_NUM)) { 
        	return "should not contain >= " + PasswordCheckConfig.LIMIT_SEQUENTIAL_CHAR_NUM + " of the same character";
        }

        if (PasswordCheckConfig.CHECK_SIMPLE_WORD  == PasswordCheckConfig.Option.ENABLE && checkSimpleWord(password)) { 
        	return "password too simple";
        }

        return "ok";
    }
}
