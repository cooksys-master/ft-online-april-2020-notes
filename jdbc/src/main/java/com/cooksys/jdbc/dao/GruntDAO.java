package com.cooksys.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entity.Grunt;

public class GruntDAO implements BaseDAO<Grunt> {
	
	private BossDAO bossDAO;
	
	public GruntDAO(BossDAO bossDAO) {
		this.bossDAO = bossDAO;
	}

	@Override
	public PreparedStatement constructCreateStatement(Grunt grunt) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement("INSERT INTO Grunt (name, salary, boss_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, grunt.getName());
		statement.setInt(2, grunt.getSalary());
		statement.setObject(3, grunt.getBoss() != null ? grunt.getBoss().getId() : null, Types.INTEGER);
		return statement;
	}

	@Override
	public PreparedStatement constructGetByIdStatement(Integer id) throws SQLException {
		return getConnection().prepareStatement("SELECT * FROM Grunt WHERE id = " + id);
	}
	
	@Override
	public PreparedStatement constructGetAllStatement() throws SQLException {
		return getConnection().prepareStatement("SELECT * FROM Grunt");
	}

	@Override
	public PreparedStatement constructUpdateStatement(Grunt Grunt) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement("UPDATE Grunt SET name = ?, salary = ?, boss_id = ? WHERE id = ?");
		statement.setString(1, Grunt.getName());
		statement.setInt(2, Grunt.getSalary());
		statement.setObject(3, Grunt.getBoss() != null ? Grunt.getBoss().getId() : null, Types.INTEGER);
		statement.setInt(4, Grunt.getId());
		return statement;
	}

	@Override
	public PreparedStatement constructDeleteStatement(Integer id) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement("DELETE FROM Grunt WHERE id = ?");
		statement.setInt(1, id);
		return statement;
	}

	@Override
	public Grunt constructOne(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			return new Grunt(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("salary"), bossDAO.getById(resultSet.getInt("Grunt_id")));
		}
		return null;
	}

	@Override
	public List<Grunt> constructAll(ResultSet resultSet) throws SQLException {
		List<Grunt> result = new ArrayList<>();
		while (resultSet.next()) {
			result.add(new Grunt(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("salary"), bossDAO.getById(resultSet.getInt("Grunt_id"))));
		}
		return result;
	}

}
