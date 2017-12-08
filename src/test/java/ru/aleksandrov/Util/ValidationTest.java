package ru.aleksandrov.Util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {
    private Validation valid = new Validation();
    @Test
    public void getName() throws Exception {
        valid.isVerifyName("name");
        assertEquals("name", valid.getName());
        valid.isVerifyName("");
        assertNull(valid.getName());
    }

    @Test
    public void getLogin() throws Exception {
        valid.isVerifyLogin("loG1in");
        assertEquals("loG1in", valid.getLogin());
        valid.isVerifyLogin(" login");
        assertNull( valid.getLogin());
    }

    @Test
    public void getEmail() throws Exception {
        valid.isVerifyEmail("email@gamail.com");
        assertEquals("email@gamail.com", valid.getEmail());
        valid.isVerifyEmail("em ail@gamail.com");
        assertNull(valid.getEmail());
    }

    @Test
    public void getPassword() throws Exception {
        valid.isVerifyPassword("password");
        assertEquals("password", valid.getPassword());
        valid.isVerifyPassword("pass word");
        assertNull(valid.getPassword());
    }

    @Test
    public void getWord() throws Exception {
        valid.isVerifyWord("  ");
        assertNull(valid.getWord());
        valid.isVerifyWord("word");
        assertEquals("word", valid.getWord());
    }

    @Test
    public void getWords() throws Exception {
        valid.isVerifyWords(new String[] {" ", " word "});
        assertNull(valid.getWords());
        valid.isVerifyWord(null);
        assertNull(valid.getWords());
    }

    @Test
    public void isVerifyName() throws Exception {
        assertTrue(valid.isVerifyName("name"));
        assertTrue(valid.isVerifyName(" Na fd df1"));
        assertTrue(valid.isVerifyName("  JH  1jk jk  "));
        assertFalse(valid.isVerifyName(""));
        assertFalse(valid.isVerifyName(" "));
        assertFalse(valid.isVerifyName(null));
    }

    @Test
    public void isVerifyLogin() throws Exception {
        assertTrue(valid.isVerifyLogin("login"));
        assertFalse(valid.isVerifyLogin(" lo gin "));
        assertTrue(valid.isVerifyLogin("Lo1Gin"));
        assertTrue(valid.isVerifyLogin("loooooogin12"));
        assertFalse(valid.isVerifyLogin("looooooogin13"));
    }

    @Test
    public void isVerifyEmail() throws Exception {
        assertTrue(valid.isVerifyEmail("1_eMail@gmail.com"));
        assertFalse(valid.isVerifyEmail(""));
        assertFalse(valid.isVerifyEmail(null));
        assertFalse(valid.isVerifyEmail(" a@gmail.com"));
        assertFalse(valid.isVerifyEmail("fdsf"));
        assertFalse(valid.isVerifyEmail("em ail@gmail.com"));
        assertFalse(valid.isVerifyEmail("em@ail@gmail.com"));
        assertFalse(valid.isVerifyEmail("em/ail@gmail.com"));
        assertFalse(valid.isVerifyEmail("em=ail@gmail.com"));
    }

    @Test
    public void isVerifyPassword() throws Exception {
        assertTrue(valid.isVerifyPassword("passwooord12"));
        assertFalse(valid.isVerifyPassword("passwoooord13"));
        assertTrue(valid.isVerifyPassword("P?/a5!@*&^$%"));
        assertFalse(valid.isVerifyPassword(""));
        assertFalse(valid.isVerifyPassword("pasw5"));
        assertFalse(valid.isVerifyPassword("password "));
        assertFalse(valid.isVerifyPassword("pass word"));
    }

    @Test
    public void isVerifyWord() throws Exception {
        assertFalse(valid.isVerifyWord(""));
        assertTrue(valid.isVerifyWord(" asd saR1 "));
        assertFalse(valid.isVerifyWord(" "));
        assertFalse(valid.isVerifyWord(null));
    }

    @Test
    public void isVerifyWords() throws Exception {
        assertFalse(valid.isVerifyWords(new String[]{" ", "word"}));
        assertTrue(valid.isVerifyWords(new String[]{" hgR 1jh ", " djfhdjsH sd "}));
        assertFalse(valid.isVerifyWords(new String[]{null, null}));
        assertFalse(valid.isVerifyWords(null));
    }

}