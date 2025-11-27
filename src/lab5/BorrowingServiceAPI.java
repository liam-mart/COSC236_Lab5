package lab5;

public interface BorrowingServiceAPI {

    boolean borrowBook(Member member, Book book);

    boolean returnBook(Member member, Book book);
}
