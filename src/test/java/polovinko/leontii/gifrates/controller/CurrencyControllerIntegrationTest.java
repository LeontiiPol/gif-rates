package polovinko.leontii.gifrates.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.services.clients.OpenExchangeRatesClient;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CurrencyControllerIntegrationTest {

  @MockBean
  private OpenExchangeRatesClient exchangeRatesClient;
  @Autowired
  private MockMvc mockMvc;
  private MockHttpServletRequestBuilder request;

  @BeforeEach
  void setUp() {
    request = MockMvcRequestBuilders.get("/api/gif-rates/currencies/codes");
    Set<String> currencyCodes = new TreeSet<>(Set.of("ABC", "BCD"));
    CurrencyCodesCollection currencyCodesCollection = new CurrencyCodesCollection(currencyCodes);
    when(exchangeRatesClient.getCurrencyCodesCollection()).thenReturn(currencyCodesCollection);
  }

  @Test
  void getCurrencyCodes_whenMethodInvoked_thenCurrencyCodesCollectionJsonReturned() throws Exception {
    mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().json("{\"codes\":[\"ABC\", \"BCD\"]}"));
  }

  @Test
  void getCurrencyCodes_whenMethodInvokedSeveralTimes_thenCurrencyCodesCollectionInitializedSingleTime() {
    IntStream.rangeClosed(1, 10)
        .forEach(this::performRequest);

    verify(exchangeRatesClient, times(1)).getCurrencyCodesCollection();
  }

  private void performRequest(int i) {
    try {
      mockMvc.perform(request);
    } catch (Exception e) {
      fail("Fail on " + i + " try of performing a request");
    }
  }
}
