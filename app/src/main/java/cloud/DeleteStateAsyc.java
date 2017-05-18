package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.stateApi.StateApi;
import com.example.colin.myapplication.backend.classes.stateApi.model.State;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;


//permet de delete les State
public class DeleteStateAsyc extends AsyncTask<Void, Void, Long> {

    private static StateApi stateApi = null;
    private DbHelper db;
    private long id =-1l;

    public DeleteStateAsyc(long id) {
        this.id = id;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Void... params) {
        if (stateApi == null) {
            com.example.colin.myapplication.backend.classes.stateApi.StateApi.Builder builder = new StateApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            stateApi = builder.build();
        }

        try {
            if (id != -1l)
                stateApi.remove(id).execute();

            return id;
        } catch (IOException e) {
            return -2l;
        }
    }
}
