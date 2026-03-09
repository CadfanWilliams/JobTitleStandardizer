package normaliser;

import java.util.List;
import java.util.Optional;

public class Normaliser {

    /** Minimum quality score required to accept a match. */
    private static final double DEFAULT_THRESHOLD = 0.1;

    private final JobTitleRepository repository;
    private final JobTitleComparator comparator;
    private final double threshold;

    /**
     * Creates a {@code Normaliser} with default dependencies and threshold.
     */
    public Normaliser() {
        this(new JobTitleRepository(), new TokenIntersectionComparator(), DEFAULT_THRESHOLD);
    }

    public Normaliser(JobTitleRepository repository, JobTitleComparator comparator, double threshold) {
        if (repository == null) throw new IllegalArgumentException("repository must not be null");
        if (comparator == null) throw new IllegalArgumentException("comparator must not be null");
        if (threshold < 0.0 || threshold > 1.0) {
            throw new IllegalArgumentException("threshold must be between 0.0 and 1.0, got: " + threshold);
        }
        this.repository = repository;
        this.comparator = comparator;
        this.threshold = threshold;
    }

    public Optional<String> normalise(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input job title must not be null or blank");
        }

        String trimmedInput = input.strip();
        List<String> titles = repository.getAll();

        double bestScore = 0.0;
        String bestMatch = null;

        for (String candidateTitle : titles) {
            double score = comparator.compare(trimmedInput, candidateTitle);

            // On a tie, the first match in repository order wins (stable, predictable behaviour)
            if (score > bestScore) {
                bestScore = score;
                bestMatch = candidateTitle;
            }
        }

        return bestScore >= threshold ? Optional.of(bestMatch) : Optional.empty();
    }
}
