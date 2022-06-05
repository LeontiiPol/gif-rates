package polovinko.leontii.gifrates.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import polovinko.leontii.gifrates.dto.currency.ExchangeRate;
import java.io.IOException;
import java.util.Map;

public class ExchangeRateDeserializer extends StdDeserializer<ExchangeRate> {

  public ExchangeRateDeserializer() {
    this(null);
  }

  public ExchangeRateDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public ExchangeRate deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
    JsonNode rootNode = parser.getCodec().readTree(parser);
    Map.Entry<String, JsonNode> ratesNodeField = getRatesNodeField(rootNode);
    String currencyCode = ratesNodeField.getKey();
    Double exchangeRate = ratesNodeField.getValue().asDouble();
    return new ExchangeRate(currencyCode, exchangeRate);
  }

  private Map.Entry<String, JsonNode> getRatesNodeField(final JsonNode rootNode) {
    return rootNode.get("rates")
        .fields()
        .next();
  }
}
