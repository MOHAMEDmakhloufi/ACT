package org.fsb.act.models;

public class TabModel {
	
	private String elementList;
	private String tabTitle;
	private String tabContent;
	
	public TabModel(String elementList, String tabTitle, String tabContent) {
		this.elementList = elementList;
		this.tabTitle = tabTitle;
		this.tabContent = tabContent;
	}
	
	public TabModel() {
	}
	
	public String getElementList() {
		return elementList;
	}
	public void setElementList(String elementList) {
		this.elementList = elementList;
	}
	public String getTabTitle() {
		return tabTitle;
	}
	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}
	public String getTabContent() {
		return tabContent;
	}
	public void setTabContent(String tabContent) {
		this.tabContent = tabContent;
	}
	
}
