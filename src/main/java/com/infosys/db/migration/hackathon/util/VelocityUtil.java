package com.infosys.db.migration.hackathon.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityUtil {
	
	private VelocityUtil() {
	}
	
	private static VelocityEngine velocityEngine = null;
	
	public static synchronized VelocityEngine getVelocityEngine(){
		if(null != velocityEngine){
			return velocityEngine;
		}
		
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();
		return velocityEngine;
	}
}
