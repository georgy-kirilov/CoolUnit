package edu.coolunit;

import edu.coolunit.entities.Action;
import edu.coolunit.exceptions.AssertFailException;

public class Assert
{
	private Assert()
	{
	}
	
	// TODO: Implement all methods
	// NOTE: To indicate that the assertion has failed, you need to throw new AssertFailException
	
	public static <T extends Comparable<T>> void areEqual(T expected, T actual)
	{
		if(expected.compareTo(actual) != 0)
		{
			throw new AssertFailException();
		}
		
		// TODO: throw appropriate exception when assert passes and catch it
	}
	
	public static void areEqual(Object expected, Object actual)
	{
		if(expected == null)
		{
			throw new AssertFailException();
		}
		
		if(expected.equals(actual))
		{
			throw new AssertFailException();
		}
		
		// TODO: throw appropriate exception when assert passes and catch it
	}
	
	public static void throwsException(Action action)
	{
		try
		{
			action.invoke();
			throw new AssertFailException();
			
		}
		catch(Exception e)
		{
		}
	}
	
	public static <T extends Exception> void throwsException(Class<T> exceptionType, Action action, boolean allowSubclasses)
	{
		try
		{
			action.invoke();
			throw new AssertFailException();
			
		}
		catch(Exception e)
		{
			if(allowSubclasses)
			{
				if(!e.getClass().isInstance(exceptionType))
				{
					throw new AssertFailException();
				}
			}
			else
			{
				if(!e.getClass().equals(exceptionType.getClass()))
				{
					throw new AssertFailException();
				}
			}
		}
	}
	
	public static <T extends Exception> void throwsException(Class<T> exceptionType, Action action)
	{
		throwsException(exceptionType, action, false);
	}
	
	public static void isTrue(boolean condition)
	{
		if(!condition)
		{
			throw new AssertFailException();	
		}
	}
	
	public static void isFalse(boolean condition)
	{
		if(condition)
		{
			throw new AssertFailException();	
		}
	}
}
