public class Word {
    
    private String word;
    private String hint;
    private String funFact;

    public Word(String word, String hint, String funFact) {
        this.word = word;
        this.hint = hint;
        this.funFact = funFact;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public boolean equals(Word another) {
        if (word.equals(another.getWord())) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Word: " + word + " | Description: " + hint + " | Fun Fact:" + funFact;
    }

}
