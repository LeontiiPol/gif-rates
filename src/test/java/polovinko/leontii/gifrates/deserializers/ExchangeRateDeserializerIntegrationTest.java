package polovinko.leontii.gifrates.deserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import polovinko.leontii.gifrates.dto.currency.ExchangeRate;
import java.io.IOException;
import java.net.URL;

@SpringBootTest
class ExchangeRateDeserializerIntegrationTest {

  @Autowired
  private ObjectMapper objectMapper;
  private URL json;

  @BeforeEach
  void setUp() {
    json = this.getClass()
        .getResource("/json/exchange-rate.json");
  }

  @Test
  void deserialize_whenExchangeRatesJsonPassed_thenJsonIsDeserializedToExchangeRates() throws IOException {
    ExchangeRate exchangeRate = objectMapper.readValue(json, ExchangeRate.class);

    assertEquals(3.672538, exchangeRate.getRate());
    assertEquals("RUB", exchangeRate.getCurrencyCode());
  }
}
