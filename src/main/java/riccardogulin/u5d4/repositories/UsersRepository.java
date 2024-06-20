package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

@Repository // Specializzazione di @Component, dedicata alle interazioni col DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Estendendo l'interfaccia JpaRepository sto ereditando tutti i metodi CRUD, non dovrò neanche implementarli
	// Posso eventualmente aggiungere ulteriori metodi custom
	// Nelle parentesi angolari devo specificare <Classe dell'Entità, Tipo dell'Id di quell'Entità>
}
