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
	
	public static void areSame(Object expected, Object actual)
	{
		if (expected != actual)
		{
			throw new AssertFailException();
		}
	}
	
	public static void fail()
	{
		throw new AssertFailException();
	}
	
	public static void isNull(Object object)
	{	
		if (object != null)
		{
			throw new AssertFailException();
		}
	}
	
	public static void isNotNull(Object object)
	{
		if (object == null)
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
		isTrue(condition, "Condition was expected to be False but was True");
	}
	
	public static void isTrue(boolean condition, String message)
	{
		if (!condition)
		{
			throw new AssertFailException(message);
		}
	}
	
	public static void isFalse(boolean condition)
	{
		isFalse(condition, "Condition was expected to be True but was False");
	}
	
	public static void isFalse(boolean condition, String message)
	{
		if (condition)
		{
			throw new AssertFailException(message);
		}
	}
}
