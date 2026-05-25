package cl.dgac.registro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {
    @Bean
    public WebClient planVueloApiWebClient(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:808x/api/v1/PlanVuelo").build();
    }
}
