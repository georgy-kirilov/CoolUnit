package edu.coolunit.entities;

import java.lang.reflect.Method;

import app.Text;
import edu.coolunit.exceptions.AssertFailException;

public class ParameterlessTestCaseResult extends TestCaseResult implements Statusable, Messagable
{
	private TestStatus status;
	private String message;
	private final AssertFailException exception;
	
	public ParameterlessTestCaseResult(Method method)
	{
		this(method, null);
	}
	
	public ParameterlessTestCaseResult(Method method, AssertFailException exception)
	{
		super(method);
		this.exception = exception;
	}

	public void setStatus(TestStatus status)
	{
		this.status = status;
	}
	
	public TestStatus getStatus()
	{
		return status;
	}

	public AssertFailException getException()
	{
		return exception;
	}
	
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		String info = String.format("%s | %s", getMethod().getName(), getStatus());
		if (getStatus() == TestStatus.FAILED)
		{
			String message = getMessage() == null ? Text.EMPTY : getMessage();
			info += " | " + message;
		}
		return info;
	}
}
