package riccardogulin.u5d4.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	private long id;
	private String name;
	private String surname;
	private String email;
	private int age;
	private String avatarURL;

	public User(String name, String surname, String email, int age) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.age = age;
	}
}
