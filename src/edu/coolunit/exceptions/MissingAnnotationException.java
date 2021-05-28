package edu.coolunit.exceptions;

public class MissingAnnotationException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public MissingAnnotationException(Class<?> annotationType, String memberName)
	{
		this(annotationType.getSimpleName() + " is missing from " + memberName);
	}
	
	public MissingAnnotationException(String message)
	{
		super(message);
	}
}
