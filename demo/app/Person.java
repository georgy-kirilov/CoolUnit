package app;

public class Person
{
	public static final int NameMaxLength = 30;
	public static final int NameMinLength = 2;
	
	private String name;
	private int age;
	private boolean married;
	private final boolean male;
	
	public Person(String name, int age)
	{
		this(name, age, true);
	}
	
	public Person(String name, int age, boolean male)
	{		
		setName(name);
		setAge(age);
		married = false;
		this.male = male;
	}
	
	public String getName()
	{
		return name;
	}
	
	private void setName(String value)
	{
		if (value == null || value.trim().length() < NameMinLength || value.trim().length() > NameMaxLength)
		{
			throw new IllegalArgumentException();
		}
		
		name = value;
	}
	
	public int getAge()
	{
		return age;
	}
	
	private void setAge(int value)
	{
		if (value < 0 || value > 150)
		{
			throw new IllegalArgumentException();
		}
		
		age = value;
	}
	
	public boolean isMale()
	{
		return male;
	}
	
	public boolean isMarried()
	{
		return married;
	}
	
	public void grow()
	{
		setAge(getAge() + 1);
	}
	
	public boolean isSuitableForMarriage()
	{
		return !isMarried() && getAge() >= 18;
	}
	
	public void marry(Person parthner)
	{
		if (!isSuitableForMarriage() || !parthner.isSuitableForMarriage() || parthner.isMale() == isMale())
		{
			throw new IllegalStateException();
		}
		
		married = true;
	}
}
