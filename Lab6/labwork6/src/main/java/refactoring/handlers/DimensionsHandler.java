package refactoring.handlers;

import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public class DimensionsHandler extends BaseUploadHandler {
    public DimensionsHandler() {}

    public DimensionsHandler(UploadHandler handler) {
        super(handler);
    }

    @Override
    public UploadResult check(ImageUploadRequest request) {
        if (request.getWidth() <= 0 || request.getHeight() <= 0) {
            return UploadResult.fail("Invalid Dimensions");
        }
        if (request.getWidth() > 4000 || request.getHeight() > 4000) {
            return UploadResult.fail("Image dimensions too large");
        }
        return checkNext(request);
    }
}
