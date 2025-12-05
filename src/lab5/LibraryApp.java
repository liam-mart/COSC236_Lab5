package lab5;

public class LibraryApp {

	// Create Controller, It will create an empty Library
	private static LibrarianController librarian = new LibrarianController();

	// Some functions for the Assignment
	private static void addMember(String name) {
		librarian.addMember(name);
	}

	private static void addBook(String title) {
		librarian.addBook(title);
	}

	private static void borrowBook(String title, String name) {
		librarian.borrowBookByMember(title, name);
	}

	private static void returnBook(String title, String name) {
		librarian.returnBookByMember(title, name);
	}

	public static void main(String[] args) {

		System.out.println(" *** Library management system demo *** ");

		// Adding one book, see the Sequence diagram in the lab document.
		addBook("Test");

		addMember("Alice"); // 1. Add a member
		borrowBook("Test", "Alice"); // 2. Borrow a book
		returnBook("Test", "Alice"); // 3. Return book
		librarian.removeBook("Test");
		librarian.removeMember("Alice");

		// Adding some books to the catalog
		System.out.println("\n *** Adding books to the library:");
		librarian.addBook("Dune");
		librarian.addBook("1984");
		librarian.addBook("Moby Dick");
		librarian.addEBook("LOTR");
		librarian.addAudioBook("The Hobbit");
		librarian.showBooks();

		// Adding members to the library
		System.out.println("\n *** Adding members to the library:");
		librarian.addMember("Alice");
		librarian.addMember("Bob");
		librarian.showMembers();

		librarian.borrowBookByMember("Dune", "Alice");
		librarian.borrowBookByMember("1984", "Alice");
		librarian.borrowBookByMember("LOTR", "Alice");
		librarian.borrowBookByMember("Moby Dick", "Bob");
		librarian.borrowBookByMember("The Hobbit", "Bob");
		librarian.borrowBookByMember("Dune", "Bob"); // will be rejected

		System.out.println("\n *** Books borrowed by Alice:");
		librarian.showMemberBooks("Alice");

		System.out.println("\n *** Books borrowed by Bob:");
		librarian.showMemberBooks("Bob");

		System.out.println("\n *** Book returned by Alice: Dune");
		librarian.returnBookByMember("Dune", "Alice");
		librarian.borrowBookByMember("Dune", "Bob"); // will be successful
		System.out.println("\n *** Books borrowed by Bob:");
		librarian.showMemberBooks("Bob");
	}

}
