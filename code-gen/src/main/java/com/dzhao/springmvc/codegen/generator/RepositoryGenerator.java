package com.dzhao.springmvc.codegen.generator;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.RepositoryTemplate;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.Collection;


/**
 * Created by dzhao on 22/09/2015.
 */
public class RepositoryGenerator extends AbstractGenerator{

    public RepositoryGenerator(
            TypeElement typeElement,
            RoundEnvironment roundEnv,
            ProcessingEnvironment processingEnv){
        super(typeElement, roundEnv, processingEnv);
    }

    @Override
    public void execute() {
        Collection<? extends Element> types = roundEnv.getElementsAnnotatedWith(typeElement);

        int count = 0;
        for (Element element : types){
            count++;
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

            RepositoryTemplate info = new RepositoryTemplate();
            info.setModelPackageName(modelPackageName);
            info.setRepositoryPackageName(repositoryPackageName);
            info.setModelSimpleName(modelSimpleName);
            info.setRepositorySimpleName(simpleName);
            try {
                // to add the target folder rootPath
                String sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
                targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
                System.out.println("targetSourcePath: " + targetSourcePath.toString());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            // to aggregate the target rootPath from annotation target
            targetSourcePath.append(repository.target());
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            // to aggregate the target rootPath from annotation package name
            System.out.println("modelPackageName: " + modelPackageName);
            System.out.println("repsitoryPackageName: " + repositoryPackageName);
            System.out.println("repsitoryPackageName: " + repositoryPackageName.replace(".", "/"));
            targetSourcePath.append(repositoryPackageName.replace(".", "/") + "/");
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            // to aggregate the target rootPath from annotation simple dao class name
            targetSourcePath.append(simpleName + ".java");
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/repository.ftl", targetSourcePath.toString(), info);
            writer.write();
        }
    }
}
