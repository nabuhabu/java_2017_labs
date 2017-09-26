public class ModDiv {
	
	
		    private int first;
		    private int second;

		    public int getFirst() {
		        return first;
		    }
		   
		    public void setFirst(int first) {
		        this.first = first;
		    }

		    public int getSecond() {
		        return second;
		    }

		    public void setSecond(int second) {
		        this.second = second;
		    }
		    
		    
		    public ModDiv(int first, int second)
		    {
		    	this.first = first;
		    	this.second = second;
		    }
		    
		    
	public ModDiv SplitNumber(int userNumber)
	{
		int first = (int)userNumber / 10;
		int second = (int)userNumber % 10;
		return new ModDiv(first, second);
	}

}
