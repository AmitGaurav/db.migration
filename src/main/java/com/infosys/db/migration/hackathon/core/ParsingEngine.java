package com.infosys.db.migration.hackathon.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.infosys.db.migration.hackathon.bean.Query;
import com.infosys.db.migration.hackathon.bean.QuerySelect;
import com.infosys.db.migration.hackathon.constant.Bucket;
import com.infosys.db.migration.hackathon.util.CommonUtil;
import com.infosys.db.migration.hackathon.util.PropertyFileUtil;
import com.infosys.db.migration.hackathon.util.VelocityUtil;

public class ParsingEngine {

	private String IN_FOLDER_LOC;
	private String OUT_FOLDER_LOC;
	
	public ParsingEngine(){
	}
	
	public ParsingEngine(String folderLocation, String outFolderLocation){
		this.IN_FOLDER_LOC = folderLocation;
		this.OUT_FOLDER_LOC = outFolderLocation;
	}
	
	public void init(String inFolderLocation, String outFolderLocation){
		this.IN_FOLDER_LOC = inFolderLocation;
		this.OUT_FOLDER_LOC = outFolderLocation;
	}
	
	/*
	 *  Check for stored procedures at the given folder location
	 */
	public void parse() throws IOException{
		File folder = new File(IN_FOLDER_LOC);
		File[] storedProcedures = folder.listFiles();

		for (File storedProcedure : storedProcedures) {
		    if (storedProcedure.isFile()) {
		        
		    	System.out.println("Stored procedure to be parsed: "+storedProcedure.getName());

		    	// Step-1
		        String[] blocks = breakProcedures(storedProcedure);	// <<<<<<---- Considering only the definition block [in between BEGIN and END]
		        
		        // Step-2
		        //List<String> signatureSQLStmts = null;
		        //List<String> declarationSQLStmts = null;
		        List<String> definitionSQLStmts = null;				// <<<<<<---- 
		        //List<String> exceptionSQLStmts = null;
		        short count = 0;
		        for(String block:blocks){
		        	if(0 == count){
		        		//signatureSQLStmts = breakBlocks(block);
		        		++count;
		        	}else if(1 == count){
		        		//declarationSQLStmts = breakBlocks(block);
		        		++count;
		        	}else if(2== count){
		        		definitionSQLStmts = breakBlocks(block);		// <<<<<<----Considering only the definition block [in between BEGIN and END]
		        		++count;
		        	}else if(3== count){
		        		//exceptionSQLStmts = breakBlocks(block);
		        		++count;
		        	}else{
		        		break;
		        	}
		        }
		        
		        // Step-3
		        // TODO: do the same for other statements [signatureStmts, 
		        // declarationStmts, exceptionStmts] too
		        
		        List<String> mongoDBStmts = new ArrayList<String>();
		        for(String sqlStmt: definitionSQLStmts){
		        	String mongoDBStmt = parseStatement(sqlStmt);
		        	mongoDBStmts.add(mongoDBStmt);
		        }
		        
		        // get the output
		        writeToFile(storedProcedure.getName(), mongoDBStmts);
		    }
		}
	}
	
	/*
	 * Break the procedure to get three blocks
	 *      0. Signature block
	 * 		1. Declaration block
	 * 		2. Definition block
	 * 		3. Exception block
	 */
	private String[] breakProcedures(File storedProcedure) throws IOException{
		String[] blocks = new String[4];

		String content = new String(Files.readAllBytes(Paths.get(storedProcedure.getPath()))).toUpperCase();
		
		content = CommonUtil.removeWhiteSpacesWitSingleWhiteSpace(content);
		content = CommonUtil.removeNewline(content);
		
		short count = 0;
		//TODO: find signature block
		count++;
		
		//TODO: find declaration block
		count++;
		
		// find definition block
		Pattern pattern = Pattern.compile("BEGIN(.*?)EXCEPTION");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
//		    System.out.println(matcher.group(1));
		    blocks[count++] = matcher.group(1);
		}
		
		//TODO: find exception block
		
		return blocks;
	}
	
	/*
	 * Break the block to get SQL statements 
	 * This could be SELECT, UPDATE, INSERT, UPDATE or DELETE
	 * 
	 * Check Bucket.java
	 * 
	 */
	private List<String> breakBlocks(String block){
		
		List<String> statements = new ArrayList<String>();
		if(null == block){
			return statements;
		}
		
		//TODO: Need to work on condition statements such if-else or loops etc.
		// For the time being, this is considered that only simple SQL statements are encountered
		
		StringTokenizer stringTokenizer = new StringTokenizer(block, ";");
		while(stringTokenizer.hasMoreTokens()){
			String statement = stringTokenizer.nextToken();
			statements.add(statement);
		}
		return statements;
	}
	
	/*
	 * This is the core, where parsing logic lives
	 * parse statements to get an equivalent MongoDB statement
	 * 
	 */
	private String parseStatement(String statement){
		String mongoDBStatement = null;
		List<Query> querySelectStructs = null;
		
		// Step-1: finalize statement category [select/delete/insert/update etc.]
		if(statement.contains(Bucket.SELECT.toString())){
			
			
			System.out.println(statement);
			QuerySelect querySelectFound = CommonUtil.pupolateSelectQueryBean(statement);
			
			querySelectStructs = PropertyFileUtil.load(Bucket.SELECT);
			querySelectFound = (QuerySelect) decideRule(querySelectStructs, querySelectFound);
			
			mongoDBStatement = transformToMongoDBStmt(querySelectFound);
			
			
			
			
			
		}else if(statement.contains(Bucket.INSERT.toString())){
			
		}else if(statement.contains(Bucket.UPDATE.toString())){
			
		}else if(statement.contains(Bucket.DELETE.toString())){
			
		}else{
			System.out.println("Not supported!");
		}
		
		return mongoDBStatement;
	}
	
	private String transformToMongoDBStmt(QuerySelect querySelectFound){
		String mongoDBStmt = null;
		
		String ruleName = querySelectFound.getRuleName();
		
		if("SQL_SELECT_RULE1".equals(ruleName)){
			mongoDBStmt = process_SQL_SELECT_RULE1(querySelectFound);
		}else if("SQL_SELECT_RULE5".equals(ruleName)
				|| "SQL_SELECT_RULE6".equals(ruleName)){
			mongoDBStmt = process_SQL_SELECT_RULE5(querySelectFound);
		}
		
		
		/*if(querySelectFound.getQueryType().equals(Bucket.SELECT)){
			
			System.out.println(ruleName);
			mongoDBStmt = processRule(querySelectFound);
			
		}else if(querySelectFound.getQueryType().equals(Bucket.UPDATE)){
			
		}else if(querySelectFound.getQueryType().equals(Bucket.INSERT)){
			
		}else if(querySelectFound.getQueryType().equals(Bucket.DELETE)){
			
		}*/
		
		return mongoDBStmt;
	}

	private String process_SQL_SELECT_RULE5(QuerySelect querySelectFound){
		
		String mongoDBStmt = null;
		
		VelocityEngine velocityEngine = VelocityUtil.getVelocityEngine();
		String ruleName = querySelectFound.getRuleName();
		
		Template template = velocityEngine.getTemplate("templates/"+ruleName+".vm");
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName", querySelectFound.getFromTables().get(0));
        velocityContext.put("selectColumns", querySelectFound.getSelectColumns());
        velocityContext.put("whereConditionsMap", querySelectFound.getWhereConditionsMap());
        velocityContext.put("mapSize", querySelectFound.getWhereConditionsMap().size());
        velocityContext.put("columns", querySelectFound.getSelectColumns());
        velocityContext.put("size", querySelectFound.getSelectColumns().size());

        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);
        mongoDBStmt = writer.toString();
        System.out.println(mongoDBStmt);

        return mongoDBStmt;
	}

	private String process_SQL_SELECT_RULE1(QuerySelect querySelectFound){
		
		String mongoDBStmt = null;
		
		VelocityEngine velocityEngine = VelocityUtil.getVelocityEngine();
		String ruleName = querySelectFound.getRuleName();
		
		Template template = velocityEngine.getTemplate("templates/"+ruleName+".vm");
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName", querySelectFound.getFromTables().get(0));
        velocityContext.put("columns", querySelectFound.getSelectColumns());
        velocityContext.put("size", querySelectFound.getSelectColumns().size());

        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);
        mongoDBStmt = writer.toString();
        System.out.println(mongoDBStmt);

        return mongoDBStmt;
	}

	private void writeToFile(String fileName, List<String> mongoDBStatements) throws IOException{
		if(mongoDBStatements.isEmpty()){
			System.out.println("MongoDB statement is not available to write.");
			return;
		}
		FileWriter writer = new FileWriter(new File(OUT_FOLDER_LOC+"/"+fileName)); 
		for(String mongoDBStatement: mongoDBStatements) {
			if(null != mongoDBStatement){
				writer.write(mongoDBStatement);
			}
		}
		writer.close();
	}
	
	private Query decideRule(List<Query> querySelectStructs, Query querySelectFound){
		//System.out.println("Found Query: \n"+querySelectFound+"\n");
		for(Query querySelectStruct: querySelectStructs){
			//System.out.println(querySelectStruct);
			if(querySelectStruct.equals(querySelectFound)){
				querySelectFound.setRuleName(querySelectStruct.getRuleName());
				System.out.print("Matching pattern found!\t");
				System.out.println("Rule Name: >>>"+querySelectStruct.getRuleName()+"<<<");
				return querySelectFound; 
			}
		}
		System.out.println("*** *** No rule found matching with the pattern *** ***");
		return querySelectFound;
	}
}
