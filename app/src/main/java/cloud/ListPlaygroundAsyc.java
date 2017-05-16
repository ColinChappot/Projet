package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.playgroundApi.PlaygroundApi;
import com.example.colin.myapplication.backend.classes.playgroundApi.model.Playground;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.DbHelper;

/**
 * Created by Colin on 15.05.2017.
 */

public class ListPlaygroundAsyc extends AsyncTask<Void, Void, List<Playground>> {

    private static PlaygroundApi playgroundAPI = null;
    private DbHelper db;

    public ListPlaygroundAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Playground> doInBackground(Void... params) {
        if (playgroundAPI == null) {
            PlaygroundApi.Builder builder = new PlaygroundApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
           playgroundAPI = builder.build();
        }

        try {

            return playgroundAPI.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Playground>();
        }
    }

    @Override
    protected void onPostExecute(List<Playground> playgrounds) {


        if (playgrounds != null) {
            db.fromCloudPlayground(playgrounds);
        }
    }

}
