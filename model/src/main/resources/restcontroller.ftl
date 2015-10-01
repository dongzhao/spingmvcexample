package ${packageName};

import ${modelPackageName};
import ${repositoryPackageName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

/**
* Auto-gen class
*/
@RestController
@RequestMapping(value = "${urlPathName}")
public class ${simpleName}{

    @Autowired
    private ${repositoryClassName} ${repositoryInstanceName};

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName(){
        return ${repositoryClassName}.class.getSimpleName();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public ${modelClassName} create(@RequestBody @Valid final ${modelClassName} ${modelInstanceName}){
        return ${repositoryInstanceName}.save(${modelInstanceName});
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @Transactional
    public ${modelClassName} update(@RequestBody @Valid final ${modelClassName} ${modelInstanceName}){
        return ${repositoryInstanceName}.save(${modelInstanceName});
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public void delete(@PathVariable("id") String id) {
        ${repositoryInstanceName}.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    @ResponseBody
    public ${modelClassName} findById(@PathVariable("id") String id) {
        return ${repositoryInstanceName}.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    @ResponseBody
    public List<${modelClassName}> findAll() {
        return ${repositoryInstanceName}.findAll();
    }

<#if methods??>
    <#list methods as method>
    @RequestMapping(value = "/${method.name}/{${method.name}}", method = RequestMethod.GET)
    @Transactional
    @ResponseBody
    public List<${modelClassName}> ${method.method}(@PathVariable("${method.name}") ${method.type} ${method.name}) {
        return ${repositoryInstanceName}.${method.method}(${method.name});
    }
    </#list>
</#if>
}
