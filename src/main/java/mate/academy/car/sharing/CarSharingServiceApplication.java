package mate.academy.car.sharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ConditionalOnProperty(
        value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true
)
@SpringBootApplication
public class CarSharingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSharingServiceApplication.class, args);
    }
}
