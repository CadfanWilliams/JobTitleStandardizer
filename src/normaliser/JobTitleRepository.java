package normaliser;

import java.util.List;

public class JobTitleRepository {

    private final List<String> normalizedTitles = List.of(
            "Architect",
            "Software engineer",
            "Quantity surveyor",
            "Accountant"
    );

    public List<String> getAll() {
        return normalizedTitles;
    }
}
