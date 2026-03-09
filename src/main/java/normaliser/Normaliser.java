package normaliser;

public class Normaliser {

    private final JobTitleRepository repository;
    private final JobTitleComparator comparator;
    private final double threshold;

    public Normaliser() {
        this(new JobTitleRepository(), new TokenIntersectionComparator(), 0.1);
    }

    public Normaliser(JobTitleRepository jobTitleRepository, TokenIntersectionComparator comparator, double threshold) {
        this.repository = jobTitleRepository;
        this.comparator = comparator;
        this.threshold = threshold;
    }

    public String normalise(String input) {

        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input job title cannot be null or blank");
        }

        double bestScore = 0.0;
        String bestMatch = null;

        for (String normalisedTitle : repository.getAll()) {
            double score = comparator.Compare(input, normalisedTitle);

            if (score > bestScore) {
                bestScore = score;
                bestMatch = normalisedTitle;
            }
        }

        return bestScore >= threshold ? bestMatch : null;
    }
}
