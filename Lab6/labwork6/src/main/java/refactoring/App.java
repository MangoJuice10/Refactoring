package refactoring;

import java.util.Collections;
import java.util.Map;
import refactoring.handlers.UploadHandler;

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
        ImageUploadRequest requestSimplified = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "dataviruses".getBytes(),
                "img.exe",
                Map.of("blocked", "true"),
                600, 400);

        UploadHandler basicUploadHandlerChain = uploadService.getBasicUploadHandlerChain();
        UploadHandler simplifiedUploadHandlerChain = uploadService.getSimplifiedUploadHandlerChain();
        System.out.println("-------------------------------------------------");
        uploadService.upload(requestValid, basicUploadHandlerChain);
        System.out.println("-------------------------------------------------");
        uploadService.upload(requestInvalid, basicUploadHandlerChain);
        System.out.println("-------------------------------------------------");
        uploadService.upload(requestSimplified, simplifiedUploadHandlerChain);
        System.out.println("-------------------------------------------------");
    }
}
