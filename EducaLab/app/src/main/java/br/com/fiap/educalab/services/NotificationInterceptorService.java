package br.com.fiap.educalab.services;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationInterceptorService extends NotificationListenerService {

    public static final String NOTIFICATION_INTERCEPTOR_ACTION  = "br.com.fiap.notification-interceptor-service";
    public static final String NOTIFICATION_PACKAGE = "CANCELLED_NOTIFICATION_PACKAGE";
    public static final String NOTIFICATION_CONTENT = "CANCELLED_NOTIFICATION_CONTENT";

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String key = sbn.getKey();
        String packageName = sbn.getPackageName();

        System.out.println("Key: " + key);
        System.out.println("Package: " + packageName);

        cancelNotification(key);

        notify(sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {

    }

    private void notify(StatusBarNotification sbn) {
        Intent intent = new Intent(NOTIFICATION_INTERCEPTOR_ACTION);
        intent.putExtra(NOTIFICATION_PACKAGE, sbn.getPackageName());
        intent.putExtra(NOTIFICATION_CONTENT, sbn.getNotification().getGroup());

        sendBroadcast(intent);
    }
}
