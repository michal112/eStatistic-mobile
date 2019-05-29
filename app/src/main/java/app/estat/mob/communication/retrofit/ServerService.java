package app.estat.mob.communication.retrofit;

import app.estat.dto.SynchronizationPayload;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerService {

    @POST("/synchronize")
    Call<Void> synchronize(@Body SynchronizationPayload synchronizationPayload);
}
