package com.dzhao.springmvc.codegen;

import java.util.List;

/**
 * Created by dzhao on 22/09/2015.
 */
public class RepositoryTemplate {
    private String modelPackageName;
    private String repositoryPackageName;
    private String modelSimpleName;
    private String repositorySimpleName;
    private List<String> repositoryMethods;
    private List<String> repositoryJoinedMethods;
    private List<MethodTemplate> methodTemplates;
    private String targetUrl;

    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getRepositoryPackageName() {
        return repositoryPackageName;
    }

    public void setRepositoryPackageName(String repositoryPackageName) {
        this.repositoryPackageName = repositoryPackageName;
    }

    public String getModelSimpleName() {
        return modelSimpleName;
    }

    public void setModelSimpleName(String modelSimpleName) {
        this.modelSimpleName = modelSimpleName;
    }

    public String getRepositorySimpleName() {
        return repositorySimpleName;
    }

    public void setRepositorySimpleName(String repositorySimpleName) {
        this.repositorySimpleName = repositorySimpleName;
    }

    public List<String> getRepositoryMethods() {
        return repositoryMethods;
    }

    public void setRepositoryMethods(List<String> repositoryMethods) {
        this.repositoryMethods = repositoryMethods;
    }

    public List<String> getRepositoryJoinedMethods() {
        return repositoryJoinedMethods;
    }

    public void setRepositoryJoinedMethods(List<String> repositoryJoinedMethods) {
        this.repositoryJoinedMethods = repositoryJoinedMethods;
    }

    public List<MethodTemplate> getMethodTemplates() {
        return methodTemplates;
    }

    public void setMethodTemplates(List<MethodTemplate> methodTemplates) {
        this.methodTemplates = methodTemplates;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
