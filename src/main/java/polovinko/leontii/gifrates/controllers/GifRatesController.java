package polovinko.leontii.gifrates.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import polovinko.leontii.gifrates.dto.error.ErrorResponseBody;
import polovinko.leontii.gifrates.dto.gif.Gif;
import polovinko.leontii.gifrates.services.gif.GifRatesService;

@RestController
@RequestMapping("/api/gif-rates/rates")
@RequiredArgsConstructor
public class GifRatesController {

  private final GifRatesService gifRatesService;

  @ApiOperation(value = "Returns random Gif depending on currency's exchange rate")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Successful", response = Gif.class),
      @ApiResponse(code = 404, message = "Bad request", response = ErrorResponseBody.class),
      @ApiResponse(code = 500, message = "Internal server error", response = ErrorResponseBody.class)
  })
  @GetMapping(value = "/{currency-code}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Gif> getCurrencyExchangeRateAsGif(
      @ApiParam(example = "RUB", value = "Three-letter currency code")
      @PathVariable(name = "currency-code") final String currencyCode) {

    return ResponseEntity.ok()
        .body(gifRatesService.getCurrencyExchangeRateAsGif(currencyCode));
  }
}
