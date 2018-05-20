package describegson.fsc.com.gradleproblem.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import describegson.fsc.com.gradleproblem.bean.AppNameIcon;

/**
 * Created by mt on 2018/5/19.
 */

public class AppNameAndIcon {
    private List mAppNameIconList;

    private AppNameIcon mAppNameIcon;

    private PackageManager mPackageManager;

    private Handler mHandler;
    

    public List getAppInfos(Context context) {

        mPackageManager = context.getPackageManager();

        List<PackageInfo> packgeInfos = mPackageManager.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);

        mAppNameIconList = new ArrayList<>();

        //调用固定的数据

 /*DataDesktop desktop =new DataDesktop();

        List myAppNameIconList =  desktop.appInfos();

        for(int i =0; i < myAppNameIconList.size(); i++){

mAppNameIconList.add(myAppNameIconList.get(i));

        }*/

        for (PackageInfo packgeInfo : packgeInfos) {

//去除系统应用

            ApplicationInfo appInfo = packgeInfo.applicationInfo;

            if (!filterApp(appInfo)) {

                continue;

            }

            String packageName = packgeInfo.packageName;

            String className = packgeInfo.applicationInfo.className;

            String appName = packgeInfo.applicationInfo.loadLabel(mPackageManager).toString();

            Drawable drawable = packgeInfo.applicationInfo.loadIcon(mPackageManager);

            Intent intent = mPackageManager.getLaunchIntentForPackage(packageName);

            mAppNameIcon = new AppNameIcon(appName, drawable, className, packageName, intent);

            mAppNameIconList.add(mAppNameIcon);

        }

        return mAppNameIconList;

    }

    //判断某一个应用程序是不是系统的应用程序，

    //如果是返回true，否则返回false。

    public boolean filterApp(ApplicationInfo info) {

//有些系统应用是可以更新的，如果用户自己下载了一个系统的应用来更新了原来的，它还是系统应用，这个就是判断这种情况的

        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {

            return true;

            //判断是不是系统应用

        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

            return true;

        }
        return false;
    }
}
