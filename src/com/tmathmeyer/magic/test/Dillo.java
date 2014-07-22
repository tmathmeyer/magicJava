package com.tmathmeyer.magic.test;

import com.tmathmeyer.magic.Runtime;
import com.tmathmeyer.magic.test.Test.Model;

public class Dillo extends Runtime implements Model
{
	String name;
	Integer size;
	
	public Dillo(String name, Integer size)
	{
		this.name = name;
		this.size = size;
	}
	
	@Override
	public Object get(String fieldName) {
		if (fieldName == null || fieldName.equals(""))
		{
			return null;
		}
		else if (fieldName.equals("name"))
		{
			return name;
		}
		else if (fieldName.equals("size"))
		{
			return size;
		}
		return null;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getSize()
	{
		return size;
	}
	
}