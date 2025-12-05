package lab5;

public class AudioBookFactory extends BookFactory {

    @Override
    Book createBook(String title) {
        return new AudioBook(title) {
        };
    }

}
