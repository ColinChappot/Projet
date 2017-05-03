package Task;

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

public class LastUpdateAdapter extends ArrayAdapter {

    /*
    Constructeur
     */

    public LastUpdateAdapter(Context context, List<Task> lastTaks){
        super(context,0,lastTaks);
    }

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

         Task task = (Task) getItem(position);

        lastupdateViewHolder.taskLastUpdate.setText(task.getTaskDescription());

        return convertView;
    }

    private class LastUpdateViewHolder{
        public TextView taskLastUpdate;
    }

}
