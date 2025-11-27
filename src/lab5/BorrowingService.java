package lab5;

import java.util.List;

public class BorrowingService implements BorrowingServiceAPI {
	
	@Override
	public boolean borrowBook(Member member, Book book) {
		if (member == null || book == null) {
			return false;
		}
		
		List<Book> borrowed = member.getBorrowedBooks();
		
		if(borrowed.contains(book)) {
			System.out.println(member.getName() + " already borrowed: " + book.getTitle());
			return false;
		}
		
		if(!book.getIsAvailable()) {
			System.out.println("Book is not available: " + book.getTitle());
			return false;
		}
		
        borrowed.add(book);
        book.setIsAvailable(false);
        System.out.println(member.getName() + " borrowed: " + book.getTitle());
        return true;
		
	}

	@Override
    public boolean returnBook(Member member, Book book) {
		if (member == null || book == null) {
			return false;
		}
		
		List<Book> borrowed = member.getBorrowedBooks();
		
		if (!borrowed.contains(book)) {
			return false;
		}
		
		borrowed.remove(book);
		book.setIsAvailable(true);
		return true;
	}
}
