package normaliser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TokenIntersectionComparator implements JobTitleComparator {

    @Override
    public double compare(String input, String target) {
        if (input == null || target == null) {
            return 0.0;
        }

        //Put inputs into sets so we can compare intersections
        Set<String> inputTokens = prepare(input);
        Set<String> targetTokens = prepare(target);

        if (inputTokens.isEmpty() || targetTokens.isEmpty()) {
            return 0.0;
        }

        //I didn't know what retainAll did before starting this task but essentially its smashing two collections together and keeping anything that's the same
        Set<String> intersection = new HashSet<>(inputTokens);
        intersection.retainAll(targetTokens);
        // So after checking for any overlapping words we are left with a "intersection" which is our collection of words in both input and target
        // We can take how many words overlapped and then divide it by how many words we checked it against giving us the percentage of overlapping words
        return (double) intersection.size() /
                Math.max(inputTokens.size(), targetTokens.size());
    }

    private Set<String> prepare(String text) {
        // Cut inputs down so they are easier to work with.
        // Split into multiples words so easier to match
        return Arrays.stream(text.toLowerCase().split("\\s+"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }
}
