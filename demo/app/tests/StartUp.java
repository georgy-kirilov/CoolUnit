package app.tests;

import java.lang.reflect.InvocationTargetException;

import edu.coolunit.CoolUnit;

public class StartUp
{

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
	{
		CoolUnit.run(PersonTests.class);

	}

}
