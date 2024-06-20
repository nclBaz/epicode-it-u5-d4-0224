package riccardogulin.u5d4;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.services.UsersService;

import java.util.Locale;
import java.util.Random;

@Component
public class UsersRunner implements CommandLineRunner {
	/*	@Autowired
		private UsersRepository usersRepository;*/
	// NON BISOGNEREBBE USARE MAI DIRETTAMENTE UNA REPOSITORY
	// MOLTO MEGLIO PASSARE SEMPRE PER IL SERVICE
	@Autowired
	private UsersService usersService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(Locale.ITALY);
		Random rndm = new Random();
		User newUser = new User(faker.lordOfTheRings().character(), faker.name().lastName(), faker.internet().emailAddress(), rndm.nextInt(0, 100));

		usersService.saveUser(newUser);
/*
		usersRepository.save(newUser);

		usersRepository.findAll().stream().forEach(System.out::println);*/


	}
}
