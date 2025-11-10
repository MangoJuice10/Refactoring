package refactoring;

import java.util.Arrays;
import java.util.List;

/* Монолитный валидатор, в котором располагаются все проверки изображения.
 * Добавление новых проверок является затруднительным из-за крайне низкой читаемости кода,
 * а также может привести к выходу из строя существующих проверок (Нарушение Open/Closed Principle) */
public class MonolithicImageValidator {
    private static final List<String> ALLOWED_EXT = Arrays.asList("png", "jpg", "jpeg", "gif");

    public static UploadResult validate(ImageUploadRequest req) {
        // 1. Auth check
        if (req.getAuthToken() == null || req.getAuthToken().isEmpty()) {
            return UploadResult.fail("Not Authenticated");
        }
        if (!isTokenValid(req.getUserId(), req.getAuthToken())) {
            return UploadResult.fail("Invalid token");
        }

        // 2. Filename Extension check
        String ext = getExtension(req.getFilename());
        if (ext == null || !ALLOWED_EXT.contains(ext.toLowerCase())) {
            return UploadResult.fail("Invalid file extension");
        }

        // 3. Image size validation
        if (req.getContent() == null || req.getContent().length == 0) {
            return UploadResult.fail("Empty content");
        }
        if (req.getContent().length > 5 * 1024 * 1024) {
            return UploadResult.fail("File too large");
        }

        // 4. Dimension
        if (req.getWidth() <= 0 || req.getHeight() <= 0) {
            return UploadResult.fail("Invalid Dimensions");
        }
        if (req.getWidth() > 4000 || req.getHeight() > 4000) {
            return UploadResult.fail("Image dimensions too large");
        }

        // 5. Simple virus scan simulation
        if (containsVirusSignature(req.getContent())) {
            return UploadResult.fail("Malicious content detected");
        }

        // 6. Content moderation
        if ("true".equalsIgnoreCase(req.getMetadata().getOrDefault("blocked", "false"))) {
            return UploadResult.fail("Content blocked by moderation");
        }

        return UploadResult.ok();
    }

    private static boolean isTokenValid(String userId, String token) {
        return (String.format("token-for-%s", userId)).equals(token);
    }

    private static String getExtension(String filename) {
        if (filename == null)
            return null;
        int idx = filename.lastIndexOf('.');
        if (idx < 0)
            return null;
        return filename.substring(idx + 1);
    }

    private static boolean containsVirusSignature(byte[] content) {
        String s = new String(content);
        return s.toLowerCase().contains("virus");
    }
}
