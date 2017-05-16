package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.materialApi.MaterialApi;
import com.example.colin.myapplication.backend.classes.materialApi.model.Material;
import com.example.colin.myapplication.backend.classes.stateApi.StateApi;
import com.example.colin.myapplication.backend.classes.stateApi.model.State;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

/**
 * Created by Colin on 16.05.2017.
 */

public class InsertStateAsyc extends AsyncTask<Void, Void, State> {

    private static StateApi stateApi = null;
    private DbHelper db;
    private State state;

    public InsertStateAsyc(State state) {
        this.state = state;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected State doInBackground(Void... params) {
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
            if (state != null)
               stateApi.insert(state).execute();

            return state;
        } catch (IOException e) {
            return new State();
        }
    }
}
