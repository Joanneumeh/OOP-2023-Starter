package ie.tudublin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import processing.core.PApplet;

public class DANI extends PApplet {

    public void settings() {
        size(1000, 1000);
        //fullScreen(SPAN);
    }

    String[] sonnet;

    public String writeSonnet(ArrayList<String> wordsList, Map<String, ArrayList<String>> followsMap) {
        Random rand = new Random();
        boolean randomBool = rand.nextBoolean();

        String sonnet = "";
        for (int i = 0; i < 14; i++) {
            String line = "";
            String word = wordsList.get(rand.nextInt(wordsList.size()));
            line += word;
            for (int j = 1; j <= 7; j++) {
                ArrayList<String> follows = followsMap.get(word);
                if (follows == null || follows.isEmpty()) {
                    break;
                }
                String follow = follows.get(rand.nextInt(follows.size()));
                line += " " + follow;
                word = follow;
            }
            sonnet += line + "\n";
        }
        System.out.println(sonnet); // print to console
        return sonnet;
    }

    public void setup() {
        colorMode(HSB);
    }

    public void draw() {
        background(0);
        fill(255);
        noStroke();
        textSize(20);
        textAlign(CENTER, CENTER);
		
    }
	public boolean[] keys = new boolean[1024]; 

	public void keyPressed()
	{
		keys[keyCode] = true;
	}

	public void keyReleased()
	{
		keys[keyCode] = false;
	}

    public void loadFile(String filename) {
        // load the file into a String array
        String[] lines = loadStrings(filename);
        Map<String, ArrayList<String>> model = new HashMap<String, ArrayList<String>>();
        // loop through each line in the file
        for (String line : lines) {
            // split the line into an array of words
            String[] words = split(line, ' ');
            // loop through each word in the line
            for (int i = 0; i < words.length - 1; i++) {
                // remove punctuation characters from the word
                String cleanedWord = words[i].replaceAll("[^\\w\\s]", "");
                // convert the word to lower case
                String lowercaseWord = cleanedWord.toLowerCase();
                // add the word to the model
                if (!model.containsKey(lowercaseWord)) {
                    model.put(lowercaseWord, new ArrayList<String>());
                }
                String nextWord = words[i + 1].replaceAll("[^\\w\\s]", "").toLowerCase();
                model.get(lowercaseWord).add(nextWord);
            }
        }
        printModel(model);
    }

    private void printModel(Map<String, ArrayList<String>> model) {
        for (String word : model.keySet()) {
            System.out.print(word + ": ");
            ArrayList<String> follows = model.get(word);
            for (String follow : follows) {
                System.out.print(follow + " ");
            }
            System.out.println();
        }
    }
	public class Follow {
		private String word;
		private int count;
		
		public Follow(String word, int count) {
			this.word = word;
			this.count = count;
		}
		
		public String getWord() {
			return word;
		}
		
		public int getCount() {
			return count;
		}
		
		//@Override
		public String toString() {
			return word + "(" + count + ")";
		}
	}
	public class Word {
		private String word;
		private ArrayList<Follow> follows;
	
		public Word(String word) {
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
}
	

