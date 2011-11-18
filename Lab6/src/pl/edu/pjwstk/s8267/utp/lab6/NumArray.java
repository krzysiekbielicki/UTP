package pl.edu.pjwstk.s8267.utp.lab6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NumArray<T extends Number> {
	ArrayList<T> list;
	Class ret;
	private static Map<Class, Method> parsers;
	private static HashMap<Class, Method> fromDouble;
	static {
		parsers = new HashMap<Class, Method>();
		fromDouble = new HashMap<Class, Method>();
		try {
			parsers.put(Integer.class, Integer.class.getMethod("parseInt", String.class));
			parsers.put(Double.class, Double.class.getMethod("parseDouble", String.class));
			parsers.put(Float.class, Float.class.getMethod("parseFloat", String.class));
			parsers.put(Long.class, Long.class.getMethod("parseLong", String.class));
			parsers.put(Short.class, Short.class.getMethod("parseShort", String.class));
			
			fromDouble.put(Integer.class, Double.class.getMethod("intValue"));
			fromDouble.put(Double.class, Double.class.getMethod("doubleValue"));
			fromDouble.put(Float.class, Double.class.getMethod("floatValue"));
			fromDouble.put(Long.class, Double.class.getMethod("longValue"));
			fromDouble.put(Short.class, Double.class.getMethod("shortValue"));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public NumArray() {
		list = new ArrayList<T>();
	}
	
	public static NumArray of(String s) throws ClassNotFoundException {
		NumArray na = new NumArray();
		na.ret = Class.forName("java.lang."+s);
		return na;
	}
	
	public static NumArray of(Class c) throws ClassNotFoundException {
		NumArray na = new NumArray();
		na.ret = c;
		return na;
	}
	
	public NumArray from(String text) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] str = text.trim().split(" ");
		for(String s : str) {
			list.add((T) parsers.get(ret).invoke(null, s));
		}
		return this;
	}
	
	public NumArray from(File f) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		BufferedReader in = new BufferedReader(new FileReader("test.txt"));

		String str;
		while ((str = in.readLine()) != null)
			list.add((T) parsers.get(ret).invoke(null, str));
		return this;
	}
	
	public Number sum() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Double sum = 0d;
		for(T n : list) {
			sum += n.doubleValue();
		}
		return (Number) fromDouble.get(ret).invoke(sum);
	}
}
