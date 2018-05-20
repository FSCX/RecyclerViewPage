package describegson.fsc.com.gradleproblem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import describegson.fsc.com.gradleproblem.R;
import describegson.fsc.com.gradleproblem.bean.AppNameIcon;


/**
 * Created by mt on 2018/5/19.
 */

public class AppNameIconAdapter extends RecyclerView.Adapter<AppNameIconAdapter.AppNameIconHolder> implements View.OnClickListener {

    private View view;

    private List<AppNameIcon> mAppNameIconList;

    private AppNameIcon mAppNameIcon;

    private Context mContext;

    private LayoutInflater mInflater;

    public OnItemClickListener mOnItemClickListener;

    public AppNameIconAdapter(List appNameIconList, Context context) {

        this.mAppNameIconList = appNameIconList;

        this.mContext = context;

        mInflater = LayoutInflater.from(context);

    }
    @Override
    public AppNameIconAdapter.AppNameIconHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.desktop_app_item, parent, false);

        view.setOnClickListener(this);

        return new AppNameIconHolder(view, mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(AppNameIconHolder holder, int position) {

        mAppNameIcon = mAppNameIconList.get(position);

        holder.appName.setText(mAppNameIcon.getAppName());

        holder.appIcon.setImageDrawable(mAppNameIcon.getAppIcon());

        holder.itemView.setTag(position);
    }
    @Override
    public int getItemCount() {
        return mAppNameIconList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {

            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());

        }
    }
    //接口回调onClickListener

    public interface OnItemClickListener {

        void onItemClick(View v, int position);
    }

//给接口设置set

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.mOnItemClickListener = onItemClickListener;

    }

    public class AppNameIconHolder extends RecyclerView.ViewHolder {
        public ImageView appIcon;
        public TextView appName;

        public AppNameIconHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            this.appIcon = (ImageView) itemView.findViewById(R.id.app_icon);

            this.appName = (TextView) itemView.findViewById(R.id.app_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {

                        listener.onItemClick(v, getPosition());

                    }
                }
            });
        }
    }
}
