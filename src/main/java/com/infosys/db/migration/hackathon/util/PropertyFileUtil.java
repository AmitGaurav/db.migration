package com.infosys.db.migration.hackathon.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import com.infosys.db.migration.hackathon.bean.Query;
import com.infosys.db.migration.hackathon.bean.QuerySelect;
import com.infosys.db.migration.hackathon.constant.Bucket;

/**
 * This utility parses all the possible structs present with 
 * SELECT
 * UPDATE
 * INSERT
 * DELETE
 * etc.
 * 
 * @author amigaura
 *
 */
public class PropertyFileUtil {
	
	private PropertyFileUtil() {
	}

	private static List<Query> queryStructs = null;
//	private static Properties updateStructs = null;
//	private static Properties insertStructs = null;
//	private static Properties deleteStructs = null;

	public static synchronized List<Query> load(Bucket bucket) {
		
		InputStream inputStream = null;
    	try {
    		if(Bucket.SELECT.equals(bucket)){
    			
    			if(null != queryStructs){
    				return queryStructs;
    			}
    			
    			queryStructs = new ArrayList<Query>();
    			
				Properties selectStructs = new Properties();
				inputStream = PropertyFileUtil.class.getResourceAsStream("/sql.rule.select.properties");
				selectStructs.load(inputStream);
    			
				for(Entry<Object, Object> entry : selectStructs.entrySet()) {
		            String ruleName = (String) entry.getKey();
		            String selectStmt = (String) entry.getValue();
		            
		            QuerySelect querySelect = CommonUtil.pupolateSelectQueryBean(selectStmt);
		            querySelect.setRuleName(ruleName);
		            		
		            queryStructs.add(querySelect);
		        }
    		}else if(Bucket.UPDATE.equals(bucket)){
    			
    		}else if(Bucket.INSERT.equals(bucket)){
    			
    		}else if(Bucket.DELETE.equals(bucket)){
    			
    		}else{
    			//something
    		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    return queryStructs;
	}
}
