package edu.coolunit;

import edu.coolunit.entities.Action;
import edu.coolunit.exceptions.AssertFailException;

public class Assert
{
	private Assert() { }
		
	public static <T extends Comparable<T>> void areEqual(T expected, T actual)
	{	
		if (expected != actual && expected != null && expected.compareTo(actual) != 0)
		{
			throw new AssertFailException();
		}
	}
	
	public static void areEqual(Object expected, Object actual)
	{
		if (expected != actual && expected != null && expected.equals(actual))
		{
			throw new AssertFailException();
		}
	}
	
	public static void exception(Action action)
	{
		exception(Exception.class, action, true);
	}
	
	public static <T extends Exception> void exception(Class<T> exceptionType, Action action)
	{
		exception(exceptionType, action, false);
	}
	
	public static <T extends Exception> void exception(Class<T> exceptionType, Action action, boolean allowSubclasses)
	{
		try
		{
			action.invoke();
		}
		catch (Exception e)
		{	
			boolean correctExceptionType = allowSubclasses 
					&& exceptionType.isAssignableFrom(e.getClass()) 
					|| !allowSubclasses && e.getClass().equals(exceptionType);
			
			if (correctExceptionType)
			{
				return;
			}
		}
		
		throw new AssertFailException();
	}
	
	public static void isTrue(boolean condition)
	{
		if (!condition)
		{
			throw new AssertFailException();
		}
	}
	
	public static void isFalse(boolean condition)
	{
		if (condition)
		{
			throw new AssertFailException();	
		}
	}
}
