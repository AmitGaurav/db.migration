package com.infosys.db.migration.hackathon.bean;

public class Query {

	protected String sql;
	protected String ruleName;
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getSql() {
		return sql;
	}

	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
}
