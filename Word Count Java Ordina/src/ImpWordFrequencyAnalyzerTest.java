import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImpWordFrequencyAnalyzerTest {
    private ImpWordFrequencyAnalyzer analyzer;

    @BeforeEach
    public void setUp() {
        analyzer = new ImpWordFrequencyAnalyzer();
    }

    @Test
    public void testCalculateHighestFrequency() {
        String text = "As we set up our picnic, we noticed how beautifully the sun shines over the lake.";
        int highestFrequency = analyzer.calculateHighestFrequency(text);
        assertEquals(2, highestFrequency);
    }

    @Test
    public void testCalculateFrequencyForWord() {
        String text = "As we set up our picnic, we noticed how beautifully the sun shines over the lake.";
        int frequency = analyzer.calculateFrequencyForWord(text, "the");
        assertEquals(2, frequency);
    }

    @Test
    public void testCalculateMostFrequentNWords() {
        String text = "As we set up our picnic, we noticed how beautifully the sun shines over the lake.";
        List<WordFrequency> mostFrequentNWords = analyzer.calculateMostFrequentNWords(text, 3);
        assertEquals(3, mostFrequentNWords.size());
        assertEquals("the", mostFrequentNWords.get(0).getWord());
        assertEquals(2, mostFrequentNWords.get(0).getFrequency());
    }
}
