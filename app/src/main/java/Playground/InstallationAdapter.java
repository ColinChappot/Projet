package Playground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.colin.projet.R;

import java.util.List;

/**
 * Created by uadmin on 03.05.2017.
 */

public class InstallationAdapter extends ArrayAdapter {

    /*
    Constructeur
     */
    public InstallationAdapter(Context context, List<Installation> installations){
        super(context,0,installations);
    }

    /*
    Création des adapters pour les listView, cela prend une row et cela la définit comme model pour chaque ligne de la listView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_installation, parent, false);
        }

        InstallationViewHolder installationViewHolder = (InstallationViewHolder) convertView.getTag();
        if(installationViewHolder == null){
            installationViewHolder = new InstallationViewHolder();
            installationViewHolder.installation =(TextView) convertView.findViewById(R.id.installation);

            convertView.setTag(installationViewHolder);
        }

        Installation installation = (Installation)getItem(position);
        installationViewHolder.installation.setText(installation.getDescripition());

        return convertView;
    }

    /*
    Création du conteneur du tag
     */
    private class InstallationViewHolder{
        public TextView installation;
    }
}
