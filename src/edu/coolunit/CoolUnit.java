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
import edu.coolunit.exceptions.InvalidFieldTypeException;
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
		
		Constructor<?> constructor = type.getConstructor();
		Object instance = constructor.newInstance();
		
		for (Method method : methods)
		{
			System.out.println(method.getName());

			if (method.getParameterCount() == 0)
			{
				handleTestMethod(method, instance, true, null);
				continue;
			}
		
			ParamsProvider paramsProvider = method.getAnnotation(ParamsProvider.class);
				
			if (paramsProvider == null)
			{
				throw new MissingAnnotationException(ParamsProvider.class, method.getName());
			}
			
			Field field = type.getDeclaredField(paramsProvider.value());
			
			if (!field.getType().isArray())
			{
				throw new InvalidFieldTypeException("Params provider field must be an array");
			}
			
			Object[] paramsEntries = (Object[]) field.get(instance);
			
			for (Object paramsEntry : paramsEntries)
			{
				handleTestMethod(method, instance, false, paramsEntry);
			}
		}
		
		return null;
	}
	

	public static List<TestClassResult> run(Class<?>... types)
	{
		throw new UnsupportedOperationException();
	}
	
	private static void handleTestMethod(Method method, Object instance, boolean parameterless, Object parameters) throws Throwable
	{
		try
		{
			if (parameterless)
			{
				method.invoke(instance);
			}
			else
			{
				method.invoke(instance, parameters);		
			}
			
			System.out.println("passed");
		}
		catch (InvocationTargetException e)
		{
			if (e.getTargetException().getClass().isAssignableFrom(AssertFailException.class))
			{
				System.out.println("failed");
			}
			else
			{
				throw e.getTargetException();
			}
		}
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
}
