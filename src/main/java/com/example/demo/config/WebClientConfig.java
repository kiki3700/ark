package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.util.ThrowingConsumer;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {
	@Bean
    public WebClient webClient() {

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                                                                  .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024*1024*50))
                                                                  .build();
        exchangeStrategies
            .messageWriters().stream()
            .filter(LoggingCodecSupport.class::isInstance)
            .forEach(writer -> ((LoggingCodecSupport)writer).setEnableLoggingRequestDetails(true));

        return WebClient.builder()
                .clientConnector(
                    new ReactorClientHttpConnector(
                        HttpClient
                            .create(ConnectionProvider.create("test", 800))
                            .secure(
                                ThrowingConsumer.unchecked(
                                    sslContextSpec -> sslContextSpec.sslContext(
                                        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build()
                                    )
                                )
                            )
                            .tcpConfiguration(
                                client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120_000)
                                                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(180))
                                                                           .addHandlerLast(new WriteTimeoutHandler(180))
                                                )
                            )
                    )
                )
                .exchangeStrategies(exchangeStrategies)
                .filter(ExchangeFilterFunction.ofRequestProcessor(
                    clientRequest -> {
                        return Mono.just(clientRequest);
                    }
                ))
                .filter(ExchangeFilterFunction.ofResponseProcessor(
                    clientResponse -> {
                        return Mono.just(clientResponse);
                    }
                ))
                .defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
                .build();
    }
}
