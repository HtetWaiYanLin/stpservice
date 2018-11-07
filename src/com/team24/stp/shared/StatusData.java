package com.team24.stp.shared;

public class StatusData {

	private String label;

	private int data[];

	public StatusData() {
		super();
		clearProperties();
	}

	

	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	public int[] getData() {
		return data;
	}



	public void setData(int[] data) {
		this.data = data;
	}



	protected void clearProperties() {
		this.label = "";
		this.data = new int[0];
	}

}
