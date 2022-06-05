package polovinko.leontii.gifrates.deserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

@SpringBootTest
class CurrencyCodesCollectionDeserializerIntegrationTest {

  @Autowired
  private ObjectMapper objectMapper;
  private URL json;

  @BeforeEach
  void setUp() {
    json = this.getClass()
        .getResource("/json/currencies.json");
  }

  @Test
  void deserialize_whenCurrenciesJsonPassed_thenJsonIsDeserializedToCurrencyCodesCollection() throws IOException {
    Set<String> expectedCurrencyCodes = Set.of("AED", "AFN", "ALL");

    CurrencyCodesCollection currencyCodesCollection = objectMapper.readValue(json, CurrencyCodesCollection.class);

    assertEquals(expectedCurrencyCodes, currencyCodesCollection.getCurrencyCodes());
  }
}
