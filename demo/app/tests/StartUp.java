package app.tests;

import edu.coolunit.CoolUnit;

public class StartUp
{
	public static void main(String[] args) throws Throwable
	{
		System.out.println(CoolUnit.run(PersonTests.class));
	}
}
