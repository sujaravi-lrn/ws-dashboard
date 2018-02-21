package com.lrn.enumType;

public class ChartSelectionColumns{ 
	public enum ChartSelectionColumnsEnum {
		
		CATALOG_ID("BASE_CATALOG_ID", "Catalog ID","Module Report Columns", "FACT_MODULE_STATUS"),
	    MODULE_TITLE("MODULE_TITLE", "Module Title","Module Report Columns", "FACT_MODULE_STATUS");
	   
	    private final String columnName;
	    private final String columnDisplayName;
	    private final String section;
	    private final String tableRef;
	
	    private ChartSelectionColumnsEnum(String columnName, String columnDisplayName, String section, String tableRef) {
	    	this.columnName = columnName;
	    	this.columnDisplayName = columnDisplayName;
	    	this.section = section;
	    	this.tableRef = tableRef;
	    }
	    
	    public String getColumnName() { return columnName; }
	    public String getColumnDisplayName() { return columnDisplayName; }
	    public String getSection() { return section; }
	    public String getTableRef() { return tableRef; }
	}
	
	    
}
