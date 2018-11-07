package com.team24.stp.shared;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PagerData {

	private int current;
	private int prev;
	private int last;
	private int next;
	private int start;
	private int end;
	private int size;
	private int totalcount;
	private String t1;//searchVel
	private String t2;//userid
	private int n1;
	private String userid;
	private long changestatus;

	public PagerData() {
		super();
		clearProperties();
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	
	public long getChangestatus() {
		return changestatus;
	}

	public void setChangestatus(long changestatus) {
		this.changestatus = changestatus;
	}

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getT2() {
		return t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public void clearProperties() {
		this.current = 1;
		this.prev = 1;
		this.last = 1;
		this.next = 2;
		this.start = 1;
		this.end = 10;
		this.size = 10;
		this.totalcount = 1;
		this.t1="";
		this.t2="";
		this.n1=0;
		this.changestatus=0;
		this.userid = "";
	}

}
