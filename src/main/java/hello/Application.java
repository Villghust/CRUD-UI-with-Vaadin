package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepository repository) {
        return (args) -> {

            // Inserindo alguns clientes
            repository.save(new Customer("João", "Santos"));
            repository.save(new Customer("Pedro", "Silva"));
            repository.save(new Customer("Joaquim", "Silva"));
            repository.save(new Customer("Tinguço", "Saturnino"));
            repository.save(new Customer("Michelle", "Da Silva"));

            // Buscar todos os clientes
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // Buscar cliente pelo ID
            Customer customer = repository.findById(1L).get();
            log.info("Cliente encontrado com findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // Buscar cliente pelo sobrenome
            log.info("Cliente encontrado com findByLastNameStartsWithIgnoreCase('Silva'):");
            log.info("--------------------------------------------");
            for (Customer silva : repository
                    .findByLastNameStartsWithIgnoreCase("Silva")) {
                log.info(silva.toString());
            }
            log.info("");
        };
    }

}