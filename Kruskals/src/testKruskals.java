import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class testKruskals {
	
	static ArrayList<City> allCities=new ArrayList<City>();
	static ArrayList<Edge> allEdges=new ArrayList<Edge>();
	static ArrayList<String[]> connectingCities=new ArrayList<>();
	
	public City getCity(String cityName)
	{
		for(City city:allCities) {
			if(city.cityName.equals(cityName))
			{
				return city;
			}			
		}
		return null;
	}
	
	public void mapCities(String fileName)
	{
		String csvFile = fileName;
		BufferedReader br = null;
		String line = "";
		int hashCode=0;
		String cvsSplitBy = ",";
		try
		{
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null)
			{
				// use comma as separator
				String[] cityRow = line.split(cvsSplitBy);
				City newCity=new City(hashCode,cityRow[0]);
				testKruskals.allCities.add(newCity);
				hashCode++;
				
				testKruskals.connectingCities.add(cityRow);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
        	if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        	}
	}
	
	public void updateCityEdges()
	{
		for(String[] a:testKruskals.connectingCities)
		{
			for(int j=1;j<a.length;j++)
			{
				Edge cityEdge=new Edge(this.getCity(a[0]),this.getCity(a[j]),Integer.parseInt(a[++j]));
				allEdges.add(cityEdge);
			}
		}
	}
	
	
	
	public static void main(String[] args)
	{
		testKruskals cityMap=new testKruskals();
		Kruskals<City> k=new Kruskals<>();
		String fileName="assn9_data.csv";
		cityMap.mapCities(fileName);
		cityMap.updateCityEdges();
		k.findMST(testKruskals.allEdges, testKruskals.allCities.size());
		System.out.println(k.printMST());
		System.out.println("Total weight " + k.getTotalWeight());
	}
	
}
