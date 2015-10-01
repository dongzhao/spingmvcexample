package com.dzhao.springmvc.codegen.generator;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.RestControllerTemplate;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;

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
public class RestControllerGenerator extends AbstractGenerator{

    public RestControllerGenerator(
            TypeElement typeElement,
            RoundEnvironment roundEnv,
            ProcessingEnvironment processingEnv){
        super(typeElement, roundEnv, processingEnv);
    }

    @Override
    public void execute() {
        Collection<? extends Element> types = roundEnv.getElementsAnnotatedWith(typeElement);
        for (Element element : types){
            StringBuilder targetSourcePath = new StringBuilder();
            GenerateRestController controller = element.getAnnotation(GenerateRestController.class);

            String modelClassName = element.getSimpleName().toString();
            String modelInstanceName = modelClassName.replaceFirst(modelClassName.substring(0, 1), modelClassName.substring(0, 1).toLowerCase());
            String modelPackageName = controller.modelPackageName();
            String repositoryPackageName = controller.repositoryPackageName();
            if(repositoryPackageName.isEmpty()){
                StringBuilder sb = new StringBuilder();
                sb.append(modelPackageName.substring(0, modelPackageName.lastIndexOf(".model."))+".repositories.*");
                repositoryPackageName = sb.toString();
            }
            String repositoryClassName = modelClassName + "Repository";
            String repositoryInstanceName = repositoryClassName.replaceFirst(repositoryClassName.substring(0, 1), repositoryClassName.substring(0, 1).toLowerCase());
            String simpleName = controller.simpleName();
            if(simpleName.isEmpty()){
                simpleName = modelClassName + "RestController";
            }
            String packageName = controller.simplePackageName();
            if(packageName.isEmpty()){
                StringBuilder sb = new StringBuilder();
                sb.append(modelPackageName.substring(0, modelPackageName.lastIndexOf(".model."))+".controllers");
                packageName = sb.toString();
            }

            StringBuilder urlBuilder = new StringBuilder(controller.rootPath());
            if(!controller.rootPath().endsWith("/")){
                urlBuilder.append("/");
            }
            urlBuilder.append(modelClassName);

            RestControllerTemplate info = new RestControllerTemplate();
            info.setModelPackageName(modelPackageName);
            info.setRepositoryPackageName(repositoryPackageName);
            info.setModelClassName(modelClassName);
            info.setModelInstanceName(modelInstanceName);
            info.setRepositoryClassName(repositoryClassName);
            info.setRepositoryInstanceName(repositoryInstanceName);
            info.setPackageName(packageName);
            info.setSimpleName(simpleName);
            info.setUrlPathName(urlBuilder.toString());
            try {
                // to add the target folder rootPath
                String sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
                targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
                System.out.println("targetSourcePath: " + targetSourcePath.toString());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            // to aggregate the target rootPath from annotation target
            targetSourcePath.append(controller.target());
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            // to aggregate the target rootPath from annotation package name
            targetSourcePath.append(packageName.replace(".", "/") + "/");
            // to aggregate the target rootPath from annotation simple dao class name
            targetSourcePath.append(simpleName + ".java");
            FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/rest-controller.ftl", targetSourcePath.toString(), info);
            writer.write();
        }
    }
}
