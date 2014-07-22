package com.tmathmeyer.magic;

import java.util.ArrayList;
import java.util.List;

public class ArgumentBuilder
{
	private final List<Class<?>> types;
	private final List<Object> objects;
	
	public ArgumentBuilder()
	{
		types = new ArrayList<>();
		objects = new ArrayList<>();
	}
	
	private ArgumentBuilder(ArgumentBuilder ab, Object o)
	{
		types = new ArrayList<>(ab.types);
		objects = new ArrayList<>(ab.objects);
		types.add(o.getClass());
		objects.add(o);
	}
	
	public ArgumentBuilder add(Object o)
	{
		return new ArgumentBuilder(this, o);
	}
	
	public Class<?>[] getTypes()
	{
		Class<?>[] result = new Class<?>[types.size()];
		types.toArray(result);
		return result;
	}
	
	public Object[] getParams()
	{
		Object[] result = new Object[types.size()];
		objects.toArray(result);
		return result;
	}
}
