package refactoring.handlers;

import refactoring.ImageUploadRequest;
import refactoring.UploadResult;

public class AuthHandler extends BaseUploadHandler {
    public AuthHandler() {}

    public AuthHandler(UploadHandler handler) {
        super(handler);
    }

    @Override
    public UploadResult check(ImageUploadRequest request) {
        if (request.getAuthToken() == null || request.getAuthToken().isEmpty()) {
            return UploadResult.fail("Not Authenticated");
        }
        if (!isTokenValid(request.getUserId(), request.getAuthToken())) {
            return UploadResult.fail("Invalid token");
        }
        return checkNext(request);
    }

    private boolean isTokenValid(String userId, String token) {
        return (String.format("token-for-%s", userId)).equals(token);
    }
}
