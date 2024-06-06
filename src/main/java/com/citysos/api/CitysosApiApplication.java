package com.citysos.api;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@SpringBootApplication
public class CitysosApiApplication {

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {
        String firebaseCredentials = System.getenv("FIREBASE_CREDENTIALS");
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ByteArrayInputStream(Base64.getDecoder().decode(firebaseCredentials)));
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .setStorageBucket(System.getenv("FIREBASE_STORAGE_BUCKET"))
                .build();
        return FirebaseApp.initializeApp(firebaseOptions);
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    public StorageClient storageClient(FirebaseApp firebaseApp) {
        return StorageClient.getInstance(firebaseApp);
    }

    @Bean
    public Storage storage() throws IOException {
        String firebaseCredentials = System.getenv("FIREBASE_CREDENTIALS");
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(Base64.getDecoder().decode(firebaseCredentials))))
                .build()
                .getService();
    }

    public static void main(String[] args) {
        SpringApplication.run(CitysosApiApplication.class, args);
    }
}
