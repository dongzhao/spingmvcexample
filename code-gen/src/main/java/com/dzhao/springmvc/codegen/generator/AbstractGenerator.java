package com.dzhao.springmvc.codegen.generator;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * Created by dzhao on 22/09/2015.
 */
public abstract class AbstractGenerator {
    final protected ProcessingEnvironment processingEnv;
    final protected TypeElement typeElement;
    final protected RoundEnvironment roundEnv;

    public AbstractGenerator(
            TypeElement typeElement,
            RoundEnvironment roundEnv,
            ProcessingEnvironment processingEnv){
        this.processingEnv = processingEnv;
        this.typeElement = typeElement;
        this.roundEnv = roundEnv;
    }

    public abstract void execute();
}
