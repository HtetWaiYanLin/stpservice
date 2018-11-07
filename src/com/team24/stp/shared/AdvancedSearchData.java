package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdvancedSearchData {

	private PagerData pager;
	
	public AdvancedSearchData() {
		super();
		clearProperties();
	}

	public PagerData getPager() {
		return pager;
	}

	public void setPager(PagerData pager) {
		this.pager = pager;
	}

	public void clearProperties() {
		pager = new PagerData();
	}

}
