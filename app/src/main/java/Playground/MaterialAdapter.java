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
 * Created by uadmin on 04.05.2017.
 */

public class MaterialAdapter extends ArrayAdapter{


    /*
    Constructeur
     */


    public MaterialAdapter(Context context, List<Material> materials){
        super(context,0,materials);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_material, parent, false);
        }

        MaterialViewHolder materialViewHolder = (MaterialViewHolder) convertView.getTag();
        if(materialViewHolder == null){
            materialViewHolder = new MaterialViewHolder();
            materialViewHolder.material =(TextView) convertView.findViewById(R.id.material);

            convertView.setTag(materialViewHolder);
        }

        Material material = (Material)getItem(position);
        materialViewHolder.material.setText(material.getMaterialName());

        return convertView;
    }

    /*
    Tag lasttask
     */
    private class MaterialViewHolder{
        public TextView material;
    }
}
