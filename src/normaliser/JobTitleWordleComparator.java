package normaliser;

import java.util.HashMap;

/*
 I want to try taking a wordle style approach where it will split the job title into words. then into arrays of letters per words.
 if the letters match exactly its a green it will return 1 but if the letter doesnt match exactly but there
 is an instance of this letter elsehwere in the array then its a yellow and a 0.5
 this way the job titles may allow spelling mistakes.
 */
public class JobTitleWordleComparator implements Comparator{
    @Override
    public double Compare(String jobTitleInput, String jobTitleTarget) {
        // Score is what will be used to track the calculation as we go along
        double score = 0;

        if(jobTitleInput == null || jobTitleTarget == null) {
            return score;
        }

        //to do a Wordle style approach we need to know split the string into letters and have a count of all how many times that letter appears
        //then we do a first scan over the word and match the greens, collect the score and remove them from the list
        // then we do a second scan for yellows

        //TODO SPLIT INTO SEPERATE WORDS AND SCAN EACH WORD INDIVIDUALLY
        char[] jobTitleAsCharArray = prepare(jobTitleInput);
        char[] jobTitleTargetAsCharArray = prepare(jobTitleTarget);
        int minLength = Math.min(jobTitleAsCharArray.length, jobTitleTargetAsCharArray.length);


        //First compare the strings char for char to find "green"
        for(int i = 0; i < minLength; i++) {
            if(jobTitleAsCharArray[i] == jobTitleTargetAsCharArray[i]){
                score += 1;
                //There may be a better way of doing this but this is essentially "deleting" the matched green so its not counted when we look for oranges due to it being uppercase
                jobTitleAsCharArray[i] = 'X';
                jobTitleTargetAsCharArray[i] = 'x';
            }
        }

        HashMap<Character, Integer> inputJobTitleMap = new HashMap<>();
        for(char c : jobTitleAsCharArray) {
            if(c != 'X'){
                inputJobTitleMap.put(c, inputJobTitleMap.getOrDefault(c, 0) + 1);
            }
        }

        HashMap<Character, Integer> targetJobTitleMap = new HashMap<>();
        for(char c : jobTitleTargetAsCharArray) {
            if(c != 'X'){
                targetJobTitleMap.put(c, targetJobTitleMap.getOrDefault(c, 0) + 1);
            }
        }

        for (Character c : inputJobTitleMap.keySet()) {
            int inputCount = inputJobTitleMap.get(c);
            int targetCount = targetJobTitleMap.getOrDefault(c, 0);

            int matches = Math.min(inputCount, targetCount);
            score += matches * 0.5;
        }
        return score;
    }

    private char[] prepare(String text) {
        return text.toLowerCase().toCharArray();
    }
}
