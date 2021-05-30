package edu.coolunit.entities;

import edu.coolunit.common.*;

import java.util.*;
import java.lang.reflect.Method;

public class MultiParameterTestCaseResult extends TestCaseResult
{
	private final Collection<TestCaseEntryResult> entries = new ArrayList<>();
	
	public MultiParameterTestCaseResult(Method method)
	{
		super(method);
	}
	
	public void addEntryResult(TestCaseEntryResult entryResult)
	{
		entries.add(entryResult);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		Object[] parameters = Arrays
				.stream(getMethod().getParameters())
				.map(p -> p.getType().getSimpleName())
				.toArray();
		
		String parametersInfo = Text.join(", ", parameters);
		sb.append(getMethod().getName() + "(" + parametersInfo + ")\n");
		
		for (TestCaseEntryResult entry : entries)
		{
			sb.append("--" + entry.toString() + "\n");
		}
		return sb.toString();
	}
}
