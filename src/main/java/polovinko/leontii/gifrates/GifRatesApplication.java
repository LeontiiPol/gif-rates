package polovinko.leontii.gifrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GifRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GifRatesApplication.class, args);
	}

}
