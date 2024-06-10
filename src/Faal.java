import com.fasterxml.jackson.annotation.JsonProperty;

public class Faal {
    @JsonProperty("Poem")
    private String Poem;

    @JsonProperty("Interpretation")
    private String Interpretation;

    public void setInterpretation(String Interpretation){
        this.Interpretation = Interpretation;
    }
    public void formatPoem() {
        StringBuilder result = new StringBuilder();
        boolean removeNext = true;
        this.Poem = this.Poem.replaceAll("\\\\r", "   ");
        this.Poem = this.Poem.replaceAll("\\\\n", "\n");
        int count = 0;
        for (int i = 0; i < this.Poem.length(); i++) {
            char currentChar = this.Poem.charAt(i);
            if (currentChar == '\n') {
                result.append("             ");

                if (removeNext == true ) {
                    removeNext = false;
                    continue;
                }
                removeNext = true;
            }
            result.append(currentChar);
        }
        System.out.println(result);;
        this.Poem = result.toString();
    }
    public void setPoem(String poem){
        this.Poem = poem;
    }
    public String getInterpretation(){
        return this.Interpretation;
    }
    public String getPoem(){
        return this.Poem;
    }

}
