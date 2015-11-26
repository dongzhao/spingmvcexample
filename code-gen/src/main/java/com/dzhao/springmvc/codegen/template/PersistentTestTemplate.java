package com.dzhao.springmvc.codegen.template;

import java.util.List;

/**
 * Created by dzhao on 26/11/2015.
 */
public class PersistentTestTemplate {

    private String modelSimpleName;
    private String modelPackageName;
    private String repositorySimpleName;
    private String repositoryPackageName;
    private String unitTestSimpleName;
    private String unitTestPackageName;

    List<FieldTestValueTemplate> fieldTestValueTemplates;

    public String getModelSimpleName() {
        return modelSimpleName;
    }

    public void setModelSimpleName(String modelSimpleName) {
        this.modelSimpleName = modelSimpleName;
    }

    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getRepositorySimpleName() {
        return repositorySimpleName;
    }

    public void setRepositorySimpleName(String repositorySimpleName) {
        this.repositorySimpleName = repositorySimpleName;
    }

    public String getRepositoryPackageName() {
        return repositoryPackageName;
    }

    public void setRepositoryPackageName(String repositoryPackageName) {
        this.repositoryPackageName = repositoryPackageName;
    }

    public String getUnitTestSimpleName() {
        return unitTestSimpleName;
    }

    public void setUnitTestSimpleName(String unitTestSimpleName) {
        this.unitTestSimpleName = unitTestSimpleName;
    }

    public String getUnitTestPackageName() {
        return unitTestPackageName;
    }

    public void setUnitTestPackageName(String unitTestPackageName) {
        this.unitTestPackageName = unitTestPackageName;
    }

    public List<FieldTestValueTemplate> getFieldTestValueTemplates() {
        return fieldTestValueTemplates;
    }

    public void setFieldTestValueTemplates(List<FieldTestValueTemplate> fieldTestValueTemplates) {
        this.fieldTestValueTemplates = fieldTestValueTemplates;
    }
}
