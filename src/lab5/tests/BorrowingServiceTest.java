package lab5.tests;

import lab5.Book;
import lab5.BorrowingBookResult;
import lab5.BorrowingService;
import lab5.Member;
import lab5.PaperBook;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BorrowingServiceTest {

    @Test
    void borrowBook_success_whenAvailableAndUnderLimit() {
        BorrowingService service = BorrowingService.getInstance();
        Member alice = new Member("Alice", service);
        Book dune = new PaperBook("Dune");

        BorrowingBookResult result = service.borrowBook(alice, dune);

        assertTrue(result.getIsSuccess(), "Borrow should succeed for available book");
        assertFalse(dune.getIsAvailable(), "Book should be marked unavailable after borrow");
        assertTrue(alice.getBorrowedBooks().contains(dune), "Member should now have the book");
    }

    @Test
    void borrowBook_fails_whenSameBookBorrowedTwiceBySameMember() {
        BorrowingService service = BorrowingService.getInstance();
        Member alice = new Member("Alice", service);
        Book dune = new PaperBook("Dune");

        BorrowingBookResult first = service.borrowBook(alice, dune);
        BorrowingBookResult second = service.borrowBook(alice, dune);

        assertTrue(first.getIsSuccess(), "First borrow should succeed");
        assertFalse(second.getIsSuccess(), "Second borrow of same book should fail");
        // Optional: check message text depending on how you phrased it
        // assertTrue(second.getBorrowingMessage().contains("already"), "Message should mention already borrowed");
    }

    @Test
    void borrowBook_fails_whenBookAlreadyBorrowedByAnotherMember() {
        BorrowingService service = BorrowingService.getInstance();
        Member alice = new Member("Alice", service);
        Member bob = new Member("Bob", service);
        Book dune = new PaperBook("Dune");

        BorrowingBookResult aliceResult = service.borrowBook(alice, dune);
        BorrowingBookResult bobResult = service.borrowBook(bob, dune);

        assertTrue(aliceResult.getIsSuccess(), "First member should borrow successfully");
        assertFalse(bobResult.getIsSuccess(), "Second member should not be able to borrow unavailable book");
        assertTrue(alice.getBorrowedBooks().contains(dune), "Book should still belong to Alice");
        assertFalse(bob.getBorrowedBooks().contains(dune), "Bob should not have the book");
    }

    @Test
    void borrowBook_fails_whenMemberAlreadyHasThreeBooks() {
        BorrowingService service = BorrowingService.getInstance();
        Member alice = new Member("Alice", service);

        Book b1 = new PaperBook("Book 1");
        Book b2 = new PaperBook("Book 2");
        Book b3 = new PaperBook("Book 3");
        Book b4 = new PaperBook("Book 4");

        service.borrowBook(alice, b1);
        service.borrowBook(alice, b2);
        service.borrowBook(alice, b3);
        BorrowingBookResult fourth = service.borrowBook(alice, b4);

        assertEquals(3, alice.getBorrowedBooks().size(), "Member should still only have three books");
        assertFalse(fourth.getIsSuccess(), "Fourth borrow should fail due to limit");
        // assertTrue(fourth.getBorrowingMessage().contains("3"), "Message should mention limit of 3");
    }

    @Test
    void returnBook_success_whenMemberHasBook() {
        BorrowingService service = BorrowingService.getInstance();
        Member alice = new Member("Alice", service);
        Book dune = new PaperBook("Dune");

        service.borrowBook(alice, dune);
        BorrowingBookResult returnResult = service.returnBook(alice, dune);

        assertTrue(returnResult.getIsSuccess(), "Return should succeed when member has the book");
        assertTrue(dune.getIsAvailable(), "Book should be available after return");
        assertFalse(alice.getBorrowedBooks().contains(dune), "Member should no longer have the book");
    }

    @Test
    void returnBook_fails_whenMemberNeverBorrowedBook() {
        BorrowingService service = BorrowingService.getInstance();
        Member alice = new Member("Alice", service);
        Book dune = new PaperBook("Dune");

        BorrowingBookResult result = service.returnBook(alice, dune);

        assertFalse(result.getIsSuccess(), "Return should fail when member never borrowed this book");
        assertTrue(dune.getIsAvailable(), "Book should still be available");
        assertFalse(alice.getBorrowedBooks().contains(dune), "Member should still not have the book");
    }
}
