package refactoring.handlers;
import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public interface UploadHandler {
    public UploadHandler setNextHandler(UploadHandler handler);
    public UploadResult check(ImageUploadRequest request);
    public UploadResult checkNext(ImageUploadRequest request);
}