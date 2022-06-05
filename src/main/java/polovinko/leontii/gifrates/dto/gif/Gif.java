package polovinko.leontii.gifrates.dto.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import polovinko.leontii.gifrates.deserializers.GifDeserializer;

@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(using = GifDeserializer.class)
public class Gif {

  @ApiModelProperty(value = "Title of GIF image")
  private String title;

  @ApiModelProperty(value = "The height of this GIF in pixels")
  private Integer height;

  @ApiModelProperty(value = "The width of this GIF in pixels")
  private Integer width;

  @ApiModelProperty(value = "The publicly-accessible direct URL for this GIF")
  @JsonProperty(value = "gif_url")
  private String gifUrl;

  @ApiModelProperty(value = "The size of this GIF in bytes")
  @JsonProperty(value = "gif_size")
  private Integer gifSize;

  @ApiModelProperty(value = "The URL for this GIF in .MP4 format")
  @JsonProperty(value = "mp4_url")
  private String mp4Url;

  @ApiModelProperty(value = "The size in bytes of the .MP4 file corresponding to this GIF")
  @JsonProperty(value = "mp4_size")
  private Integer mp4Size;

  @ApiModelProperty(value = "The URL for this GIF in .webp format")
  @JsonProperty(value = "webp_url")
  private String webpUrl;

  @ApiModelProperty(value = "The size in bytes of the .webp file corresponding to this GIF")
  @JsonProperty(value = "webp_size")
  private Integer webpSize;
}
