package riccardogulin.u5d4.exceptions;

public class ItemNotFoundException extends RuntimeException {
	public ItemNotFoundException(long id) {
		super("Il record con id " + id + " non è stato trovato!");
	}
}
