import java.util.ArrayList;
import java.util.List;
interface Component{
    void display();
    void add(Component component);
    void remove(Component component);
}

class NewsPage implements Component {
    private String name;
    private List<Component> components = new ArrayList<>();

    public NewsPage(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void display() {
        // display the page
        System.out.println(this.getName());
        for(Component component : components) {
            component.display();
        }
    }

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }
}

class Paragraph implements Component {
    private String text;

    public Paragraph(String text) {
        this.text = text;
    }

    public void display() {
        // display the paragraph
        System.out.println(text);
    }

    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }
}

class Image implements Component {
    private String path;

    public Image(String path) {
        this.path = path;
    }

    public void display() {
        // display the image
        System.out.println("Image: " + path);
    }

    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }
}


class Video implements Component {
    private String url;

    public Video(String url) {
        this.url = url;
    }

    public void display() {
        // display the video
        System.out.println("Video: " + url);
    }

    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }
}


public class Problem_1{
    public static void main(String[] args){
        NewsPage homePage = new NewsPage("Main Page");
        Paragraph p1 = new Paragraph("Welcome to our website!");
        Image i1 = new Image("path/to/image.png");
        Video v1 = new Video("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

        homePage.add(p1);
        homePage.add(i1);

        NewsPage newsPage = new NewsPage("News Page");
        Paragraph p2 = new Paragraph("Breaking news: ...");
        Paragraph p3 = new Paragraph("More news: ...");
        Image i2 = new Image("path/to/another/image.png");

        newsPage.add(p2);
        newsPage.add(p3);
        newsPage.add(i2);

        homePage.add(newsPage);
        homePage.add(v1);

        homePage.display();

    }
}
