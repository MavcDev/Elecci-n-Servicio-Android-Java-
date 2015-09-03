package com.app.eleccion.ListViewComponent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.eleccion.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Cananguchal Code on 27/07/2015.
 */
public class AdapterListView extends BaseAdapter {

    private Context context;
    private Class classActivity;
    private LayoutInflater layoutInflater;
    private List<ContentListView> ListViewData;
    private ArrayList<ContentListView> ListViewDataAux;


    public AdapterListView(Context context, List<ContentListView> ListViewData, Class classActivity) {
        this.context = context;
        this.ListViewData = ListViewData;
        this.classActivity = classActivity;
        layoutInflater = LayoutInflater.from(context);
        this.ListViewDataAux = new ArrayList<ContentListView>();
        this.ListViewDataAux.addAll(ListViewData);
    }

    @Override
    public int getCount() {
        return ListViewData.size();
    }

    @Override
    public ContentListView getItem(int position) {
        return ListViewData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LabelListView labelListView;
        if(convertView == null) {
            labelListView = new LabelListView();
            convertView = layoutInflater.inflate(R.layout.list_view_label, null);
            labelListView.label1 = (TextView) convertView.findViewById(R.id.label1);
            labelListView.label2 = (TextView) convertView.findViewById(R.id.label2);
            labelListView.label3 = (TextView) convertView.findViewById(R.id.label3);
            convertView.setTag(labelListView);
        }else{
            labelListView = (LabelListView) convertView.getTag();
        }
        labelListView.label1.setText(ListViewData.get(position).getLabel1());
        labelListView.label2.setText(ListViewData.get(position).getLabel2());
        labelListView.label3.setText(ListViewData.get(position).getLabel3());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String id = ListViewData.get(position).getParameter();
                 Intent intent = new Intent(context, classActivity);
                 intent.putExtra("id", id);
                 context.startActivity(intent);
            }
        });
        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ListViewData.clear();
        if (charText.length() == 0) {
            ListViewData.addAll(ListViewDataAux);
        }
        else
        {
            for (ContentListView dataList : ListViewDataAux)
            {
                if (dataList.getLabel1().toLowerCase(Locale.getDefault()).contains(charText) || dataList.getLabel2().toLowerCase(Locale.getDefault()).contains(charText) || dataList.getLabel3().toLowerCase(Locale.getDefault()).contains(charText) || dataList.getParameter().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    ListViewData.add(dataList);
                }
            }
        }
        notifyDataSetChanged();
    }
}
