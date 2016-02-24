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
	public static void addItem(int Musicid, String criticalPoints)
	{
		try{
			String sql = "insert into boom "
					+   " (Musicid, cp , refer)"
					+	" values ("+ Integer.toString(Musicid) + ", '"+ criticalPoints +
					"' , 'null')";
			System.out.println(sql);
			mystat.executeUpdate(sql);
	    }
		catch(Exception e)
		{
			//e.printStackTrace();
			System.out.println("item insert failed");
		}
		System.out.println("additem complete");
	}
	
	// delete_item
	public static void deleteItem(int Musicid)
	{
		int row = 0;
		try{
			String sql = "delete from boom where Musicid =" + Integer.toString(Musicid);
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
	public static ArrayList<Integer> getMusiclist()
	{
		ArrayList<Integer> lists = new ArrayList<Integer>();
		try{
			String sql;
			sql = "SELECT Musicid FROM boom";
			ResultSet rs = mystat.executeQuery(sql);
	    
			while(rs.next()){
				lists.add(rs.getInt("Musicid"));
			}
		}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		System.out.println("getMusiclist complete");
		return lists;
	}
	
	//get item
	public static String getItem(int musicid)
	{
		/*int id = musicid;
		String analysis = "";
		try{
			String sql;
			sql = "SELECT Musicid FROM boom";
			ResultSet rs = mystat.executeQuery(sql);
	    
			while(rs.next()){
				if(id == rs)
				{
					
				}
			}
		}
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		System.out.println("getMusiclist complete");*/
		return "";
	}
	
	public static void main(String[] args) {
		Database_Interface db = new Database_Interface();
		addItem(158,"cmowmco");
		System.out.println(getMusiclist());
		deleteItem(111);
	}

}
