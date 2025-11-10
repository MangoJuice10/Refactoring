package refactoring.handlers;

import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public class VirusHandler extends BaseUploadHandler {
    public VirusHandler() {}

    public VirusHandler(UploadHandler handler) {
        super(handler);
    }

    @Override
    public UploadResult check(ImageUploadRequest request) {
        if (containsVirusSignature(request.getContent())) {
            return UploadResult.fail("Malicious content detected");
        }
        return checkNext(request);
    }

    private static boolean containsVirusSignature(byte[] content) {
        String s = new String(content);
        return s.toLowerCase().contains("virus");
    }
}
