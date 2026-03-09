import normaliser.Normaliser;

public class Main {
    public static void main(String[] args) {

        Normaliser n = new Normaliser();

        System.out.println(n.normalise("Java engineer"));
        System.out.println(n.normalise("C# engineer"));
        System.out.println(n.normalise("Chief Accountant"));
        System.out.println(n.normalise("Chief Quantity Surveyor"));
    }
}