package storageservice.utils;

import org.springframework.data.repository.CrudRepository;

import storageservice.utils.models.UrlMapper;

public interface UrlMapperRepository extends CrudRepository<UrlMapper, String> {

}