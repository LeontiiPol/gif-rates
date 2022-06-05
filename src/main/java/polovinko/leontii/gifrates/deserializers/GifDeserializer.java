package polovinko.leontii.gifrates.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import polovinko.leontii.gifrates.dto.gif.Gif;
import java.io.IOException;

public class GifDeserializer extends StdDeserializer<Gif> {

  public GifDeserializer() {
    this(null);
  }

  public GifDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Gif deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException {
    JsonNode rootNode = parser.getCodec().readTree(parser);
    String gifTitle = getGifTitle(rootNode);
    JsonNode gifDataJsonNode = getGifDataJsonNode(rootNode);
    return buildGif(gifTitle, gifDataJsonNode);
  }

  private String getGifTitle(final JsonNode rootNode) {
    return rootNode.get("data")
        .get("title")
        .asText();
  }

  private JsonNode getGifDataJsonNode(final JsonNode rootNode) {
    return rootNode.get("data")
        .get("images")
        .get("original");
  }

  private Gif buildGif(final String title, final JsonNode gifDataJsonNode) {
    Gif gif = new Gif();
    gif.setTitle(title);
    gif.setHeight(gifDataJsonNode.get("height").asInt());
    gif.setWidth(gifDataJsonNode.get("width").asInt());
    gif.setGifSize(gifDataJsonNode.get("size").asInt());
    gif.setGifUrl(gifDataJsonNode.get("url").asText());
    gif.setMp4Url(gifDataJsonNode.get("mp4").asText());
    gif.setMp4Size(gifDataJsonNode.get("mp4_size").asInt());
    gif.setWebpUrl(gifDataJsonNode.get("webp").asText());
    gif.setWebpSize(gifDataJsonNode.get("webp_size").asInt());
    return gif;
  }
}
