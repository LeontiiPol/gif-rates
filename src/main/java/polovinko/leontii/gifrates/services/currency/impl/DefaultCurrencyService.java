package polovinko.leontii.gifrates.services.currency.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.dto.currency.ExchangeRate;
import polovinko.leontii.gifrates.services.clients.OpenExchangeRatesClient;
import polovinko.leontii.gifrates.services.currency.CurrencyCodesCollectionSingletonService;
import polovinko.leontii.gifrates.services.currency.CurrencyService;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyService implements CurrencyService {

  private final CurrencyCodesCollectionSingletonService currencyCodesService;
  private final OpenExchangeRatesClient openExchangeRatesClient;

  @Override
  public CurrencyCodesCollection getCurrencyCodesCollection() {
    return currencyCodesService.getCurrencyCodesCollection();
  }

  @Override
  public boolean hasCurrencyIncreased(final String currencyCode) {
    if (!isCurrencyCodePresent(currencyCode)) {
      throw new IllegalArgumentException("Currency code not found");
    }
    ExchangeRate latestRate = openExchangeRatesClient.getLatestExchangeRate(currencyCode);
    LocalDate yesterdayDate = getYesterdayDate();
    ExchangeRate yesterdayRate = openExchangeRatesClient.getHistoricalExchangeRate(yesterdayDate, currencyCode);
    return latestRate.getRate() > yesterdayRate.getRate();
  }

  private boolean isCurrencyCodePresent(final String currencyCode) {
    CurrencyCodesCollection currencyCodesCollection = currencyCodesService.getCurrencyCodesCollection();
    return currencyCodesCollection.getCurrencyCodes()
        .contains(currencyCode);
  }

  private LocalDate getYesterdayDate() {
    return LocalDate.now()
        .minusDays(1);
  }
}
