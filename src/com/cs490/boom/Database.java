package com.cs490.boom;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    // connection
    public static Connection mycon;
    public static Statement mystat;
    public static Database database;
    // construction

    public Database() {
        /*
        String user = "root";
        String password = "123456";
        try {
            mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/boom_database", user, password);
            mystat = mycon.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        database = this;
         */
        //SQLite starts here
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            mycon = DriverManager.getConnection("jdbc:sqlite:boom_database.db");
            mystat = mycon.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        database = this;

        try {
            ResultSet rs = mystat.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name = 'boom'");
            if (!rs.next()) {
                System.out.println("NEW DB...Create table");
                mystat.executeUpdate("create table boom (name TEXT PRIMARY KEY NOT NULL, "
                        + "path TEXT NOT NULL, "
                        + "preference INT)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static String getTableName(String musicName) {
            String tableName = "point_" + musicName;
            tableName = tableName.replace(" ", "_");
            tableName = tableName.replace(" ", "_");
            tableName = tableName.replace("(", "_");
            tableName = tableName.replace(")", "_");
            return tableName;
    }
    
    // add_item
    public static void add(Music mu) {
        try {
            String sql = "insert into boom " + "(name, path , preference)" + " values ('" + mu.getName() + "' , '"
                    + mu.getPath() + "' ," + mu.getPreference() + ")";
            System.out.println(sql);
            mystat.executeUpdate(sql);
            
            String tableName = getTableName(mu.getName());
            sql = "create table " + tableName
                    + " ("
                    + "start INT PRIMARY KEY NOT NULL, "
                    + "duration INT NOT NULL, "
                    + "tag INT NOT NULL, "
                    + "preference INT NOT NULL"
                    + ")";
            System.out.println(sql);
            mystat.executeUpdate(sql);
            for (Point p : mu.points) {
                sql = "insert into " + tableName
                        + "(start, duration, tag, preference) "
                        + " values ( " + p.start + ", " + p.duration + ", " + p.tag + ", " + p.preference
                        + ")";
                System.out.println(sql);
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
            String sql ="delete from boom where name ='" + name + "'";
            row = mystat.executeUpdate(sql);
            String tableName = getTableName(name);
            sql = "DROP TABLE " + tableName;
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
    
    public static void update_preference(String name, int preference) {
        try {
            String sql = "update boom set preference = " + preference + " where name ='" + name + "'";
            mystat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
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
            String tableName = getTableName(name);
            sql = "SELECT * FROM " + tableName;
            rs = mystat.executeQuery(sql);

            while (rs.next()) {
                Point p = new Point(rs.getInt("start"), rs.getInt("duration"),
                        rs.getInt("tag"), rs.getInt("preference"));
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
