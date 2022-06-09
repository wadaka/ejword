package model;

import java.io.Serializable;
import java.util.List;

public class EJWord implements Serializable{
	private String searchWord="";
	private String mode="";
	private int pageNo;
	private int total;
	private int limit;
	private List<Word> list=null;
	private String[][] pager=null;
	public EJWord() {}
	public EJWord(String searchWord, String mode, int pageNo,int limit) {
		this.searchWord = searchWord;
		this.mode = mode;
		this.pageNo = pageNo;
		this.limit=limit;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String[][] getPager() {
		return pager;
	}
	public void setPager(String[][] pager) {
		this.pager = pager;
	}
	public List<Word> getList() {
		return list;
	}
	public void setList(List<Word> list) {
		this.list = list;
	}
}
