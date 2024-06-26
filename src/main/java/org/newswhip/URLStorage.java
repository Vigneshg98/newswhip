package org.newswhip;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class URLStorage {
    private Map<String, Map<String, Integer>> storage;

    public URLStorage() {
        this.storage = new HashMap<>();
    }

    public void addURL(String url, int score) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
//        System.out.println("URL domain: " + domain);
        if (!storage.containsKey(domain)) {
            storage.put(domain, new HashMap<>());
        }
        storage.get(domain).put(url, score);
        printStorage();
    }

    public void removeURL(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        if (storage.containsKey(domain)) {
//            System.out.println("URL domain exists: " + domain);
            storage.get(domain).remove(url);
            // check and remove the domain entry from the storage map if its nested map is empty.
            if (storage.get(domain).isEmpty()) {
                storage.remove(domain);
            }
        }
        printStorage();
    }

    public String exportStatistics() {
        StringBuilder builder = new StringBuilder("domain;urls;social_score\n");
        for (String domain : storage.keySet()) {
//            System.out.println("Domain: " + domain);
            int urlCount = storage.get(domain).size();
            int totalScore = storage.get(domain).values().stream().mapToInt(Integer::intValue).sum();
            builder.append(String.format("%s;%d;%d\n", domain, urlCount, totalScore));
        }
        return builder.toString();
    }

    public void printStorage() {
        System.out.println("Current Storage:");
        for (Map.Entry<String, Map<String, Integer>> domainEntry : storage.entrySet()) {
            System.out.println(domainEntry.getKey() + " -> {");
            Map<String, Integer> urls = domainEntry.getValue();
            for (Map.Entry<String, Integer> urlEntry : urls.entrySet()) {
                System.out.println("    " + urlEntry.getKey() + ": " + urlEntry.getValue());
            }
            System.out.println("}");
        }
    }
}
