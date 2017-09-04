package com.infosys.db.migration.hackathon.bean;

import java.util.List;
import java.util.Map;

import com.infosys.db.migration.hackathon.constant.Bucket;

public class QuerySelect extends Query{
	
	public QuerySelect(){
	}
	
	public QuerySelect(String sql){
		super.sql = sql;
	}
	
	private Bucket queryType;
	private List<String> selectColumns;
	private List<String> fromTables;
	
	private String selectFragment;
	private String fromFragment;
	private String whereFragment;
	private String orderByFragment;
	private String groupByFragment;
	private String havingFragment;
	private String insertFragment;
	
	private List<String> orderByColumns;
	private List<String> groupByColumns;
	
	private Map<String, String> whereConditionsMap;
	
	private boolean isAsPresent;
	private boolean isWherePresent;
	private boolean isOrderByPresent;
	private boolean isGroupByPresent;
	private boolean isHavingPresent;
	private boolean isUnionPresent;
	private boolean isUnionAllPresent;
	private boolean isCountPresent;
	private boolean isSumPresent;
	private boolean isAvgPresent;
	private boolean isDistinctPresent;
	private boolean isTopPresent;
	private boolean isAscPresent;
	private boolean isDescPresent;
	private boolean isAnyPresent;
	private boolean isInsertPresent;
	private boolean isInnerJoinPresent;
	private boolean isLeftJoinPresent;
	private boolean isRightJoinPresent;
	private boolean isFullOuterJoinPresent;
	private boolean isNotPresent;
	private boolean isBetweenPresent;
	private boolean isInPresent;
	private boolean isAndPresent;
	private boolean isOrPresent;
	private boolean isLikePresent;
	private boolean isMaxPresent;
	private boolean isMinPresent;
	
	
	public Map<String, String> getWhereConditionsMap() {
		return whereConditionsMap;
	}

	public void setWhereConditionsMap(Map<String, String> whereConditionsMap) {
		this.whereConditionsMap = whereConditionsMap;
	}

	public boolean isMaxPresent() {
		return isMaxPresent;
	}

	public void setMaxPresent(boolean isMaxPresent) {
		this.isMaxPresent = isMaxPresent;
	}

	public boolean isMinPresent() {
		return isMinPresent;
	}

	public void setMinPresent(boolean isMinPresent) {
		this.isMinPresent = isMinPresent;
	}

	public boolean isLikePresent() {
		return isLikePresent;
	}

	public void setLikePresent(boolean isLikePresent) {
		this.isLikePresent = isLikePresent;
	}

	public boolean isOrPresent() {
		return isOrPresent;
	}

	public void setOrPresent(boolean isOrPresent) {
		this.isOrPresent = isOrPresent;
	}

	public boolean isAndPresent() {
		return isAndPresent;
	}

	public void setAndPresent(boolean isAndPresent) {
		this.isAndPresent = isAndPresent;
	}

	public boolean isAsPresent() {
		return isAsPresent;
	}

	public void setAsPresent(boolean isAsPresent) {
		this.isAsPresent = isAsPresent;
	}

	public Bucket getQueryType() {
		return queryType;
	}
	public void setQueryType(Bucket queryType) {
		this.queryType = queryType;
	}
	
	public List<String> getSelectColumns() {
		return selectColumns;
	}

	public void setSelectColumns(List<String> selectColumns) {
		this.selectColumns = selectColumns;
	}

	public List<String> getFromTables() {
		return fromTables;
	}

	public void setFromTables(List<String> fromTables) {
		this.fromTables = fromTables;
	}

	public boolean isWherePresent() {
		return isWherePresent;
	}
	public void setWherePresent(boolean isWherePresent) {
		this.isWherePresent = isWherePresent;
	}
	public boolean isOrderByPresent() {
		return isOrderByPresent;
	}
	public void setOrderByPresent(boolean isOrderByPresent) {
		this.isOrderByPresent = isOrderByPresent;
	}
	public boolean isGroupByPresent() {
		return isGroupByPresent;
	}
	public void setGroupByPresent(boolean isGroupByPresent) {
		this.isGroupByPresent = isGroupByPresent;
	}
	public boolean isHavingPresent() {
		return isHavingPresent;
	}
	public void setHavingPresent(boolean isHavingPresent) {
		this.isHavingPresent = isHavingPresent;
	}
	public boolean isUnionPresent() {
		return isUnionPresent;
	}
	public void setUnionPresent(boolean isUnionPresent) {
		this.isUnionPresent = isUnionPresent;
	}
	public boolean isUnionAllPresent() {
		return isUnionAllPresent;
	}
	public void setUnionAllPresent(boolean isUnionAllPresent) {
		this.isUnionAllPresent = isUnionAllPresent;
	}
	public boolean isCountPresent() {
		return isCountPresent;
	}
	public void setCountPresent(boolean isCountPresent) {
		this.isCountPresent = isCountPresent;
	}
	public boolean isSumPresent() {
		return isSumPresent;
	}
	public void setSumPresent(boolean isSumPresent) {
		this.isSumPresent = isSumPresent;
	}
	public boolean isAvgPresent() {
		return isAvgPresent;
	}
	public void setAvgPresent(boolean isAvgPresent) {
		this.isAvgPresent = isAvgPresent;
	}
	public String getSelectFragment() {
		return selectFragment;
	}
	public void setSelectFragment(String selectFragment) {
		this.selectFragment = selectFragment;
	}
	public String getFromFragment() {
		return fromFragment;
	}
	public void setFromFragment(String fromFragment) {
		this.fromFragment = fromFragment;
	}
	public String getWhereFragment() {
		return whereFragment;
	}
	public void setWhereFragment(String whereFragment) {
		this.whereFragment = whereFragment;
	}
	public String getOrderByFragment() {
		return orderByFragment;
	}
	public void setOrderByFragment(String orderByFragment) {
		this.orderByFragment = orderByFragment;
	}
	public String getGroupByFragment() {
		return groupByFragment;
	}
	public void setGroupByFragment(String groupByFragment) {
		this.groupByFragment = groupByFragment;
	}
	public String getHavingFragment() {
		return havingFragment;
	}
	public void setHavingFragment(String havingFragment) {
		this.havingFragment = havingFragment;
	}
	public List<String> getOrderByColumns() {
		return orderByColumns;
	}
	public void setOrderByColumns(List<String> orderByColumns) {
		this.orderByColumns = orderByColumns;
	}
	public List<String> getGroupByColumns() {
		return groupByColumns;
	}
	public void setGroupByColumns(List<String> groupByColumns) {
		this.groupByColumns = groupByColumns;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public boolean isDistinctPresent() {
		return isDistinctPresent;
	}
	public void setDistinctPresent(boolean isDistinctPresent) {
		this.isDistinctPresent = isDistinctPresent;
	}
	public boolean isTopPresent() {
		return isTopPresent;
	}
	public void setTopPresent(boolean isTopPresent) {
		this.isTopPresent = isTopPresent;
	}
	public boolean isAscPresent() {
		return isAscPresent;
	}
	public void setAscPresent(boolean isAscPresent) {
		this.isAscPresent = isAscPresent;
	}
	public boolean isDescPresent() {
		return isDescPresent;
	}
	public void setDescPresent(boolean isDescPresent) {
		this.isDescPresent = isDescPresent;
	}
	public boolean isAnyPresent() {
		return isAnyPresent;
	}
	public void setAnyPresent(boolean isAnyPresent) {
		this.isAnyPresent = isAnyPresent;
	}
	public boolean isInsertPresent() {
		return isInsertPresent;
	}
	public void setInsertPresent(boolean isInsertPresent) {
		this.isInsertPresent = isInsertPresent;
	}
	public String getInsertFragment() {
		return insertFragment;
	}
	public void setInsertFragment(String insertFragment) {
		this.insertFragment = insertFragment;
	}
	public boolean isInnerJoinPresent() {
		return isInnerJoinPresent;
	}

	public void setInnerJoinPresent(boolean isInnerJoinPresent) {
		this.isInnerJoinPresent = isInnerJoinPresent;
	}

	public boolean isLeftJoinPresent() {
		return isLeftJoinPresent;
	}

	public void setLeftJoinPresent(boolean isLeftJoinPresent) {
		this.isLeftJoinPresent = isLeftJoinPresent;
	}

	public boolean isRightJoinPresent() {
		return isRightJoinPresent;
	}

	public void setRightJoinPresent(boolean isRightJoinPresent) {
		this.isRightJoinPresent = isRightJoinPresent;
	}

	public boolean isFullOuterJoinPresent() {
		return isFullOuterJoinPresent;
	}

	public void setFullOuterJoinPresent(boolean isFullOuterJoinPresent) {
		this.isFullOuterJoinPresent = isFullOuterJoinPresent;
	}

	public boolean isNotPresent() {
		return isNotPresent;
	}

	public void setNotPresent(boolean isNotPresent) {
		this.isNotPresent = isNotPresent;
	}

	public boolean isBetweenPresent() {
		return isBetweenPresent;
	}

	public void setBetweenPresent(boolean isBetweenPresent) {
		this.isBetweenPresent = isBetweenPresent;
	}

	public boolean isInPresent() {
		return isInPresent;
	}

	public void setInPresent(boolean isInPresent) {
		this.isInPresent = isInPresent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((selectColumns == null) ? 0 : selectColumns.hashCode());
		result = prime * result + (isAnyPresent ? 1231 : 1237);
		result = prime * result + (isAsPresent ? 1231 : 1237);
		result = prime * result + (isAscPresent ? 1231 : 1237);
		result = prime * result + (isAvgPresent ? 1231 : 1237);
		result = prime * result + (isBetweenPresent ? 1231 : 1237);
		result = prime * result + (isCountPresent ? 1231 : 1237);
		result = prime * result + (isDescPresent ? 1231 : 1237);
		result = prime * result + (isDistinctPresent ? 1231 : 1237);
		result = prime * result + (isFullOuterJoinPresent ? 1231 : 1237);
		result = prime * result + (isGroupByPresent ? 1231 : 1237);
		result = prime * result + (isHavingPresent ? 1231 : 1237);
		result = prime * result + (isInPresent ? 1231 : 1237);
		result = prime * result + (isInnerJoinPresent ? 1231 : 1237);
		result = prime * result + (isInsertPresent ? 1231 : 1237);
		result = prime * result + (isLeftJoinPresent ? 1231 : 1237);
		result = prime * result + (isNotPresent ? 1231 : 1237);
		result = prime * result + (isOrderByPresent ? 1231 : 1237);
		result = prime * result + (isRightJoinPresent ? 1231 : 1237);
		result = prime * result + (isSumPresent ? 1231 : 1237);
		result = prime * result + (isTopPresent ? 1231 : 1237);
		result = prime * result + (isUnionAllPresent ? 1231 : 1237);
		result = prime * result + (isUnionPresent ? 1231 : 1237);
		result = prime * result + (isWherePresent ? 1231 : 1237);
		result = prime * result + (isAndPresent ? 1231 : 1237);
		result = prime * result + (isLikePresent ? 1231 : 1237);
		result = prime * result + (isMaxPresent ? 1231 : 1237);
		result = prime * result + (isMinPresent ? 1231 : 1237);
		result = prime * result
				+ ((queryType == null) ? 0 : queryType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuerySelect other = (QuerySelect) obj;
		
		if (selectColumns == null 
				|| other.selectColumns == null 
				|| selectColumns.isEmpty()
				|| other.selectColumns.isEmpty() ) {
			return false;
		}
		
		/*if((other.columns.size() == 1 && columns.size() == 1) 
				&& !("*".equals(columns.get(0)) && "*".equals(other.columns.get(0)))){
				return false;
		}*/
		
		if (isAndPresent != other.isAndPresent)
			return false;
		if (isAnyPresent != other.isAnyPresent)
			return false;
		if (isAsPresent != other.isAsPresent)
			return false;
		if (isAscPresent != other.isAscPresent)
			return false;
		if (isAvgPresent != other.isAvgPresent)
			return false;
		if (isBetweenPresent != other.isBetweenPresent)
			return false;
		if (isCountPresent != other.isCountPresent)
			return false;
		if (isDescPresent != other.isDescPresent)
			return false;
		if (isDistinctPresent != other.isDistinctPresent)
			return false;
		if (isFullOuterJoinPresent != other.isFullOuterJoinPresent)
			return false;
		if (isGroupByPresent != other.isGroupByPresent)
			return false;
		if (isHavingPresent != other.isHavingPresent)
			return false;
		if (isInPresent != other.isInPresent)
			return false;
		if (isInnerJoinPresent != other.isInnerJoinPresent)
			return false;
		if (isInsertPresent != other.isInsertPresent)
			return false;
		if (isLeftJoinPresent != other.isLeftJoinPresent)
			return false;
		if (isLikePresent != other.isLikePresent)
			return false;
		if (isMaxPresent != other.isMaxPresent)
			return false;
		if (isMinPresent != other.isMinPresent)
			return false;
		if (isNotPresent != other.isNotPresent)
			return false;
		if (isOrPresent != other.isOrPresent)
			return false;
		if (isOrderByPresent != other.isOrderByPresent)
			return false;
		if (isRightJoinPresent != other.isRightJoinPresent)
			return false;
		if (isSumPresent != other.isSumPresent)
			return false;
		if (isTopPresent != other.isTopPresent)
			return false;
		if (isUnionAllPresent != other.isUnionAllPresent)
			return false;
		if (isUnionPresent != other.isUnionPresent)
			return false;
		if (isWherePresent != other.isWherePresent)
			return false;
		if (queryType != other.queryType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuerySelect [queryType=" + queryType + ", columns=" + selectColumns
				+ ", tables=" + fromTables + ", selectFragment=" + selectFragment
				+ ", fromFragment=" + fromFragment + ", whereFragment="
				+ whereFragment + ", orderByFragment=" + orderByFragment
				+ ", groupByFragment=" + groupByFragment + ", havingFragment="
				+ havingFragment + ", insertFragment=" + insertFragment
				+ ", orderByColumns=" + orderByColumns + ", groupByColumns="
				+ groupByColumns + ", whereConditionsMap=" + whereConditionsMap
				+ ", isAsPresent=" + isAsPresent + ", isWherePresent="
				+ isWherePresent + ", isOrderByPresent=" + isOrderByPresent
				+ ", isGroupByPresent=" + isGroupByPresent
				+ ", isHavingPresent=" + isHavingPresent + ", isUnionPresent="
				+ isUnionPresent + ", isUnionAllPresent=" + isUnionAllPresent
				+ ", isCountPresent=" + isCountPresent + ", isSumPresent="
				+ isSumPresent + ", isAvgPresent=" + isAvgPresent
				+ ", isDistinctPresent=" + isDistinctPresent
				+ ", isTopPresent=" + isTopPresent + ", isAscPresent="
				+ isAscPresent + ", isDescPresent=" + isDescPresent
				+ ", isAnyPresent=" + isAnyPresent + ", isInsertPresent="
				+ isInsertPresent + ", isInnerJoinPresent="
				+ isInnerJoinPresent + ", isLeftJoinPresent="
				+ isLeftJoinPresent + ", isRightJoinPresent="
				+ isRightJoinPresent + ", isFullOuterJoinPresent="
				+ isFullOuterJoinPresent + ", isNotPresent=" + isNotPresent
				+ ", isBetweenPresent=" + isBetweenPresent + ", isInPresent="
				+ isInPresent + ", isAndPresent=" + isAndPresent
				+ ", isOrPresent=" + isOrPresent + ", isLikePresent="
				+ isLikePresent + ", isMaxPresent=" + isMaxPresent
				+ ", isMinPresent=" + isMinPresent + ", sql=" + sql
				+ ", ruleName=" + ruleName + "]";
	}

	}
