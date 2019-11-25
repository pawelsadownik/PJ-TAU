package pl.edu.pjatk.tau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class App
{
    public static void main( String[] args ) throws InterruptedException {

        SpringApplication.run(App.class, args);
    }
}
