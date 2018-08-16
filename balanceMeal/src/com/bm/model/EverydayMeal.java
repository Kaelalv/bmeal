package com.bm.model;

import java.util.Date;

public class EverydayMeal {
	private Integer id;
	private String mealtime;
	private String mealtit;
	private String mainingredient;
	private String mealimg;
	private String condiment;
	private String mealintro;
	private String mealpublish;
	private String mealzan;
	
	public String getMealzan() {
		return mealzan;
	}
	public void setMealzan(String mealzan) {
		this.mealzan = mealzan;
	}
	
	
	public String getMealpublish() {
		return mealpublish;
	}
	public void setMealpublish(String mealpublish) {
		this.mealpublish = mealpublish;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMealtime() {
		return mealtime;
	}
	public void setMealtime(String mealtime) {
		this.mealtime = mealtime;
	}
	public String getMealtit() {
		return mealtit;
	}
	public void setMealtit(String mealtit) {
		this.mealtit = mealtit;
	}
	public String getMainingredient() {
		return mainingredient;
	}
	public void setMainingredient(String mainingredient) {
		this.mainingredient = mainingredient;
	}
	public String getMealimg() {
		return mealimg;
	}
	public void setMealimg(String mealimg) {
		this.mealimg = mealimg;
	}
	public String getCondiment() {
		return condiment;
	}
	public void setCondiment(String condiment) {
		this.condiment = condiment;
	}
	public String getMealintro() {
		return mealintro;
	}
	public void setMealintro(String mealintro) {
		this.mealintro = mealintro;
	}
	
}
