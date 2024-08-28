package domain.card;

public class AppConfig {
	public static CardDAO cardDAO() {
		return new CardDAO();
	}
	
	public static LibraryDAO libraryDAO() {
		return new LibraryDAO();
	}
}
