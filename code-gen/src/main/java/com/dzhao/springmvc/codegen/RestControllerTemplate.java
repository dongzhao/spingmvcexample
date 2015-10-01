package com.dzhao.springmvc.codegen;

import java.util.List;

/**
 * Created by dzhao on 22/09/2015.
 */
public class RestControllerTemplate {
    private String modelPackageName;
    private String repositoryPackageName;
    private String modelClassName;
    private String modelInstanceName;
    private String repositoryClassName;
    private String repositoryInstanceName;
    private String urlPathName;
    private String simpleName;
    private String packageName;
    private List<MethodTemplate> methods;

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

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getModelClassName() {
        return modelClassName;
    }

    public void setModelClassName(String modelClassName) {
        this.modelClassName = modelClassName;
    }

    public String getModelInstanceName() {
        return modelInstanceName;
    }

    public void setModelInstanceName(String modelInstanceName) {
        this.modelInstanceName = modelInstanceName;
    }

    public String getRepositoryClassName() {
        return repositoryClassName;
    }

    public void setRepositoryClassName(String repositoryClassName) {
        this.repositoryClassName = repositoryClassName;
    }

    public String getRepositoryInstanceName() {
        return repositoryInstanceName;
    }

    public void setRepositoryInstanceName(String repositoryInstanceName) {
        this.repositoryInstanceName = repositoryInstanceName;
    }

    public String getUrlPathName() {
        return urlPathName;
    }

    public void setUrlPathName(String urlPathName) {
        this.urlPathName = urlPathName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<MethodTemplate> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodTemplate> methods) {
        this.methods = methods;
    }
}
