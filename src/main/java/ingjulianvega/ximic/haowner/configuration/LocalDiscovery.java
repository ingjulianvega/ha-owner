package ingjulianvega.ximic.haowner.configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local-discovery")
@EnableDiscoveryClient
public class LocalDiscovery {
}
