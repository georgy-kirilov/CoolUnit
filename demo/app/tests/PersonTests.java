package app.tests;

import app.Text;
import app.Person;
import edu.coolunit.Assert;
import edu.coolunit.annotations.*;
import edu.coolunit.exceptions.MissingAnnotationException;

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
	@ParamsProvider("invalidNames")
	public void name_invalidValue_should_throwException(String name)
	{
		Assert.throwsException(IllegalArgumentException.class, () -> new Person(name, 5), false);
	}
}
