package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.installationPlacedApi.InstallationPlacedApi;
import com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced;
import com.example.colin.myapplication.backend.classes.taskApi.TaskApi;
import com.example.colin.myapplication.backend.classes.taskApi.model.Task;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

/**
 * Created by Colin on 16.05.2017.
 */

public class InsertInstallationPlacedAsyc extends AsyncTask<Void, Void, InstallationPlaced> {

    private static InstallationPlacedApi installationPlacedApi = null;
    private DbHelper db;
    private InstallationPlaced installationPlaced;

    public InsertInstallationPlacedAsyc(InstallationPlaced installationPlaced) {
        this.installationPlaced = installationPlaced;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected InstallationPlaced doInBackground(Void... params) {
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
            if (installationPlaced != null)
                installationPlacedApi.insert(installationPlaced).execute();

            return installationPlaced;
        } catch (IOException e) {
            return new InstallationPlaced();
        }
    }
}
