package com.cooksys.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entity.Boss;
import entity.Entity;

public class BossDAO implements BaseDAO<Boss> {

	@Override
	public PreparedStatement constructCreateStatement(Boss boss) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement(
				"INSERT INTO Boss (name, salary, boss_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, boss.getName());
		statement.setInt(2, boss.getSalary());
		statement.setObject(3, boss.getBoss() != null ? boss.getBoss().getId() : null, Types.INTEGER);
		return statement;
	}

	@Override
	public PreparedStatement constructGetByIdStatement(Integer id) throws SQLException {
		return getConnection().prepareStatement("SELECT * FROM Boss WHERE id = " + id);
	}

	@Override
	public PreparedStatement constructGetAllStatement() throws SQLException {
		return getConnection().prepareStatement("SELECT * FROM Boss");
	}

	@Override
	public PreparedStatement constructUpdateStatement(Boss boss) throws SQLException {
		PreparedStatement statement = getConnection()
				.prepareStatement("UPDATE Boss SET name = ?, salary = ?, boss_id = ? WHERE id = ?");
		statement.setString(1, boss.getName());
		statement.setInt(2, boss.getSalary());
		statement.setObject(3, boss.getBoss() != null ? boss.getBoss().getId() : null, Types.INTEGER);
		statement.setInt(4, boss.getId());
		return statement;
	}

	@Override
	public PreparedStatement constructDeleteStatement(Integer id) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement("DELETE FROM Boss WHERE id = ?");
		statement.setInt(1, id);
		return statement;
	}

	@Override
	public Boss constructOne(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			return new Boss(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("salary"),
					getById(resultSet.getInt("boss_id")));
		}
		return null;
	}

	@Override
	public List<Boss> constructAll(ResultSet resultSet) throws SQLException {
		List<Boss> result = new ArrayList<>();
		while (resultSet.next()) {
			result.add(new Boss(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("salary"),
					getById(resultSet.getInt("boss_id"))));
		}
		return result;
	}
	
	public List<String> getChildren(Boss boss) {
		List<String> result = new ArrayList<>();
		try (Connection connection = getConnection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT name FROM Boss WHERE boss_id = ? UNION SELECT name FROM Grunt WHERE boss_id = ?");
			statement.setInt(1, boss.getId());
			statement.setInt(2, boss.getId());
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				result.add(resultSet.getString("name"));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}

//	public static Boss createBoss(Boss boss) {
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("INSERT INTO Boss (name, salary, boss_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//			statement.setString(1, boss.getName());
//			statement.setInt(2, boss.getSalary());
//			statement.setObject(3, boss.getBoss() != null ? boss.getBoss().getId() : null, Types.INTEGER);
//			statement.executeUpdate();
//			ResultSet generatedKeys = statement.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				boss.setId(generatedKeys.getInt(1));
//				return boss;
//			}
//			return null;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public static Boss getBossById(Integer id) {
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Boss WHERE id = " + id);
//			ResultSet resultSet = statement.executeQuery();
//			if (resultSet.next()) {
//				return new Boss(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("salary"), getBossById(resultSet.getInt("boss_id")));
//			}
//			return null;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public static List<Boss> getBosses() {
//		List<Boss> result = new ArrayList<>();
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Boss");
//			ResultSet resultSet = statement.executeQuery();
//			while (resultSet.next()) {
//				result.add(new Boss(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("salary"), getBossById(resultSet.getInt("boss_id"))));
//			}
//			return result;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return result;
//		}
//	}
//	
//	public static boolean updateBoss(Boss boss) {
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("UPDATE Boss SET name = ?, salary = ?, boss_id = ? WHERE id = ?");
//			statement.setString(1, boss.getName());
//			statement.setInt(2, boss.getSalary());
//			statement.setObject(3, boss.getBoss() != null ? boss.getBoss().getId() : null, Types.INTEGER);
//			statement.setInt(4, boss.getId());
//			int affectedRows = statement.executeUpdate();
//			return affectedRows > 0;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	public static boolean deleteBoss(Integer id) {
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("DELETE FROM Boss WHERE id = ?");
//			statement.setInt(1, id);
//			int affectedRows = statement.executeUpdate();
//			return affectedRows > 0;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	public static boolean deleteBoss(Boss boss) {
//		try (Connection connection = getConnection()) {
//			PreparedStatement statement = connection.prepareStatement("DELETE FROM Boss WHERE id = ?");
//			statement.setInt(1, boss.getId());
//			int affectedRows = statement.executeUpdate();
//			return affectedRows > 0;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

}
