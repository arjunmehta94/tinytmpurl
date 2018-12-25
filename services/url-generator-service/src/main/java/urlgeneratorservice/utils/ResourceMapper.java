package urlgeneratorservice.utils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ResourceMapper {
    String getResource(String resourceId) throws IOException;

    String createResource(String resource, String expiry) throws IOException, NoSuchAlgorithmException;

    void deleteResource(String resourceId) throws IOException;
}
