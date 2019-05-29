package app.estat.mob.communication.service;

import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.SimpleJobService;

import app.estat.mob.communication.retrofit.ServerService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SynchronizationService extends SimpleJobService {

    @Override
    public int onRunJob(@NonNull JobParameters job) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl()
//                .addConverterFactory(JacksonConverterFactory.create())
        return RESULT_SUCCESS;
    }
}
