package infobite.must.eat.modal.default_modal;

public class OffersModel {
    String coupon_id;
    String coupon_image;
    String coupon_name;
    String coupon_number;
    String coupon_exp_date;

    public OffersModel() {
    }

    public OffersModel(String coupon_id, String coupon_image, String coupon_name, String coupon_number, String coupon_exp_date) {
        this.coupon_id = coupon_id;
        this.coupon_image = coupon_image;
        this.coupon_name = coupon_name;
        this.coupon_number = coupon_number;
        this.coupon_exp_date = coupon_exp_date;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_image() {
        return coupon_image;
    }

    public void setCoupon_image(String coupon_image) {
        this.coupon_image = coupon_image;
    }

    public String getCoupon_name() {
        return coupon_name;
    }

    public void setCoupon_name(String coupon_name) {
        this.coupon_name = coupon_name;
    }

    public String getCoupon_number() {
        return coupon_number;
    }

    public void setCoupon_number(String coupon_number) {
        this.coupon_number = coupon_number;
    }

    public String getCoupon_exp_date() {
        return coupon_exp_date;
    }

    public void setCoupon_exp_date(String coupon_exp_date) {
        this.coupon_exp_date = coupon_exp_date;
    }
}
