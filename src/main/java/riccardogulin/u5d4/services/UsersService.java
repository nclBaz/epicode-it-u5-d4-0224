package riccardogulin.u5d4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.exceptions.ItemNotFoundException;
import riccardogulin.u5d4.repositories.UsersRepository;

import java.util.List;

@Service // Specializzazione di @Component
// Il service è una classe che ci consente di poter aggiungere della logica personalizzata ulteriore durante le varie
// operazioni col database, ad esempio posso effettuare dei controlli di validazione sugli attributi di un nuovo record,
// oppure magari aggiungere ulteriori attributi
@Slf4j
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	public void saveUser(User newUser) {
		// Nella save di solito è opportuno effettuare una serie di controlli prima di salvare il nuovo utente
		// Durante una save solitamente può anche essere necessario dover aggiungere ulteriori dati "Server Generated"

		// 1. Controllo se esiste già un utente con questa email
		if (usersRepository.existsByEmail(newUser.getEmail())) {
			throw new RuntimeException("L'email " + newUser.getEmail() + " è già in uso!");
		}
		// 2. Effetto ulteriori controlli per validare i campi del nuovo utente
		if (newUser.getName().length() < 2) throw new RuntimeException("Il nome non può essere più corto di 2 caratteri");

		// 3. Aggiungo un avatar di default al nuovo utente
		newUser.setAvatarURL("https://placebear.com/200/300");

		// 4. Salvo il nuovo utente
		usersRepository.save(newUser);

		// 5. Log di avvenuto salvataggio
		log.info("Nuovo utente " + newUser.getEmail() + " salvato correttamente");
	}

	public List<User> findAll() {
		return usersRepository.findAll();
	}

	public User findById(long userId) {
/*		Optional<User> foundOrNot = usersRepository.findById(userId);
		if (foundOrNot.isPresent()) {
			return foundOrNot.get();
			// Qua dopo aver verificato che lo user sia stato trovato, con il .get posso "convertire" l'Optional in User
		} else {
			// Se lo user non è stato trovato invece, lanciamo un'eccezione Not Found
			throw new ItemNotFoundException(userId);
		}*/
		return usersRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException(userId)); // Alternativa molto più compatta al codice commentato sopra
	}

	public void findByIdAndDelete(long userId) {
		User found = this.findById(userId);
		usersRepository.delete(found);
		log.info("Utente con id " + userId + " eliminato correttamente");
	}

	public void findByIdAndUpdate(long userId, User updatedUser) {
		// 1. Cerco l'utente tramite id
		User found = this.findById(userId);
		// 2. Aggiorno campo per campo i dati dell'utente del db con quelli passati come parametro a questo metodo
		found.setName(updatedUser.getName());
		found.setSurname(updatedUser.getSurname());
		found.setAge(updatedUser.getAge());
		found.setEmail(updatedUser.getEmail());
		// 3. Risalvo lo user così modificato
		usersRepository.save(found);
		// 4. Log
		log.info("Utente con id " + userId + " modificato correttamente");
	}

	public long count() {
		return usersRepository.count();
	}

	public List<User> filterBySurname(String surname) {
		return usersRepository.findBySurname(surname);
	}

	public List<User> filterByNameSurname(String name, String surname) {
		return usersRepository.findByNameAndSurname(name, surname);
	}

	public List<User> filterByNameStartsWith(String partialName) {
		return usersRepository.findByNameStartingWithIgnoreCase(partialName);
	}

	public List<User> filterByListOfNames(List<String> names) {
		return usersRepository.findByNameIn(names);
	}

	public List<User> filterByAgeLessThan(int age) {
		return usersRepository.findByAgeLessThan(age);
	}

	public User findOneByAgeGreaterThan(int age) {
		return usersRepository.findFirstByAgeGreaterThan(age).orElseThrow(() -> new RuntimeException("Non trovato"));
	}
}
