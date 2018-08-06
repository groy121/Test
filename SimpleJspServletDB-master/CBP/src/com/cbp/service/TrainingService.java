package com.cbp.service;

import java.util.List;

import com.cbp.bean.Training;
import com.cbp.dao.TrainingDao;

public class TrainingService {
	public void addTraining(Training training) {
		new TrainingDao().addTraining(training);
	}
	
	public void deleteTraining(int trainingId) {
		new TrainingDao().deleteTraining(trainingId);
	}
	
	public void updateTraining(Training training) {
		new TrainingDao().updateTraining(training);
	}
	
	public List<Training> getAllTrainings() {
		return new TrainingDao().getAllTrainings();
	}
	
	public Training getTrainingById(int trainingId) {
		return new TrainingDao().getTrainingById(trainingId);
	}
	
}
