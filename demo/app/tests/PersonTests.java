package app.tests;

import app.Text;
import app.Person;
import edu.coolunit.annotations.*;

import edu.coolunit.Assert;

@TestClass
public class PersonTests
{
	public String[] invalidNames = new String[]
	{
		null,
		"\n\t",
		Text.EMPTY,
		Text.create('X', Person.NameMaxLength + 1),
		Text.create('X', Person.NameMinLength - 1),
	};
	
	@TestCase
	public void demo_test()
	{
		
	}
	@TestCase
	@ParamsProvider("invalidNames")
	public void name_invalidValue_should_throwException(String name)
	{
		Assert.exception(() -> new Person(name, 5));
		Assert.isTrue(true, "marry() does not set correct value"); 
	}
}
