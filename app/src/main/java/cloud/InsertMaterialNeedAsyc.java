package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.installationPlacedApi.InstallationPlacedApi;
import com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced;
import com.example.colin.myapplication.backend.classes.materielNeededApi.MaterielNeededApi;
import com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

////permet d'inserer les materialNeed
public class InsertMaterialNeedAsyc extends AsyncTask<Void, Void, MaterielNeeded> {

    private static MaterielNeededApi materielNeededApi = null;
    private DbHelper db;
    private MaterielNeeded materielNeeded;

    public InsertMaterialNeedAsyc(MaterielNeeded materielNeeded) {
        this.materielNeeded = materielNeeded;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected MaterielNeeded doInBackground(Void... params) {
        if (materielNeededApi == null) {
            com.example.colin.myapplication.backend.classes.materielNeededApi.MaterielNeededApi.Builder builder = new MaterielNeededApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            materielNeededApi = builder.build();
        }

        try {
            if (materielNeeded != null)
                materielNeededApi.insert(materielNeeded).execute();

            return materielNeeded;
        } catch (IOException e) {
            return new MaterielNeeded();
        }
    }
}
