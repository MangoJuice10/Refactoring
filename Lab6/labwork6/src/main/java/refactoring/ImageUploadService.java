package refactoring;

public class ImageUploadService {
    public boolean upload(ImageUploadRequest request) {
        System.out.printf("Starting upload for file: %s\n", request.getFilename());
        UploadResult result = MonolithicImageValidator.validate(request);
        if (!result.isSuccess()) {
            System.out.printf("Upload failed: %s\n", result.getMessage());
            return false;
        }

        // Сохранение файла
        System.out.println("Saving file...");

        System.out.printf("Successfully uploaded the file %s\n", request.getFilename());
        return true;
    }
}
