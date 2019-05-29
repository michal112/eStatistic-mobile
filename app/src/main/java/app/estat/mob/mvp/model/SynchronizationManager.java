package app.estat.mob.mvp.model;

import app.estat.mob.communication.retrofit.ServerService;
import app.estat.mob.communication.retrofit.ServerSettings;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SynchronizationManager {
    private ServerService mService;

    private ServerSettings mServerSettings;

    public SynchronizationManager() {
        mServerSettings = new ServerSettings();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(createUri(mServerSettings))
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        mService = retrofit.create(ServerService.class);
    }

    private String createUri(ServerSettings settings) {
        return settings.getProtocol() + "://" +
                settings.getIp() + ":" + settings.getPort();
    }
}
