package com.mlliu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD,
		ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnoation {
	String name() default "test";

	int id() default 0;
	
	Class<Long> gid() default Long.class;

}
