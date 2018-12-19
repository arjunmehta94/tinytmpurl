package urlgeneratorservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UrlResourceMapper implements ResourceMapper {

    @Autowired
    private Environment env;

    @Override
    public String getResource(String resourceId) {
        return Resource.getResource(resourceId);
    }

    @Override
    public String createResource(String resource, int expiry) {
        int substring = Integer.parseInt(env.getProperty("hash.length"));
        String resourceId = new String(DigestUtils.md5Digest(resource.getBytes()));
        return Resource.createResource(resourceId.substring(0, substring + 1), resource, expiry);
    }

    @Override
    public void deleteResource(String resourceId) {
        Resource.deleteResource(resourceId);
    }
}
