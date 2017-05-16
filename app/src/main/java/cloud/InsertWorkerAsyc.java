package cloud;

import android.os.AsyncTask;

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

public class InsertWorkerAsyc  extends AsyncTask<Void, Void, Worker> {

    private static WorkerApi workerApi = null;
    private DbHelper db;
    private Worker worker;

    public InsertWorkerAsyc(Worker worker) {
        this.worker = worker;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Worker doInBackground(Void... params) {
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
            if(worker!=null)
                workerApi.insert(worker).execute();

            return worker;
        } catch (IOException e) {
            return new Worker();
        }
    }

}