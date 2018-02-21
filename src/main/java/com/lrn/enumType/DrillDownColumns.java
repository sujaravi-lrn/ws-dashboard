package com.lrn.enumType;

public class DrillDownColumns{ 
	public enum DrillDownDetailsColumnsEnum {
		
		CATALOG_ID("BASE_CATALOG_ID", "Catalog ID","Module Report Columns", "FACT_MODULE_STATUS",false),
	    MODULE_TITLE("MODULE_TITLE", "Module Title","Module Report Columns", "FACT_MODULE_STATUS",false),
	    MODULE_LANGUAGE("MODULE_LANGUAGE", "Module Language","Module Report Columns","FACT_MODULE_STATUS",false), 
	    MODULE_STATUS("MODULE_STATUS", "Module Status","Module Report Columns", "FACT_MODULE_STATUS",false),
	    MODULE_STATUS_DETAIL("MODULE_STATUS_DETAIL", "Module Status Detail","Module Report Columns", "FACT_MODULE_STATUS",false),
	    ASSIGNMENT_DATE("ASSIGNMENT_DATE", "Assignment Date","Module Report Columns", "FACT_MODULE_STATUS",true),
	    COMPLETION_DATE("COMPLETION_DATE", "Completion Date/Time (PT)","Module Report Columns", "FACT_MODULE_STATUS",true),
	    DUE_DATE("DUE_DATE", "Due Date","Module Report Columns", "FACT_MODULE_STATUS",true),
	    DAYS_PAST_DUE("DAYS_PAST_DUE", "Days Past Due","Module Report Columns", "DAYS_PAST_DUE",false),
	    ASSIGNMENT_TYPE("ASSIGNMENT_TYPE", "Assignment Type","Module Report Columns", "FACT_MODULE_STATUS",false),
	    TIME_SPENT("TIME_SPENT", "Time Spent","Module Report Columns", "FACT_MODULE_STATUS",false);
	
	    private final String columnName;
	    private final String columnDisplayName;
	    private final String section;
	    private final String tableRef;
	    private final boolean isDateColumn;
	
	    private DrillDownDetailsColumnsEnum(String columnName, String columnDisplayName, String section, String tableRef, boolean isDateColumn) {
	    	this.columnName = columnName;
	    	this.columnDisplayName = columnDisplayName;
	    	this.section = section;
	    	this.tableRef = tableRef;
	    	this.isDateColumn = isDateColumn;
	    }
	    
	    public String getColumnName() { return columnName; }
	    public String getColumnDisplayName() { return columnDisplayName; }
	    public String getSection() { return section; }	    
	    public String getTableRef() { return tableRef; }
	    public boolean isDateColumn() { return isDateColumn; }
	    
	    public static boolean isDateColumn(String columnName) {
	    	for(DrillDownDetailsColumnsEnum drillDownDetailsColumnsEnum: values()){
	    		if (drillDownDetailsColumnsEnum.getColumnName().equalsIgnoreCase(columnName)) {
	                return drillDownDetailsColumnsEnum.isDateColumn();
	            }
	    	}
	    	
	    	return false;
	    }
	}
	
	public enum DrillDownSummaryColumnsEnum {
		
		COMPLETE_COUNT("COMPLETE_COUNT", "# Modules Total Complete","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),
		COMPLETE_ON_TIME("COMPLETE_ON_TIME", "# Modules Complete:On Time","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),
		COMPLETE_PAST_DUE("COMPLETE_PAST_DUE", "# Modules Complete:Past Due","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),		
		COMPLETE_NO_DUE_DATE("COMPLETE_NO_DUE_DATE", "# Modules Complete:No Due Date","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),
		TOTAL_INCOMPLETE("INCOMPLETE_COUNT", "# Modules Total Incomplete","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_STATUS"),		
	    NOT_STARTED_NOT_DUE_YET("NOT_STARTED_NOT_DUE_YET", "# Modules Not Started:Not Due Yet","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),	    
	    NOT_STARTED_PAST_DUE("NOT_STARTED_PAST_DUE", "# Modules Not Started:Past Due","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),
	    NOT_STARTED_NO_DUE_DATE("NOT_STARTED_NO_DUE_DATE", "# Modules Not Started:No Due Date", "Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),
	    IN_PROCESS_NOT_DUE_YET("IN_PROCESS_NOT_DUE_YET", "# Modules In Progress:Not Due Yet","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),
	    IN_PROCESS_PAST_DUE("IN_PROCESS_PAST_DUE", "# Modules In Progress:Past Due","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL"),
	    IN_PROCESS_NO_DUE_DATE("IN_PROCESS_NO_DUE_DATE", "# Modules In Progress:No Due Date","Module Report Columns", "Drill Down Summary","FACT_ASSIGNMENT_DETAIL");
		
		
	    private final String columnName;
	    private final String columnDisplayName;
	    private final String section;
	    private final String getfactAssignmentTableRef;
	
	    private DrillDownSummaryColumnsEnum(String columnName, String columnDisplayName, String section, String partOfReport,String getfactAssignmentTableRef) {
	    	this.columnName = columnName;
	    	this.columnDisplayName = columnDisplayName;
	    	this.section = section;
	    	this.getfactAssignmentTableRef=getfactAssignmentTableRef;
	    }
	    
	    public String getColumnName() { return columnName; }
	    public String getColumnDisplayName() { return columnDisplayName; }
	    public String getSection() { return section; }
	    public String getfactAssignmentTableRef() { return getfactAssignmentTableRef; }
	
	    
	}
	
	
	
}