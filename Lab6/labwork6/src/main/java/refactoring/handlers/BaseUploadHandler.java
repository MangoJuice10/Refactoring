package refactoring.handlers;

import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public abstract class BaseUploadHandler implements UploadHandler {
    private UploadHandler nextHandler;

    public BaseUploadHandler() {
    }

    public BaseUploadHandler(UploadHandler handler) {
        nextHandler = handler;
    }

    public UploadHandler setNextHandler(UploadHandler handler) {
        nextHandler = handler;
        return nextHandler;
    }

    public abstract UploadResult check(ImageUploadRequest request);

    public UploadResult checkNext(ImageUploadRequest request) {
        if (nextHandler == null) {
            return UploadResult.ok();
        }
        return nextHandler.check(request);
    }
}
