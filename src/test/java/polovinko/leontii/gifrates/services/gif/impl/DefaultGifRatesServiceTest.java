package polovinko.leontii.gifrates.services.gif.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import polovinko.leontii.gifrates.dto.gif.GifTag;
import polovinko.leontii.gifrates.services.clients.GiphyClient;
import polovinko.leontii.gifrates.services.currency.CurrencyService;

@ExtendWith(MockitoExtension.class)
class DefaultGifRatesServiceTest {

  private static final String CURRENCY_CODE = "ABC";

  @Mock
  private GiphyClient giphyClient;
  @Mock
  private CurrencyService  currencyService;
  @InjectMocks
  private DefaultGifRatesService gifRatesService;

  @Test
  void getCurrencyExchangeRateAsGif_whenCurrencyExchangeRateIncreased_thenRichGifReturned() {
    when(currencyService.hasCurrencyIncreased(CURRENCY_CODE)).thenReturn(true);

    gifRatesService.getCurrencyExchangeRateAsGif(CURRENCY_CODE);

    verify(giphyClient).getRandomGifByTag(GifTag.RICH);
  }

  @Test
  void getCurrencyExchangeRateAsGif_whenCurrencyExchangeRateDecreased_thenBrokeGifReturned() {
    when(currencyService.hasCurrencyIncreased(CURRENCY_CODE)).thenReturn(false);

    gifRatesService.getCurrencyExchangeRateAsGif(CURRENCY_CODE);

    verify(giphyClient).getRandomGifByTag(GifTag.BROKE);
  }
}
