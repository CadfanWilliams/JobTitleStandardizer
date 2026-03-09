package normaliser;


/**
 * Strategy interface for comparing a job title input against a normalised target title.
 *
 * <p>Implementations return a quality score in the range [0.0, 1.0], where:
 * <ul>
 *   <li>0.0 indicates no similarity</li>
 *   <li>1.0 indicates a perfect match</li>
 * </ul>
 */
public interface Comparator {

    /**
     * Compares an input job title against a normalised target title and returns a similarity score.
     *
     * @param input  the raw job title provided by the user; must not be null
     * @param target the normalised job title to compare against; must not be null
     * @return a score between 0.0 (no match) and 1.0 (perfect match)
     */
    double Compare(String input, String target);
}
