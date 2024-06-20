package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

import java.util.List;
import java.util.Optional;

@Repository // Specializzazione di @Component, dedicata alle interazioni col DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Estendendo l'interfaccia JpaRepository sto ereditando tutti i metodi CRUD, non dovrò neanche implementarli
	// Posso eventualmente aggiungere ulteriori metodi custom
	// Nelle parentesi angolari devo specificare <Classe dell'Entità, Tipo dell'Id di quell'Entità>


	// ******************************************** DERIVED QUERIES *******************************************
	List<User> findBySurname(String surname); // SELECT * FROM users WHERE surname = :surname oppure SELECT u FROM User WHERE u.surname = :surname

	List<User> findByName(String name);

	List<User> findByNameAndSurname(String name, String surname); // SELECT u FROM User WHERE u.surname = :surname AND u.name = :name

	List<User> findByNameStartingWithIgnoreCase(String partialName); // SELECT u FROM User WHERE LOWER(u.name) LIKE LOWER(CONCAT(:partialName, '%'))

	List<User> findByNameIn(List<String> names);

	List<User> findByAgeLessThan(int age);

	List<User> findByEmailNull();

	Optional<User> findFirstByAgeGreaterThan(int age);

	boolean existsByEmail(String email);

	// LINK ALLA DOCUMENTAZIONE
	// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.query-creation

	// ******************************************** JPQL QUERIES *******************************************
	@Query("SELECT u FROM User WHERE u.age >= 18")
	List<User> filterBySoloMaggiorenni();


}
