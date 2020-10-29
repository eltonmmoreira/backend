package br.com.pessoa.api.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Configuration
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return toLocalDateTime( p.getValueAsString() );
    }

    public LocalDateTime toLocalDateTime(String value) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            ZonedDateTime zonedDateTime = LocalDateTime.parse(value, formatter)
                    .atZone(ZoneId.systemDefault());

            return zonedDateTime.withZoneSameInstant(
                    ZoneId.systemDefault()).toLocalDateTime();
        } catch (DateTimeParseException e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            ZonedDateTime zonedDateTime = LocalDateTime.parse(value, formatter)
                    .atZone(ZoneId.systemDefault());

            return zonedDateTime.withZoneSameInstant(
                    ZoneId.systemDefault()).toLocalDateTime();
        }
    }
}
