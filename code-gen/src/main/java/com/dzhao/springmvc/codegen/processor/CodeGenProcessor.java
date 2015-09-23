package com.dzhao.springmvc.codegen.processor;

import com.dzhao.springmvc.codegen.GeneratorType;
import com.dzhao.springmvc.codegen.generator.AbstractGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dzhao on 22/09/2015.
 */
public class CodeGenProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return new HashSet<String>(GeneratorType.listAnnotationClassNames());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        System.out.println("#####################################################");
        System.out.println("start generating code ...");
        for(TypeElement typeElement : annotations){
            GeneratorType type = getTypeByName(typeElement.getQualifiedName().toString());
            if(type==null){
                continue;
            }
            Class<? extends AbstractGenerator> cls = type.getClazz();

            try {
                Constructor<? extends AbstractGenerator> ct = cls.getConstructor(
                        TypeElement.class,
                        RoundEnvironment.class,
                        ProcessingEnvironment.class
                );

                try {
                    AbstractGenerator generator = ct.newInstance(typeElement, roundEnv, processingEnv);
                    generator.execute();
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return false;
    }

    private GeneratorType getTypeByName(String typeName){
        try{
            return GeneratorType.valueFrom(typeName);
        }catch(Exception e){
            return null;
        }
    }
}
