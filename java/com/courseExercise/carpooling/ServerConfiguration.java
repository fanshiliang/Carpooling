package com.courseExercise.carpooling;

public class ServerConfiguration {

	private String schema = "http";
	private String host = "localhost";
	private int port = 12306;
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
