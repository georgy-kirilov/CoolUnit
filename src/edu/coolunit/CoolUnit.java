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
import edu.coolunit.entities.Messagable;
import edu.coolunit.entities.MultiParameterTestCaseResult;
import edu.coolunit.entities.ParameterlessTestCaseResult;
import edu.coolunit.entities.Statusable;
import edu.coolunit.entities.TestCaseEntryResult;
import edu.coolunit.entities.TestClassResult;
import edu.coolunit.entities.TestStatus;
import edu.coolunit.exceptions.AssertFailException;
import edu.coolunit.exceptions.InvalidFieldTypeException;
import edu.coolunit.exceptions.MissingAnnotationException;

public final class CoolUnit
{
	private CoolUnit() { }
	
	public static TestClassResult run(Class<?> type) throws Throwable
	{
		TestClassResult testClassResult = new TestClassResult(type);
		
		if (type.getDeclaredAnnotation(TestClass.class) == null)
		{
			throw new MissingAnnotationException(TestClass.class, type.getName());
		}
		
		List<Method> methods = extractTestMethods(type);
		
		Constructor<?> constructor = type.getConstructor();
		Object instance = constructor.newInstance();
		
		for (Method method : methods)
		{	
			if (method.getParameterCount() == 0)
			{
				ParameterlessTestCaseResult testCaseResult = new ParameterlessTestCaseResult(method);
				handleTestMethod(method, instance, new Object[0], testCaseResult);
				testClassResult.addTestCase(testCaseResult);
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
			
			Object[] parameterEntries = (Object[]) field.get(instance);
			MultiParameterTestCaseResult testCaseResult = new MultiParameterTestCaseResult(method);
			
			for (Object parameterEntry : parameterEntries)
			{	
				Object[] parameters = new Object[] { parameterEntry };
				
				if (parameterEntry != null && parameterEntry.getClass().isArray())
				{
					parameters = (Object[]) parameterEntry;
				}
				
				TestCaseEntryResult entryResult = new TestCaseEntryResult(parameters);
				handleTestMethod(method, instance, parameters, entryResult);
				testCaseResult.addEntryResult(entryResult);
			}
			
			testClassResult.addTestCase(testCaseResult);
		}
		
		return testClassResult;
	}
	
	public static List<TestClassResult> run(Class<?>... types)
	{
		throw new UnsupportedOperationException();
	}
	
	private static <T extends Statusable & Messagable> void handleTestMethod(
				Method method, 
				Object instance, 
				Object[] parameters, 
				T testResult) throws Throwable
	{
		try
		{
			if (parameters.length == 0)
			{
				method.invoke(instance);
			}
			else
			{
				method.invoke(instance, parameters);
			}
			
			testResult.setStatus(TestStatus.PASSED);
		}
		catch (InvocationTargetException e)
		{
			if (e.getTargetException().getClass().isAssignableFrom(AssertFailException.class))
			{
				testResult.setStatus(TestStatus.FAILED);
				testResult.setMessage(e.getTargetException().getMessage());
			}
			else
			{
				throw e.getTargetException();
			}
		}
	}
	
	private static List<Method> extractTestMethods(Class<?> type)
	{
		return Arrays
				.stream(type.getMethods())
				.filter(m -> isTestMethodSuitable(m))
				.collect(Collectors.toList());
	}
	
	private static boolean isTestMethodSuitable(Method method)
	{
		return method.getAnnotation(TestCase.class) != null
			   && Modifier.isPublic(method.getModifiers())
			   && method.getReturnType().equals(Void.TYPE);
	}
}
