package edu.coolunit.exceptions;

public class InvalidFieldTypeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public InvalidFieldTypeException(String message)
	{
		super(message);
	}
}
