package com.bookit.utils;

public class ConfigFileReader {
	public String getReportConfigPath() {
		String reportConfigPath = System.getProperty("user.dir")+ConfigurationReader.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
}
