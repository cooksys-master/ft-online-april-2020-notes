package com.cooksys.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Entity;

public interface BaseDAO <T extends Entity> {

	PreparedStatement constructCreateStatement(T t) throws SQLException;

	PreparedStatement constructGetByIdStatement(Integer id) throws SQLException;

	PreparedStatement constructGetAllStatement() throws SQLException;

	PreparedStatement constructUpdateStatement(T t) throws SQLException;

	PreparedStatement constructDeleteStatement(Integer id) throws SQLException;

	T constructOne(ResultSet resultSet) throws SQLException;

	List<T> constructAll(ResultSet resultSet) throws SQLException;

	default Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "bondstone");
	}

	default T create(T t) {
		try (Connection connection = getConnection()) {
			PreparedStatement statement = constructCreateStatement(t);
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				t.setId(generatedKeys.getInt(1));
				return t;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	default T getById(Integer id) {
		try (Connection connection = getConnection()) {
			return constructOne(constructGetByIdStatement(id).executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	default List<T> getAll() {
		try (Connection connection = getConnection()) {
			return constructAll(constructGetAllStatement().executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	default boolean update(T t) {
		try (Connection connection = getConnection()) {
			return constructUpdateStatement(t).executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	default boolean delete(Integer id) {
		try (Connection connection = getConnection()) {
			return constructDeleteStatement(id).executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	default boolean delete(T t) {
		try (Connection connection = getConnection()) {
			return constructDeleteStatement(t.getId()).executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
