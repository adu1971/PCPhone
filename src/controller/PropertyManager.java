package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class PropertyManager implements Serializable {

	private final static String RESOURCE_NAME = "./src/controller/Ayar.yap";

	public Properties readProperties() {

		String resName = PropertyManager.RESOURCE_NAME.replace("/",
				System.getProperty("file.separator"));
		Properties properties = new Properties();
		File resFile = new File(resName);

		try {
			FileInputStream fis = new FileInputStream(resFile);
			properties.load(fis);
		} catch (FileNotFoundException fnfe) {
			System.err.println(resName + " dosyası bulunamadı!");
		} catch (IOException ioe) {
			System.err.println(resName + " dosyası bulundu ama yüklenemedi!");
		}
		System.out.println(properties);
		return properties;
	}
}
