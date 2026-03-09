import normaliser.Normaliser;

public class Main {
    public static void main(String[] args) {

        Normaliser normaliser = new Normaliser();

        String[] inputs = {
                "Java engineer",
                "C# engineer",
                "Accountant",
                "Chief Accountant",
                "Chief Quantity Surveyor"
        };

        for (String input : inputs) {
            String result = normaliser.normalise(input)
                    .orElse("[no match found]");
            System.out.printf("%-30s -> %s%n", input, result);
        }
    }
}