//package ada.tech.java.config;
//
//import ada.tech.java.client.EnvioClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.support.WebClientAdapter;
//import org.springframework.web.service.invoker.HttpServiceProxyFactory;
//
//@Configuration
//public class WebClientConfig {
//    @Value("${negocio.envio.url}")
//    private String envioUrl;
//    @Bean
//    WebClient webClient(){
//        return WebClient.builder().baseUrl(envioUrl)
//                .build();
//    }
//    @Bean
//    EnvioClient envioClient (WebClient webClient){
//        HttpServiceProxyFactory httpServiceProxyFactory =
//                HttpServiceProxyFactory.builder(WebClientAdapter
//                        .forClient(webClient)).build();
//        return httpServiceProxyFactory.createClient(EnvioClient.class);
//    }
//}
