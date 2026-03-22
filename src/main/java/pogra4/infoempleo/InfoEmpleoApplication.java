package pogra4.infoempleo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "pogra4.infoempleo",
        "presentation",
        "logic",
        "data"
})
@EnableJpaRepositories(basePackages = "data")
public class InfoEmpleoApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfoEmpleoApplication.class, args);
    }
}