package pl.witoldbrzezinski.transportmarket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TransportmarketApplicationTests {

  @Test
  public void main() {
    TransportmarketApplication.main(new String[] {});
  }
}
