package com.tmathmeyer.magic;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.NotFoundException;

public class InstanceFactory
{
	private static Map<String, Pair<Class<?>, Class<?>>> generatedClasses = new HashMap<>();
	
	public static <T> boolean makeType (String classname, String packageName, ContextBuilder typeContext, Class<T> superinterface)
	{
		try
		{
			Class<T> newtype = ClassBuilder.generate(classname, packageName, typeContext, superinterface);
			generatedClasses.put(classname, new Pair<Class<?>, Class<?>>(newtype, superinterface));
			return true;
		}
		catch (NotFoundException | CannotCompileException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static <T> boolean makeType (String classname, ContextBuilder typeContext, Class<T> superinterface)
	{
		return makeType(classname, null, typeContext, superinterface);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T instantiate(String type, ArgumentBuilder ab) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		Pair<Class<?>, Class<?>> classType = generatedClasses.get(type);
		
		Class<?> defined = classType.getA();
		Class<T> cast = (Class<T>) classType.getB();
		
		T result = cast.cast(defined.getConstructor(ab.getTypes()).newInstance(ab.getParams()));
		return result;
	}
	
}
