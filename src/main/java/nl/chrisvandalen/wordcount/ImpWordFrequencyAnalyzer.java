package nl.chrisvandalen.wordcount;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class ImpWordFrequencyAnalyzer implements WordFrequencyAnalyzer {

    @Override
    public int calculateHighestFrequency(String text) {
        return frequencies(text).values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if (word == null || word.isBlank()) {
            return 0;
        }
        return frequencies(text).getOrDefault(word.toLowerCase(Locale.ROOT), 0);
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        if (n <= 0) {
            return List.of();
        }
        return frequencies(text).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .map(entry -> (WordFrequency) new ImpWordFrequency(entry.getKey(), entry.getValue()))
                .toList();
    }

    private Map<String, Integer> frequencies(String text) {
        if (text == null || text.isBlank()) {
            return Map.of();
        }

        Map<String, Integer> frequencies = new HashMap<>();
        Arrays.stream(text.toLowerCase(Locale.ROOT).split("\\W+"))
                .filter(word -> !word.isBlank())
                .forEach(word -> frequencies.merge(word, 1, Integer::sum));
        return frequencies;
    }
}
