package ${repositoryPackageName};

import ${modelPackageName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import java.math.BigDecimal;

@Repository
public interface ${repositorySimpleName} extends JpaRepository<${modelSimpleName}, String> {
<#if repositoryMethods??>
    <#list repositoryMethods as method>
    ${method}
    </#list>
</#if>
}