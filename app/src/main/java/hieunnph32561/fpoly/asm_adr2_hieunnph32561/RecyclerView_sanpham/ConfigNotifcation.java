package hieunnph32561.fpoly.asm_adr2_hieunnph32561.RecyclerView_sanpham;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import hieunnph32561.fpoly.asm_adr2_hieunnph32561.R;

public class ConfigNotifcation extends Application {
    public static final String CHANNEL_ID="asmm";

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }
    private void config(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.CHANNEL_ID);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            AudioAttributes attributes=new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
            channel.setSound(uri,attributes);



            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
