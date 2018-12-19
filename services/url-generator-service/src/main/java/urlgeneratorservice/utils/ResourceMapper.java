package urlgeneratorservice.utils;

public interface ResourceMapper {
    String getResource(String resourceId);

    String createResource(String resource, int expiry);

    void deleteResource(String resourceId);
}
