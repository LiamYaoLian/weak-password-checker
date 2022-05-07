package indi.ll.weakpasswordchecker;

public class PasswordCheckConfig {
	
	enum Option {
		ENABLE,
		DISABLE;
	}

    public static final Option CHECK_PASSWORD_LENGTH = Option.ENABLE;

    public static final String MIN_LENGTH = "8";

    public static final String MAX_LENGTH = "30";

    public static final Option CHECK_CONTAIN_DIGIT = Option.ENABLE;

    public static final Option CHECK_CONTAIN_LETTER = Option.ENABLE;

    public static final Option CHECK_LOWER_CASE = Option.DISABLE;

    public static final Option CHECK_UPPER_CASE = Option.ENABLE;

    public static final Option CHECK_CONTAIN_SPECIAL_CHAR = Option.DISABLE;

    public static final String SPECIAL_CHAR = "!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~";

    public static final Option CHECK_HORIZONTAL_KEY_SEQUENTIAL = Option.ENABLE;

    public static final String LIMIT_HORIZONTAL_KEY_NUM = "4";

    public static final Option CHECK_SLANT_KEY_SEQUENTIAL = Option.ENABLE;

    public static final String LIMIT_SLANT_KEY_NUM = "4";

    /*
     * Check if the password contains of a snippet of a - z or z - a
     */
    public static final Option CHECK_SEQUENTIAL_CHAR = Option.ENABLE;

    public static final String LIMIT_SEQUENTIAL_CHAR_NUM = "4";

    /*
     * Check if there is a sequence of the same character, such as "1111", "aaaa", etc
     */
    public static final Option CHECK_SEQUENTIAL_SAME_CHAR = Option.ENABLE;

    public static final String LIMIT_SEQUENTIAL_SAME_CHAR_NUM = "4";

    public static final String[] KEYBOARD_HORIZONTAL_ARR = { "01234567890", "qwertyuiop", "asdfghjkl", "zxcvbnm" };

    public static final String[] KEYBOARD_SLANT_ARR = { "1qaz", "2wsx", "3edc", "4rfv", "5tgb", "6yhn", "7ujm", "8ik,",
            "9ol.", "0p;/", "=[;.", "-pl,", "0okm", "9ijn", "8uhb", "7ygv", "6tfc", "5rdx", "4esz" };

    public static final Option CHECK_SIMPLE_WORD = Option.ENABLE;

    // need to update
    public static final String[] SIMPLE_WORDS = { "trustno1", "admin", "szim", "epicrouter", "password", "grouter", "dare", "root",
            "guest", "user", "success", "pussy", "mustang", "fuckme", "jordan", "test", "hunter", "jennifer", "batman",
            "thomas", "soccer", "sexy", "killer", "george", "asshole", "fuckyou", "summer", "hello", "secret", "fucker",
            "enter", "cookie", "administrator",
            "xiaoming", "taobao", "iloveyou", "woaini", "982464",
            "monkey", "letmein", "dragon", "baseball", "master", "sunshine", "ashley", "bailey", "shadow",
            "superman", "football", "michael", "qazwsx" };
    
    
    
}
