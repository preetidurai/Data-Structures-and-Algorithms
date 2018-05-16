package application;

public class cell {
	int row;
	int col;
	
	wall cellWall;
	public cell()
	{
		this(0,0);
	}
	public cell(int row,int col)
	{
		this.row=row;
		this.col=col;
		cellWall=new wall(true,true,true,true);
	}
	public void breakLeftWall() {
		this.cellWall.left=false;
	}
	public void breakRightWall() {
		this.cellWall.right=false;
	}
	public void breakTopWall() {
		this.cellWall.top=false;
	}
	public void breakBottomWall() {
		this.cellWall.bottom=false;
	}
	
}
