package connect.database.test.com.clents;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Connect to server
 * Created by wangj on 4/2/2018.
 */

public class ConnectServer implements Runnable{
    private final String strip="192.168.1.102";
    private final int port=8000;
    private DataOutputStream out;
    private DataInputStream in;
    private Socket server;
    /**
     *
     * @return Server Socket
     */

    public void CloseSocket(){
        try {
            server.close();
            in.close();
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try{
            //server=new Socket();
            //server.connect(new InetSocketAddress(strip,port));
            server=new Socket();
            server.connect(new InetSocketAddress("192.168.1.102",8000),5000);
            //server.setSoTimeout(5000);
            out=new DataOutputStream(server.getOutputStream());
            in=new DataInputStream(server.getInputStream());
            Log.d("Connect Server", ":Success");
        }catch(IOException e){
            e.printStackTrace();
        }

    }


    /**
     * 发送消息方法
     * @param data : Expected Send Message
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public MessageBox Send(JSONObject data) throws JSONException,IOException{
        MessageBox message=null;
        String buffer;

        out.writeUTF(data.toString());

        buffer = in.readUTF();
        message=MessageBox.valueOf(buffer);
        return message;
    }
    
}
