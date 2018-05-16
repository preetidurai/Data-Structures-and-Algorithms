import java.util.ArrayList;

public class City {
	int id;
	String cityName;
	
	public City()
	{
		this(65535,"");
	}
	
	public City(int hashCode,String cityName)
	{
		this.id=hashCode;
		this.cityName=cityName;
	}

}
