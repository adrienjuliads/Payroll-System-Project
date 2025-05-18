package org.example;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;

public class FirebaseConfig {
    private static boolean initialized = false;

    public static void initialize() {
        if (initialized) return;

        try {
            FileInputStream serviceAccount = new FileInputStream(
                    "src/main/java/org/example/payrollsystemproject-firebase-adminsdk-fbsvc-9754248793.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://payrollsystemproject-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("✅ Firebase initialized.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}
