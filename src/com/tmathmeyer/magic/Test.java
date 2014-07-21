package com.tmathmeyer.magic;

public class Test
{
	public static void main(String ... args) throws InstantiationException, IllegalAccessException
	{
		ContextBuilder cb = ContextBuilder.createBuilder()
							.fieldType(Integer.class).named("size")
							.fieldType(String.class).named("name");
		
		InstanceFactory.makeType("dillo", cb, IAnimal.class);
		
		IAnimal test = InstanceFactory.instantiate("testType");
		
		System.out.println(test.getClass().getName());
	}
	
	public static interface IAnimal
	{
		
	}
}