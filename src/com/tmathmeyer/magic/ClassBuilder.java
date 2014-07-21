package com.tmathmeyer.magic;

import java.util.Map.Entry;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class ClassBuilder
{
	@SuppressWarnings("unchecked")
	public static <T> Class<T> generate(String className, ContextBuilder  properties, Class<T> superinterface) throws NotFoundException, CannotCompileException
	{
		CtClass cc = ClassPool.getDefault().makeClass(className);
		cc.addInterface(resolveCtClass(superinterface));

		for (Entry<String, Class<?>> entry : properties) {

			cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));

			cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));

			cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
		}

		return (Class<T>)cc.toClass();
	}

	private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
			throws CannotCompileException {

		String getterName = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

		StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ")
				.append(getterName).append("(){return this.")
				.append(fieldName).append(";}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class<?> fieldClass)
			throws CannotCompileException {

		String setterName = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

		StringBuffer sb = new StringBuffer();
		sb.append("public void ").append(setterName).append("(")
				.append(fieldClass.getName()).append(" ").append(fieldName)
				.append(")").append("{").append("this.").append(fieldName)
				.append("=").append(fieldName).append(";").append("}");
		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtClass resolveCtClass(Class<?> clazz) throws NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		return pool.get(clazz.getName());
	}
}
