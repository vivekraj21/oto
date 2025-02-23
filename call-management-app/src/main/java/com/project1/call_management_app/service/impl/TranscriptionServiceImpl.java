package com.project1.call_management_app.service.impl;

import java.io.File;
import java.io.IOException;

import com.project1.call_management_app.service.TranscriptionService;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TranscriptionServiceImpl implements TranscriptionService {
    private static final String API_KEY = "64e0aabb58f35b19f2226453159f365815aa2632aca988e2196511d7ac4464fcd7a10ffc963d6755a5ea76a589aaa24c8fecd1e42ac716d19f52a1ba109e554e"; // Replace with actual API key
    private final OkHttpClient client = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    public String processFile(MultipartFile file) throws IOException {
        // Step 1: Get Upload URL
        RequestBody requestBody = RequestBody.create(JSON, "{\"file_name\": \"" + file.getOriginalFilename() + "\"}");
        Request request = new Request.Builder()
                .url("https://api.tor.app/developer/transcription/local_file/get_upload_url")
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Failed to get upload URL");
        }

        JSONObject jsonResponse = new JSONObject(response.body().string());
        String uploadUrl = jsonResponse.getString("upload_url");
        String publicUrl = jsonResponse.getString("public_url");

        // Step 2: Upload the File
        File tempFile = File.createTempFile("audio", ".mp3");
        file.transferTo(tempFile);

        RequestBody fileBody = RequestBody.create(MediaType.parse("audio/mpeg"), tempFile);
        Request uploadRequest = new Request.Builder().url(uploadUrl).put(fileBody).build();
        Response uploadResponse = client.newCall(uploadRequest).execute();

        if (!uploadResponse.isSuccessful()) {
            throw new IOException("File upload failed");
        }

        // Step 3: Initiate Transcription
        String configJson = String.format("{\"url\": \"%s\", \"language\": \"en-US\", \"service\": \"Standard\"}", publicUrl);
        RequestBody transcriptionBody = RequestBody.create(JSON, configJson);
        Request transcriptionRequest = new Request.Builder()
                .url("https://api.tor.app/developer/transcription/local_file/initiate_transcription")
                .post(transcriptionBody)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        Response transcriptionResponse = client.newCall(transcriptionRequest).execute();
        if (!transcriptionResponse.isSuccessful()) {
            throw new IOException("Failed to initiate transcription");
        }

        return transcriptionResponse.body().string();
    }
}

