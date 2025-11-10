package refactoring.handlers;

import java.util.Arrays;
import java.util.List;

import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public class ExtensionHandler extends BaseUploadHandler {
    private static final List<String> ALLOWED_EXT = Arrays.asList("png", "jpg", "jpeg", "gif");

    public ExtensionHandler() {}

    public ExtensionHandler(UploadHandler handler) {
        super(handler);
    }

    @Override
    public UploadResult check(ImageUploadRequest request) {
        String ext = getExtension(request.getFilename());
        if (ext == null || !ALLOWED_EXT.contains(ext.toLowerCase())) {
            return UploadResult.fail("Invalid file extension");
        }
        return checkNext(request);
    }

    private String getExtension(String filename) {
        if (filename == null)
            return null;
        int idx = filename.lastIndexOf('.');
        if (idx < 0)
            return null;
        return filename.substring(idx + 1);
    }
}
