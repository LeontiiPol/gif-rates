package polovinko.leontii.gifrates.deserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import polovinko.leontii.gifrates.dto.gif.Gif;
import java.io.IOException;
import java.net.URL;

@SpringBootTest
class GifDeserializerIntegrationTest {

  @Autowired
  private ObjectMapper objectMapper;
  private URL json;

  @BeforeEach
  void setUp() {
    json = this.getClass()
        .getResource("/json/gif.json");
  }

  @Test
  void deserialize_whenGifJsonPassed_thenJsonIsDeserializedToGif() throws IOException {
    Gif gif = objectMapper.readValue(json, Gif.class);

    assertEquals("Gif Title", gif.getTitle());
    assertEquals(100, gif.getHeight());
    assertEquals(150, gif.getWidth());
    assertEquals("gifUrl", gif.getGifUrl());
    assertEquals(250, gif.getGifSize());
    assertEquals("mp4Url", gif.getMp4Url());
    assertEquals(200, gif.getMp4Size());
    assertEquals("webpUrl", gif.getWebpUrl());
    assertEquals(300, gif.getWebpSize());
  }
}
