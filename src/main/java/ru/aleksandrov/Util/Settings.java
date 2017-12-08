package ru.aleksandrov.Util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private final static Settings INSTANCE = new Settings();

    private final Properties properties = new Properties();

    private Settings(){
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader()
                    .getResource("project.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Settings getSettings(){
        return INSTANCE;
    }

    public String value(String key){
        return properties.getProperty(key);
    }
}