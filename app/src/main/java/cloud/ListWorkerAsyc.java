package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.workerApi.WorkerApi;
import com.example.colin.myapplication.backend.classes.workerApi.model.Worker;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.DbHelper;

import static cloud.EntityDB.setWorkerUpdated;


//donne la liste des Worker du cloud
public class ListWorkerAsyc extends AsyncTask<Void, Void, List<Worker>> {

    private static WorkerApi workerAPI = null;
    private DbHelper db;

    public ListWorkerAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Worker> doInBackground(Void... params) {
        if (workerAPI == null) {
            WorkerApi.Builder builder = new WorkerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            workerAPI = builder.build();
        }

        try {

            return workerAPI.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Worker>();
        }
    }

    @Override
    protected void onPostExecute(List<Worker> workers) {


        if (workers != null) {
            db.fromCloudWorker(workers);
        }
        setWorkerUpdated();
        new ListTaskAsyc(db).execute();
    }

}
