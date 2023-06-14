package com.example.SimpleCRUD;

import com.example.SimpleCRUD.model.Contact;
import com.example.SimpleCRUD.model.Person;
import com.example.SimpleCRUD.repository.ContactRepository;
import com.example.SimpleCRUD.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SimpleCrudApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(SimpleCrudApplication.class, args);

		ContactRepository contactRepository = cac.getBean(ContactRepository.class);
		PersonRepository personRepository = cac.getBean(PersonRepository.class);
		personRepository.deleteAll();

		Contact c1 = new Contact("name1", "email1", "123-456-1");
		Contact c2 = new Contact("name2", "email2", "123-456-2");
		List<Contact> contacts1 = Arrays.asList(c1,c2);
		Person p1 = new Person("Mano Brown", contacts1);
		personRepository.save(p1);

		Contact c3 = new Contact("name3", "email3", "123-456-3");
		Contact c4 = new Contact("name4", "email4", "123-456-4");
		List<Contact> contacts2 = Arrays.asList(c3,c4);
		Person p2 = new Person("Batman", contacts2);
		personRepository.save(p2);
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
