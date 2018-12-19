package storageservice.utils.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;

@Entity
@Table(name="urlmapper")
public class UrlMapper {
    private String value;

    @Id
    private String resource;


//    @Column(name = "expiration", columnDefinition="DATETIME")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date expiration;

//    public Integer getId() {
//        return id;
//    }

    public Date getExpiration() {
        return expiration;
    }

    public String getResource() {
        return resource;
    }

    public String getValue() {
        return value;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setValue(String value) {
        this.value = value;
    }
}