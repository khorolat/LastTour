package articlesapp.zack.com.lasttour;

/**
 * Created by Admin on 8/4/2017.
 */

public class Mall {


     String name;
     String url;
     String description;

    public Mall() {
    }

    public Mall(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



