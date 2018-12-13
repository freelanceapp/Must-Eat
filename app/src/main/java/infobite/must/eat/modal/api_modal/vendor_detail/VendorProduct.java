package infobite.must.eat.modal.api_modal.vendor_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorProduct implements Parcelable {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_content")
    @Expose
    private String productContent;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("product_description")
    @Expose
    private String productDescription;
    @SerializedName("product_quantity_type")
    @Expose
    private String productQuantityType;
    @SerializedName("product_full_rate")
    @Expose
    private String productFullRate;
    @SerializedName("product_half_rate")
    @Expose
    private String productHalfRate;
    @SerializedName("product_stock_type")
    @Expose
    private String productStockType;
    @SerializedName("product_stock_quantity")
    @Expose
    private String productStockQuantity;
    @SerializedName("product_delivery_time_slot")
    @Expose
    private String productDeliveryTimeSlot;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("product_category_id")
    @Expose
    private String productCategoryId;
    @SerializedName("product_topping_name")
    @Expose
    private String productToppingName;
    @SerializedName("product_topping_rate")
    @Expose
    private String productToppingRate;
    @SerializedName("product_delivery_time")
    @Expose
    private String productDeliveryTime;
    @SerializedName("product_recommendation")
    @Expose
    private String productRecommendation;
    @SerializedName("product_most_popular")
    @Expose
    private String productMostPopular;
    @SerializedName("product_availability")
    @Expose
    private String productAvailability;
    @SerializedName("product_created_date")
    @Expose
    private String productCreatedDate;
    public final static Parcelable.Creator<VendorProduct> CREATOR = new Creator<VendorProduct>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VendorProduct createFromParcel(Parcel in) {
            return new VendorProduct(in);
        }

        public VendorProduct[] newArray(int size) {
            return (new VendorProduct[size]);
        }

    };

    protected VendorProduct(Parcel in) {
        this.productId = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.productContent = ((String) in.readValue((String.class.getClassLoader())));
        this.productImage = ((String) in.readValue((String.class.getClassLoader())));
        this.productDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.productQuantityType = ((String) in.readValue((String.class.getClassLoader())));
        this.productFullRate = ((String) in.readValue((String.class.getClassLoader())));
        this.productHalfRate = ((String) in.readValue((String.class.getClassLoader())));
        this.productStockType = ((String) in.readValue((String.class.getClassLoader())));
        this.productStockQuantity = ((String) in.readValue((String.class.getClassLoader())));
        this.productDeliveryTimeSlot = ((String) in.readValue((String.class.getClassLoader())));
        this.productType = ((String) in.readValue((String.class.getClassLoader())));
        this.productCategoryId = ((String) in.readValue((String.class.getClassLoader())));
        this.productToppingName = ((String) in.readValue((String.class.getClassLoader())));
        this.productToppingRate = ((String) in.readValue((String.class.getClassLoader())));
        this.productDeliveryTime = ((String) in.readValue((String.class.getClassLoader())));
        this.productRecommendation = ((String) in.readValue((String.class.getClassLoader())));
        this.productMostPopular = ((String) in.readValue((String.class.getClassLoader())));
        this.productAvailability = ((String) in.readValue((String.class.getClassLoader())));
        this.productCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public VendorProduct() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductQuantityType() {
        return productQuantityType;
    }

    public void setProductQuantityType(String productQuantityType) {
        this.productQuantityType = productQuantityType;
    }

    public String getProductFullRate() {
        return productFullRate;
    }

    public void setProductFullRate(String productFullRate) {
        this.productFullRate = productFullRate;
    }

    public String getProductHalfRate() {
        return productHalfRate;
    }

    public void setProductHalfRate(String productHalfRate) {
        this.productHalfRate = productHalfRate;
    }

    public String getProductStockType() {
        return productStockType;
    }

    public void setProductStockType(String productStockType) {
        this.productStockType = productStockType;
    }

    public String getProductStockQuantity() {
        return productStockQuantity;
    }

    public void setProductStockQuantity(String productStockQuantity) {
        this.productStockQuantity = productStockQuantity;
    }

    public String getProductDeliveryTimeSlot() {
        return productDeliveryTimeSlot;
    }

    public void setProductDeliveryTimeSlot(String productDeliveryTimeSlot) {
        this.productDeliveryTimeSlot = productDeliveryTimeSlot;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductToppingName() {
        return productToppingName;
    }

    public void setProductToppingName(String productToppingName) {
        this.productToppingName = productToppingName;
    }

    public String getProductToppingRate() {
        return productToppingRate;
    }

    public void setProductToppingRate(String productToppingRate) {
        this.productToppingRate = productToppingRate;
    }

    public String getProductDeliveryTime() {
        return productDeliveryTime;
    }

    public void setProductDeliveryTime(String productDeliveryTime) {
        this.productDeliveryTime = productDeliveryTime;
    }

    public String getProductRecommendation() {
        return productRecommendation;
    }

    public void setProductRecommendation(String productRecommendation) {
        this.productRecommendation = productRecommendation;
    }

    public String getProductMostPopular() {
        return productMostPopular;
    }

    public void setProductMostPopular(String productMostPopular) {
        this.productMostPopular = productMostPopular;
    }

    public String getProductAvailability() {
        return productAvailability;
    }

    public void setProductAvailability(String productAvailability) {
        this.productAvailability = productAvailability;
    }

    public String getProductCreatedDate() {
        return productCreatedDate;
    }

    public void setProductCreatedDate(String productCreatedDate) {
        this.productCreatedDate = productCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(productId);
        dest.writeValue(productName);
        dest.writeValue(productContent);
        dest.writeValue(productImage);
        dest.writeValue(productDescription);
        dest.writeValue(productQuantityType);
        dest.writeValue(productFullRate);
        dest.writeValue(productHalfRate);
        dest.writeValue(productStockType);
        dest.writeValue(productStockQuantity);
        dest.writeValue(productDeliveryTimeSlot);
        dest.writeValue(productType);
        dest.writeValue(productCategoryId);
        dest.writeValue(productToppingName);
        dest.writeValue(productToppingRate);
        dest.writeValue(productDeliveryTime);
        dest.writeValue(productRecommendation);
        dest.writeValue(productMostPopular);
        dest.writeValue(productAvailability);
        dest.writeValue(productCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}
