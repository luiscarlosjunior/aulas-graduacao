import java.util.ArrayList;
import java.util.List;

public class BookCollection {
    private List<String> books = new ArrayList<>();
    
    public void addBook(String book) {
        books.add(book);
    }
    
    public Iterator<String> createIterator() {
        return new BookIterator(books);
    }
    
    private class BookIterator implements Iterator<String> {
        private List<String> books;
        private int position = 0;
        
        public BookIterator(List<String> books) {
            this.books = books;
        }
        
        public boolean hasNext() {
            return position < books.size();
        }
        
        public String next() {
            return books.get(position++);
        }
    }
}
