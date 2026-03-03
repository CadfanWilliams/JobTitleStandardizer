# Job Title Standardizer

Provided with a list of ideal  job titles return the best match when provided with an input string.
Concretely, given a normalized job titles list of “Architect", "Software engineer", "Quantity
surveyor", and "Accountant", write a process that returns the normalized result for the input.
input normalized

"Java engineer" > "Software engineer"
"C# engineer" > "Software engineer"
"Accountant" > "Accountant"
Chief Accountant" > "Accountant"

internally in the process a quality score is considered (q), where 0.0 <= q <= 1.0, to find the
closest match.

Sources
- https://www.w3schools.com/java/ref_string_split.asp
- https://stackoverflow.com/questions/5235401/split-string-into-array-of-character-strings
- https://www.geeksforgeeks.org/javascript/set-vs-array-in-javascript/
- https://www.w3schools.com/java/java_hashmap.asp
- https://stackoverflow.com/questions/18856066/java-collections-quickest-way-to-find-if-there-is-a-common-element-between-two