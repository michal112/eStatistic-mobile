package app.estat.mob.communication.service;

import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.SimpleJobService;

public class SyncService extends SimpleJobService {
    @Override
    public int onRunJob(@NonNull JobParameters job) {

        return RESULT_SUCCESS;
    }
}
