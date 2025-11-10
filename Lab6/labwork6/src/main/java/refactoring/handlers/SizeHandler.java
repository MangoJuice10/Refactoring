package refactoring.handlers;

import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public class SizeHandler extends BaseUploadHandler {
    public SizeHandler() {}

    public SizeHandler(UploadHandler handler) {
        super(handler);
    }

    @Override
    public UploadResult check(ImageUploadRequest request) {
        if (request.getContent() == null || request.getContent().length == 0) {
            return UploadResult.fail("Empty content");
        }
        if (request.getContent().length > 5 * 1024 * 1024) {
            return UploadResult.fail("File too large");
        }
        return checkNext(request);
    }
}
