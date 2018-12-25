package urlgeneratorservice.utils;

import java.time.LocalDate;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;

public class RestUrlResource {
    private String resource;
    private String expiration;

    @JsonCreator
    public RestUrlResource(
            @JsonProperty("resource") String resource,
            @JsonProperty("expiration") String expiration) {
        this.resource = resource;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try {
            format.parse(expiration);
            this.expiration = expiration;
        } catch (ParseException e) {
            this.expiration = format.format(LocalDate.now().plusDays(1));
        }
    }

    public String getResource() {
        return resource;
    }

    public String getExpiration() {
        return expiration;
    }
}