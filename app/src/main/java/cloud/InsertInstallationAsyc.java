package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.installationApi.InstallationApi;
import com.example.colin.myapplication.backend.classes.installationApi.model.Installation;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

/**
 * Created by Colin on 15.05.2017.
 */

public class InsertInstallationAsyc extends AsyncTask<Void, Void, Installation> {

    private static com.example.colin.myapplication.backend.classes.installationApi.InstallationApi InstallationApi = null;
    private DbHelper db;
    private Installation installation;

    public InsertInstallationAsyc(Installation installation) {
       this.installation = installation;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Installation doInBackground(Void... params) {
        if (InstallationApi == null) {
            InstallationApi.Builder builder = new InstallationApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            InstallationApi = builder.build();
        }

        try {
            if(installation!=null)
                InstallationApi.insert(installation).execute();
            return installation;
        } catch (IOException e) {
            return new Installation();
        }
    }

}

