package polovinko.leontii.gifrates.services.currency;

import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;

public interface CurrencyService {

  CurrencyCodesCollection getCurrencyCodesCollection();

  boolean hasCurrencyIncreased(String currencyCode);
}
