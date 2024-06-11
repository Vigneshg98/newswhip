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
}
