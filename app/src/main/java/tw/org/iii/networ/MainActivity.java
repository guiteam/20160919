package tw.org.iii.networ;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager mgr ; // 呼叫mgr
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE); // CALL SYSTEM
        NetworkInfo info = mgr.getActiveNetworkInfo() ; // Not get ActiveNetWork
//        info.getState();
        if(info!=null && info.isConnected()){
            try {
                Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
                while (ifs.hasMoreElements()){
                    NetworkInterface ip = ifs.nextElement() ;
                    Enumeration<InetAddress> ips = ip.getInetAddresses();
                    while (ips.hasMoreElements()){
                        InetAddress ia = ips.nextElement() ;
                        Log.d("brad",ia.getHostAddress());
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }else{
            Log.d("brad","xx");
        } ;
    }
    public  void test1(View v){

    MyThread myt = new MyThread() ;
        myt.start();


    }
    private class MyThread extends  Thread{
        @Override
        public void run() {
            super.run();
            try {
                URL url =new URL("http://www.google.com");
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.connect();
               
                InputStream in = conn.getInputStream();
                int c ; StringBuffer sb = new StringBuffer() ;
                while ((c=in.read())!=-1) {
                    sb.append((char)c);
                }
                in.close();
                Log.d("brad",sb.toString());

            } catch (Exception ee) {
                Log.d("brad",ee.toString());
            }
        }
    }
}
