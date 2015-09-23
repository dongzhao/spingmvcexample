package com.dzhao.springmvc.codegen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dzhao on 22/09/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GenerateRestController {
    String modelPackageName() default "";
    String repositoryPackageName() default "";
    String modelName() default "";
    String simpleName() default "";
    String simplePackageName() default "";
    String urlPath() default "";
    String target() default "target/generated-sources/apt/";
}
