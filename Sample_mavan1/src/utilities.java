package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class utilities {
	
	public static String getPropertyValue(String key) throws IOException{
		FileInputStream fs=new FileInputStream("./src/resources/config.properties");
		
		Properties pr=new Properties();
		pr.load(fs);
		return pr.getProperty(key);
		
	}

}
