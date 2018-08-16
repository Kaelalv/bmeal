package com.bm.model;

public class Value {
	private Integer value_id;
	private String foodname;
	private String foodimg;
	private String foodvalue;
	private String valuepublish;
	
	public Integer getValue_id() {
		return value_id;
	}
	public void setValue_id(Integer value_id) {
		this.value_id = value_id;
	}
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	
	public String getFoodimg() {
		return foodimg;
	}
	public void setFoodimg(String foodimg) {
		this.foodimg = foodimg;
	}
	public String getFoodvalue() {
		return foodvalue;
	}
	public void setFoodvalue(String foodvalue) {
		this.foodvalue = foodvalue;
	}
	public String getValuepublish() {
		return valuepublish;
	}
	public void setValuepublish(String valuepublish) {
		this.valuepublish = valuepublish;
	}
	
}
