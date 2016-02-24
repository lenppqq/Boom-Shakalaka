package boomshakalak;
import java.sql.*;
import java.util.*;
public class Database_Interface {
	
    //connection
	static Connection mycon;
    static Statement mystat;
    
	//construction
    public Database_Interface()
	{
		String user = "root";
		String password = "wly12345";
		try{
			mycon = 
				DriverManager.getConnection
				("jdbc:mysql://localhost:3306/boom_database",user, password);	
			mystat = mycon.createStatement();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    
	// add_item
	public static void add(Music mu)
	{
		try{
			String sql = "insert into boom "
					+   " (name, path , preference)"
					+	" values('"+ mu.getName() + "' , '"+ mu.getPath() +
					"' ," + mu.getPreference() +")";
			System.out.println(sql);
			mystat.executeUpdate(sql);
			System.out.println("add item complete");
	    }
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("item add failed");
		}
	}
	
	// delete_item
	public static void delete_name(String name)
	{
		int row = 0;
		try{
			String sql = "delete from boom where name ='" + name +"'";
			row = mystat.executeUpdate(sql);
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(row==0)
		{
			System.out.println("this item is now in database");
		}
		else
		{
			System.out.println("delete complete");
		}
	}
	
	// get list
	public static ArrayList<String> getlist()
	{
		ArrayList<String> lists = new ArrayList<String>();
		try{
			String sql;
			sql = "SELECT name FROM boom";
			ResultSet rs = mystat.executeQuery(sql);
	    
			while(rs.next()){
				lists.add(rs.getString("name"));
			}
			System.out.println("getMusiclist complete");
		}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		return lists;
	}

	//get item
	public static Music getrow(String name)
	{
		Music m = new Music();
		try{
			String sql;
			sql = "SELECT * FROM boom where name= '"+ name + "'";
			ResultSet rs = mystat.executeQuery(sql);
			
			while(rs.next()){
				m.setName(rs.getString("name"));
				m.setPath(rs.getString("path"));
				m.setPreference(rs.getInt("preference"));		
			}
			System.out.println(" row return success");
		}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		return m;
	}
	

}
