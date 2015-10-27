package com.dzhao.springmvc.codegen.processor;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.MethodTemplate;
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
public class RepositoryCodeGenProcessorTest extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return new HashSet<String>(Arrays.asList(GenerateRepository.class.getName(), GenerateMethod.class.getName()));
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        RepositoryTemplate template = new RepositoryTemplate();
        List<MethodTemplate> methodTemplates = new ArrayList<MethodTemplate>();
        StringBuilder targetSourcePath = new StringBuilder();
        for(TypeElement typeElement : annotations){
            Set<? extends Element> types = roundEnv.getElementsAnnotatedWith(typeElement);
            if(typeElement.getQualifiedName().toString().equalsIgnoreCase(GenerateRepository.class.getName())) {
                for (Element element : types) {
                    GenerateRepository repository = element.getAnnotation(GenerateRepository.class);
                    String modelSimpleName = element.getSimpleName().toString();
                    String modelPackageName = repository.modelPackageName();
                    String repositoryPackageName = repository.packageName();
                    if (repositoryPackageName.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(modelPackageName.substring(0, modelPackageName.lastIndexOf(".model.")) + ".repositories");
                        repositoryPackageName = sb.toString();
                    }
                    String simpleName = repository.simpleName();
                    if (simpleName.isEmpty()) {
                        simpleName = modelSimpleName + "Repository";
                    }
                    
                    template.setModelPackageName(modelPackageName);
                    template.setRepositoryPackageName(repositoryPackageName);
                    template.setModelSimpleName(modelSimpleName);
                    template.setRepositorySimpleName(simpleName);
                    template.setRepositoryMethods(new ArrayList<String>());
                    template.setTargetUrl(repository.target());
                    
/*                    if (repository.joinedMethod() != null) {
                        for (GenerateJoinedMethod joinedMethod : repository.joinedMethod()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("List<" + modelSimpleName + "> ");
                            sb.append("findBy");

                            StringBuilder methodNameBuilder = new StringBuilder();
                            StringBuilder parameterBuilder = new StringBuilder();
                            for (GenerateMethod method : joinedMethod.method()) {
                                OperatorEnum operator = method.opertor();
                                String fieldName = method.name();
                                System.out.println(" ### field name: " + fieldName);
                                System.out.println(" ### field type name: " + method.type());

                                if (methodNameBuilder.length() > 0) {
                                    methodNameBuilder.append("And");
                                }
                                methodNameBuilder.append(fieldName.substring(0, 1).toUpperCase());
                                methodNameBuilder.append(fieldName.substring(1, fieldName.length()));
                                if (!operator.equals(OperatorEnum.EQUALS)) {
                                    methodNameBuilder.append(operator.getValue());
                                }
                                if (parameterBuilder.length() > 0) {
                                    parameterBuilder.append(", ");
                                }
                                parameterBuilder.append(method.type() + " " + fieldName);
                            }
                            sb.append(methodNameBuilder);
                            sb.append("(" + parameterBuilder + ");");
                            template.getRepositoryMethods().add(sb.toString());
                        }
                    }*/
                }
            }else if(typeElement.getQualifiedName().toString().equalsIgnoreCase(GenerateMethod.class.getName())){
                Set<VariableElement> fields = ElementFilter.fieldsIn(types);
                for (VariableElement field : fields) {
                    MethodTemplate methodTemplate = new MethodTemplate();

                    TypeMirror fieldType = field.asType();
                    String fullTypeClassName = fieldType.toString();
                    methodTemplate.setType(fullTypeClassName);
                    GenerateMethod method = field.getAnnotation(GenerateMethod.class);
                    if(method.name()==null || method.name().isEmpty() ){
                        methodTemplate.setName(field.getSimpleName().toString());
                    }else{
                        methodTemplate.setName(method.name());
                    }
                    methodTemplate.setOperator(method.opertor().getValue());
                    methodTemplates.add(methodTemplate);
                }                    
            }
        }

        if(template.getModelPackageName()==null || template.getModelPackageName().isEmpty() )
        {
            return false;
        }

        try {
            String sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
            targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for (MethodTemplate methodTemplate : methodTemplates){
            StringBuilder sb = new StringBuilder();
            sb.append("List<" + template.getModelSimpleName() + "> ");
            sb.append("findBy");
            sb.append(methodTemplate.getName().substring(0, 1).toUpperCase());
            sb.append(methodTemplate.getName().substring(1, methodTemplate.getName().length()));
            if (!methodTemplate.getOperator().equals(OperatorEnum.EQUALS.getValue())) {
                sb.append(methodTemplate.getOperator());
            }
            sb.append("(" + methodTemplate.getType() + " " + methodTemplate.getName() + ");");
            template.getRepositoryMethods().add(sb.toString());
        }
        System.out.println("### url: " + template.getTargetUrl());
        targetSourcePath.append(template.getTargetUrl());
        System.out.println("### model : " + template.getModelPackageName());
        System.out.println("### package name: " + template.getRepositoryPackageName());
        targetSourcePath.append(template.getRepositoryPackageName().replace(".", "/") + "/");
        targetSourcePath.append(template.getRepositorySimpleName() + ".java");
        FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/repository.ftl", targetSourcePath.toString(), template);
        writer.write();


        return false;
    }

}
