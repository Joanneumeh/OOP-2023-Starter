package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

import java.util.*;


public class DANI extends PApplet {

	

	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    String[] sonnet;

    public String[] writeSonnet()
    {
        return null;
    }

	public void setup() {
		colorMode(HSB);

       
	}

	public void keyPressed() {

	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
        
	}
	
	   public void loadFile(String filename) {
		  // load the file into a String array
		  String[] lines = loadStrings(filename);
		  // loop through each line in the file
		  for (String line : lines) {
			// split the line into an array of words
			String[] words = split(line, ' ');
			// loop through each word in the line
			for (String word : words) {
			  // remove punctuation characters from the word
			  String cleanedWord = word.replaceAll("[^\\w\\s]", "");
			  // convert the word to lower case
			  String lowercaseWord = cleanedWord.toLowerCase();
			  // add the word to the model
			  addWordToModel(lowercaseWord);
			}
		  }
		}
	  
		private void addWordToModel(String word) {
		  // add the word to the model
		  // implementation omitted for brevity
		}
		public Word findWord(String str, Word[] words) {
			
			for (Word w : words) {
				if (w.getWord().equals(str)) {
					return w;
				}
			}
			return null;
		}
		
		
    private Map<String, Map<String, Integer>> model;

    public void ModelPrinter(Map<String, Map<String, Integer>> model) {
        this.model = model;
    }

    public void printModel() {
        for (String word : model.keySet()) {
            System.out.print(word + ": ");
            Map<String, Integer> nextWords = model.get(word);
            for (String nextWord : nextWords.keySet()) {
                int count = nextWords.get(nextWord);
                System.out.print(nextWord + "(" + count + ") ");
            }
            System.out.println();
        }
    }
}

			
		
		
	  
	  

