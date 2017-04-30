package Worker;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.colin.projet.R;


import java.util.List;

/**
 * Created by uadmin on 30.04.2017.
 */

public class WorkerAdapter extends ArrayAdapter<Worker> {

    public WorkerAdapter(Context context, List<Worker> playgrounds){
        super(context, 0, playgrounds);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_worker, parent, false);
        }


        WorkerViewHoldder viewHolder = (WorkerViewHoldder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new WorkerViewHoldder();
            viewHolder.namePG = (TextView) convertView.findViewById(R.id.workerName);

            convertView.setTag(viewHolder);
        }

        //getitem (position) va récupérer l'item [position] de la list<Playground> playgrounds
        Worker worker = getItem(position);
        viewHolder.namePG.setText(worker.getName());

        return convertView;
    }

    private class WorkerViewHoldder{
        public TextView namePG;
    }
}
