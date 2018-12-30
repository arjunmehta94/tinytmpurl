package storageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import storageservice.utils.UrlMapperRepository;
import storageservice.utils.models.UrlMapper;

import storageservice.utils.restmodels.RestUrlMapper;

import java.util.Optional;

@RestController("/")
public class MainController {
    @Autowired
    private UrlMapperRepository urlMapperRepository;

    @GetMapping(path="/url/{hash}")
    public String getUrl(@PathVariable String hash) {
        return findUrlMapper(hash).getValue();
    }

    @PostMapping(path="/url", consumes = "application/json")
    public String createUrlMapping(@RequestBody RestUrlMapper restUrlMapper) {
        String id = restUrlMapper.getResourceId();
        UrlMapper item = findUrlMapper(id);
        if (item == null) {
            UrlMapper urlMapper = new UrlMapper();
            urlMapper.setResource(id);
            urlMapper.setValue(restUrlMapper.getResource());
            urlMapper.setExpiration(restUrlMapper.getExpiration());
            urlMapperRepository.save(urlMapper);
        } else {
            if (item.getExpiration() != restUrlMapper.getExpiration()) {
                item.setExpiration(restUrlMapper.getExpiration());
                urlMapperRepository.save(item);
            }
        }
        return id;
    }

    @DeleteMapping(path="/url/{hash}")
    public void deleteUrlMapping(@PathVariable String hash) {
        UrlMapper obj = findUrlMapper(hash);
        if (obj != null) {
            urlMapperRepository.delete(obj);
        }
        return;
    }

    UrlMapper findUrlMapper(String id) {
        Optional<UrlMapper> obj = urlMapperRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        return null;
    }
}