import java.util.AbstractCollection;
import java.util.Iterator;

public class Edge implements Comparable<Edge> {
	City u;
	City v;
	int weight;
	
	public Edge()
	{
		this(null,null,0);
	}
	
	public Edge(City u,City v, int weight)
	{
		this.u=u;
		this.v=v;
		this.weight=weight;
	}
	@Override
	public int compareTo(Edge a) 
	{
		if(this.weight==a.weight)
			return 0;
		else if(this.weight<a.weight)
			return -1;
		else if(this.weight>a.weight)
			return 1;
		else
			return 0;
	}
	
	public int getuId()
	{
		return this.u.id;
		
	}	
	public int getvId()
	{
		return this.v.id;
	}

}
