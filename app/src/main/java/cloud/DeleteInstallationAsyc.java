package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.installationPlacedApi.InstallationPlacedApi;
import com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;


//Permet de delete les installation
public class DeleteInstallationAsyc extends AsyncTask<Void, Void, Long> {

    private static InstallationPlacedApi installationPlacedApi = null;
    private DbHelper db;
    private long id =-1l;

    public DeleteInstallationAsyc(long id) {
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Void... params) {
        if (installationPlacedApi == null) {
            com.example.colin.myapplication.backend.classes.installationPlacedApi.InstallationPlacedApi.Builder builder = new InstallationPlacedApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            installationPlacedApi = builder.build();
        }

        try {
            if (id != -1l)
                installationPlacedApi.remove(id);

            return id;
        } catch (IOException e) {
            return -2l;
        }
    }
}
