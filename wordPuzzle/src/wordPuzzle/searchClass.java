package wordPuzzle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class searchClass {
	private ArrayList<ArrayList<String>> matchedWords=new ArrayList<ArrayList<String>>();
	private int matchedWordsCount;
	private int rowNum;
	private int colNum;
	private long runningTime;
	private linearProbingHashTable<String> hashTable=new linearProbingHashTable<>(109616);	
	private ArrayList<String> holderList=new ArrayList<>();
 
	public int getLoadFactor()
	{
		return hashTable.getLoadFactor();
	}
	/**Public constructor to assign the row 
	 * and column number to be searched***/
	public searchClass(int row,int col)
	{
		this.rowNum=row;
		this.colNum=col;		
	}
	
	
	
	/**This function will load the dictionary from user defined location*
	 * @param- location of dictionary text file**/
	public void loadDictionary(String dictFileLoc) {
		String line;
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(dictFileLoc));
			do
			{
				line=br.readLine();
				if(line!=null)
				{
					hashTable.insert(line,true);
				}
			}while(line!=null);			
			br.close();			
		}catch(Exception e)
		{
			System.out.println("File could not be opened");
		}
	}
	
	/**returns the number of items in the hashTable currently loaded
	 * @return- number of items in hashTable***/
	public int getDictionarySize()
	{
		return hashTable.getCurrentSize();
	}
	
	
	/****Returns the total matches found*
	 * @return- total matches found**/
	public int getMatchCount() {
		return matchedWordsCount;
	}
	
	
	/****Will search in all 8 directions**/
	public void startSearch(char[][] grid)
	{
		long startTime = System.currentTimeMillis();
		for(int i=0;i<rowNum;i++)
		{
			for(int j=0;j<colNum;j++)
			{
				
				holderList.clear();
				horForwardSearch(grid,i,j);
				horReverseSearch(grid,i,j);
				verDownSearch(grid,i,j);
				verUpSearch(grid,i,j);
				diagRightDownSearch(grid,i,j);
				diagRightUpSearch(grid,i,j);
				diagLeftDownSearch(grid,i,j);
				diagLeftUpSearch(grid,i,j);
				matchedWords.add(new ArrayList<String>(holderList));
			}		
		}	
		long endTime   = System.currentTimeMillis();
		runningTime = endTime - startTime;
	}
	public int getWordCount()
	{
		return matchedWordsCount;
	}
	public long getRunningTime()
	{
		return runningTime;
	}
	private void diagLeftUpSearch(char[][] grid, int r, int c)
	{
		String word="";
		
		while(r<rowNum && c>=0)
		{			
			word+=grid[r][c];
		
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			r++;
			c--;
		}
	}
	private void diagRightUpSearch(char[][] grid, int r, int c)
	{
		String word="";
		
		while(r>=0 && c<colNum)
		{			
			word+=grid[r][c];
		
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			r--;
			c++;
		}
	}
	private void diagRightDownSearch(char[][] grid, int r, int c)
	{
		String word="";
		
		while(r<rowNum && c<colNum)
		{			
			word+=grid[r][c];
		
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			r++;
			c++;
		}
	}
	private void diagLeftDownSearch(char[][] grid, int r, int c)
	{
		String word="";
		
		while(r>=0 && c>=0)
		{			
			word+=grid[r][c];
		
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			r--;
			c--;
		}
	}
	private void verDownSearch(char[][] grid,int r,int c)
	{
		String word="";
		
		while(r<rowNum)
		{			
			word+=grid[r][c];
		
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			r++;
		}
	}
	private void verUpSearch(char[][] grid,int r,int c)
	{
		String word="";
		
		while(r>=0)
		{			
			word+=grid[r][c];
		
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			r--;
		}
	}
	private void horReverseSearch(char[][] grid,int r,int c)
	{
		String word="";
		
		while(c>=0)
		{			
			word+=grid[r][c];
		
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			c--;
		}
	}
	
	
	private void horForwardSearch(char[][] grid,int r,int c)
	{
		String word="";
		
		while(c<colNum)
		{			
			word+=grid[r][c];
	
			if(hashTable.contains(word))
			{
				matchedWordsCount++;
				holderList.add(word);
			}
			c++;
		}
	}

	public String printMatchedWords()
	{		
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<matchedWords.size();i++)
		{
			sb.append("\n"+(i+1)+"   ");
			Set<String> set=new HashSet<>();
			set.addAll(matchedWords.get(i));
			matchedWords.get(i).clear();
			matchedWords.get(i).addAll(set);
			for(int j=0;j<matchedWords.get(i).size();j++)
			{
				sb.append(matchedWords.get(i).get(j));
				sb.append("\t");
			}
			
		}
		sb.append("\nTotal Running time was " + runningTime+ " ms");
		return new String(sb);
	}

}
