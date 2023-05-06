package code;

import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class MessagingConfig {

    @Bean
    public Consumer<String> consume() {
        return message -> {
            log.info("Successfully received message: {}", message);
        };
    }

    @Bean
    public Supplier<String> supply() {
        return () -> {
            final String dateTime = LocalDateTime.now().format(ofPattern("uuuu-MM-dd HH:mm:ss"));
            final String message = format("Test msg - %s (%s)", randomAlphanumeric(8), dateTime);
            log.info("Sending message: {}", message);
            return message;
        };
    }
}