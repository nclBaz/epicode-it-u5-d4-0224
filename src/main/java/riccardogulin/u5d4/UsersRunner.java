package riccardogulin.u5d4;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.services.UsersService;

import java.util.ArrayList;
import java.util.List;
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

		usersService.findAll().forEach(System.out::println);

		try {

			usersService.findByIdAndUpdate(3, new User("Giovanni", "Storti", "aldo@baglio.it", 20));
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		System.out.println("Numero utenti nel db " + usersService.count());

		System.out.println("--------------------------- FILTRA PER SURNAME ---------------------");
		usersService.filterBySurname("Baglio").forEach(System.out::println);

		System.out.println("--------------------------- FILTRA PER NAME AND SURNAME ---------------------");

		usersService.filterByNameSurname("Elrond", "Baglio").forEach(System.out::println);

		System.out.println("--------------------------- FILTRA PER NOME CHE INIZIA CON ---------------------");
		usersService.filterByNameStartsWith("al").forEach(System.out::println);

		System.out.println("--------------------------- FILTRA PER LISTA DI NOMI ---------------------");
		List<String> names = new ArrayList<>();
		names.add("Aldo");
		names.add("Giovanni");
		names.add("Elrond");
		usersService.filterByListOfNames(names).forEach(System.out::println);

		System.out.println("--------------------------- FILTRA PER ETA' INFERIORE A ---------------------");
		usersService.filterByAgeLessThan(18).forEach(System.out::println);

		System.out.println("--------------------------- CERCA IL PRIMO PER ETA' SUPERIORE A ---------------------");
		try {
			System.out.println(usersService.findOneByAgeGreaterThan(1000));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
}
