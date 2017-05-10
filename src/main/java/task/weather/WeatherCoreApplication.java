/**
 * 
 */
package task.weather;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Jogireddy Kotam
 * @date 09-May-2017
 *
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = "task.weather")
public class WeatherCoreApplication extends SpringBootServletInitializer {
    
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeatherCoreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherCoreApplication.class, args);
    }
    
    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/messages_en"); // name of the resource bundle
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

}
