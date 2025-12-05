package lab5;

import java.util.List;

public class BorrowingService implements BorrowingServiceAPI {

	@Override
	public BorrowingBookResult borrowBook(Member member, Book book) {
		BorrowingBookResult result = new BorrowingBookResult();
		if (member == null || book == null) {
			result.setBorrowingMessage("Member or book doesn't exist.");
			result.isSuccess(false);
			return result;
		}
		if (member.getBorrowedBooks().contains(book)) {
			result.setBorrowingMessage(member.getName() + " already has this book.");
			result.isSuccess(false);
			return result;
		}
		if (!book.getIsAvailable()) {
			result.setBorrowingMessage("This book is unavailable.");
			result.isSuccess(false);
			return result;
		}
		if (member.borrowedBooksCount() >= 3) {
			result.isSuccess(false);
			result.setBorrowingMessage(member.getName() + " already has 3 books.");
			return result;
		}

		List<Book> borrowed = member.getBorrowedBooks();
		borrowed.add(book);
		book.setIsAvailable(false);
		result.isSuccess(true);
		result.setBorrowingMessage(member.getName() + " borrowed: " + book.getTitle() + ".");
		return result;

	}

	@Override
	public BorrowingBookResult returnBook(Member member, Book book) {
		BorrowingBookResult result = new BorrowingBookResult();
		if (member == null || book == null) {
			result.isSuccess(false);
			result.setBorrowingMessage("Member or book is invalid.");
			return result;
		}

		List<Book> borrowed = member.getBorrowedBooks();

		if (!borrowed.contains(book)) {
			result.setBorrowingMessage(member.getName() + " doesn't have this book.");
			result.isSuccess(false);
			return result;
		}

		result.isSuccess(true);
		borrowed.remove(book);
		result.setBorrowingMessage(member.getName() + " has returned " + book.getTitle() + ".");
		book.setIsAvailable(true);
		return result;
	}
}
