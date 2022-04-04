package fu.prm391.sample.servicedemo;

import android.app.Service;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;

public class MyHandler extends Handler {

    private Service service;
    public MyHandler(@NonNull Looper looper, Service service) {
        super(looper);
        this.service = service;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        try {
            FileOutputStream fos = service.openFileOutput("data.txt", Context.MODE_PRIVATE);
//            DataOutputStream dos = new DataOutputStream(fos);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            for (int i = 0; i < 10; i++) {
                Date currentTime = Calendar.getInstance().getTime();
                writer.write(currentTime.toString());
                writer.newLine();
//                dos.writeChars(String.valueOf(i));
//                dos.writeChars("\n");
//                dos.flush();
                Thread.sleep(1000);
            }
            writer.close();
            fos.close();
//            stopSelf();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
