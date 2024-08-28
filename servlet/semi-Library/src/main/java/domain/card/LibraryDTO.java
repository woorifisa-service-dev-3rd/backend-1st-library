package domain.card;

public class LibraryDTO {
    private Long id;
    private String name;

    private LibraryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    

    public Long getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public static LibraryDTO of(Long id, String name) {
        return new LibraryDTO(id, name);
    }
}