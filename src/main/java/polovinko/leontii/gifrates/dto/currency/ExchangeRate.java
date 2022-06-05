package polovinko.leontii.gifrates.dto.currency;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import polovinko.leontii.gifrates.deserializers.ExchangeRateDeserializer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = ExchangeRateDeserializer.class)
public class ExchangeRate {

  private String currencyCode;
  private Double rate;
}
