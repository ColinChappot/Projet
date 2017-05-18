package cloud;

import android.os.AsyncTask;
import android.util.Log;

import com.example.colin.myapplication.backend.classes.workerApi.WorkerApi;
import com.example.colin.myapplication.backend.classes.workerApi.model.Worker;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

/**
 * Created by Colin on 16.05.2017.
 */

//permet de delete les workers
public class DeleteWorkerAsyc extends AsyncTask<Void, Void, Long> {

    private static WorkerApi workerApi = null;
    private DbHelper db;
    private Long id =-1l;

    public DeleteWorkerAsyc(Long id) {
        this.id = id;
        Log.e("debugCloud", String.valueOf(id));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Void... params) {
        if (workerApi == null) {
            com.example.colin.myapplication.backend.classes.workerApi.WorkerApi.Builder builder = new WorkerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            workerApi = builder.build();
        }

        try {
            if(id != -1l)
                workerApi.remove(id).execute();


            return id;
        } catch (IOException e) {
            return -2l;
        }
    }

}