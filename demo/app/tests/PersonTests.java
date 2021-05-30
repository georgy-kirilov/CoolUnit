package app.tests;

import app.Text;
import app.Person;
import edu.coolunit.annotations.*;

import edu.coolunit.Assert;

@TestClass
public class PersonTests
{
	public Integer[] invalidAges = new Integer[] { -5, -10, 160, };
	
	public Integer[] validAges = new Integer[] { 0, 150, 69, 13, 40, };
	
	public String[] validNames = new String[]
	{
		"John",
		"Steven",
		"Alex",
		"Denis",
	};
	
	public String[] invalidNames = new String[]
	{
		null,
		"\n\t",
		Text.EMPTY,
		Text.create('X', Person.NameMaxLength + 1),
		Text.create('X', Person.NameMinLength - 1),
	};
	
	public Object[] ctorValues = new Object[]
	{
		new Object[] {"Ivan", 10 },	
	};
	
	@TestCase
	@ParamsProvider("ctorValues")
	public void ctor_validInput_should_createInstance(String name, int age)
	{
		Assert.fail();
	}
	
	@TestCase
	@ParamsProvider("validAges")
	public void age_validValue_should_setAgeCorrectly(int age)
	{
		Person person = new Person("John", age);
		Assert.areEqual(age, person.getAge());
	}
	
	@TestCase
	@ParamsProvider("invalidAges")
	public void age_invalidValue_should_throwException(int age)
	{
		Assert.exception(IllegalArgumentException.class, () -> new Person("John", age));
	}
	
	@TestCase
	@ParamsProvider("validNames")
	public void name_validValue_should_setNameCorrectly(String name)
	{
		Person person = new Person(name, 5);
		Assert.areEqual(name, person.getName());
	}
	
	@TestCase
	@ParamsProvider("invalidNames")
	public void name_invalidValue_should_throwException(String name)
	{
		Assert.exception(() -> new Person(name, 5));
	}
}
