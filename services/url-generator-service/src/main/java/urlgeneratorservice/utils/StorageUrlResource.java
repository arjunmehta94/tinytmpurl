package urlgeneratorservice.utils;

public class StorageUrlResource {
    private String resource;
    private String resourceId;
    private String expiration;

    public StorageUrlResource(String resourceId,
                              String resource,
                              String expiration) {
        this.resourceId = resourceId;
        this.resource = resource;
        this.expiration = expiration;
    }

    public String getResource() {
        return resource;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
