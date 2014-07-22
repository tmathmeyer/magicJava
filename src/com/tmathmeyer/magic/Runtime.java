package com.tmathmeyer.magic;

public abstract class Runtime
{
	public <T> T get(String fieldName, Class<T> type)
	{
		return type.cast(get(fieldName));
	}
	
	public abstract Object get(String fieldName);
}