package com.enaaskills.apigateway.filter;

import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaderValidationUtil.validateToken;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtAuthenticationFilter implements GlobalFilter {

    //Objet HTTP pour appeler un autre microservice
   private final WebClient webClient;


       @Autowired
       public JwtAuthenticationFilter(WebClient.Builder webClientBuilder) {      //On initialise WebClient pour envoyer les requêtes à auth-service
           this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
       }
    private static final String SECRET_KEY = "maCleSuperSecrete123456789012345";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Autoriser les requêtes vers /api/auth/** sans token (ex: login, register)
        if (path.startsWith("/api/auth")) {
            return chain.filter(exchange);
        }


        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

            // Appel à l'Auth Service pour valider le token
        return webClient.get()
                .uri("/auth/validate?token=" + token)        //Appel GET vers le endpoint /api/auth/validate avec le token en paramètre.
                .retrieve()       //
                .bodyToMono(Boolean.class)     //On attend une réponse true ou false selon si le token est valide.
                .flatMap(isValid -> {
                    if (Boolean.TRUE.equals(isValid)) { //si true laisse passer vers le microservice
                        return chain.filter(exchange);
                    } else {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();  //si non return 401
                    }
                })
                .onErrorResume(e -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });


    }
}
