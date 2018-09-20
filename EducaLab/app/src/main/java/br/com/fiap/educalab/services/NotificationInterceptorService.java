package br.com.fiap.educalab.services;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.educalab.shared.SharedContent;

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

        Log.i("NOTIFICATION-SERVICE","Key: " + key);
        Log.i("NOTIFICATION-SERVICE","Package: " + packageName);

        // Apenas cancelara as notificacoes caso o prazo nao esteja expirado
        // e a aplicacao esteja na "black list"
        if (!SharedContent.expired() && SharedContent.shouldBlock(packageName)) {
            cancelNotification(key);
            notify(sbn);
        }
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
