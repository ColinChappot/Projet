package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.taskApi.TaskApi;
import com.example.colin.myapplication.backend.classes.taskApi.model.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.DbHelper;

/**
 * Created by Colin on 16.05.2017.
 */

public class ListTaskAsyc extends AsyncTask<Void, Void, List<Task>> {

    private static TaskApi taskApi = null;
    private DbHelper db;

    public ListTaskAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Task> doInBackground(Void... params) {
        if (taskApi == null) {
            TaskApi.Builder builder = new TaskApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            return taskApi.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Task>();
        }
    }

    @Override
    protected void onPostExecute(List<Task> tasks) {


        if (tasks != null) {
            db.fromCloudTask(tasks);
        }
    }

}
