import com.dzhao.springmvc.codegen.generator.ExtendedRepositoryGenerator;
import com.dzhao.springmvc.model.generic.BaseDomain;
import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by dzhao on 30/09/2015.
 */
public class CodeGenApp {
    private final static String modelPackage = "com.dzhao.springmvc.model";
    public static void main(String[] args){
        Reflections reflections = new Reflections(modelPackage);
        Set<Class<? extends BaseDomain>> allClasses = reflections.getSubTypesOf(BaseDomain.class);
        for (Class<? extends BaseDomain> clazz : allClasses){
            ExtendedRepositoryGenerator.execute(clazz);
        }
    }
}
