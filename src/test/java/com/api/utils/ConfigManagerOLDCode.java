package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLDCode {
	//WAP to read the properties file from src/test/resources/config/config.properties
	
	private static Properties prop = new Properties();
	
	//create a static block:
	static {
		//Operation of loading the properties file in the memory
		//static block it will be executed, once during class_loading_time
		File configFile = new File(System.getProperty("user.dir") +
				File.separator + "src" + File.separator + "test" + File.separator + "resources "
				+ File.separator + "config" + File.separator + "config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getProperty(String key) {
		
		//special class: properties 
//		Properties prop = new Properties();
		
		//load the properties file using load method
		
		
		return prop.getProperty(key);
	}
	
}
