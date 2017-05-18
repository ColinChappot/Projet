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
import java.util.ArrayList;
import java.util.List;

import db.DbHelper;

import static cloud.EntityDB.setStateUpdated;


//donne la liste des State du cloud
public class ListStateAsyc  extends AsyncTask<Void, Void, List<State>> {

    private static StateApi stateApi = null;
    private DbHelper db;

    public ListStateAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<State> doInBackground(Void... params) {
        if (stateApi == null) {
            StateApi.Builder builder = new StateApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            return stateApi.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<State>();
        }
    }

    @Override
    protected void onPostExecute(List<State> states) {


        if (states != null) {
            db.fromCloudState(states);
        }
        setStateUpdated();
    }

}
