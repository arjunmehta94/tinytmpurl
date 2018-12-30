package urlgeneratorservice.utils;

import java.time.LocalDate;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.DatatypeConverter;
import java.text.ParseException;
import java.util.TimeZone;

public class RestUrlResource {
    private String email;
    private String resource;
    private String expiration;

    @JsonCreator
    public RestUrlResource(
            @JsonProperty("email") String email,
            @JsonProperty("resource") String resource,
            @JsonProperty("expiration") String expiration) {
        this.email = email;
        this.resource = resource;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.expiration = format.format(DatatypeConverter.parseDateTime(expiration).getTime());

    }

    public String getResource() {
        return resource;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getEmail() {
        return email;
    }
}