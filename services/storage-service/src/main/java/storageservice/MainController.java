package storageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        return findUrlMapper(hash);
    }

    @PostMapping(path="/url", consumes = "application/json")
    public String createUrlMapping(@RequestBody RestUrlMapper restUrlMapper) {
        UrlMapper urlMapper = new UrlMapper();
        String id = restUrlMapper.getResourceId();
        urlMapper.setResource(id);
        urlMapper.setValue(restUrlMapper.getResource());
        urlMapper.setExpiration(restUrlMapper.getExpiration());
        if (findUrlMapper(id) != null) {
            urlMapperRepository.save(urlMapper);
            return id;
        }
        return null;
    }

    String findUrlMapper(String id) {
        Optional<UrlMapper> obj = urlMapperRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get().getValue();
        }
        return null;
    }
}