package describegson.fsc.com.gradleproblem.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import describegson.fsc.com.gradleproblem.R;
import describegson.fsc.com.gradleproblem.bean.AppNameIcon;
import describegson.fsc.com.gradleproblem.model.AppNameAndIcon;
import describegson.fsc.com.gradleproblem.view.PageIndicatorView;
import describegson.fsc.com.gradleproblem.view.PageRecyclerView;

public class MainActivity extends Activity {
    private PageRecyclerView mRecyclerView = null;
    private List<AppNameIcon> mAppNameIconList = null;
    private PageRecyclerView.PageAdapter myAdapter = null;
    private AppNameIcon mAppNameIcon;
    private AppNameAndIcon mAppNameAndIcon;
    private Context context;
    //private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mRecyclerView = (PageRecyclerView) findViewById(R.id.cusom_swipe_view);
        // 设置指示器
        mRecyclerView.setIndicator((PageIndicatorView) findViewById(R.id.indicator));
        // 设置行数和列数
        mRecyclerView.setPageSize(3, 3);
        // 设置页间距
        mRecyclerView.setPageMargin(30);
        // 设置数据
        mRecyclerView.setAdapter(myAdapter = mRecyclerView.new PageAdapter(mAppNameIconList, new PageRecyclerView.CallBack() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.desktop_app_item, parent, false);
                return new MyHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                mAppNameIcon = mAppNameIconList.get(position);
                ((MyHolder) holder).appName.setText(mAppNameIcon.getAppName());
                ((MyHolder) holder).appIcon.setImageDrawable(mAppNameIcon.getAppIcon());
            }

            @Override
            public void onItemClickListener(View view, int position) {
                //intent = new Intent();
                Toast.makeText(MainActivity.this, "点击："
                        + mAppNameIconList.get(position).getAppName(), Toast.LENGTH_SHORT).show();
                startActivity(mAppNameIconList.get(position).getIntentPackage());
            }
        }));
    }
    private void initData() {
        mAppNameAndIcon =new AppNameAndIcon();
        mAppNameIconList = mAppNameAndIcon.getAppInfos(MainActivity.this);
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        public ImageView appIcon;
        public TextView appName;
        public LinearLayout clickApp;

        public MyHolder(View itemView) {
            super(itemView);
            this.appIcon = (ImageView) itemView.findViewById(R.id.app_icon);

            this.appName = (TextView) itemView.findViewById(R.id.app_name);
            this.clickApp = (LinearLayout) itemView.findViewById(R.id.clickApp);

            clickApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getAdapterPosition() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
