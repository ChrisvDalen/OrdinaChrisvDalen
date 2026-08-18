package nl.chrisvandalen.wordcount;

public record ImpWordFrequency(String word, int frequency) implements WordFrequency {

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }
}
