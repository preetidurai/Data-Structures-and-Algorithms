package wordPuzzle;

public class linearProbingHashTable<AnyType> {
	private final static int DEFAULT_TABLE_SIZE=101;
	private hashEntry<AnyType> [ ] array;
	private int currentSize;

	public int getLoadFactor()
	{
		return((currentSize*100/array.length));
	}
	public linearProbingHashTable()
	{
		this(DEFAULT_TABLE_SIZE);
	}
	public linearProbingHashTable(int size)
	{
		allocateArray(2*size);
		makeArrayEmpty();
	}
	public searchHashEntry<AnyType> getFoundHashEntry(AnyType x)
	{
		searchHashEntry<AnyType> item=new searchHashEntry<>();
		int currentPos=findPos(x);
		if(isActive(currentPos))
			item.isPresent=true;
		if(isWord(currentPos))
			item.isWord=true;
		return item;

	}
	public boolean contains(AnyType x)
	{
		int currentPos=findPos(x);
		return isActive(currentPos);
	}
	public void insert(AnyType x, boolean isWord)
	{
		int currentPos=findPos(x);
		if(isActive(currentPos))
			return;
		array[currentPos]=new hashEntry<>(x,true,isWord);
		
		if(++currentSize>array.length/2)
		{
			rehash();
		}
			
	}
	
	public void remove(AnyType x)
	{
		int currentPos=findPos(x);
		if(isActive(currentPos))
		{
			array[currentPos].isActive=false;
		}
				
	}
	public int getCurrentSize()
	{
		return currentSize;
	}
	public void printHashTable()
	{
		for(int i=0;i<array.length;i++)
		{
			if(array[i]==null)
				continue;
			else
			{
				System.out.println(array[i].element);
			}
			
		}
	}
	/**
	* Rehashing for linear Probing hash table.
	*/
	private void rehash( )
	{
	  hashEntry<AnyType> [ ] oldArray=array;
	  allocateArray(2*array.length);
	 
	  makeArrayEmpty();
	  // Copy table over
	  currentSize = 0;
	  for(int i=0;i<oldArray.length;i++)
	  {		  
		  if(oldArray[i]!=null)
		  {
			  insert(oldArray[i].element,oldArray[i].isWord);
		  }
		
	   }
	 }
	private void allocateArray( int arraySize )
    {
		array = new hashEntry[nextPrime(arraySize)];
	}
	private int nextPrime(int size)
	{
		while(true)
		{
			if(isPrime(size++))
				break;
		}
		return size;
	}
	boolean isPrime(int n) {
	    for(int i=2;i<n;i++) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	private void makeArrayEmpty()
	{
		currentSize=0;
		for(int i=0;i<array.length;i++)
		{
			array[i]=null;
		}
	}
	
	private boolean isActive(int pos)
	{
		return(array[pos]!=null && array[pos].isActive);
	}
	private boolean isWord(int pos)
	{
		return(array[pos]!=null && array[pos].isWord);
	}
	private int findPos(AnyType x)
	{
		int currentPos=myHash(x);
		while(array[currentPos]!=null && !array[currentPos].element.equals(x))
		{
			
			currentPos++;
			if(currentPos>=array.length)
				currentPos=0;
		}
		return currentPos;
	}
	private int myHash(AnyType x)
	{
		int hashVal=x.hashCode();
		hashVal%=array.length;
		if(hashVal<0)
		{
			hashVal+=array.length;
		}
		return hashVal;
	}	
}
