package storageservice.utils.restmodels;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;

public class RestUrlMapper {
    private String resourceId;
    private String resource;
    private Date expiration;

    @JsonCreator
    public RestUrlMapper(
            @JsonProperty("resourceId") String resourceId,
            @JsonProperty("resource") String resource,
            @JsonProperty("expiration") String expiration) {
        this.resourceId = resourceId;
        this.resource = resource;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try {
            this.expiration = format.parse(expiration);
        } catch (ParseException e) {
            this.expiration = null; // todo
        }
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getResource() {
        return resource;
    }

    public Date getExpiration() {
        return expiration;
    }
}