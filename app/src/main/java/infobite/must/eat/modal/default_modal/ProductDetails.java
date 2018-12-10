package infobite.must.eat.modal.default_modal;

/**
 * Created by Dell on 12/1/2018.
 */

public class ProductDetails {

    private int image;
    private int imagetwo;
    private String title;
    private String subtitle;
    private String more;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }
    public int getImagetwo() {
        return imagetwo;
    }

    public void setImagetwo(int imagetwo) {
        this.imagetwo = imagetwo;
    }


    public ProductDetails(int image, String title, String subtitle, String more, int imagtwo)     {

        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.more = more;
        this.imagetwo = imagtwo;

    }


}
