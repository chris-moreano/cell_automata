
public class Elementary_CA_01
{
	private final static int SIZE = 32;
	private final static int REPEATS = 32;
	
	public static void main(String[] args)
	{
		int[] ca = new int[SIZE];
		
		// initial conditions = random
		for( int i = 0; i < ca.length; i++ )
			ca[i] = (int)Math.floor(Math.random() * 2.0);
		
		// run the ca a number of times
		int counter = 0;
		while( counter < REPEATS )
		{
			int[] newValues = new int[SIZE]; // temporary storage
			
			// recalculate each cell
			for( int i = 0; i < ca.length; i++ )
			{
				// get the values in the neighborhood
				int left = (i > 0) ? ca[i-1] : ca[ca.length - 1];
				int center = ca[i];
				int right = (i < ca.length - 1) ? ca[i+1] : ca[0];
				
				// apply the rule set
				int newValue = 0;
				if( left == 1 && center == 0 && right == 0 ) newValue = 1;
				else if( left == 1 && center == 1 && right == 0 ) newValue = 1;
				else if( left == 0 && center == 1 && right == 1 ) newValue = 1;
				else if( left == 1 && center == 0 && right == 1 ) newValue = 1;
			
				// store the new value
				newValues[i] = newValue;
				
				// print out the new value
				if( newValue == 1 ) System.out.print(newValue + " ");
				else if( newValue == 0 ) System.out.print("- ");
			}
			System.out.print("\n"); // print a new line
			
			// copy over the array
			ca = newValues;
			
			counter++;
		}
	}

}
