package guru.spingframework.spring5recipeapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class Spring5RecipeAppApplication {

    private static final Logger logger = LogManager.getLogger(Spring5RecipeAppApplication.class);

    public static void main(String[] args) {

        final ConfigurableApplicationContext context = SpringApplication.run(Spring5RecipeAppApplication.class, args);

        final AtomicInteger counter = new AtomicInteger(0);
        logger.info("**************** START: Total Bean Objects: {} ******************", context.getBeanDefinitionCount());

        Arrays.asList(context.getBeanDefinitionNames())
                .forEach(beanName -> {
                    logger.info("{}) Bean Name: {} ", counter.incrementAndGet(), beanName);
                });

        logger.info("**************** END: Total Bean: {} ******************", context.getBeanDefinitionCount());
    }
}
