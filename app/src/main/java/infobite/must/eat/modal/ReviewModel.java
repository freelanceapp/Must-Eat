package infobite.must.eat.modal;

public class ReviewModel {
   private String review_name;
    private String review_comment;
    private String review_date;
    private String review_number;

    public ReviewModel() {
    }

    public String getReview_name() {
        return review_name;
    }

    public void setReview_name(String review_name) {
        this.review_name = review_name;
    }

    public String getReview_comment() {
        return review_comment;
    }

    public void setReview_comment(String review_comment) {
        this.review_comment = review_comment;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public String getReview_number() {
        return review_number;
    }

    public void setReview_number(String review_number) {
        this.review_number = review_number;
    }
}
