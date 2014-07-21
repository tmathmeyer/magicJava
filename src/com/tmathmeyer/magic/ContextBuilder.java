package com.tmathmeyer.magic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ContextBuilder implements Iterable<Entry<String, Class<?>>>
{
	static class BuilderStateA extends ContextBuilder
	{
		private Map<String, Class<?>> context;
		public BuilderStateA()
		{
			context = new HashMap<>();
		}
		
		public BuilderStateA(Map<String, Class<?>> newcontext, String s, Class<?> clazz)
		{
			context = newcontext;
			context.put(s, clazz);
		}

		public <T> BuilderStateB<T> fieldType(Class<T> clazz)
		{
			return new BuilderStateB<T>(context, clazz);
		}

		@Override
		public Iterator<Entry<String, Class<?>>> iterator()
		{
			return context.entrySet().iterator();
		}
	}
	
	static class BuilderStateB<T> extends ContextBuilder
	{
		private Map<String, Class<?>> context;
		private final Class<T> clazz;
		
		public BuilderStateB(Map<String, Class<?>> newcontext, Class<T> clazz)
		{
			context = newcontext;
			this.clazz = clazz;
		}
		
		public BuilderStateA named(String s)
		{
			return new BuilderStateA(context, s, clazz);
		}
		
		@Override
		public Iterator<Entry<String, Class<?>>> iterator()
		{
			return context.entrySet().iterator();
		}
	}
	
	
	
	public static BuilderStateA createBuilder()
	{
		return new BuilderStateA();
	}
}
