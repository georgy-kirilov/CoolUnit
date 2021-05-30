package edu.coolunit.entities;

public class TestCaseEntryResult implements Statusable, Messagable
{
	private final Object[] parameters;
	private String message;
	private TestStatus status;
	
	public TestCaseEntryResult(Object[] parameters)
	{
		this.parameters = parameters;
	}

	public Object[] getParameters()
	{
		return parameters;
	}

	public TestStatus getStatus()
	{
		return status;
	}
	
	public void setStatus(TestStatus status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < getParameters().length; i++)
		{
			sb.append(getParameters()[i]);
			
			if (i < getParameters().length - 1)
			{
				sb.append(", ");
			}
		}
		
		String info = getStatus().toString();
		info += " (" + sb.toString() + ")";
		
		if (getStatus() == TestStatus.FAILED)
		{
			info += " -> " + getMessage();
		}
		
		return info;
	}
}
