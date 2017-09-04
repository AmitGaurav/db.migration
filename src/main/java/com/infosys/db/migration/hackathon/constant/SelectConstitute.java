package com.infosys.db.migration.hackathon.constant;

public enum SelectConstitute {
	SELECT("SELECT"),
	FROM("FROM"),
	WHERE("WHERE"),
	DISTINCT("DISTINCT"),
	ORDER_BY("ORDER BY"),
	HAVING("HAVING"),
	SUM("SUM"),
	AVG("AVG"),
	COUNT("COUNT"),
	MAX("MAX"),
	MIN("IN"),
	GROUP_BY("GROUP BY"),
	UNION("UNION"),
	UNION_ALL("UNION ALL"),
	TOP("TOP"),
	ASC("ASC"),
	DESC("DESC"),
	INSERT("INSERT"),
	ANY(" ANY "),
	INNER_JOIN("INNER JOIN"),
	LEFT_JOIN("LEFT JOIN"),
	RIGHT_JOIN("RIGHT JOIN"),
	FULL_OUTER_JOIN("FULL OUTER JOIN"),
	AS("AS"),
	IN("IN"),
	NOT("NOT"),
	BETWEEN("BETWEEN"),
	AND("AND"),
	OR("OR"),
	LIKE("LIKE"),
	ROWNUM("ROWNUM"),
	LIMIT("LIMIT");
	
	private String syntax;
	private SelectConstitute(String s) {
		syntax = s;
	}
	
	public String valueOf() {
		return syntax;
	} 
}
