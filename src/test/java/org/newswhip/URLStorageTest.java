package org.newswhip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;

public class URLStorageTest {

    @Test
    public void testAddURL() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        String stats = storage.exportStatistics();
        Assertions.assertTrue(stats.contains("newswhip.com;1;10"));
    }

    @Test
    public void testRemoveURL() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        storage.removeURL("http://www.newswhip.com");
        String stats = storage.exportStatistics();
        Assertions.assertFalse(stats.contains("newswhip.com"));
    }

    @Test
    public void testExportStatistics() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        storage.addURL("http://www.newswhip.com/home", 20);
        String stats = storage.exportStatistics();
        Assertions.assertTrue(stats.contains("newswhip.com;2;30"));
    }

    @Test
    public void testRemoveNonExistentURL() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        storage.removeURL("http://www.nonexistent.com");
        String stats = storage.exportStatistics();
        Assertions.assertTrue(stats.contains("newswhip.com;1;10"));
    }

    @Test
    public void testAddMultipleDomains() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        storage.addURL("http://www.test.com", 15);
        String stats = storage.exportStatistics();
        Assertions.assertTrue(stats.contains("newswhip.com;1;10"));
        Assertions.assertTrue(stats.contains("test.com;1;15"));
    }

    @Test
    public void testAddInvalidURL() {
        URLStorage storage = new URLStorage();
        Assertions.assertThrows(URISyntaxException.class, () -> {
            storage.addURL("invalid-url", 10);
        });
    }

    @Test
    public void testRemoveInvalidURL() {
        URLStorage storage = new URLStorage();
        Assertions.assertThrows(URISyntaxException.class, () -> {
            storage.removeURL("invalid-url");
        });
    }

    @Test
    public void testAddAndRemoveSameURL() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        storage.removeURL("http://www.newswhip.com");
        String stats = storage.exportStatistics();
        Assertions.assertFalse(stats.contains("newswhip.com"));
    }

    @Test
    public void testExportEmptyStorage() {
        URLStorage storage = new URLStorage();
        String stats = storage.exportStatistics();
        Assertions.assertEquals("domain;urls;social_score\n", stats);
    }

    @Test
    public void testAddDuplicateURL() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        storage.addURL("http://www.newswhip.com", 20); // Adding same URL with different score
        String stats = storage.exportStatistics();
        Assertions.assertTrue(stats.contains("newswhip.com;1;20")); // Should update the score to 20
    }

    @Test
    public void testRemoveLastURLInDomain() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", 10);
        storage.addURL("http://www.newswhip.com/home", 20);
        storage.removeURL("http://www.newswhip.com/home");
        String stats = storage.exportStatistics();
        Assertions.assertTrue(stats.contains("newswhip.com;1;10"));
        storage.removeURL("http://www.newswhip.com");
        stats = storage.exportStatistics();
        Assertions.assertFalse(stats.contains("newswhip.com"));
    }

    @Test
    public void testAddURLWithNegativeScore() throws URISyntaxException {
        URLStorage storage = new URLStorage();
        storage.addURL("http://www.newswhip.com", -10);
        String stats = storage.exportStatistics();
        Assertions.assertTrue(stats.contains("newswhip.com;1;-10"));
    }
}
