package polovinko.leontii.gifrates.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies/rate")
public class GifCurrencyController {

  @GetMapping("/{code}")
  public ResponseEntity<Object> getRateAsGif(@PathVariable String code) {
    return null;
  }
}
