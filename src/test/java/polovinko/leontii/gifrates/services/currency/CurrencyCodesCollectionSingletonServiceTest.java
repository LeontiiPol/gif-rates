package polovinko.leontii.gifrates.services.currency;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.services.clients.OpenExchangeRatesClient;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class CurrencyCodesCollectionSingletonServiceTest {

  private static final String CURRENCY_CODE = "ABC";

  @Mock
  private OpenExchangeRatesClient exchangeRatesClient;
  @InjectMocks
  private CurrencyCodesCollectionSingletonService currencyCodesService;
  private CurrencyCodesCollection expectedCurrencyCodesCollection;

  @BeforeEach
  void setUp() {
    expectedCurrencyCodesCollection = new CurrencyCodesCollection(Set.of(CURRENCY_CODE));
    when(exchangeRatesClient.getCurrencyCodesCollection()).thenReturn(expectedCurrencyCodesCollection);
  }

  @Test
  void getCurrenciesCodesCollection_whenMethodInvoked_thenCurrencyCodesCollectionFromClientReturned() {
    CurrencyCodesCollection currencyCodesCollection = currencyCodesService.getCurrencyCodesCollection();

    assertSame(expectedCurrencyCodesCollection, currencyCodesCollection);
  }

  @Test
  void getCurrenciesCodesCollection_whenMethodInvokedSeveralTimes_thenCurrencyCodesCollectionCachedAndCacheReturned() {
    CurrencyCodesCollection currencyCodesCollection1 = currencyCodesService.getCurrencyCodesCollection();
    CurrencyCodesCollection currencyCodesCollection2 = currencyCodesService.getCurrencyCodesCollection();

    verify(exchangeRatesClient, times(1)).getCurrencyCodesCollection();
    assertSame(expectedCurrencyCodesCollection, currencyCodesCollection1);
    assertSame(currencyCodesCollection1, currencyCodesCollection2);
  }
}
