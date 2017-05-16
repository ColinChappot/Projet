package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.installationPlacedApi.model.InstallationPlaced;
import com.example.colin.myapplication.backend.classes.materialApi.MaterialApi;
import com.example.colin.myapplication.backend.classes.materialApi.model.Material;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;

/**
 * Created by Colin on 16.05.2017.
 */

public class InsertMaterialAsyc extends AsyncTask<Void, Void, Material> {

    private static MaterialApi materialApi = null;
    private DbHelper db;
    private Material material;

    public InsertMaterialAsyc(Material material) {
        this.material = material;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Material doInBackground(Void... params) {
        if (materialApi == null) {
            com.example.colin.myapplication.backend.classes.materialApi.MaterialApi.Builder builder = new MaterialApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);
            builder.setRootUrl("https://myapplication-167216.appspot.com/_ah/api/");
            builder.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                @Override
                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                    abstractGoogleClientRequest.setDisableGZipContent(true);
                }
            });
            materialApi = builder.build();
        }

        try {
            if (material != null)
                materialApi.insert(material).execute();

            return material;
        } catch (IOException e) {
            return new Material();
        }
    }
}
