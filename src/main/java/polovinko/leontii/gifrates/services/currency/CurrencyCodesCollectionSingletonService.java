package polovinko.leontii.gifrates.services.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.services.clients.OpenExchangeRatesClient;

@Service
@RequiredArgsConstructor
public class CurrencyCodesCollectionSingletonService {

  private volatile CurrencyCodesCollection currencyCodesCollection;
  private final OpenExchangeRatesClient exchangeRatesClient;

  public CurrencyCodesCollection getCurrencyCodesCollection() {
    if (currencyCodesCollection == null) {
      synchronized (this) {
        if (currencyCodesCollection == null) {
          currencyCodesCollection = exchangeRatesClient.getCurrencyCodesCollection();
        }
      }
    }
    return currencyCodesCollection;
  }
}
