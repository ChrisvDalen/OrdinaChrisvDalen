package nl.chrisvandalen.wordcount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImpWordFrequencyAnalyzerTest {

    private static final String TEXT =
            "As we set up our picnic, we noticed how beautifully the sun shines over the lake.";

    private ImpWordFrequencyAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new ImpWordFrequencyAnalyzer();
    }

    @Test
    void calculatesHighestFrequency() {
        assertEquals(2, analyzer.calculateHighestFrequency(TEXT));
    }

    @Test
    void calculatesFrequencyCaseInsensitively() {
        assertEquals(2, analyzer.calculateFrequencyForWord(TEXT, "THE"));
    }

    @Test
    void returnsMostFrequentWordsInStableOrder() {
        List<WordFrequency> result = analyzer.calculateMostFrequentNWords(TEXT, 3);

        assertEquals(3, result.size());
        assertEquals("the", result.getFirst().getWord());
        assertEquals(2, result.getFirst().getFrequency());
    }

    @Test
    void handlesNullAndBlankInput() {
        assertEquals(0, analyzer.calculateHighestFrequency(null));
        assertEquals(0, analyzer.calculateFrequencyForWord(null, "the"));
        assertEquals(List.of(), analyzer.calculateMostFrequentNWords(" ", 3));
    }

    @Test
    void handlesNonPositiveLimit() {
        assertEquals(List.of(), analyzer.calculateMostFrequentNWords(TEXT, 0));
    }
}
