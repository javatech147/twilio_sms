package taskcom.android.manish.taskapp.rest;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @FormUrlEncoded
    @POST("Accounts/{ACCOUNT_SID}/SMS/Messages.json")
    Call<ResponseBody> sendOtp(
            @Path("ACCOUNT_SID") String accountSId,
            @Header("Authorization") String auth,
            @Header("mime-type") String contentType,
            @FieldMap Map<String, String> data
    );

    // Get list of Messages
    @GET("Accounts/{ACCOUNT_SID}/Messages.json")
    Call<ServerResponseMessageList> getOtpSentMessagesList(
            @Path("ACCOUNT_SID") String accountSId,
            @Header("Authorization") String auth,
            @Header("mime-type") String contentType
    );

}
