package com.dzhao.core.model.generic;

import com.dzhao.core.utility.IdGenerator;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Version;
import java.util.Date;

/**
 * Created by dzhao on 19/08/2015.
 */
public abstract class AbstractDomain implements BaseDomain<String> {
    @Id
    protected String id = IdGenerator.generateId();
    @Version
    protected Long version;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "created_datetime")
    protected Date creationDate;
    @Temporal(value = javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "modified_datetime")
    protected Date lastModificationDate;

    public String getId() {
        return id;
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
