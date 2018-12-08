package infobite.must.eat.modal.api_modal.login_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData implements Parcelable
{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_fullname")
    @Expose
    private String userFullname;
    @SerializedName("user_profile_pic")
    @Expose
    private String userProfilePic;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("user_registration_date")
    @Expose
    private String userRegistrationDate;
    public final static Parcelable.Creator<UserData> CREATOR = new Creator<UserData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        public UserData[] newArray(int size) {
            return (new UserData[size]);
        }

    }
            ;

    protected UserData(Parcel in) {
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.userFullname = ((String) in.readValue((String.class.getClassLoader())));
        this.userProfilePic = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.userRegistrationDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserData() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserRegistrationDate() {
        return userRegistrationDate;
    }

    public void setUserRegistrationDate(String userRegistrationDate) {
        this.userRegistrationDate = userRegistrationDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(userFullname);
        dest.writeValue(userProfilePic);
        dest.writeValue(email);
        dest.writeValue(phone);
        dest.writeValue(userRegistrationDate);
    }

    public int describeContents() {
        return 0;
    }

}
