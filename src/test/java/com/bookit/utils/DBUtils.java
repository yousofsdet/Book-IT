package com.bookit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DBUtils {
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	CommonSeleniumUtils utils = new CommonSeleniumUtils();
	
	public void createConnection() {
		String url = ConfigurationReader.getProperty("dburl"+ConfigurationReader.getProperty("environment"));
		//connect to the data base
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void destroy() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param query
	 * @return returns a single cell value. If the results in multiple rows and/or
	 *         columns of data, only first column of the first row will be returned.
	 *         The rest of the data will be ignored
	 */
	public static Object getCellValue(String query) {
		return getQueryResultList(query).get(0).get(0);
	}

	/**
	 * 
	 * @param query
	 * @return returns a list of Strings which represent a row of data. If the query
	 *         results in multiple rows and/or columns of data, only first row will
	 *         be returned. The rest of the data will be ignored
	 */
	public static List<Object> getRowList(String query) {
		return getQueryResultList(query).get(0);
	}

	/**
	 * 
	 * @param query
	 * @return returns a map which represent a row of data where key is the column
	 *         name. If the query results in multiple rows and/or columns of data,
	 *         only first row will be returned. The rest of the data will be ignored
	 */
	public static Map<String, Object> getRowMap(String query) {
		return getQueryResultMap(query).get(0);
	}

	/**
	 * 
	 * @param query
	 * @return returns query result in a list of lists where outer list represents
	 *         collection of rows and inner lists represent a single row
	 */
	public static List<List<Object>> getQueryResultList(String query) {
		executeQuery(query);
		List<List<Object>> rowList = new ArrayList<>();
		ResultSetMetaData rsmd;

		try {
			rsmd = resultSet.getMetaData();
			while (resultSet.next()) {
				List<Object> row = new ArrayList<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					row.add(resultSet.getObject(i));
				}

				rowList.add(row);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowList;

	}

	/**
	 * 
	 * @param query
	 * @param column
	 * @return list of values of a single column from the result set
	 */
	public static List<Object> getColumnData(String query, String column) {
		executeQuery(query);
		List<Object> rowList = new ArrayList<>();
		ResultSetMetaData rsmd;

		try {
			rsmd = resultSet.getMetaData();
			while (resultSet.next()) {
				rowList.add(resultSet.getObject(column));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowList;

	}

	/**
	 * 
	 * @param query
	 * @return returns query result in a list of maps where the list represents
	 *         collection of rows and a map represents represent a single row with
	 *         key being the column name
	 */
	public static List<Map<String, Object>> getQueryResultMap(String query) {
		executeQuery(query);
		List<Map<String, Object>> rowList = new ArrayList<>();
		ResultSetMetaData rsmd;

		try {
			rsmd = resultSet.getMetaData();

			while (resultSet.next()) {

				Map<String, Object> colNameValueMap = new HashMap<>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {

					colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
				}

				rowList.add(colNameValueMap);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowList;

	}

	/**
	 * 
	 * @param query
	 * @return List of columns returned in result set
	 */
	public static List<String> getColumnNames(String query) {
		executeQuery(query);
		List<String> columns = new ArrayList<>();
		ResultSetMetaData rsmd;

		try {
			rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				columns.add(rsmd.getColumnName(i));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return columns;

	}

	private static void executeQuery(String query) {
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static String getStringData(String query) {
		executeQuery(query);
		String data="";
		try {
			resultSet.next();
			data = resultSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public static int getRowCount() throws Exception {

		resultSet.last();

		int rowCount = resultSet.getRow();
		return rowCount;

	}
	
	public boolean verifyConferenceHasBeenCraeted(String firstName, String lastName, String date) {
		//format date to the appropriate format 
		date = utils.convertDateFormat("MMMM dd, yyyy", "yyyy-MM-dd", date).trim();
		//query to check if conference exists for this student based on name and date
		String query = "select count(*) > 0 \n" + 
				"from conference\n" + 
				"where reservator_id = (select id from users where firstname ='"+firstName+"' and lastname = '"+lastName+"') and date = '"+date+"';";	
		System.out.println(query);
		executeQuery(query);
		boolean isExists = false;
		try {
			resultSet.next();
			isExists = resultSet.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return isExists;
	}
	
	public boolean verifyCalendarEventWasCraeted(String name) {
		//format date to the appropriate format 
		//query to check if conference exists for this student based on name and date
		String query = "select count(*) > 0 from calendar_event where name = '"+name+"'";	
		System.out.println(query);
		executeQuery(query);
		boolean isExists = false;
		try {
			resultSet.next();
			isExists = resultSet.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return isExists;
	}
	
	
	
	
	
	

}
