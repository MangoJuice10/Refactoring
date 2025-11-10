package refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import refactoring.handlers.UploadHandler;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ImageUploadServiceChainTest {
    private ImageUploadService uploadService;
    private UploadHandler basicUploadHandlerChain;
    @BeforeEach
    void setUp() {
        uploadService = new ImageUploadService();
        basicUploadHandlerChain = uploadService.getBasicUploadHandlerChain();
    }

    @Test
    void validSmallImagePasses() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "pic.png",
                Collections.emptyMap(),
                800, 600
        );
        UploadResult res = basicUploadHandlerChain.check(req);
        assertTrue(res.isSuccess(), res.getMessage());
    }

    @Test
    void missingTokenFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "",
                "data".getBytes(),
                "pic.png",
                Collections.emptyMap(),
                800, 600
        );
        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Not Authenticated", res.getMessage());
    }

    @Test
    void invalidTokenFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "wrong-token",
                "data".getBytes(),
                "pic.png",
                Collections.emptyMap(),
                800, 600
        );
        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Invalid token", res.getMessage());
    }

    @Test
    void noExtensionFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "pic",
                Collections.emptyMap(),
                800, 600
        );
        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Invalid file extension", res.getMessage());
    }

    @Test
    void invalidExtensionFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "pic.exe",
                Collections.emptyMap(),
                800, 600
        );
        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Invalid file extension", res.getMessage());
    }

    @Test
    void emptyContentFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                new byte[0],
                "pic.png",
                Collections.emptyMap(),
                800, 600
        );
        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Empty content", res.getMessage());
    }

    @Test
    void tooLargeFileFails() {
        byte[] large = new byte[6 * 1024 * 1024]; // 6MB > allowed 5MB
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                large,
                "pic.png",
                Collections.emptyMap(),
                800, 600
        );

        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("File too large", res.getMessage());
    }

    @Test
    void invalidDimensionsFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "pic.png",
                Collections.emptyMap(),
                -10, 100
        );

        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Invalid Dimensions", res.getMessage());
    }

    @Test
    void tooLargeDimensionsFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "pic.png",
                Collections.emptyMap(),
                5000, 5000
        );

        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Image dimensions too large", res.getMessage());
    }

    @Test
    void blockedByModerationFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "data".getBytes(),
                "pic.png",
                Map.of("blocked", "true"),
                800, 600
        );

        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Content blocked by moderation", res.getMessage());
    }

    @Test
    void virusDetectedFails() {
        ImageUploadRequest req = new ImageUploadRequest(
                "user1",
                "token-for-user1",
                "helloVIRUSbye".getBytes(),
                "pic.png",
                Collections.emptyMap(),
                800, 600
        );
        UploadResult res = basicUploadHandlerChain.check(req);
        assertFalse(res.isSuccess());
        assertEquals("Malicious content detected", res.getMessage());
    }
}
