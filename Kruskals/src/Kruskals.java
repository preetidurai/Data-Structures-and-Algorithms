import java.util.ArrayList;
import java.util.List;

public class Kruskals<E> {
	List<Edge> MST=new ArrayList<>();
		
	public void findMST(ArrayList<Edge> edges,int numVertices) 
	{
		DisjSet ds=new DisjSet(numVertices);
		PriorityQueue<Edge> pq=new PriorityQueue<Edge>(edges);
		
		int i=0;
		while(this.MST.size()!=numVertices-1)
		{
			Edge e=pq.remove();
			int uset=ds.find( e.getuId());
			int vset=ds.find(e.getvId());
			if(uset!=vset)
			{
				this.MST.add(e);
				ds.union(uset,vset);
			}			
		}
	}
	public String printMST()	
	{
		StringBuilder sb=new StringBuilder();
		sb.append("Minimum Spanning tree is "+"\n\n");
		for(Edge e:this.MST)
		{
			sb.append("("+e.u.cityName +",");			
			sb.append(e.v.cityName+")");
			sb.append("\t");
			sb.append(e.weight);
			sb.append("\n");			
		}
		return sb.toString();		
	}
	public int getTotalWeight()
	{
		int weight=0;
		for(Edge e:this.MST)
		{
			weight+=e.weight;
		}
		return weight;
	}
	

}
