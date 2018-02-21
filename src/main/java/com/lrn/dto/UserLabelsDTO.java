package com.lrn.dto;

import java.io.Serializable;
import java.util.List;

/**  
* UserLabelsDTO - data transfer object class for list of User Label. 
* It stores the data for the list of User Label. 
* 
*/

public class UserLabelsDTO implements Serializable {

	private static final long serialVersionUID = 1501851520350617333L;
	
	private List<UserLabelDTO> userLabelList;

	/** Gets the list of user labels
	 * 
	 * @return the userLabelList, holds list of {@link UserLabelDTO}
	 */
	public List<UserLabelDTO> getUserLabelList() {
		return userLabelList;
	}
	
	/** Sets the list of user labels
	 * 
	 * @param userLabelList the userLabelList - list of {@link UserLabelDTO} to set
	 */
	public void setUserLabelList(List<UserLabelDTO> userLabelList) {
		this.userLabelList = userLabelList;
	}
}
