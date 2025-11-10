package refactoring;

import refactoring.handlers.AuthHandler;
import refactoring.handlers.DimensionsHandler;
import refactoring.handlers.ExtensionHandler;
import refactoring.handlers.ModerationHandler;
import refactoring.handlers.SizeHandler;
import refactoring.handlers.UploadHandler;
import refactoring.handlers.VirusHandler;

public class ImageUploadService {
    public boolean upload(ImageUploadRequest request, UploadHandler uploadHandlerChain) {
        System.out.printf("Starting upload for file: %s\n", request.getFilename());
        UploadHandler basicUploadHandlerChain = uploadHandlerChain;
        UploadResult result = basicUploadHandlerChain.check(request);
        if (!result.isSuccess()) {
            System.out.printf("Upload failed: %s\n", result.getMessage());
            return false;
        }

        // Сохранение файла
        System.out.println("Saving file...");

        System.out.printf("Successfully uploaded the file %s\n", request.getFilename());
        return true;
    }

    public UploadHandler getBasicUploadHandlerChain() {
        UploadHandler virusHandler = new VirusHandler();
        UploadHandler moderationHandler = new ModerationHandler(virusHandler);
        UploadHandler dimensionsHandler = new DimensionsHandler(moderationHandler);
        UploadHandler sizeHandler = new SizeHandler(dimensionsHandler);
        UploadHandler extensionHandler = new ExtensionHandler(sizeHandler);
        UploadHandler authHandler = new AuthHandler(extensionHandler);
        return authHandler;
    }

    public UploadHandler getSimplifiedUploadHandlerChain() {
        UploadHandler authHandler = new AuthHandler();
        UploadHandler extensionHandler = new ExtensionHandler();
        UploadHandler sizeHandler = new SizeHandler();
        UploadHandler dimensionsHandler = new DimensionsHandler();
        return authHandler
                .setNextHandler(extensionHandler)
                .setNextHandler(sizeHandler)
                .setNextHandler(dimensionsHandler);
    }
}
