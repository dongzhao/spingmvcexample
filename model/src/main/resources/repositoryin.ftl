package ${packageName};

import ${modelPackageName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ${repositorySimpleName} extends JpaRepository<${modelSimpleName}, String> {
<#if methods??>
<#list methods as method>
public ${method}
</#list>
</#if>
}