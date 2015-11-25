package com.htf.fmusic.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.htf.fmusic.common.ConstantDateTimeService;
import com.htf.fmusic.common.CurrentTimeDateTimeService;
import com.htf.fmusic.common.DateTimeService;
import com.htf.fmusic.common.Profiles;

/**
 * @author HTFeeds
 */
@Configuration
@ComponentScan("com.htf.fmusic")
@Import({ WebMvcConfiguration.class, HibernateConfiguration.class, SecurityConfiguration.class })
public class FMusicApplicationContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Configuration
    @Profile(Profiles.PROD)
    @PropertySource("classpath:application.properties")
    static class ProductionProperties {
    }

    @Bean
    @Profile(Profiles.PROD)
    DateTimeService currentTimeDateTimeService() {
        return new CurrentTimeDateTimeService();
    }

    @Profile(Profiles.DEV)
    @Configuration
    @PropertySource("classpath:development.properties")
    static class DevelopmentProperties {
    }

    @Bean
    @Profile(Profiles.DEV)
    DateTimeService constantDateTimeService() {
        return new ConstantDateTimeService();
    }

}
