package normaliser;

import java.util.List;

public class Normaliser {
    private final List<String> JobTitlesList;
    private final List<String> NormalisedJobTitlesList;

    Normaliser(List<String> jobTitles, List<String> normalisedJobTitles) {
        this.JobTitlesList = jobTitles;
        this.NormalisedJobTitlesList = normalisedJobTitles;
    }

    int compare(String jobTitle, String normalisedJobTitle) {
        // This function will compare a job title against a normalised job titles and return a quality
        // Job titles may be split up into multiple words
        return 0;
    }

    //take in job titles
    //compare each job title in list against normalised job titles list
    //for each job title give a score of "closeness" against each normalised job title
    //highest score per job title is what it gets assigned to..
    //output "Job title 'C# Engineer' was normalised to 'Software Engineer'"
}
