package refactoring;

public class UploadResult {
    private final boolean success;
    private final String message;

    public UploadResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static UploadResult ok() {
        return new UploadResult(true, "OK");
    }

    public static UploadResult fail(String reason) {
        return new UploadResult(false, reason);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
