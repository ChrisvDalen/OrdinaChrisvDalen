import java.util.*;
import java.util.stream.Collectors;


public class ImpWordFrequencyAnalyzer implements WordFrequencyAnalyzer{

	
	private Map<String, Integer> frequencyMapImp;
	
	
	// calculate frequency method
	private void calcFrequency(String text) {
		frequencyMapImp = new HashMap<>();
		String [] words = text.toLowerCase().split("\\W+");
		for(String word : words) {
			frequencyMapImp.put(word, frequencyMapImp.getOrDefault(word, 0) + 1);
			
		}
	}

	@Override
	public int calculateHighestFrequency(String text) {
	calcFrequency(text);
	return Collections.max(frequencyMapImp.values());
	}

	@Override
	public int calculateFrequencyForWord(String text, String word) {
	calcFrequency(text);
	return frequencyMapImp.getOrDefault(word.toLowerCase(), 0);
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
	calcFrequency(text);
	return frequencyMapImp.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
            .limit(n)
            .map(entry -> new ImpWordFrequency(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());
	}
}
