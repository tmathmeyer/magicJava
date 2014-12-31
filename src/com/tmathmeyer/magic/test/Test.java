package com.tmathmeyer.magic.test;

import java.lang.reflect.InvocationTargetException;

import com.tmathmeyer.magic.ArgumentBuilder;
import com.tmathmeyer.magic.ContextBuilder;
import com.tmathmeyer.magic.InstanceFactory;
import com.tmathmeyer.magic.Runtime;

public class Test
{
	public static void main(String ... args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		ContextBuilder cb = ContextBuilder.createBuilder()
							.fieldType(Integer.class).named("size")
							.fieldType(String.class).named("name");
		ArgumentBuilder ab = new ArgumentBuilder().add("tommy").add(7);
		
		InstanceFactory.makeType("Dillo", "com.tmathmeyer.magic.test", cb, Model.class);
		
		Runtime test = InstanceFactory.instantiate("Dillo", ab);
		
		System.out.println(test.get("name", String.class));
		
		System.out.println(test.getClass().getName());
	}
	
	public static interface Model
	{
		
	}
	
	
}
