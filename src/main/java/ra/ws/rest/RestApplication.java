package ra.ws.rest;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("ra.ws")
@EnableJpaRepositories("ra.ws")
@EntityScan("ra.ws")
@ServletComponentScan("ra.ws.utils.security")
@EnableHystrix
public class RestApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(RestApplication.class);

	@PostConstruct
    public void postConstruct() {
       logger.debug("postConstruct...");          
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

    @PreDestroy
    public static void preShutdown() {
    	logger.debug("preShutdown...");
    }
    
}

