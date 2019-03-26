package com.rybalchenko;

public class TextService {
    public static String staticText() {
        return "Some";
    }

    public String variable(String variable) {
        return variable;
    }

    public String exception(String text) throws RuntimeException {
        if (text==null){
            throw new CustomException("This is an exception");
        }
        return text;
    }

}
