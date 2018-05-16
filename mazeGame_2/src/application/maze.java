package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public class maze {
	int totRows;
	int totCols;
	DisJSet set;
	ArrayList<cell> cells=new ArrayList<>();
	Set<Integer> chosenNums=new HashSet<>();
	private final int left=0,top=1,right=2,bottom=3;
	
	public maze(int rows,int cols)
	{
		this.totRows=rows;
		this.totCols=cols;		
		this.set=new DisJSet(rows*cols);
		for(int i=0;i<totRows;i++)
		{
			for(int j=0;j<totCols;j++)
			{
				this.cells.add(new cell(i,j));
				if(i==0 && j==0)
				{
					this.cells.get(i*totCols+j).breakLeftWall();
				}
				if(i==totRows-1 && j==totCols-1)
				{
					this.cells.get(i*totCols+j).breakRightWall();
				}
			}
		}
	}
	int pickRandomNum(int n)
	{
		Random r=new Random();
		return r.nextInt(n);
	}
	cell pickRandomCell()
	{
		int genRow,genCol,cellNum;
		genRow=pickRandomNum(totRows);
		genCol=pickRandomNum(totCols);
		cellNum=genRow*totCols+genCol;	
		chosenNums.add(cellNum);
		return cells.get(genRow*totCols+genCol);		
	}
	
	
	int chooseAWallToBreak(cell chosenCell)
	{
		int wallToBreak=pickRandomNum(4);
		if(chosenCell.col==totCols-1 && chosenCell.row==0)
		{
			while(wallToBreak==right || wallToBreak==top)
				wallToBreak=pickRandomNum(4);
		}
		else if(chosenCell.row==totRows-1 && chosenCell.col==0)
		{
			while(wallToBreak==bottom || wallToBreak==left)
				wallToBreak=pickRandomNum(4);
		}
		else if(chosenCell.row==0 && chosenCell.col==0)
		{
			while(wallToBreak==left || wallToBreak==top)
				wallToBreak=pickRandomNum(4);
		}
		else if(chosenCell.row==totRows-1 && chosenCell.col==totCols-1)
		{
			while(wallToBreak==right || wallToBreak==bottom)
				wallToBreak=pickRandomNum(4);
		}
		else
		{
			if(chosenCell.row==0)
			{
				while(wallToBreak==top)
					wallToBreak=pickRandomNum(4);
			}
			if(chosenCell.col==0)
			{
				while(wallToBreak==left)
					wallToBreak=pickRandomNum(4);
			}
			if(chosenCell.row==totRows-1) {
				while(wallToBreak==bottom)
					wallToBreak=pickRandomNum(4);
			}
			if(chosenCell.col==totCols-1) {
				while(wallToBreak==right)
					wallToBreak=pickRandomNum(4);
			}

		}
		return wallToBreak;		
	}
	
	cell selectAdjacentCell(cell chosenCell,int sideSelected) {
		if(sideSelected == left)
		{
			return cells.get(chosenCell.row*totCols+(chosenCell.col-1));
		}
		else if(sideSelected == right)
		{
			return cells.get(chosenCell.row*totCols+(chosenCell.col+1));
		}
		else if(sideSelected == top)
		{
			return cells.get((chosenCell.row-1)*totCols+chosenCell.col);
		}
		else
		{
			return cells.get((chosenCell.row+1)*totCols+chosenCell.col);
		}
	}
	
	void breakWalls(cell chosenCell, cell adjCell, int sideSelected)
	{
		if(sideSelected==left)
		{
			chosenCell.breakLeftWall();
			adjCell.breakRightWall();			
		}
		else if(sideSelected==right)
		{
			chosenCell.breakRightWall();
			adjCell.breakLeftWall();
		}
		else if(sideSelected==top)
		{
			chosenCell.breakTopWall();
			adjCell.breakBottomWall();
		}
		else if(sideSelected==bottom)
		{
			chosenCell.breakBottomWall();
			adjCell.breakTopWall();
		}
	}

	void generateMaze() {
		cell chosenCell=new cell();
		cell adjCell=new cell();
		int sideSelected,ss;
		int root1, root2;
		for(int i=0;i<totRows*totCols-1;)
		{
			chosenCell=pickRandomCell();
		
			System.out.println("Chosen Cell: Row Num " + chosenCell.row+" Col Num " + chosenCell.col);
			sideSelected=chooseAWallToBreak(chosenCell);
			System.out.println("Side selected "+ sideSelected);
			adjCell=selectAdjacentCell(chosenCell,sideSelected);
			System.out.println("Adjacent Cell: Row Num " + adjCell.row+" Col Num " + adjCell.col);
			
			root1=chosenCell.row*totCols+(chosenCell.col);
			root2=adjCell.row*totCols+(adjCell.col);			
			
			System.out.println("root1-"+set.find(root1) + " root2-"+ set.find(root2));
			/*If the two cells are not already in same set, then union the two sets*/
			if(set.find(root1)!=set.find(root2))
			{				
				set.union(set.find(root1), set.find(root2));
				System.out.println("Root of root1 " +root1+ " is "+ set.find(root1));
				System.out.println("Root of root2 " + root2+ " is " + set.find(root2)+"\n");
				
				/*Break appropriate walls*/
				breakWalls(chosenCell,adjCell,sideSelected);
				i++;
			}
			System.out.println("ï is .. "+i);

		}
		
	System.out.println(Arrays.toString(chosenNums.toArray()));
	
	}
	
	

}
