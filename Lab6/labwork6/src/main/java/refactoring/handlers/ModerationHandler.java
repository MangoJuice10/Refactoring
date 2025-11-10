package refactoring.handlers;

import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public class ModerationHandler extends BaseUploadHandler {
    public ModerationHandler() {}

    public ModerationHandler(UploadHandler handler) {
        super(handler);
    }

    @Override
    public UploadResult check(ImageUploadRequest request) {
        if ("true".equalsIgnoreCase(request.getMetadata().getOrDefault("blocked", "false"))) {
            return UploadResult.fail("Content blocked by moderation");
        }
        return checkNext(request);
    }
}
