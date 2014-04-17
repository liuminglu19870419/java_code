package com.mlliu.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@MyAnnoation(name = "MyAnnoation", id = 100)
//
public class TestAnnoation {
	@MyAnnoation(name = "age", id = 200)
	private Integer age;

	@MyAnnoation(name = "TestAnnoation", id = 100)
	public TestAnnoation() {

	}

	@MyAnnoation(name = "a", id = 100)
	public void a() {
		@MyAnnoation(name = "map", id = 100)
		Map<String, String> map = new HashMap<String, String>();
	}

	public void b(@MyAnnoation(name = "a", id = 500) Integer a) {

	}

	public static void main(String[] args) throws ClassNotFoundException {

		Class clazz = Class.forName("com.mlliu.annotation.TestAnnoation");
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			MyAnnoation testA = (MyAnnoation) annotation;
			System.out.println("id= \"" + testA.id() + "\"; name= \""
					+ testA.name() + "\"; gid = " + testA.gid());
		}

		Method[] methods = TestAnnoation.class.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(MyAnnoation.class)) {
				MyAnnoation annotation = method.getAnnotation(MyAnnoation.class);
				System.out.println("method = " + method.getName() + " ; id = "
						+ annotation.id() + " ; description = "
						+ annotation.name() + "; gid= " + annotation.gid());
			}
		}
	}
}
