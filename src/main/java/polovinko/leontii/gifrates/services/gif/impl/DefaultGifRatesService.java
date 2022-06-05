package polovinko.leontii.gifrates.services.gif.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import polovinko.leontii.gifrates.dto.gif.Gif;
import polovinko.leontii.gifrates.dto.gif.GifTag;
import polovinko.leontii.gifrates.services.clients.GiphyClient;
import polovinko.leontii.gifrates.services.currency.CurrencyService;
import polovinko.leontii.gifrates.services.gif.GifRatesService;

@Service
@RequiredArgsConstructor
public class DefaultGifRatesService implements GifRatesService {

  private final GiphyClient giphyClient;
  private final CurrencyService currencyService;

  @Override
  public Gif getCurrencyExchangeRateAsGif(final String currencyCode) {
    if (currencyService.hasCurrencyIncreased(currencyCode)) {
      return giphyClient.getRandomGifByTag(GifTag.RICH);
    }
    return giphyClient.getRandomGifByTag(GifTag.BROKE);
  }
}
