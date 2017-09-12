package com.e12e.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ThreadUtils implements Runnable {

	public ThreadUtils(Class<?> clazz, String methodName, Object param) {
		this.clazz = clazz;
		this.methodName = methodName;
		this.param = param;
	}

	public static void main(String[] args) {
		// createThread(10, Test.class, "show", "666");
		List list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		System.out.println(listCut(list, 4));
	}

	/**
	 * list分割
	 * 
	 * @param list
	 *            目标list
	 * @param count
	 *            分割数量
	 * @return
	 */
	public static List listCut(List list, int count) {
		List result = new ArrayList();
		if (list != null) {
			if (list.size() <= count) {
				result.add(list);
			} else {
				int i = list.size() / count;
				int j = list.size() % count;
				System.out.println(i + ":" + j);
				List addList = new ArrayList();
				int listCount = i + 1;
				for (int k = 0; k < list.size(); k++) {
					addList.add(list.get(k));
					if (addList.size() == listCount) {
						result.add(addList);
						addList = new ArrayList();
					}
					if (result.size() == j) {
						listCount = i;
					}
				}
			}
		}
		return result;

	}

	private static List createParams(Object... objects) {
		List list = new ArrayList();
		for (Object obj : objects) {
			list.add(obj);
		}
		return list;
	}

	/**
	 * 创建线程
	 * 
	 * @param threadCount
	 *            线程数量
	 * @param clazz
	 *            线程类
	 * @param methodName
	 *            线程类的方法名
	 * @param objects
	 *            线程参数
	 */
	public static void createThread(List list, Class clazz, String methodName) {
		for (int i = 0; i < list.size(); i++) {
			new Thread(new ThreadUtils(clazz, methodName, list.get(i))).start();
		}
	}

	/**
	 * 创建线程
	 * 
	 * @param threadCount
	 *            线程数量
	 * @param clazz
	 *            线程类
	 * @param methodName
	 *            线程类的方法名
	 * @param objects
	 *            线程参数
	 */
	public static void createThread(int threadCount, Class clazz, String methodName, Object... objects) {
		List list = createParams(objects);
		for (int i = 0; i < threadCount; i++) {
			new Thread(new ThreadUtils(clazz, methodName, list)).start();
		}
	}

	@Override
	public void run() {
		try {
			Method method = clazz.getMethod(methodName, List.class);
			method.invoke(clazz.newInstance(), param);
		} catch (Exception e) {
			System.out.println("创建线程失败");
			e.printStackTrace();
		}

	}

	private Class<?> clazz;

	private String methodName;

	private Object param;

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}
