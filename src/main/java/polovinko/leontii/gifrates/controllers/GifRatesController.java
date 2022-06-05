package polovinko.leontii.gifrates.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import polovinko.leontii.gifrates.dto.gif.Gif;
import polovinko.leontii.gifrates.services.gif.GifRatesService;

@RestController
@RequestMapping("/api/gif-rates/rates")
@RequiredArgsConstructor
public class GifRatesController {

  private final GifRatesService gifRatesService;

  @GetMapping("/{currencyCode}")
  public ResponseEntity<Gif> getCurrencyExchangeRateAsGif(@PathVariable final String currencyCode) {
    return ResponseEntity.ok()
        .body(gifRatesService.getCurrencyExchangeRateAsGif(currencyCode));
  }
}
