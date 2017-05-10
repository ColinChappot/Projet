package playground;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.colin.projet.R;

import java.util.List;

/**
 * Created by uadmin on 28.04.2017.
 */

public class PlayGroundAdapter extends ArrayAdapter<Playground> {

    /*
   Constructeur
    */
    public PlayGroundAdapter(Context context, List<Playground> playgrounds){
        super(context, 0, playgrounds);
    }

    /*
    Création des adapters pour les listView, cela prend une row et cela la définit comme model pour chaque ligne de la listView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_playground, parent, false);
        }


        PlayGroundViewHoldder viewHolder = (PlayGroundViewHoldder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PlayGroundViewHoldder();
            viewHolder.namePG = (TextView) convertView.findViewById(R.id.playgroundName);

            convertView.setTag(viewHolder);
        }

        //getitem (position) va récupérer l'item [position] de la list<Playground> playgrounds
        Playground playground = getItem(position);
        viewHolder.namePG.setText(playground.getPlayGroundName());

        return convertView;
    }

    /*
  Création du conteneur du tag
   */
    private class PlayGroundViewHoldder{
        public TextView namePG;
    }
}


