package me.moer.api;

import org.androidannotations.annotations.ResId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Yun on 2016. 5. 13..
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface IntervalClick {

    int[] value() default ResId.DEFAULT_VALUE;

    String[] resName() default "";

    long intervalMilliseconds() default 600;

}
