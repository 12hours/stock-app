package com.epam.mentoring.approutes.config;

import com.epam.mentoring.data.model.dto.view.ProductIncomeView;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.text.SimpleDateFormat;
import java.util.HashMap;

@Configuration
@ComponentScan({"com.epam.mentoring.approutes"})
public class RoutesConfig {

    @Bean(name = "objectMapper")
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);

        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(ProductView.class, view2HalSerializer());
        simpleModule.addSerializer(ProductTypeView.class, view2HalSerializer());
        simpleModule.addSerializer(ProductIncomeView.class, view2HalSerializer());
        objectMapper.registerModule(simpleModule);

        return objectMapper;
    }

//    @Bean
//    JacksonJsonProvider jsonProvider() {
//        return new com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider(objectMapper());
//    }

    @Bean(name = "json-jackson")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JacksonDataFormat jacksonDataFormat(ObjectMapper objectMapper) {
        // Undocumented camel feature!!
        // https://stackoverflow.com/questions/33397359/how-to-configure-jackson-objectmapper-for-camel-in-spring-boot
        return new JacksonDataFormat(objectMapper, HashMap.class);
    }

    @Bean
    public LinkBuilder linkBuilder() {
        return new LinkBuilder();
    }

    @Bean
    public View2HalSerializer view2HalSerializer() {
        return new View2HalSerializer();
    }


}
