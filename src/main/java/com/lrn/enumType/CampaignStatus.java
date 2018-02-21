package com.lrn.enumType;


public class CampaignStatus{ 
	public enum CampaignStatusEnum {
		 PLANNING(1, "Draft"),
	      SCHEDULED(2, "Scheduled"),
	      STARTED(3, "Started"),
	      PAST_DUE(4, "Past Due"),
	      CLOSED(5, "Closed"),
	      //CANCELED(6, "Canceled"),
	      ABORTED(7, "Aborted");

	    private final int val;
	    private final String label;

	    private CampaignStatusEnum(int val, String label) {
	      this.val = val;
	      this.label = label;
	    }

	    public int getVal() { return val; }
	    public String getLabel() { return label; }	
	
	}
	
	 public enum Status {
		 PLANNING(1),
		 SCHEDULED(2),
		 ABORTED(3);

		 private final int val;
		 
		 private Status(int val) {
			 this.val = val;
		 }
		 
		 public int getVal() { return val; }
		 public String getLabel() { return toString(); }
		 public static Status forVal(int val) { 
			 for (Status item : values()) {
			      if (item.getVal() == val) return item;
			 }
		return null;  }
	 }
}