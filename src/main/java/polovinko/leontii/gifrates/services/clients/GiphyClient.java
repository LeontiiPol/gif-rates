package polovinko.leontii.gifrates.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import polovinko.leontii.gifrates.dto.gif.Gif;
import polovinko.leontii.gifrates.dto.gif.GifTag;

@FeignClient(name = "giphy", url = "${client.giphy.api.url}")
public interface GiphyClient {

  @GetMapping("${client.giphy.api.random}")
  Gif getRandomGifByTag(@RequestParam final GifTag tag);
}
