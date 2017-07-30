package org.com.cay.springmvc.tag;

import java.util.List;
import java.util.Map;

public class SpringMVCTags {

	private String username;
	private String password;
	private boolean testBoolean;
	private String[] selectArray;
	private String[] testArray;
	private Integer radiobuttonId;
	private Integer selectId;
	private List<Integer> selectIds;
	private Map<Integer, String> testMap;
	private String remark;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTestBoolean() {
		return testBoolean;
	}

	public void setTestBoolean(boolean testBoolean) {
		this.testBoolean = testBoolean;
	}

	public String[] getSelectArray() {
		return selectArray;
	}

	public void setSelectArray(String[] selectArray) {
		this.selectArray = selectArray;
	}

	public String[] getTestArray() {
		return testArray;
	}

	public void setTestArray(String[] testArray) {
		this.testArray = testArray;
	}

	public Integer getRadiobuttonId() {
		return radiobuttonId;
	}

	public void setRadiobuttonId(Integer radiobuttonId) {
		this.radiobuttonId = radiobuttonId;
	}

	public Integer getSelectId() {
		return selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}

	public List<Integer> getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(List<Integer> selectIds) {
		this.selectIds = selectIds;
	}

	public Map<Integer, String> getTestMap() {
		return testMap;
	}

	public void setTestMap(Map<Integer, String> testMap) {
		this.testMap = testMap;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
