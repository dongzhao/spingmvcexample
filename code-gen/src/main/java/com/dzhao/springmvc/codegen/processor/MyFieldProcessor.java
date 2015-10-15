package com.dzhao.springmvc.codegen.processor;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.RepositoryTemplate;
import com.dzhao.springmvc.codegen.annotation.*;
import com.dzhao.springmvc.codegen.enums.OperatorEnum;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.*;

/**
 * Created by dzhao on 22/09/2015.
 */
public class MyFieldProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return new HashSet<String>(Arrays.asList(MyField.class.getName(), MyClass.class.getName()));
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        // Only one annotation, so just use annotations.iterator().next();
        for(TypeElement typeElement : annotations){
            System.out.println("#### typeElement name: " + typeElement.getQualifiedName().toString());
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(typeElement);
            if(typeElement.getQualifiedName().toString().equalsIgnoreCase(MyClass.class.getName())){
                // processing class annotation
                for (Element element : elements){
                    MyClass myClass = element.getAnnotation(MyClass.class);
                    System.out.println("### class name: " + myClass.name());
                }
            }else if(typeElement.getQualifiedName().toString().equalsIgnoreCase(MyField.class.getName())){
                // process field annoatation
                Set<VariableElement> fields = ElementFilter.fieldsIn(elements);
                for (VariableElement field : fields) {
                    TypeMirror fieldType = field.asType();
                    String fullTypeClassName = fieldType.toString();
                    System.out.println("### field type class name: " + fullTypeClassName);
                    System.out.println("### field name: " + field.getSimpleName());
                    MyField myField = field.getAnnotation(MyField.class);
                    System.out.println("### field annotated name: " + myField.name());
                }
            }else
                continue;
        }
        return false;
    }

}
