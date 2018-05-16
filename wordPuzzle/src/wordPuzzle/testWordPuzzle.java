package wordPuzzle;

import java.util.Random;
import java.util.Scanner;

public class testWordPuzzle {
	
	public static char[][] createGrid(int row,int col)
	{
		char[][] grid=new char[row][col];
		Random rand=new Random();
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
			{
				grid[i][j] = (char) (rand.nextInt(26) + 'a');
				
			}
		return grid;
	}
	
	public static void printGrid(char[][] grid,int row,int col) {
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				System.out.print(grid[i][j]+"\t");				
			}
			System.out.print('\n');
		}
			
	}
	public static void main(String [] args)
	{
		int numRow,numCol;
		Scanner in=new Scanner(System.in);
		System.out.println("Enter number of row and col you want");
		numRow=in.nextInt();
		numCol=in.nextInt();
		System.out.println("Type 1 for normal search, 2 for prefix search and 3 for both searches");
		int choice= in.nextInt();
		String words;
		in.close();
		
		searchClass wordSearch=new searchClass(numRow,numCol);
		prefixSearch wordPrefixSearch=new prefixSearch(numRow,numCol);
		char[][] masterGrid=new char[numRow][numCol];

		masterGrid=createGrid(numRow,numCol);
		printGrid(masterGrid,numRow,numCol);
		
		System.out.println("Loading dictionaries...");
		wordSearch.loadDictionary("src\\wordPuzzle\\dictionary.txt");
		wordPrefixSearch.loadDictionary("src\\wordPuzzle\\dictionary.txt");
		System.out.println("Searching the matrix for all possible words....");
		
		switch(choice)
		{
		case 1:
			wordSearch.startSearch(masterGrid);
			words=wordSearch.printMatchedWords();
			//System.out.println(words);
			System.out.println("Normal Search - "+wordSearch.getRunningTime()+" ms");
			System.out.println("Total words found " + wordSearch.getWordCount() );
			break;
		case 2:
			wordPrefixSearch.startSearch(masterGrid);
		    words=wordPrefixSearch.printMatchedWords();
			//System.out.println(words);
			System.out.println("Prefix Search- "+wordPrefixSearch.getRunningTime() + " ms");
			System.out.println("Total words found " + wordPrefixSearch.getWordCount() );
			break;		
		default:
			wordSearch.startSearch(masterGrid);
			words=wordSearch.printMatchedWords();
			System.out.println(words);
			System.out.println("Normal Search - "+wordSearch.getRunningTime()+" ms");
			wordPrefixSearch.startSearch(masterGrid);
		    words=wordPrefixSearch.printMatchedWords();
			System.out.println("Prefix Search - "+wordPrefixSearch.getRunningTime() + " ms");	
			//System.out.println(words);
			System.out.println("Total words found in normal search " + wordSearch.getWordCount() );
			System.out.println("Total words found in prefix search " + wordPrefixSearch.getWordCount() );
				
		}
	}

}
