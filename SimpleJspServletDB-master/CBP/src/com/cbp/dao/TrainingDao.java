package com.cbp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cbp.bean.Training;

import co.cbp.util.DbUtil;

public class TrainingDao {

	private Connection connection;

	public TrainingDao() {
		connection = DbUtil.getConnection();
	}

	public void addTraining(Training training) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into trainings(trainingId,title,trainer,duration,stDate,edDate) values (?, ?, ?, ?, ?, ? )");
			preparedStatement.setInt(1, training.getTrainingId());
			preparedStatement.setString(2, training.getTitle());
			preparedStatement.setString(3, training.getTrainer());
			preparedStatement.setInt(4, training.getDuration());
			preparedStatement.setDate(5, new java.sql.Date(training.getStDate().getTime()));
			preparedStatement.setDate(6, new java.sql.Date(training.getEdDate().getTime()));
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTraining(int trainingId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from trainings where trainingId=?");
			preparedStatement.setInt(1, trainingId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTraining(Training training) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update trainings set title=?, trainer=?, duration=?, stDate=?, edDate=?" +
							"where trainingId=?");
			preparedStatement.setString(1, training.getTitle());
			preparedStatement.setString(2, training.getTrainer());
			preparedStatement.setInt(3, training.getDuration());
			preparedStatement.setDate(4, new java.sql.Date(training.getStDate().getTime()));
			preparedStatement.setDate(5, new java.sql.Date(training.getEdDate().getTime()));
			preparedStatement.setInt(6, training.getTrainingId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Training> getAllTrainings() {
		List<Training> trainings = new ArrayList<Training>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from trainings");
			while (rs.next()) {
				Training training = new Training();
				training.setTrainingId(rs.getInt("trainingId"));
				training.setTitle(rs.getString("title"));
				training.setTrainer(rs.getString("trainer"));
				training.setDuration(rs.getInt("duration"));
				training.setStDate(rs.getDate("stDate"));
				training.setEdDate(rs.getDate("edDate"));
				trainings.add(training);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return trainings;
	}
	
	public Training getTrainingById(int trainingId) {
		Training training = new Training();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select * from trainings where trainingId=?");
			preparedStatement.setInt(1, trainingId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				training.setTrainingId(rs.getInt("trainingId"));
				training.setTitle(rs.getString("title"));
				training.setTrainer(rs.getString("trainer"));
				training.setDuration(rs.getInt("duration"));
				training.setStDate(rs.getDate("stDate"));
				training.setEdDate(rs.getDate("edDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return training;
	}
}
