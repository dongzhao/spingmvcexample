package com.dzhao.springmvc.codegen.processor;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.RepositoryTemplate;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateMethod;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.*;

/**
 * Created by dzhao on 22/09/2015.
 */
public class RepositoryCodeGenProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return new HashSet<String>(Arrays.asList(GenerateRepository.class.getName()));
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("#####################################################");
        System.out.println("start generating repositories ...");
        for(TypeElement typeElement : annotations){
            System.out.println("#### typeElement name: " + typeElement.getQualifiedName().toString());
            System.out.println("#### GenerateRepository name: " + GenerateRepository.class.getName());
            if(!typeElement.getQualifiedName().toString().equalsIgnoreCase(GenerateRepository.class.getName())){
                continue;
            }
            Collection<? extends Element> types = roundEnv.getElementsAnnotatedWith(typeElement);
            for (Element element : types){

                StringBuilder targetSourcePath = new StringBuilder();
                GenerateRepository repository = element.getAnnotation(GenerateRepository.class);

                String modelSimpleName = element.getSimpleName().toString();
                String modelPackageName = repository.modelPackageName();
                String repositoryPackageName = repository.packageName();
                if(repositoryPackageName.isEmpty()){
                    StringBuilder sb = new StringBuilder();
                    sb.append(modelPackageName.substring(0, modelPackageName.lastIndexOf(".model."))+".repositories");
                    repositoryPackageName = sb.toString();
                }
                String simpleName = repository.simpleName();
                if(simpleName.isEmpty()){
                    simpleName = modelSimpleName + "Repository";
                }

                RepositoryTemplate template = new RepositoryTemplate();
                template.setModelPackageName(modelPackageName);
                template.setRepositoryPackageName(repositoryPackageName);
                template.setModelSimpleName(modelSimpleName);
                template.setRepositorySimpleName(simpleName);
                template.setRepositoryMethods(new ArrayList<String>());
                if(repository.method()!=null) {
                    for (GenerateMethod method : repository.method()) {
                        String fieldName = method.name();
                        System.out.println(" ### field name: "  + fieldName);
                        System.out.println(" ### field type name: "  + method.type());

                        StringBuilder sb = new StringBuilder();
                        sb.append("List<" + modelSimpleName + "> ");
                        sb.append("findBy");
                        sb.append(fieldName.substring(0, 1).toUpperCase());
                        sb.append(fieldName.substring(1, fieldName.length()));
                        sb.append("(" + method.type() + " " + fieldName + ");");
                        template.getRepositoryMethods().add(sb.toString());
                    }
                }

                try {
                    String sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
                    targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
                    System.out.println("targetSourcePath: " + targetSourcePath.toString());
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                targetSourcePath.append(repository.target());
                targetSourcePath.append(repositoryPackageName.replace(".", "/") + "/");
                targetSourcePath.append(simpleName + ".java");
                FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/repository.ftl", targetSourcePath.toString(), template);
                writer.write();
            }
        }
        return false;
    }

}
