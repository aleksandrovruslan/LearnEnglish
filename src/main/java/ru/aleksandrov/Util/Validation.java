package ru.aleksandrov.Util;

import java.util.regex.Pattern;

public class Validation {
    private String name;
    private String login;
    private String email;
    private String password;
    private String word;
    private String[] words;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getWord() {
        return word;
    }

    public String[] getWords() {
        return words;
    }

    public boolean isVerifyName(String name){
        this.name = null;
        if(name != null) {
            name = name.trim();
            if (!name.isEmpty()) {
                this.name = name;
                return true;
            }
        }
        return false;
    }

    public boolean isVerifyLogin(final String login){
        this.login = null;
        if(!login.isEmpty() && !login.contains(" ")
                && (login.length() < 13)){
            this.login = login;
            return true;
        }
        return false;
    }

    public boolean isVerifyEmail(final String email){
        this.email = null;
        if(email != null) {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN
                    , Pattern.CASE_INSENSITIVE);
            if (pattern.matcher(email).matches()) {
                this.email = email;
                return true;
            }
        }
        return false;
    }

    public boolean isVerifyPassword(final String password){
        this.password = null;
        if(!password.isEmpty() && !password.contains(" ")
                && (password.length() > 6) && (password.length() < 13)){
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean isVerifyWord(String word){
        this.word = null;
        if((word != null)){
            word = word.trim().toLowerCase();
            if(!word.isEmpty()) {
                this.word = word;
                return true;
            }
        }
        return false;
    }

    public boolean isVerifyWords(String[] words){
        this.words = null;
        if(words != null && (words.length > 0)){
            for(int i = 0; i < words.length; i ++){
                if(!isVerifyWord(words[i])){
                    return false;
                }
                words[i] = word;
            }
            this.words = words;
            return true;
        }
        return false;
    }
}
