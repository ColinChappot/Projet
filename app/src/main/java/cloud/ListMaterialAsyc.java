package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.materialApi.MaterialApi;
import com.example.colin.myapplication.backend.classes.materialApi.model.Material;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.DbHelper;

/**
 * Created by Colin on 16.05.2017.
 */

public class ListMaterialAsyc extends AsyncTask<Void, Void, List<Material>> {

    private static MaterialApi materialApi = null;
    private DbHelper db;

    public ListMaterialAsyc(DbHelper db) {
        this.db = db;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Material> doInBackground(Void... params) {
        if (materialApi == null) {
            MaterialApi.Builder builder = new MaterialApi.Builder(AndroidHttp.newCompatibleTransport(),
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

            return materialApi.list().execute().getItems();
        } catch (IOException e) {
            return new ArrayList<Material>();
        }
    }

    @Override
    protected void onPostExecute(List<Material> materials) {


        if (materials != null) {
            db.fromCloudMaterial(materials);
        }
    }

}
