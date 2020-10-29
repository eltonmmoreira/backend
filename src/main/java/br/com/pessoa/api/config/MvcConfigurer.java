package br.com.pessoa.api.config;

import br.com.pessoa.api.serialization.*;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;
    private final LocalDateSerializer localDateSerializer;
    private final LocalDateDeserializer localDateDeserializer;
    private final LocalDateTimeSerializer localDateTimeSerializer;
    private final LocalDateTimeDeserializer localDateTimeDeserializer;
    private final LocalTimeSerializer localTimeSerializer;
    private final LocalTimeDeserializer localTimeDeserializer;

    public MvcConfigurer(ObjectMapper objectMapper,
                         LocalDateSerializer localDateSerializer,
                         LocalDateDeserializer localDateDeserializer,
                         LocalDateTimeSerializer localDateTimeSerializer,
                         LocalDateTimeDeserializer localDateTimeDeserializer,
                         LocalTimeSerializer localTimeSerializer,
                         LocalTimeDeserializer localTimeDeserializer) {
        this.objectMapper = objectMapper;
        this.localDateSerializer = localDateSerializer;
        this.localDateDeserializer = localDateDeserializer;
        this.localDateTimeSerializer = localDateTimeSerializer;
        this.localDateTimeDeserializer = localDateTimeDeserializer;
        this.localTimeSerializer = localTimeSerializer;
        this.localTimeDeserializer = localTimeDeserializer;
    }

    private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        SimpleModule module = new SimpleModule("api", Version.unknownVersion());
        module.addSerializer(LocalDate.class, localDateSerializer);
        module.addDeserializer(LocalDate.class, localDateDeserializer);
        module.addSerializer(LocalDateTime.class, localDateTimeSerializer);
        module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        module.addSerializer(LocalTime.class, localTimeSerializer);
        module.addDeserializer(LocalTime.class, localTimeDeserializer);
        objectMapper.registerModule(module);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter());
        converters.add(customJackson2HttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
