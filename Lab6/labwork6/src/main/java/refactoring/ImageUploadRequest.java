package refactoring;
import java.util.Map;

public class ImageUploadRequest {
    private final String userId;
    private final String authToken;
    private final byte[] content;
    private final String filename;
    private final Map<String, String> metadata;
    private final int width;
    private final int height;

    public ImageUploadRequest(String userId, String authToken, byte[] content, String filename, Map<String, String> metadata, int width, int height) {
        this.userId = userId;
        this.authToken = authToken;
        this.content = content;
        this.filename = filename;
        this.metadata = metadata;
        this.width = width;
        this.height = height;
    }

    public String getUserId() {
        return userId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public byte[] getContent() {
        return content;
    }

    public String getFilename() {
        return filename;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}