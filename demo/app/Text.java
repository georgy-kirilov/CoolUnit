package app;

public class Text
{
	public static String create(char symbol, int count)
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < count; i++)
		{
			sb.append(symbol);
		}
		
		return sb.toString();
	}
	
	public static final String EMPTY = "";
}
