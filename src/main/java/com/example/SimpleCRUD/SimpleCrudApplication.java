package com.example.SimpleCRUD;

import com.example.SimpleCRUD.model.Contact;
import com.example.SimpleCRUD.model.Person;
import com.example.SimpleCRUD.repository.ContactRepository;
import com.example.SimpleCRUD.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class SimpleCrudApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(SimpleCrudApplication.class, args);

		ContactRepository contactRepository = cac.getBean(ContactRepository.class);
		PersonRepository personRepository = cac.getBean(PersonRepository.class);
		personRepository.deleteAll();
		contactRepository.deleteAll();

		Person p2 = new Person("Batman");
		Person p1 = new Person("Mano Brown");

		Contact c1 = new Contact("name1", "email1", "123-456-1", p1);
		Contact c2 = new Contact("name2", "email2", "123-456-2", p2);
		contactRepository.save(c1);
		contactRepository.save(c2);


	}

//	@Bean
//	CommandLineRunner init(ContactRepository repository){
//		return args -> {
//			repository.deleteAll();
//			LongStream.range(1,11)
//					.mapToObj(i -> {
//						Contact c = new Contact();
//						c.setName("Contact " + i);
//						c.setEmail("Contact" + i + "@email.com");
//						c.setPhone("(111) 111-1111");
//						return c;
//					}).map(c -> repository.save(c))
//					.forEach(System.out::println);
//		};
//	}
}
