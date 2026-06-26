package cl.dgac.registro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {
    @Bean(name="planVueloApiWebClient")
    public WebClient planVueloApiWebClient(){
        return WebClient.builder().baseUrl("http://localhost:8083").build();
    }
}
