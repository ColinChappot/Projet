package task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.colin.projet.R;

import java.util.List;



public class LastUpdateAdapter extends ArrayAdapter {

    /*
    Constructeur
     */

    public LastUpdateAdapter(Context context, List<Task> lastTaks){
        super(context,0,lastTaks);
    }


    /*
     Création des adapters pour les listView, cela prend une row et cela la définit comme model pour chaque ligne de la listView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup  parent){

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_lasttask, parent, false);
        }

        LastUpdateViewHolder lastupdateViewHolder = (LastUpdateViewHolder) convertView.getTag();
        if(lastupdateViewHolder == null){
            lastupdateViewHolder = new LastUpdateViewHolder();
            lastupdateViewHolder.taskLastUpdate=(TextView) convertView.findViewById(R.id.taskLastUpdate);

            convertView.setTag(lastupdateViewHolder);
        }

        Task task = (Task)getItem(position);
        lastupdateViewHolder.taskLastUpdate.setText(task.getLastTask());

        return convertView;
    }

    /*
    Tag lasttask
     */
    private class LastUpdateViewHolder{
        public TextView taskLastUpdate;
    }

}
