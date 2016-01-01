package co.bk.restspringboot;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * Base class for Model objects. Child objects should implement toString(), equals() and hashCode().
 * 
 */
@MappedSuperclass
public abstract class EntityBean {

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    //private Date createDate = new Date();
    private Long createDate = new Date().getTime();

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    //private Date updateDate = new Date();
    private Long updateDate = new Date().getTime();

    @Version
    @Column(name = "version")
    private Integer version;
    
    /**
     * Returns a multi-line String with key=value pairs.
     * @return a String representation of this class.
     */
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should not be a part of this comparison.
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are equals() and hashCode() importation" for more information:
     * 		https://developer.jboss.org/wiki/EqualsAndHashCode
     * @return hashCode
     */
    public abstract int hashCode();

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
