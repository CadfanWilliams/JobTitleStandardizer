package normaliser;

import java.util.List;

/**
 * <p>This just returns a list of normalizedTitles</p>
 * <p>In a production system this would be backed by a database or external configuration.
 */
public class JobTitleRepository {

    private static final List<String> normalizedTitles = List.of(
            "Architect",
            "Software engineer",
            "Quantity surveyor",
            "Accountant"
    );

    public List<String> getAll() {
        return normalizedTitles;
    }
}
