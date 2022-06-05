package polovinko.leontii.gifrates.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.dto.currency.ExchangeRate;
import polovinko.leontii.gifrates.dto.gif.Gif;
import polovinko.leontii.gifrates.dto.gif.GifTag;
import polovinko.leontii.gifrates.services.clients.GiphyClient;
import polovinko.leontii.gifrates.services.clients.OpenExchangeRatesClient;
import polovinko.leontii.gifrates.services.currency.CurrencyCodesCollectionSingletonService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureMockMvc
class GifRatesControllerIntegrationTest {

  private static final String CURRENCY_CODE = "RUB";

  @MockBean
  private CurrencyCodesCollectionSingletonService currencyCodesService;
  @MockBean
  private OpenExchangeRatesClient exchangeRatesClient;
  @MockBean
  private GiphyClient giphyClient;
  @Autowired
  private MockMvc mockMvc;
  private MockHttpServletRequestBuilder request;
  private Gif gif;

  @BeforeEach
  void setUp() {
    request = MockMvcRequestBuilders.get("/api/gif-rates/rates/" + CURRENCY_CODE);
    gif = new Gif();
    gif.setTitle("Gif Title");
    gif.setHeight(100);
    gif.setWidth(100);
    gif.setGifUrl("gifUrl");
    gif.setGifSize(150);
    gif.setWebpUrl("webpUrl");
    gif.setWebpSize(200);
    gif.setMp4Url("mp4Url");
    gif.setMp4Size(250);
  }

  @Test
  void getCurrencyExchangeRateAsGif_whenCurrencyCodeNotFound_thenReturnsBadRequestResponse() throws Exception {
    CurrencyCodesCollection emptyCurrencyCodesCollection = new CurrencyCodesCollection(Collections.emptySet());
    when(currencyCodesService.getCurrencyCodesCollection()).thenReturn(emptyCurrencyCodesCollection);
    String dateAndTimeRegexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

    mockMvc.perform(request)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("Currency code not found")))
        .andExpect(jsonPath("$.happenedAt", matchesPattern(dateAndTimeRegexp)));
  }

  @ParameterizedTest(name = "{index} {0}")
  @MethodSource("getArguments")
  void getCurrencyExchangeRateAsGif(String name,
                                    ExchangeRate latest,
                                    ExchangeRate historical,
                                    GifTag tag) throws Exception {

    CurrencyCodesCollection currencyCodesCollection = new CurrencyCodesCollection(Set.of(CURRENCY_CODE));
    when(currencyCodesService.getCurrencyCodesCollection()).thenReturn(currencyCodesCollection);
    when(exchangeRatesClient.getLatestExchangeRate(CURRENCY_CODE)).thenReturn(latest);
    LocalDate yesterdayDate = LocalDate.now().minusDays(1);
    when(exchangeRatesClient.getHistoricalExchangeRate(yesterdayDate, CURRENCY_CODE)).thenReturn(historical);
    when(giphyClient.getRandomGifByTag(tag)).thenReturn(gif);

    mockMvc.perform(request)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title", is(gif.getTitle())))
        .andExpect(jsonPath("$.height", is(gif.getHeight())))
        .andExpect(jsonPath("$.width", is(gif.getWidth())))
        .andExpect(jsonPath("$.gif_url", is(gif.getGifUrl())))
        .andExpect(jsonPath("$.gif_size", is(gif.getGifSize())))
        .andExpect(jsonPath("$.webp_url", is(gif.getWebpUrl())))
        .andExpect(jsonPath("$.webp_size", is(gif.getWebpSize())))
        .andExpect(jsonPath("$.mp4_url", is(gif.getMp4Url())))
        .andExpect(jsonPath("$.mp4_size", is(gif.getMp4Size())));
  }

  private static Stream<Arguments> getArguments() {
    return Stream.of(
        Arguments.of(
            "whenYesterdayRateHigherThanLatest_thenInvokesGetRandomGifByTagWithBrokeTag",
            new ExchangeRate(CURRENCY_CODE, 1.9),
            new ExchangeRate(CURRENCY_CODE, 2.0),
            GifTag.BROKE
        ),
        Arguments.of(
            "whenYesterdayAndLatestRatesEqual_thenInvokesGetRandomGifByTagWithBrokeTag",
            new ExchangeRate(CURRENCY_CODE, 2.0),
            new ExchangeRate(CURRENCY_CODE, 2.0),
            GifTag.BROKE
        ),
        Arguments.of(
            "whenLatestRatesHigherThanYesterday_thenInvokesGetRandomGifByTagWithRichTag",
            new ExchangeRate(CURRENCY_CODE, 2.0),
            new ExchangeRate(CURRENCY_CODE, 1.9),
            GifTag.RICH
        )
    );
  }
}
