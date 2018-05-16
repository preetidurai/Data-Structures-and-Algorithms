package wordPuzzle;

public class hashEntry<AnyType>
{
	protected AnyType element; 
    protected boolean isActive; 
    protected boolean isWord;
	protected hashEntry( AnyType e )
	{ 
		this( e, true , false);
		
	}
	protected hashEntry( AnyType e, boolean i, boolean w )
	{ 
		 element = e; 
		 isActive = i; 
		 isWord=w;
	}

}
