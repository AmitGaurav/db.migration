package com.infosys.db.migration.hackathon;

import java.util.regex.PatternSyntaxException;

import com.infosys.db.migration.hackathon.util.SQLStmtValidatorUtil;

public class TestProgram {
	
	public static void main (String[] args){
		

		System.out.println(":----------:");
//		   args[0] = "RegexDemo";
		   String str1 = "apple";
		   String str2 = "applet";
	     /* if (args.length != 2)
	      {
	         System.err.println("usage: java RegexDemo regex input");
	         return;
	      }*/
	      // Convert new-line (\n) character sequences to new-line characters.
	      str2 = str2.replaceAll("\\\\n", "\n");
	      try
	      {
	    	  
	    	  String s = "SELECT  FROM Customers ";
	    	  System.out.println(SQLStmtValidatorUtil.isSelectStmtValid_1(s));
	    	  
	    	  /*String s = "SELECT COUNT(DISTINCT Country) FROM Customers ";
	          Pattern p = Pattern.compile(".*SELECT *(.*) *FROM.*");
	          Matcher m = p.matcher(s);
	          m.find();
	          String text = m.group(1);
	          System.out.println(text);*/
	    	  
	         /*System.out.println("regex = " + str1);
	         System.out.println("input = " + str2);
	         Pattern p = Pattern.compile(str1);
	         Matcher m = p.matcher(str2);
	         while (m.find())
	            System.out.println("Found [" + m.group() + "] starting at "
	                               + m.start() + " and ending at " + (m.end() - 1));*/
	      }
	      catch (PatternSyntaxException pse)
	      {
	         System.err.println("Bad regex: " + pse.getMessage());
	         System.err.println("Description: " + pse.getDescription());
	         System.err.println("Index: " + pse.getIndex());
	         System.err.println("Incorrect pattern: " + pse.getPattern());
	      }
	   
	      
	}
}
