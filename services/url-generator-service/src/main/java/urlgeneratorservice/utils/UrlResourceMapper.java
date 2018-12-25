package urlgeneratorservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UrlResourceMapper implements ResourceMapper {

    @Autowired
    private Environment env;

    @Override
    public String getResource(String resourceId) throws IOException {
        return Resource.getResource(env, resourceId);
    }

    @Override
    public String createResource(String resource, String expiry) throws IOException, NoSuchAlgorithmException {
        String resourceId = generateResourceId(resource);
        return Resource.createResource(env, resourceId, resource, expiry);
    }

    @Override
    public void deleteResource(String resourceId) throws IOException {
        Resource.deleteResource(env, resourceId);
    }

    private String generateResourceId(String resource) throws NoSuchAlgorithmException {
        int substring = Integer.parseInt(env.getProperty("hash.length"));
        byte[] arr = MessageDigest.getInstance("MD5").digest(resource.getBytes());
        String resourceId = Base64.getEncoder().encodeToString(arr);
        return resourceId.substring(0, substring + 1);
    }
}
