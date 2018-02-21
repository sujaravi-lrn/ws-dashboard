package com.lrn.dto;

import java.io.Serializable;
import java.util.Date;

/**  
* DisplayDimCurriculumDTO - data transfer object class for Displaying campaigns 
* as well as those campaigns which are selected
* 
*/

public class DisplayDimCurriculumDTO implements Serializable {

	private Long id;
	private String name;
	private Date openDate;	
	private String status;
	
	private CampaignSelectionDTO campaignSelectionDTO;

	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the openDate
	 */
	public Date getOpenDate() {
		return openDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param openDate the openDate to set
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	/**
	 * @return the campaignSelectionDTO
	 */
	public CampaignSelectionDTO getCampaignSelectionDTO() {
		return campaignSelectionDTO;
	}

	/**
	 * @param campaignSelectionDTO the campaignSelectionDTO to set
	 */
	public void setCampaignSelectionDTO(CampaignSelectionDTO campaignSelectionDTO) {
		this.campaignSelectionDTO = campaignSelectionDTO;
	}

	

}
