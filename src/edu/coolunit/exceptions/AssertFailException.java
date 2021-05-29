package edu.coolunit.exceptions;

public class AssertFailException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public AssertFailException()
	{
	}
	
	public AssertFailException(String message)
	{
		super(message);
	}
}
