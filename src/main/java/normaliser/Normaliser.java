package normaliser;

import java.util.List;
import java.util.Optional;

/**
 * Normalises a raw job title input to the closest matching canonical job title.
 *
 * <p>Matching is delegated to a {@link JobTitleComparator} which scores each candidate
 * title in the range [0.0, 1.0]. The highest-scoring title above the configured
 * threshold is returned. If no title meets the threshold, {@link Optional#empty()} is returned.
 *
 */
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

    /**
     * Creates a {@code Normaliser} with the given dependencies, allowing full control
     * over the data source, comparison strategy, and match threshold.
     *
     * @param repository the source of normalised titles; must not be null
     * @param comparator the strategy used to score candidate titles; must not be null
     * @param threshold  the minimum score [0.0, 1.0] required to accept a match
     * @throws IllegalArgumentException if threshold is outside [0.0, 1.0]
     */
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

    /**
     * Returns the normalised title that best matches the given input.
     *
     * <p>The input is matched case-insensitively and leading/trailing whitespace is ignored.
     *
     * @param input the raw job title to normalise; must not be null or blank
     * @return an {@link Optional} containing the best-matching normalised title,
     *         or {@link Optional#empty()} if no title scores at or above the threshold
     * @throws IllegalArgumentException if {@code input} is null or blank
     */
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
