package edu.coolunit.exceptions;

public class MissingAnnotationException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public MissingAnnotationException(String message)
	{
		super(message);
	}
}
