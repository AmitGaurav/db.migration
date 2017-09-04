package com.infosys.db.migration.hackathon.core;

import java.io.IOException;

public class Demo {

	public static void main(String[] args) {

		String inFolderLocation = System.getProperty("user.dir")+"/in";
		String outFolderLocation = System.getProperty("user.dir")+"/out";
		
		ParsingEngine parsingEngine = new ParsingEngine();
		parsingEngine.init(inFolderLocation, outFolderLocation);
		
		try {
			parsingEngine.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
