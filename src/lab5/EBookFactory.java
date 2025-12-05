package lab5;

public class EBookFactory extends BookFactory {

    @Override
    Book createBook(String title) {
        return new EBook(title) {
        };
    }

}
