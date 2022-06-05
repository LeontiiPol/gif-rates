package polovinko.leontii.gifrates.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import polovinko.leontii.gifrates.dto.currency.CurrencyCodesCollection;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class CurrencyCodesCollectionDeserializer extends StdDeserializer<CurrencyCodesCollection> {

  public CurrencyCodesCollectionDeserializer() {
    this(null);
  }

  public CurrencyCodesCollectionDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public CurrencyCodesCollection deserialize(final JsonParser parser,
                                             final DeserializationContext ctxt) throws IOException {
    Set<String> currencyCodes = new TreeSet<>();
    Iterator<String> fieldNames = getFieldNames(parser);
    fieldNames.forEachRemaining(currencyCodes::add);
    return new CurrencyCodesCollection(currencyCodes);
  }

  private Iterator<String> getFieldNames(final JsonParser parser) throws IOException {
    return parser.getCodec()
        .readTree(parser)
        .fieldNames();
  }
}
