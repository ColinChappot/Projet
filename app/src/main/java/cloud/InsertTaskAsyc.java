package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.taskApi.TaskApi;
import com.example.colin.myapplication.backend.classes.taskApi.model.Task;
import com.example.colin.myapplication.backend.classes.workerApi.WorkerApi;
import com.example.colin.myapplication.backend.classes.workerApi.model.Worker;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;


//permet d'inserer les InsertTask
public class InsertTaskAsyc extends AsyncTask<Void, Void, Task> {

    private static TaskApi taskApi = null;
    private DbHelper db;
    private Task task;

    public InsertTaskAsyc(Task task) {
        this.task = task;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Task doInBackground(Void... params) {
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
            if(task!=null)
                taskApi.insert(task).execute();

            return task;
        } catch (IOException e) {
            return new Task();
        }
    }

}