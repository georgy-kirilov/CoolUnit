package edu.coolunit.common;

public class Text
{
	public static final String EMPTY = "";
	
	public static String join(String separator, Object[] objects)
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < objects.length; i++)
		{
			builder.append(objects[i]);
			
			if (i < objects.length - 1)
			{
				builder.append(separator);
			}
		}
		
		return builder.toString();
	}
}
