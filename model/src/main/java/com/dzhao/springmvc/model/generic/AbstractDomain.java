package com.dzhao.springmvc.model.generic;

import com.dzhao.core.utility.IdGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dzhao on 19/08/2015.
 */
public abstract class AbstractDomain implements BaseDomain<Integer> {
    @Id
    //protected String id = IdGenerator.generateId();
    //@GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;
    @Version
    protected Long version;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "created_datetime")
    protected Date creationDate;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "modified_datetime")
    protected Date lastModificationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected Long getVersion() {
        return version;
    }

    /**
     * Set to protected as it's not supposed to be called externally
     * @param version
     */
    protected void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Set to protected as it's not supposed to be called externally
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * Set to protected as it's not supposed to be called externally
     * @param lastModificationDate
     */
    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }
}
