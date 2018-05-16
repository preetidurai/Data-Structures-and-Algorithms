package wordPuzzle;

public class searchHashEntry<AnyType> {
	public boolean isPresent;
	public boolean isWord;
	public searchHashEntry() {
		this(false,false);
	}
	public searchHashEntry(boolean present,boolean word)
	{
		this.isPresent=present;
		this.isWord=word;
	}
}
