package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.materielNeededApi.MaterielNeededApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.DbHelper;

import static cloud.EntityDB.setMaterialNeededUpdated;


//donne la liste des materialNeed du cloud
public class ListMaterialNeedAsyc extends AsyncTask<Void, Void, List<com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded>> {

    private static MaterielNeededApi materialNeededApi = null;
    private DbHelper db;

    public ListMaterialNeedAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded> doInBackground(Void... params) {
        if (materialNeededApi == null) {
            MaterielNeededApi.Builder builder = new MaterielNeededApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            materialNeededApi = builder.build();
        }

        try {

            return materialNeededApi.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded>();
        }
    }

    @Override
    protected void onPostExecute(List<com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded> materielNeededs) {


        if (materielNeededs != null) {
            db.fromCloudMaterialNeeded(materielNeededs);
        }
        setMaterialNeededUpdated ();
        new ListPlacedInstallationAsyc(db).execute();
    }

}
