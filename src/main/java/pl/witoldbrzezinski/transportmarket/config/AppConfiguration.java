package pl.witoldbrzezinski.transportmarket.config;

import java.time.Clock;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Clock getClock() {
    return Clock.systemDefaultZone();
  }
}
