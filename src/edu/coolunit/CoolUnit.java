package edu.coolunit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.coolunit.annotations.ParamsProvider;
import edu.coolunit.annotations.TestCase;
import edu.coolunit.annotations.TestClass;
import edu.coolunit.entities.TestClassResult;
import edu.coolunit.exceptions.AssertFailException;
import edu.coolunit.exceptions.MissingAnnotationException;

public class CoolUnit
{
	private CoolUnit()
	{
	}
	
	public static TestClassResult run(Class<?> type) throws Throwable
	{
		if (type.getDeclaredAnnotation(TestClass.class) == null)
		{
			throw new MissingAnnotationException(TestClass.class, type.getName());
		}
		
		List<Method> methods = getTestMethods(type);
		
		for (Method method : methods)
		{
			ParamsProvider paramsProvider = method.getAnnotation(ParamsProvider.class);
			
			if (method.getParameterCount() > 0 && paramsProvider == null)
			{
				throw new MissingAnnotationException(ParamsProvider.class, method.getName());
			}
			
			Constructor<?> constructor = type.getConstructor();
			Object instance = constructor.newInstance();
			
			Field field = type.getDeclaredField(paramsProvider.value());
			
			if (!field.getType().isArray())
			{
				throw new IllegalStateException("Params provider field must be an array");
			}
			
			Object[] paramsEntries = (Object[]) field.get(instance);
		
			for (Object paramsEntry : paramsEntries)
			{
				try
				{
					method.invoke(instance, paramsEntry);
					System.out.println("passed");
				}
				catch (InvocationTargetException e)
				{
					if (e.getTargetException().getClass().equals(AssertFailException.class))
					{
						System.out.println("failed");						
					}
					else
					{
						throw e.getTargetException();						
					}
				}
			}
		}
		
		return null;
	}
	
	private static List<Method> getTestMethods(Class<?> type)
	{
		return Arrays
				.stream(type.getMethods())
				.filter(m -> m.getAnnotation(TestCase.class) != null 
					&& Modifier.isPublic(m.getModifiers()) 
					&& m.getReturnType().equals(Void.TYPE))
				.collect(Collectors.toList());
	}
	
	public static List<TestClassResult> run(Class<?>... types)
	{
		throw new UnsupportedOperationException();
	}
}
