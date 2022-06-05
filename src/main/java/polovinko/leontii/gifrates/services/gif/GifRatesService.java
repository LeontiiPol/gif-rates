package polovinko.leontii.gifrates.services.gif;

import polovinko.leontii.gifrates.dto.gif.Gif;

public interface GifRatesService {

  Gif getCurrencyExchangeRateAsGif(String currencyCode);
}
