package com.dzhao.springmvc.codegen.processor;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.MethodTemplate;
import com.dzhao.springmvc.codegen.RepositoryTemplate;
import com.dzhao.springmvc.codegen.RestControllerTemplate;
import com.dzhao.springmvc.codegen.annotation.GenerateJoinedMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.annotation.GenerateRestController;
import com.dzhao.springmvc.codegen.enums.OperatorEnum;

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
public class RestControllerCodeGenProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return new HashSet<String>(Arrays.asList(GenerateRestController.class.getName()));
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("#####################################################");
        System.out.println("start generating restcontroller ...");
        for(TypeElement typeElement : annotations){
            if(!typeElement.getQualifiedName().toString().equalsIgnoreCase(GenerateRestController.class.getName())){
                continue;
            }
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

                RestControllerTemplate template = new RestControllerTemplate();
                template.setModelPackageName(modelPackageName);
                template.setRepositoryPackageName(repositoryPackageName);
                template.setModelClassName(modelClassName);
                template.setModelInstanceName(modelInstanceName);
                template.setRepositoryClassName(repositoryClassName);
                template.setRepositoryInstanceName(repositoryInstanceName);
                template.setPackageName(packageName);
                template.setSimpleName(simpleName);
                template.setUrlPathName(urlBuilder.toString());
                template.setMethods(new ArrayList<MethodTemplate>());
                if(controller.method()!=null) {
                    for (GenerateMethod method : controller.method()) {
                        OperatorEnum operator = method.opertor();
                        String fieldName = method.name();
                        MethodTemplate methodTemplate = new MethodTemplate();
                        methodTemplate.setName(method.name());
                        methodTemplate.setType(method.type());
                        StringBuilder sb = new StringBuilder();
                        sb.append("findBy");
                        sb.append(fieldName.substring(0, 1).toUpperCase());
                        sb.append(fieldName.substring(1, fieldName.length()));
                        if (!operator.equals(OperatorEnum.EQUALS)) {
                            sb.append(operator.getValue());
                        }
                        methodTemplate.setMethod(sb.toString());
                        template.getMethods().add(methodTemplate);
                    }
                }

                if(controller.joinedMethod()!=null) {
                    for (GenerateJoinedMethod joinedMethod : controller.joinedMethod()) {
                        for (GenerateMethod method : joinedMethod.method()){
                            OperatorEnum operator = method.opertor();
                            String fieldName = method.name();
                            MethodTemplate methodTemplate = new MethodTemplate();
                            methodTemplate.setName(method.name());
                            methodTemplate.setType(method.type());
                            StringBuilder sb = new StringBuilder();
                            sb.append("findBy");
                            sb.append(fieldName.substring(0, 1).toUpperCase());
                            sb.append(fieldName.substring(1, fieldName.length()));
                            if (!operator.equals(OperatorEnum.EQUALS)) {
                                sb.append(operator.getValue());
                            }
                            methodTemplate.setMethod(sb.toString());
                            template.getMethods().add(methodTemplate);
                        }


                    }
                }


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
                FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/restcontroller.ftl", targetSourcePath.toString(), template);
                writer.write();
            }
        }
        return false;
    }

}
