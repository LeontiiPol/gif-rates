package polovinko.leontii.gifrates.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.dto.error.ErrorResponseBody;
import polovinko.leontii.gifrates.services.currency.CurrencyService;

@RestController
@RequestMapping("/api/gif-rates/currencies")
@RequiredArgsConstructor
public class CurrencyController {

  private final CurrencyService currencyService;

  @ApiOperation("Returns currency codes in alphabetic order")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Successful", response = CurrencyCodesCollection.class),
      @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseBody.class)
  })
  @GetMapping(value = "/codes", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CurrencyCodesCollection> getCurrencyCodes() {
    return ResponseEntity.ok()
        .body(currencyService.getCurrencyCodesCollection());
  }
}
