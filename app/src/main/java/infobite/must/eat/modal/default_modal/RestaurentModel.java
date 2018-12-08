package infobite.must.eat.modal.default_modal;

public class RestaurentModel {
    String r_name;
    String r_type;
    String r_rate;
    String r_discount;
    String r_img;
    int r_img1;

    public int getR_img1() {
        return r_img1;
    }

    public void setR_img1(int r_img1) {
        this.r_img1 = r_img1;
    }

    public RestaurentModel() {
    }

    public RestaurentModel(String r_name, String r_type, String r_rate, String r_discount, String r_img) {
        this.r_name = r_name;
        this.r_type = r_type;
        this.r_rate = r_rate;
        this.r_discount = r_discount;
        this.r_img = r_img;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_type() {
        return r_type;
    }

    public void setR_type(String r_type) {
        this.r_type = r_type;
    }

    public String getR_rate() {
        return r_rate;
    }

    public void setR_rate(String r_rate) {
        this.r_rate = r_rate;
    }

    public String getR_discount() {
        return r_discount;
    }

    public void setR_discount(String r_discount) {
        this.r_discount = r_discount;
    }

    public String getR_img() {
        return r_img;
    }

    public void setR_img(String r_img) {
        this.r_img = r_img;
    }
}
