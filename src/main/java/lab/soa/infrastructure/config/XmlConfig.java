package lab.soa.infrastructure.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class XmlConfig {
    @Bean
    XmlMapper xmlMapper() {
        return XmlMapper.builder()
                .enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .addModule(new JavaTimeModule())
                .build();
    }

    @Bean
    MappingJackson2XmlHttpMessageConverter xmlHttpMessageConverter(XmlMapper xmlMapper) {
        MappingJackson2XmlHttpMessageConverter converter =
            new MappingJackson2XmlHttpMessageConverter(xmlMapper);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        return converter;
    }
}
