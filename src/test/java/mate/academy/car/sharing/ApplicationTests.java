package mate.academy.car.sharing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "app.scheduling.enable=false")
@SpringBootTest
class ApplicationTests {
    @Test
    void contextLoads() {
    }
}
