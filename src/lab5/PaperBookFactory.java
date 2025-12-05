package lab5;

public class PaperBookFactory extends BookFactory {

    @Override
    Book createBook(String title) {
        return new PaperBook(title) {
        };
    }

}
