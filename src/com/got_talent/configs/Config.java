package com.got_talent.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

	private String url;
	private String username;
	private String password;

	public Config(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Config() {
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	
	@Override
	public String toString() {
		return "Config [url=" + url + ", username=" + username + ", password=" + password + "]";
	}

	public Connection connection() throws SQLException {
		Connection connection = DriverManager.getConnection(this.url, this.username, this.password);
		return connection;
	}

}
