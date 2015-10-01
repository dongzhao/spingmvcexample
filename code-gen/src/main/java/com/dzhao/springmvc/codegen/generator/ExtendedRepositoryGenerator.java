package com.dzhao.springmvc.codegen.generator;

import com.dzhao.springmvc.codegen.FreeMarkerWriter;
import com.dzhao.springmvc.codegen.RepositoryTemplate;
import com.dzhao.springmvc.codegen.annotation.GenerateRepo;
import com.dzhao.springmvc.codegen.annotation.GenerateRepoMethod;
import com.dzhao.springmvc.codegen.annotation.GenerateRepository;
import com.dzhao.springmvc.codegen.domain.GenerateRepoTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dzhao on 30/09/2015.
 */
public class ExtendedRepositoryGenerator {

    private ExtendedRepositoryGenerator(){}

    public static void execute(Class<?> clazz){

        StringBuilder targetSourcePath = new StringBuilder();
        GenerateRepo repository = clazz.getAnnotation(GenerateRepo.class);
        if(repository==null)
            return;
        String modelSimpleName = clazz.getSimpleName().toString();
        String modelPackageName = clazz.getPackage().getName()+".*";
        String packageName = repository.packageName();

        if(packageName.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(modelPackageName.substring(0, modelPackageName.lastIndexOf(".model."))+".repositories");
            packageName = sb.toString();
        }

        String simpleName = repository.simpleName();
        if(simpleName.isEmpty()){
            simpleName = modelSimpleName + "Repository";
        }

        GenerateRepoTemplate info = new GenerateRepoTemplate();
        info.setModelPackageName(modelPackageName);
        info.setPackageName(packageName);
        info.setModelSimpleName(modelSimpleName);
        info.setSimpleName(simpleName);
        info.setMethods(new ArrayList<String>());
        for (Field field : clazz.getDeclaredFields()){
            GenerateRepoMethod generateRepoMethod = field.getDeclaredAnnotation(GenerateRepoMethod.class);
            if(generateRepoMethod==null)
                continue;
            String fieldName = field.getName();
            StringBuilder sb = new StringBuilder();
            sb.append("List<"+ modelSimpleName +"> ");
            sb.append("findBy");
            sb.append(fieldName.substring(0,1).toUpperCase());
            sb.append(fieldName.substring(1, fieldName.length()));
            sb.append("(" + field.getType().getName() + " " + fieldName + ");");
            System.out.println("### field type name: " + field.getType().getName());
            System.out.println("### field name: " + field.getName());
            info.getMethods().add(sb.toString());
        }

        // to add the target folder rootPath
        String sourcePath = clazz.getClassLoader().getResource(".").getPath();

        targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
        // to aggregate the target rootPath from annotation target
        if(repository.target().isEmpty()){
            targetSourcePath.append("src/main/generated/");
        }else{
            targetSourcePath.append(repository.target());
        }
        targetSourcePath.append(packageName.replace(".", "/") + "/");
        // to aggregate the target rootPath from annotation simple dao class name
        targetSourcePath.append(simpleName + ".java");
        FreeMarkerWriter writer = new FreeMarkerWriter("repositoryin.ftl", targetSourcePath.toString(), info);
        writer.write();
    }
}
