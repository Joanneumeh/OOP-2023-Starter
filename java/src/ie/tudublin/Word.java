package ie.tudublin;
import java.util.ArrayList;


public class Word {
    private String word;
    private ArrayList<Follow> follows;

    public Word(String word, ArrayList<Follow> arrayList) {
        this.word = word;
        this.follows = new ArrayList<>();
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Follow> getFollows() {
        return follows;
    }

    public void addFollow(Follow follow) {         //add a Follow object to the follows list.
        follows.add(follow);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word).append(": ");
        for (Follow follow : follows) {
            sb.append(follow).append(", ");       //separated by commas and spaces. 
        }
        sb.delete(sb.length() - 2, sb.length()); // remove the last comma and space
        return sb.toString();
    }
    public Follow findFollow(String str) {
        for (Follow w : follows) {
            if (w.getWord().equals(str)) {
                return w;
            }
        }
        return null;
    }
    
}

