package refactoring;

import java.util.Collections;

public class App {
    public static void main(String[] args) {
        ImageUploadService uploadService = new ImageUploadService();
        ImageUploadRequest requestValid = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "pic.png",
                Collections.emptyMap(),
                600, 600);
        ImageUploadRequest requestInvalid = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "img.exe", // bad extension
                Collections.emptyMap(),
                600, 400);
        System.out.println("-------------------------------------------------");
        uploadService.upload(requestValid);
        System.out.println("-------------------------------------------------");
        uploadService.upload(requestInvalid);
        System.out.println("-------------------------------------------------");
    }
}
