package infobite.must.eat.retrofit_provider;

import infobite.must.eat.constant.Constant;
import infobite.must.eat.modal.api_modal.login_response.LoginModal;
import infobite.must.eat.modal.api_modal.notification_response.NotificationMainModel;
import infobite.must.eat.modal.api_modal.offer_response.OfferMainModal;
import infobite.must.eat.modal.api_modal.vendor_detail.VendorDetailMainModal;
import infobite.must.eat.modal.api_modal.vendor_list.VendorListMainModal;
import infobite.must.eat.modal.api_modal.version_response.VersionModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;
import rx.Observable;

public interface RetrofitApiClient {

    @Multipart
    @POST(Constant.USER_REGISTRATION)
    Call<LoginModal> userRegistration(@Part("name") RequestBody name, @Part MultipartBody.Part file,
                                      @Part("email") RequestBody email, @Part("password") RequestBody password,
                                      @Part("mobile_number") RequestBody mobile_number);

    @FormUrlEncoded
    @POST(Constant.USER_LOGIN)
    Call<LoginModal> userLogin(@Field("username") String username,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST(Constant.USER_PROFILE)
    Call<LoginModal> userProfile(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST(Constant.VERIFICATION)
    Call<LoginModal> userVerification(@Field("mobile_number") String mobile_number,
                                      @Field("otp_number") String otp_number);

    @FormUrlEncoded
    @POST(Constant.VENDOR_DETAIL)
    Call<VendorDetailMainModal> vendorDetail(@Field("vendor_id") String vendor_id);

    @FormUrlEncoded
    @POST(Constant.VENDOR_LIST)
    Call<VendorListMainModal> vendorList(@Field("latitude") double latitude, @Field("longitude") double longitude,
                                         @Field("radius") String radius);

    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getUserList(@Field("user") String user,
                                   @Field("age") String age,
                                   @Field("state") String state,
                                   @Field("city") String city);

    @GET(Constant.OFFER_LIST)
    Call<OfferMainModal> getOfferList();

    @GET(Constant.NOTIFICATION_LIST)
    Call<NotificationMainModel> getNotificationList();

    @GET(Constant.APP_VERSION)
    Call<VersionModel> getVersion();



    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getAllLikes(@Field("id") String id);

    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Observable<ResponseBody> addShortedList(@Field("id") String id,
                                            @Field("like_id") String like_id);

    @FormUrlEncoded
    @POST(Constant.FOROGOT_PASSWORD)
    Call<ResponseBody> getShortedList(@Field("user_id") String user_id);

    /*
     * Download image
     * */
    @GET
    Call<ResponseBody> getImageDetails(@Url String fileUrl);
}