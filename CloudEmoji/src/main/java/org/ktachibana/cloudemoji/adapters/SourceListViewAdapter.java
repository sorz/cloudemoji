package org.ktachibana.cloudemoji.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.linearlistview.LinearListView;

import org.ktachibana.cloudemoji.R;
import org.ktachibana.cloudemoji.models.Category;
import org.ktachibana.cloudemoji.models.Source;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SourceListViewAdapter extends BaseAdapter {
    private Source mSource;
    private Context mContext;
    private CategoryListViewAdapter[] mAdapters;

    public SourceListViewAdapter(Context context, Source source) {
        this.mContext = context;
        this.mSource = source;
        mAdapters = new CategoryListViewAdapter[source.getCategories().size()];

        for (int i = 0 ; i < mAdapters.length ; ++i) {
            mAdapters[i] = new CategoryListViewAdapter(mContext, source.getCategories().get(i).getEntries());
        }
    }

    @Override
    public int getCount() {
        return mSource.getCategories().size();
    }

    @Override
    public Object getItem(int i) {
        return mSource.getCategories().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Standard view holder pattern
        final ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater
                    = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_category, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // Setup contents
        final Category category = mSource.getCategories().get(i);
        viewHolder.categoryTitleTextView.setText(category.getName());
        viewHolder.categoryContentsListView.setAdapter(mAdapters[i]);

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.categoryTitleTextView)
        TextView categoryTitleTextView;

        @InjectView(R.id.categoryContentsListView)
        LinearListView categoryContentsListView;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
