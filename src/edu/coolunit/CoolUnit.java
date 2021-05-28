package edu.coolunit;

import java.util.List;

import edu.coolunit.entities.TestClassResult;

public class CoolUnit
{
	private CoolUnit()
	{
	}
	
	public static TestClassResult run(Class<?> type)
	{
		throw new UnsupportedOperationException();
	}
	
	public static List<TestClassResult> run(Class<?>... types)
	{
		throw new UnsupportedOperationException();
	}
}
