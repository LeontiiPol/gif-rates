package polovinko.leontii.gifrates.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import polovinko.leontii.gifrates.deserializers.GifDeserializer;

@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(using = GifDeserializer.class)
public class Gif {

  private String title;
  private Integer height;
  private Integer width;
  @JsonProperty(value = "gif_url")
  private String gifUrl;
  @JsonProperty(value = "gif_size")
  private Integer gifSize;
  @JsonProperty(value = "mp4_url")
  private String mp4Url;
  @JsonProperty(value = "mp4_size")
  private Integer mp4Size;
  @JsonProperty(value = "webp_url")
  private String webpUrl;
  @JsonProperty(value = "webp_size")
  private Integer webpSize;
}
