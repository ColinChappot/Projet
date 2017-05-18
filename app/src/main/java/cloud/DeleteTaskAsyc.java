package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.taskApi.TaskApi;
import com.example.colin.myapplication.backend.classes.taskApi.model.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

/**
 * Created by Colin on 17.05.2017.
 */

public class DeleteTaskAsyc extends AsyncTask<Void, Void, Long> {

    private static TaskApi taskApi = null;
    private DbHelper db;
    private long id =-1l;

    public DeleteTaskAsyc(long id) {
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Void... params) {
        if (taskApi == null) {
            com.example.colin.myapplication.backend.classes.taskApi.TaskApi.Builder builder = new TaskApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            taskApi = builder.build();
        }

        try {
            if (id != -1l)
                taskApi.remove(id).execute();

            return id;
        } catch (IOException e) {
            return -2l;
        }
    }

}