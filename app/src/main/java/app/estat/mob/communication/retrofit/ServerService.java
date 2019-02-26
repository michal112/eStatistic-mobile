package app.estat.mob.communication.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerService {

    @POST("\cow")
    Call<Void> saveCow(@Body Cow cow)
}
