package org.haftrust.verifier.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "org.haftrust.verifier.service" })
@Import({ DbConfig.class })
public class RootConfig {

}
