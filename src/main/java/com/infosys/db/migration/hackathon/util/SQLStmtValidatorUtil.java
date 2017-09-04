package com.infosys.db.migration.hackathon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLStmtValidatorUtil {

	private SQLStmtValidatorUtil(){
	}


	public static boolean isSelectStmtValid_1(final String sqlStmt){
		if(null == sqlStmt){
			return false;
		}
		String sqlStmtWithSingleWhiteSpace = CommonUtil.removeWhiteSpacesWitSingleWhiteSpace(sqlStmt);
		// SQL statement must have SELECT and FROM
		if(!hasSQLStmtSelectAndFrom(sqlStmtWithSingleWhiteSpace)){
			return false;
		}
		// there must be some column names or * after SELECT and before FROM
		if(!areColumnNamesValid(sqlStmtWithSingleWhiteSpace)){
			return false;
		}
		return true;
	}

	private static boolean hasSQLStmtSelectAndFrom(String sqlStmtWithSingleWhiteSpace) {
		if(!sqlStmtWithSingleWhiteSpace.toUpperCase().contains("SELECT") 
				|| !sqlStmtWithSingleWhiteSpace.toUpperCase().contains("FROM")){
			return false;
		}
		return true;
	}

	private static boolean areColumnNamesValid(String sqlStmtWithSingleWhiteSpace) {
		boolean isValid = true;
		Pattern p = Pattern.compile(".*SELECT *(.*) *FROM.*");
        Matcher m = p.matcher(sqlStmtWithSingleWhiteSpace);
        m.find();
        String columnNames = m.group(1);
        if(CommonUtil.isSingleWhiteSpace(columnNames)){
        	isValid = false;
        }else if ("*".equalsIgnoreCase(columnNames)){
        	isValid = true;
        }else if(columnNames.length() == 0){
        	isValid = false;
        }
        return isValid;
	}
}
