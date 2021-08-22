package ir.android_web.languageresources;

/**
 * Created by Arash on 12/25/2018.
 */

public class BookItem {

    String name;
    String link;
    String pic;

    public BookItem(String name, String link, String pic) {
        this.name = name;
        this.link = link;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
