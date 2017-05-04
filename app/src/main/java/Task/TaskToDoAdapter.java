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
 * Created by uadmin on 01.05.2017.
 */

public class TaskToDoAdapter extends ArrayAdapter<Task> {

    /*
    Constructeur
     */
    public TaskToDoAdapter(Context context, List<Task> tasks){
        super(context,0,tasks);
    }

    /*
     Création des adapters pour les listView, cela prend une row et cela la définit comme model pour chaque ligne de la listView
      */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_task, parent, false);
        }

        TaskViewHolder taskViewHolder = (TaskViewHolder) convertView.getTag();
        if(taskViewHolder == null){
            taskViewHolder = new TaskViewHolder();
            taskViewHolder.taskDescrip=(TextView) convertView.findViewById(R.id.taskDescription);

            convertView.setTag(taskViewHolder);
        }

        Task task = getItem(position);
        taskViewHolder.taskDescrip.setText(task.getTaskDescription());

        return convertView;
    }


    /*
    Tag task
     */
    private class TaskViewHolder{
        public TextView taskDescrip;
    }
}
