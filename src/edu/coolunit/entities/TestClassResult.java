package edu.coolunit.entities;

import java.util.ArrayList;
import java.util.List;

public class TestClassResult
{
	private final Class<?> type;
	private final List<TestCaseResult> testCaseResults;
	
	public TestClassResult(Class<?> type)
	{
		testCaseResults = new ArrayList<>();
		
		if (type == null)
		{
			throw new IllegalArgumentException("Type cannot be null");
		}
		
		this.type = type;
	}
	
	public Class<?> getType()
	{
		return type;
	}
	
	public List<TestCaseResult> getTestCases()
	{
		return testCaseResults;
	}
	
	public void addTestCase(TestCaseResult testCaseResult)
	{
		testCaseResults.add(testCaseResult);
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (TestCaseResult testCase : testCaseResults)
		{
			sb.append(testCase.toString() + "\n");
		}
		return sb.toString();
	}
}
