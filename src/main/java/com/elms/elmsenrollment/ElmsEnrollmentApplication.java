package com.elms.elmsenrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(exclude = {ContextStackAutoConfiguration.class})
public class ElmsEnrollmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElmsEnrollmentApplication.class, args);
    }

}
