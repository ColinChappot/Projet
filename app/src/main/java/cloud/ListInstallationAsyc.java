package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.installationApi.InstallationApi;
import com.example.colin.myapplication.backend.classes.installationApi.model.Installation;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.DbHelper;

import static cloud.EntityDB.setInstallationUpdated;


//donne la liste des installation du cloud
public class ListInstallationAsyc  extends AsyncTask<Void, Void, List<Installation>> {

    private static InstallationApi InstallationApi = null;
    private DbHelper db;

    public ListInstallationAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Installation> doInBackground(Void... params) {
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

            return InstallationApi.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Installation>();
        }
    }
    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Installation> installations) {

        if (installations != null) {
            db.fromCloudInstallation(installations);
        }
        setInstallationUpdated();
        new ListMaterialAsyc(db).execute();
    }

}

