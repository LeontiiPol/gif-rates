package polovinko.leontii.gifrates.services.clients;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import polovinko.leontii.gifrates.dto.currency.ExchangeRate;
import java.time.LocalDate;

@FeignClient(name = "rates", url = "${client.rates.api.url}")
public interface OpenExchangeRatesClient {

  @GetMapping("${client.rates.api.codes}")
  CurrencyCodesCollection getCurrencyCodesCollection();

  @GetMapping("${client.rates.api.latest}")
  ExchangeRate getLatestExchangeRate(@RequestParam(name = "symbols") final String currencyCode);

  @GetMapping("${client.rates.api.historical}")
  ExchangeRate getHistoricalExchangeRate(@PathVariable @DateTimeFormat(iso = DATE) final LocalDate date,
                                         @RequestParam(name = "symbols") final String currencyCode);
}
