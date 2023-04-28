package cz.kct;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.jackson.JacksonEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("cz.kct")
@EnableJpaRepositories("cz.kct.repository")
@Configuration

public class Config {
    @Bean
    public JacksonEncoder feignEncoder() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new JacksonEncoder(objectMapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
