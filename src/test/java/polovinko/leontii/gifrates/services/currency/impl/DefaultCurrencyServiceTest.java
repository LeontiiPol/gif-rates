package polovinko.leontii.gifrates.services.currency.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.dto.currency.ExchangeRate;
import polovinko.leontii.gifrates.services.clients.OpenExchangeRatesClient;
import polovinko.leontii.gifrates.services.currency.CurrencyCodesCollectionSingletonService;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class DefaultCurrencyServiceTest {

  private static final String CURRENCY_CODE = "ABC";

  @Mock
  private OpenExchangeRatesClient exchangeRatesClient;
  @Mock
  private CurrencyCodesCollectionSingletonService currencyCodesService;
  @InjectMocks
  private DefaultCurrencyService currencyService;
  private CurrencyCodesCollection expectedCurrencyCodesCollection;

  @BeforeEach
  void setUp() {
    expectedCurrencyCodesCollection = new CurrencyCodesCollection(Set.of(CURRENCY_CODE));
    when(currencyCodesService.getCurrencyCodesCollection()).thenReturn(expectedCurrencyCodesCollection);
  }

  @Test
  void getCurrencyCodesCollection_whenMethodInvoked_thenCurrencyCodesCollectionReturned() {
    CurrencyCodesCollection currencyCodesCollection = currencyService.getCurrencyCodesCollection();

    assertSame(expectedCurrencyCodesCollection, currencyCodesCollection);
  }

  @Test
  void hasCurrencyIncreased_whenCurrencyCodeNotFound_thenThrowsException() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> currencyService.hasCurrencyIncreased(""));
    assertEquals("Currency code not found", exception.getMessage());
  }

  @ParameterizedTest(name = "{index} {0}")
  @MethodSource("getArguments")
  void hasCurrencyIncreased(String name, ExchangeRate latest, ExchangeRate historical, boolean result) {
    when(exchangeRatesClient.getLatestExchangeRate(CURRENCY_CODE)).thenReturn(latest);
    LocalDate yesterdayDate = LocalDate.now().minusDays(1);
    when(exchangeRatesClient.getHistoricalExchangeRate(yesterdayDate, CURRENCY_CODE)).thenReturn(historical);

    assertEquals(result, currencyService.hasCurrencyIncreased(CURRENCY_CODE));
  }

  private static Stream<Arguments> getArguments() {
    return Stream.of(
        Arguments.of(
            "whenYesterdayRateHigherThanLatest_thenReturnsFalse",
            new ExchangeRate(CURRENCY_CODE, 1.9),
            new ExchangeRate(CURRENCY_CODE, 2.0),
            false
        ),
        Arguments.of(
            "whenYesterdayAndLatestRatesEqual_thenReturnsFalse",
            new ExchangeRate(CURRENCY_CODE, 2.0),
            new ExchangeRate(CURRENCY_CODE, 2.0),
            false
        ),
        Arguments.of(
            "whenLatestRatesHigherThanYesterday_thenReturnsTrue",
            new ExchangeRate(CURRENCY_CODE, 2.0),
            new ExchangeRate(CURRENCY_CODE, 1.9),
            true)
    );
  }
}
