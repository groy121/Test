package com.cbp.bean;

import java.util.Date;

public class Training {
	private int trainingId;
	private String title;
	private String trainer;
	private int duration;
	private Date stDate;
	private Date edDate;
	
	public int getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public Date getEdDate() {
		return edDate;
	}

	public void setEdDate(Date edDate) {
		this.edDate = edDate;
	}

	@Override
	public String toString() {
		return "Training [trainingId=" + trainingId + ", title=" + title
				+ ", trainer=" + trainer + ", duration=" + duration + ", stDate="
				+ stDate + ", edDate="+edDate+ "]";
	}
}
