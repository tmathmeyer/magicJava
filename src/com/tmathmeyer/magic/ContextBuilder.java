package com.tmathmeyer.magic;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class ContextBuilder implements Iterable<Entry<String, Class<?>>>
{
	public static class BuilderStateA extends ContextBuilder
	{
		private SortedMap<String, Class<?>> context;
		public BuilderStateA()
		{
			context = new TreeMap<>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
				
			});
		}
		
		public BuilderStateA(SortedMap<String, Class<?>> newcontext, String s, Class<?> clazz)
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
		
		@Override
		public int getSize()
		{
			return context.size();
		}
	}
	
	public static class BuilderStateB<T> extends ContextBuilder
	{
		private SortedMap<String, Class<?>> context;
		private final Class<T> clazz;
		
		public BuilderStateB(SortedMap<String, Class<?>> newcontext, Class<T> clazz)
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

		@Override
		public int getSize()
		{
			return context.size();
		}
	}
	
	
	
	public static BuilderStateA createBuilder()
	{
		return new BuilderStateA();
	}

	public abstract int getSize();
}
