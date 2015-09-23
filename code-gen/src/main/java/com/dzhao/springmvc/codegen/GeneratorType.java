package com.dzhao.springmvc.codegen;

import com.dzhao.springmvc.codegen.generator.AbstractGenerator;
import com.dzhao.springmvc.codegen.generator.RepositoryGenerator;
import com.dzhao.springmvc.codegen.generator.RestControllerGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dzhao on 22/09/2015.
 */
public enum GeneratorType {
    REPOSITORY("com.dzhao.springmvc.codegen.annotation.GenerateRepository", RepositoryGenerator.class),
    RESTCONTROLLER("com.dzhao.springmvc.codegen.annotation.GenerateRestController", RestControllerGenerator.class);
    private final String annotationClassName;
    private final Class<? extends AbstractGenerator> clazz;

    private GeneratorType(String annotationClassName, Class<? extends AbstractGenerator> clazz){
        this.annotationClassName = annotationClassName;
        this.clazz = clazz;
    }

    public String getAnnotationClassName(){
        return annotationClassName;
    }

    public Class<? extends AbstractGenerator> getClazz(){
        return clazz;
    }

    public static List<String> listAnnotationClassNames(){
        List<String> values = new ArrayList<String>();
        for(GeneratorType type : Arrays.asList(GeneratorType.values())) {
            values.add(type.getAnnotationClassName());
        }
        return values;
    }

    public static GeneratorType valueFrom(String annotationClassName){
        for(GeneratorType type : Arrays.asList(GeneratorType.values())) {
            if(annotationClassName.equals(type.getAnnotationClassName())){
                return type;
            }
        }
        throw new UnsupportedOperationException("unsupported enum type of the annotation class name ["+ annotationClassName +"]");
    }
}
