package polovinko.leontii.gifrates.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.services.currency.CurrencyService;

@RestController
@RequestMapping("/api/gif-rates/currencies")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyService currencyService;

  @GetMapping("/codes")
  public ResponseEntity<CurrencyCodesCollection> getCurrencyCodes() {
    return ResponseEntity.ok()
        .body(currencyService.getCurrencyCodesCollection());
  }
}
