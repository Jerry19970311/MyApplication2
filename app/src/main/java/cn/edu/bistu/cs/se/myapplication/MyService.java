package cn.edu.bistu.cs.se.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG="myServiceTag";
    private LocalBinder myBinder=new LocalBinder();
    public class LocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.v(TAG,"onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"onStartCommand()");
        int n=intent.getIntExtra("num",0);
        Log.v(TAG,"int is:"+n);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG,"onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG,"onUnbind()");
        return super.onUnbind(intent);
    }

    public int add(int x,int y){
        return x+y;
    }
}
