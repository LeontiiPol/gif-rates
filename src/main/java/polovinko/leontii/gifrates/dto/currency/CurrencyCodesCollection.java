package polovinko.leontii.gifrates.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import polovinko.leontii.gifrates.deserializers.CurrencyCodesCollectionDeserializer;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = CurrencyCodesCollectionDeserializer.class)
public class CurrencyCodesCollection {

  @JsonProperty(value = "codes")
  private Set<String> currencyCodes;
}
