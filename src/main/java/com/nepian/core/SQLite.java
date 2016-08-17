package com.nepian.core;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

import com.nepian.core.utils.FileUtil;

public class SQLite {
	private File file;
	private Connection connection;
	
	public SQLite(File file) {
		this.file = file;
		this.connection = connect();
	}
	
	public SQLite(JavaPlugin plugin, String fileName) {
		this(FileUtil.loadFile(plugin.getDataFolder(), fileName));
	}
	
	/**
	 * データベースと接続する
	 * @return
	 */
	private Connection connect() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + file);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * データベースから切断する
	 */
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * PreparedStatement を取得する
	 * @param token
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String token) throws SQLException {
		return connection.prepareStatement(token);
	}
	
	/**
	 * Connection を取得する
	 * @return
	 */
	public Connection getConnection() {
		return connection;
	}
}