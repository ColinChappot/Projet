package cloud;

import android.os.AsyncTask;

import com.example.colin.myapplication.backend.classes.materielNeededApi.MaterielNeededApi;
import com.example.colin.myapplication.backend.classes.materielNeededApi.model.MaterielNeeded;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import db.DbHelper;


//permet de delete les MaterialNeed
public class DeleteMaterialNeedAsyc extends AsyncTask<Void, Void, Long> {

    private static MaterielNeededApi materielNeededApi = null;
    private DbHelper db;
    private long id =-1l;

    public DeleteMaterialNeedAsyc(long id) {
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Void... params) {
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
            if (id != -1l)
                materielNeededApi.remove(id).execute();

            return id;
        } catch (IOException e) {
            return -2l;
        }
    }
}
