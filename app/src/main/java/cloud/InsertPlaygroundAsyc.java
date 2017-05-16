package cloud;

import android.os.AsyncTask;
import android.util.Log;

import com.example.colin.myapplication.backend.classes.playgroundApi.PlaygroundApi;
import com.example.colin.myapplication.backend.classes.playgroundApi.model.Playground;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

/**
 * Created by Colin on 15.05.2017.
 */

public class InsertPlaygroundAsyc extends AsyncTask<Void, Void, Playground> {

    private static PlaygroundApi playgroundApi = null;
    private DbHelper db;
    private Playground playground;

    public InsertPlaygroundAsyc(Playground playground) {
        this.playground = playground;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Playground doInBackground(Void... params) {
        if (playgroundApi == null) {
            com.example.colin.myapplication.backend.classes.playgroundApi.PlaygroundApi.Builder builder = new PlaygroundApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
                playgroundApi = builder.build();
        }

        try {
            if(playground!=null)
                playgroundApi.insert(playground).execute();

            return playground;
        } catch (IOException e) {
            return new Playground();
        }
    }

}
