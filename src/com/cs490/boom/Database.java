package com.cs490.boom;

import java.sql.*;
import java.util.*;

public class Database {

	// connection
	static Connection mycon;
	static Statement mystat;

	// construction
	public Database() {
		String user = "root";
		String password = "wly12345";
		try {
			mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/boom_database", user, password);
			mystat = mycon.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// add_item
	public static void add(Music mu) {
		try {
			String sql = "insert into boom " + " (name, path , preference)" + " values('" + mu.getName() + "' , '"
					+ mu.getPath() + "' ," + mu.getPreference() + ")";
			System.out.println(sql);
			mystat.executeUpdate(sql);
			String tableName = mu.getName() + "_point";
			sql = "create table " + tableName
					+ "("
					+ "start INTEGER, "
					+ "duration INTEGER, "
					+ "tag INTEGER, "
					+ "preference INTEGER, "
					+ "primary key(start))";
			mystat.executeUpdate(sql);
			for (Point p : mu.points) {
				sql = "insert into " + tableName + 
						"(start, duration, tag, preference) " +
						"value( " + p.start +", " + p.duration + ", " + p.tag + ", " + p.preference
						+")";
				mystat.executeUpdate(sql);
			}
			System.out.println("add item complete");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("item add failed");
		}
	}

	// delete_item
	public static void delete_name(String name) {
		int row = 0;
		try {
			String sql = "delete from boom where name ='" + name + "'";
			row = mystat.executeUpdate(sql);
			sql = "DROP TABLE " + name + "_point";
			mystat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (row == 0) {
			System.out.println("delete imcomplete");
		} else {
			System.out.println("delete complete");
		}
	}

	// get list
	public static ArrayList<String> getlist() {
		ArrayList<String> lists = new ArrayList<String>();
		try {
			String sql;
			sql = "SELECT name FROM boom";
			ResultSet rs = mystat.executeQuery(sql);

			while (rs.next()) {
				lists.add(rs.getString("name"));
			}
			System.out.println("getMusiclist complete");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	// get item
	public static Music getrow(String name) {
		Music m = new Music("dafaw", "abfc", 10);
		try {
			String sql;
			sql = "SELECT * FROM boom where name= '" + name + "'";
			ResultSet rs = mystat.executeQuery(sql);

			while (rs.next()) {
				m.setName(rs.getString("name"));
				m.setPath(rs.getString("path"));
				m.setPreference(rs.getInt("preference"));
			}
			
			sql = "SELECT * FROM "+ name +"_point";
			rs = mystat.executeQuery(sql);
			
			while (rs.next()) {
				Point p = new Point(rs.getInt("start"),rs.getInt("duration"),
						rs.getInt("tag"),rs.getInt("preference"));
				m.points.add(p);
				//System.out.println(m.points.get(0));
			}
			//System.out.println(m.points.get(1));
			
			System.out.println(" row return success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}


}
