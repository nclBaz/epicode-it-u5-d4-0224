package riccardogulin.u5d4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.repositories.UsersRepository;

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
		// 2. Effetto ulteriori controlli per validare i campi del nuovo utente
		if (newUser.getName().length() < 2) throw new RuntimeException("Il nome non può essere più corto di 2 caratteri");

		// 3. Aggiungo un avatar di default al nuovo utente
		newUser.setAvatarURL("https://placebear.com/200/300");

		// 4. Salvo il nuovo utente
		usersRepository.save(newUser);

		// 5. Log di avvenuto salvataggio
		log.info("Nuovo utente " + newUser.getEmail() + " salvato correttamente");
	}
}
