package boomshakalak;

public class Music {
	String name;
	String path;
	int preference;
	public String getName()
	{
		return "name5";
	}
	public String getPath()
	{
		return "pathn6";
	}
	public int getPreference()
	{
		return 3;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public void setPreference(int preference)
	{
		this.preference = preference;
	}
	
	public static void main(String[] args) {
		Database_Interface db = new Database_Interface();
		Music mu = new Music();
		//add(mu);
		//delete_name("n1");
		//System.out.println(getlist());
		System.out.println(db.getrow("name3").preference);
		
	}
}
