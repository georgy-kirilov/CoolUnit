package app.tests;

import app.Text;
import app.Person;
import edu.coolunit.Assert;
import edu.coolunit.annotations.*;

@TestClass
public class PersonTests
{
	private String[] invalidNames = new String[]
	{
		null,
		"\n\t",
		Text.EMPTY,
		Text.create('X', Person.NameMaxLength + 1),
		Text.create('X', Person.NameMinLength - 1),
	};
	
	@TestCase
	@ParamsProvider("invalidNames")
	public void name_invalidValue_should_throwException(String name)
	{
		Assert.throwsException(() -> new Person(name, 5));
	}
}
