package com.dzhao.springmvc.codegen.processor;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.RepositoryTemplate;
import com.dzhao.springmvc.codegen.annotation.*;
import com.dzhao.springmvc.codegen.enums.OperatorEnum;
import com.dzhao.springmvc.codegen.template.FieldInfoTemplate;
import com.dzhao.springmvc.codegen.template.FieldTestValueTemplate;
import com.dzhao.springmvc.codegen.template.PersistentTestTemplate;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.*;

/**
 * Created by dzhao on 22/09/2015.
 */
public class PersistentTestCodeGenProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return new HashSet<String>(Arrays.asList(GeneratePersistentTest.class.getName()));
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("#####################################################");
        System.out.println("start generating persistent test class ...");
        for(TypeElement typeElement : annotations){
            if(!typeElement.getQualifiedName().toString().equalsIgnoreCase(GeneratePersistentTest.class.getName())){
                continue;
            }
            Collection<? extends Element> types = roundEnv.getElementsAnnotatedWith(typeElement);
            for (Element element : types){

                StringBuilder targetSourcePath = new StringBuilder();
                GeneratePersistentTest test = element.getAnnotation(GeneratePersistentTest.class);

                TypeElement classElement = (TypeElement)element;
                PackageElement packageElement = (PackageElement)classElement.getEnclosingElement();

                String modelSimpleName = classElement.getSimpleName().toString();
                String modelPackageName = packageElement.getQualifiedName().toString();
                String repositorySimpleName = modelSimpleName + "Repository";
                String repositoryPackageName = test.repositoryPackageName();
                if(repositoryPackageName.isEmpty()){
                    StringBuilder sb = new StringBuilder();
                    sb.append(modelPackageName.substring(0, modelPackageName.lastIndexOf(".model"))+".repositories");
                    repositoryPackageName = sb.toString();
                }
                String simpleName = test.simpleName();
                if(simpleName.isEmpty()){
                    simpleName = modelSimpleName + "PersistentTest";
                }
                String pkgName = test.packageName();
                if(pkgName.isEmpty()){
                    pkgName = modelPackageName;
                }
                PersistentTestTemplate template = new PersistentTestTemplate();
                template.setModelPackageName(modelPackageName);
                template.setRepositoryPackageName(repositoryPackageName);
                template.setModelSimpleName(modelSimpleName);
                template.setRepositorySimpleName(repositorySimpleName);
                template.setUnitTestSimpleName(simpleName);
                template.setUnitTestPackageName(pkgName);
                template.setFieldTestValueTemplates(new ArrayList<FieldTestValueTemplate>());
                for (Element enclosedElement : element.getEnclosedElements()) {
                    if (enclosedElement.getKind() == ElementKind.FIELD) {
                        HasTestValue hasTestValue = enclosedElement.getAnnotation(HasTestValue.class);
                        if(hasTestValue!=null){
                            String fieldType = enclosedElement.asType().toString();
                            FieldTestValueTemplate fieldInfoTemplate = new FieldTestValueTemplate();
                            fieldInfoTemplate.setName(enclosedElement.getSimpleName().toString());
                            fieldInfoTemplate.setType(fieldType);
                            fieldInfoTemplate.setTestValueIn(resetModelValue(hasTestValue.input(), fieldType));
                            String valueOut = hasTestValue.expected().isEmpty() ? hasTestValue.input() : hasTestValue.expected();
                            fieldInfoTemplate.setTestValueOut(resetModelValue(valueOut, fieldType));
                            template.getFieldTestValueTemplates().add(fieldInfoTemplate);
                        }
                    }
                }

                try {
                    String sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
                    targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
                    System.out.println("targetSourcePath: " + targetSourcePath.toString());
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

                targetSourcePath.append(test.target());
                targetSourcePath.append(repositoryPackageName.replace(".", "/") + "/");
                targetSourcePath.append(simpleName + ".java");
                FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/persistentTest.ftl", targetSourcePath.toString(), template);
                writer.write();
            }
        }
        return false;
    }

    private static String resetModelValue(String value, String type){
        if(boolean.class.getName().equals(type) || Boolean.class.getName().equals(type) ||
                int.class.getName().equals(type) || Integer.class.getName().equals(type) ||
                float.class.getName().equals(type) || Float.class.getName().equals(type) ||
                double.class.getName().equals(type) || Double.class.getName().equals(type) ||
                char.class.getName().equals(type) || Character.class.getName().equals(type) ){
            return value;
        }else if(String.class.getName().equals(type)){
            return "\"" + value + "\"";
        }
        throw new RuntimeException("Unsupported field type [" + type + "]");
    }

}
