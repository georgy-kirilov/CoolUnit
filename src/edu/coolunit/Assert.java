package edu.coolunit;

import edu.coolunit.entities.Action;

public class Assert
{
	private Assert()
	{
	}
	
	// TODO: Implement all methods
	// NOTE: To indicate that the assertion has failed, you need to throw new AssertFailException
	
	public static <T extends Comparable<T>> void areEqual(T expected, T actual)
	{
		throw new UnsupportedOperationException();
	}
	
	public static void areEqual(Object expected, Object actual)
	{
		throw new UnsupportedOperationException();
	}
	
	public static void throwsException(Action action)
	{
		throw new UnsupportedOperationException();
	}
	
	public static <T extends Exception> void throwsException(Class<T> exceptionType, Action action)
	{
		// TODO: Think about additional parameter that indicates whether the type of the exception should be considered a subclass (instance of) or should be compared directly to the given type
		throw new UnsupportedOperationException();
	}
	
	public static void isTrue(boolean condition)
	{
		throw new UnsupportedOperationException();
	}
	
	public static void isFalse(boolean condition)
	{
		throw new UnsupportedOperationException();
	}
}
