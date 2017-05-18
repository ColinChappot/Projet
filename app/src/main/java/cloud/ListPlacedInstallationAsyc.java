package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.installationPlacedApi.InstallationPlacedApi;
import com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced;
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

import static cloud.EntityDB.setInstallationPlacedUpdated;


//donne la liste des placedInstallation du cloud
public class ListPlacedInstallationAsyc extends AsyncTask<Void, Void, List<InstallationPlaced>> {

    private static InstallationPlacedApi installationPlacedApi = null;
    private DbHelper db;

    public ListPlacedInstallationAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<InstallationPlaced> doInBackground(Void... params) {
        if (installationPlacedApi == null) {
            InstallationPlacedApi.Builder builder = new InstallationPlacedApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            return installationPlacedApi.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<InstallationPlaced>();
        }
    }

    @Override
    protected void onPostExecute(List<InstallationPlaced> installationPlaceds) {


        if (installationPlaceds != null) {
            db.fromCloudInstallationPlaced(installationPlaceds);
        }
        setInstallationPlacedUpdated();
        new ListStateAsyc(db).execute();
    }

}
