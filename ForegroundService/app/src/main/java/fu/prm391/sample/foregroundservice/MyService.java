package fu.prm391.sample.foregroundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(getApplicationContext(), "Start foreground service", Toast.LENGTH_SHORT).show();

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("MyChannel", "MyChannel", NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);

        Intent intent1 = new Intent(getApplicationContext(), SampleActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                100, intent1, PendingIntent.FLAG_IMMUTABLE);

        Notification.Builder builder = new Notification.Builder(getApplicationContext(), "MyChannel");
        builder.setContentTitle("Thong bao");
        builder.setContentText("Call Sample Activity");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        notification.flags = Notification.FLAG_AUTO_CANCEL;
        startForeground(100, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}