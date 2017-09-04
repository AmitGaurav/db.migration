package com.infosys.db.migration.hackathon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.infosys.db.migration.hackathon.bean.Query;
import com.infosys.db.migration.hackathon.bean.QuerySelect;
import com.infosys.db.migration.hackathon.constant.Bucket;
import com.infosys.db.migration.hackathon.constant.SelectConstitute;
/**
 * This utility stores all the common util functions
 * used in development
 * 
 * @author amigaura
 *
 */
public class CommonUtil {
	

	private static final String SINGLE_WHITE_SPACE = " ";
	private static final String EMPTY = "";
	
	private CommonUtil(){
	}

	public static String removeNewline(String inputString){
		return inputString.replaceAll("\\r?\\n", "");
	}
	
	public static String removeWhiteSpacesWitSingleWhiteSpace(String inputString){
		return inputString.replaceAll("^ +| +$|( )+", "$1");
	}
	
	public static boolean isSingleWhiteSpace(String inputString){
		return CommonUtil.SINGLE_WHITE_SPACE.equalsIgnoreCase(inputString);
	}
	
	public static boolean isEmpty(String inputString){
		return CommonUtil.EMPTY.equalsIgnoreCase(inputString);
	}
	
	/**
	 * 
	 * This is to populate SQL equivalent bean called <b>Query</b>
	 * 
	 * @see Query
	 * @see QuerySelect
	 * 
	 * @param selectStmt
	 * @return
	 */
	public static QuerySelect pupolateSelectQueryBean(String selectStmt){
		
		QuerySelect selectQuery = new QuerySelect(selectStmt);
		selectQuery.setQueryType(Bucket.SELECT);
		String selectFragment = extractFragment(selectStmt, "SELECT");
		selectQuery.setSelectFragment(selectFragment);
		selectQuery.setSelectColumns(getEntities(selectFragment));

		selectQuery.setDistinctPresent(selectStmt.contains(SelectConstitute.DISTINCT.valueOf()));
		selectQuery.setAsPresent(selectStmt.contains(SelectConstitute.AS.valueOf()));
		
		String fromFragment = extractFragment(selectStmt, "FROM");
		selectQuery.setFromFragment(fromFragment);
		selectQuery.setFromTables(getEntities(fromFragment));

		boolean isWherePresent = selectStmt.contains(SelectConstitute.WHERE.valueOf());
		selectQuery.setWherePresent(isWherePresent);
		if(isWherePresent){
			String whereFragment = extractFragment(selectStmt, "WHERE");
			
			List<String> whereConditions = getFragmentsFromWhereClause(whereFragment);
			Map<String, String> whereConditionsMap = new HashMap<String, String>();
			for(String whereCondition: whereConditions){
				whereConditionsMap = breakWhereConditions(whereConditionsMap, whereCondition);
			}
			selectQuery.setWhereConditionsMap(whereConditionsMap);
			
			selectQuery.setWhereFragment(whereFragment);
		}
		
		selectQuery.setInnerJoinPresent(selectStmt.contains(SelectConstitute.INNER_JOIN.valueOf()));
		selectQuery.setLeftJoinPresent(selectStmt.contains(SelectConstitute.LEFT_JOIN.valueOf()));
		selectQuery.setRightJoinPresent(selectStmt.contains(SelectConstitute.RIGHT_JOIN.valueOf()));
		selectQuery.setFullOuterJoinPresent(selectStmt.contains(SelectConstitute.FULL_OUTER_JOIN.valueOf()));
		
		selectQuery.setNotPresent(selectStmt.contains(SelectConstitute.NOT.valueOf()));
		selectQuery.setInPresent(selectStmt.contains(SelectConstitute.IN.valueOf()));
		selectQuery.setBetweenPresent(selectStmt.contains(SelectConstitute.BETWEEN.valueOf()));
		selectQuery.setAndPresent(selectStmt.contains(SelectConstitute.AND.valueOf()));
		selectQuery.setOrPresent(selectStmt.contains(SelectConstitute.OR.valueOf()));
		selectQuery.setLikePresent(selectStmt.contains(SelectConstitute.LIKE.valueOf()));
		selectQuery.setMaxPresent(selectStmt.contains(SelectConstitute.MAX.valueOf()));
		selectQuery.setMinPresent(selectStmt.contains(SelectConstitute.MIN.valueOf()));
		
		boolean isOrderByPresent = selectStmt.contains(SelectConstitute.ORDER_BY.valueOf());
		selectQuery.setOrderByPresent(isOrderByPresent);
		if(isOrderByPresent){
			String orderByFragment = extractFragment(selectStmt, "ORDER_BY");		
			selectQuery.setOrderByFragment(orderByFragment);
			selectQuery.setOrderByColumns(getEntities(orderByFragment));

			selectQuery.setAscPresent(selectStmt.contains(SelectConstitute.ASC.toString()));
			selectQuery.setDescPresent(selectStmt.contains(SelectConstitute.DESC.toString()));
		}
		
		boolean isGroupByPresent = selectStmt.contains(SelectConstitute.GROUP_BY.valueOf());
		selectQuery.setGroupByPresent(isGroupByPresent);
		if(isGroupByPresent){
			String groupByFragment = extractFragment(selectStmt, "GROUP_BY");
			selectQuery.setGroupByFragment(groupByFragment);
			selectQuery.setGroupByColumns(getEntities(groupByFragment));
			
			selectQuery.setCountPresent(selectStmt.contains(SelectConstitute.COUNT.valueOf()));
			selectQuery.setAvgPresent(selectStmt.contains(SelectConstitute.AVG.valueOf()));
			selectQuery.setSumPresent(selectStmt.contains(SelectConstitute.SUM.valueOf()));
			
			String havingFragment = extractFragment(selectStmt, "HAVING");
			selectQuery.setHavingFragment(havingFragment);
			selectQuery.setHavingPresent(selectStmt.contains(SelectConstitute.HAVING.valueOf()));
		}

		boolean isInsertPresent = selectStmt.contains(SelectConstitute.INSERT.valueOf());
		selectQuery.setInsertPresent(isInsertPresent);
		if(isInsertPresent){
			String insertFragment = extractFragment(selectStmt, "INSERT");
			selectQuery.setInsertFragment(insertFragment);
		}

		selectQuery.setAnyPresent(selectStmt.contains(SelectConstitute.ANY.valueOf()));
		selectQuery.setTopPresent(selectStmt.contains(SelectConstitute.TOP.valueOf()));
		selectQuery.setUnionAllPresent(selectStmt.contains(SelectConstitute.UNION_ALL.valueOf()));
		selectQuery.setUnionPresent(selectStmt.contains(SelectConstitute.UNION.valueOf()));
		
		return selectQuery;
	}
	
	private static List<String> getFragmentsFromWhereClause(String inpstr) {
		List<String> retalist = new ArrayList<String>();

		Pattern pattern1 = Pattern.compile("\\b(?:AND|OR)\\b");
		Matcher matcher = pattern1.matcher(inpstr);
		boolean found = false;
		int endPoint = 0;

		while (matcher.find()) {
			// System.out.println("I found the text "+matcher.group()+" starting at index "+
			// matcher.start()+" and ending at index "+matcher.end());
			String stringNeeded = inpstr.substring(endPoint,
					matcher.start() - 1).replaceAll("(^ )|( $)", "");
//			System.out.println("String needed........." + stringNeeded);
			retalist.add(stringNeeded);
			endPoint = matcher.end();
			found = true;
		}
		if (inpstr != null) {
			int inpstrlen = inpstr.length();
			String stringNeeded = inpstr.substring(endPoint, inpstrlen)
					.replaceAll("(^ )|( $)", "");
			//System.out.println("String needed........." + stringNeeded);
			retalist.add(stringNeeded);
		}
		if (!found) {
//			System.out.println("No match found.");
		}

		return retalist;
	}
	
	private static Map<String, String> breakWhereConditions(Map<String, String> map, String whereFragment){

		populateMapForWhereConditions(whereFragment, map);
		
		//TODO: Consider cases: 
		// 1. when AND or OR comes
		// 2. when OR only comes
		// 3. when nested AND or nested OR comes
		
		return map;
	}

	private static void populateMapForWhereConditions(String whereFragment,
														Map<String, 
														String> map) {
		if(whereFragment.contains("=")){
			int position = whereFragment.indexOf('=');
			map.put(whereFragment.substring(0, position),
						whereFragment.substring(position + 1, whereFragment.length()));
		}
	}
	
	private static List<String> getEntities(String fragment){
		List<String> entities = new ArrayList<String>();
		
		if(null == fragment){
			return entities;
		}
		
		if("*".equals(fragment.trim())){
			entities.add("*");
		}else{
			StringTokenizer stringTokenizer = new StringTokenizer(fragment, ",");
			while(stringTokenizer.hasMoreElements()){
				entities.add(stringTokenizer.nextToken());
			}
		}
		return entities;
	}
	
	private static String extractFragment(String statement, String fragmentType){
		
		String fragment = null;
		Pattern pattern = null;
		Matcher matcher = null;
		
		if("SELECT".equals(fragmentType)){
			pattern = Pattern.compile("SELECT(.*?)FROM");
			matcher = pattern.matcher(statement);
			while (matcher.find()) {
				fragment = matcher.group(1);
			}
		}else if("FROM".equals(fragmentType)){
			pattern = Pattern.compile("FROM(.*?$)");
			matcher = pattern.matcher(statement);
			while (matcher.find()) {
				fragment = matcher.group(1);
			}
			if(null != fragment && fragment.contains(SelectConstitute.WHERE.valueOf())){
				pattern = Pattern.compile("(^.*?)WHERE");
				matcher = pattern.matcher(fragment);
				while (matcher.find()) {
					fragment = matcher.group(1);
				}				
			}
		}else if("WHERE".equals(fragmentType)){

			pattern = Pattern.compile("WHERE(.*?$)");
			matcher = pattern.matcher(statement);
			while (matcher.find()) {
				fragment = matcher.group(1);
			}
			if(null != fragment && fragment.contains(SelectConstitute.ORDER_BY.valueOf())){
				pattern = Pattern.compile("(^.*?)ORDER BY");
				matcher = pattern.matcher(fragment);
				while (matcher.find()) {
					fragment = matcher.group(1);
				}				
			}

		}else if("GROUP_BY".equals(fragmentType)){
			
		}else if("HAVING".equals(fragmentType)){
			
		}else if("ORDER_BY".equals(fragmentType)){
			
		}
		return null != fragment ? fragment.trim() : "";
	}
}
