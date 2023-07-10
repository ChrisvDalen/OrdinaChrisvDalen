import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordFrequencyControllerREST {
    private final ImpWordFrequencyAnalyzer analyzer = new ImpWordFrequencyAnalyzer();

    @GetMapping("/highestFrequency")
    public ResponseEntity<Integer> getHighestFrequency(@RequestParam String text) {
        return ResponseEntity.ok(analyzer.calculateHighestFrequency(text));
    }

    @GetMapping("/frequencyForWord")
    public ResponseEntity<Integer> getFrequencyForWord(@RequestParam String text, @RequestParam String word) {
        return ResponseEntity.ok(analyzer.calculateFrequencyForWord(text, word));
    }

    @GetMapping("/mostFrequentNWords")
    public ResponseEntity<List<WordFrequency>> getMostFrequentNWords(@RequestParam String text, @RequestParam int n) {
        return ResponseEntity.ok(analyzer.calculateMostFrequentNWords(text, n));
    }
}
