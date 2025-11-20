import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemItem {
    private List<FileSystemItem> items = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void addItem(FileSystemItem item) {
        items.add(item);
    }

    public void removeItem(FileSystemItem item) {
        items.remove(item);
    }

    @Override
    public void display() {
        System.out.println("Folder: " + name);
        for (FileSystemItem item : items) {
            item.display();
        }
    }
}