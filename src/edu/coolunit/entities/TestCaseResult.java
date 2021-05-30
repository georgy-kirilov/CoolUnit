package edu.coolunit.entities;

import java.lang.reflect.Method;

public abstract class TestCaseResult
{
	private final Method method;
	
	public TestCaseResult(Method method)
	{
		this.method = method;
	}
	
	public Method getMethod()
	{
		return method;
	}
}
