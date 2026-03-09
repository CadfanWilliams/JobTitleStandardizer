# Job Title Normaliser

Given a list of normalised (canonical) job titles, returns the best match for any raw input string.

## Example

| Input | Normalised Output |
|---|---|
| `Java engineer` | `Software engineer` |
| `C# engineer` | `Software engineer` |
| `Accountant` | `Accountant` |
| `Chief Accountant` | `Accountant` |
| `Chief Quantity Surveyor` | `Quantity surveyor` |

---

## How It Works

Each candidate title in the repository is scored against the input using a **token intersection strategy**:

1. Both strings are lowercased and split into word tokens
2. The number of shared tokens is divided by the larger token count (Jaccard-like coefficient)
3. This gives a quality score `q` where `0.0 <= q <= 1.0`
4. The highest-scoring title above the configured threshold is returned
5. If no title meets the threshold, `Optional.empty()` is returned

---

## Project Structure

```
src/
├── main/java/
│   ├── Main.java                          # Entry point / demo
│   └── normaliser/
│       ├── JobTitleComparator.java        # Comparator strategy interface
│       ├── TokenIntersectionComparator.java  # Default comparator implementation
│       ├── JobTitleRepository.java        # Repository interface
│       ├── InMemoryJobTitleRepository.java   # In-memory implementation
│       └── Normaliser.java               # Main public API
└── test/java/normaliser/
    ├── NormaliserTest.java               # Integration + mock tests for Normaliser
    └── TokenIntersectionComparatorTest.java  # Unit tests for the comparator
```

---

## Running

**Requirements:** Java 17+, Maven 3.6+

```bash
# Run all tests
mvn test

# Run the demo main class
mvn compile exec:java -Dexec.mainClass=Main
```

---

## Possible Improvements

- Swap in a more sophisticated comparator (e.g. Levenshtein distance, TF-IDF, or an embedding-based semantic similarity) for better fuzzy matching
- Load normalised titles from a database or config file via the `JobTitleRepository` interface
- Add a confidence level to the return value so callers can decide whether to accept a low-scoring match
- Cache comparator results for repeated inputs

---

## References

- [Java String.split()](https://www.w3schools.com/java/ref_string_split.asp)
- [Set vs Array in JavaScript](https://www.geeksforgeeks.org/javascript/set-vs-array-in-javascript/)
- [Java HashMap](https://www.w3schools.com/java/java_hashmap.asp)
- [Finding common elements between two collections](https://stackoverflow.com/questions/18856066/java-collections-quickest-way-to-find-if-there-is-a-common-element-between-two)
