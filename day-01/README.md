# Day 1 - Sonar sweep

## Part One

### Problem
Count the number of times a depth measurement increases from the previous measurement.

### Example
```text
199
200 (increased)
208 (increased)
210 (increased)
200
207 (increased)
240 (increased)
269 (increased)
260
263 (increased)
```

### Result
```text
7
```

### Execute

```shell
mvn package
java -jar target/day-01-1.0-SNAPSHOT.jar -p one -i path/to/input/file
```

---

## Part Two

### Problem:
Instead of using single measurements, consider sums of a **three-measurement sliding window**.
Your goal now is to count the number of times the sum of measurements in this sliding window increases from the previous sum.

### Example:
```text
199  A      
200  A B    
208  A B C   607
210    B C D 618 (increased)
200  E   C D 618
207  E F   D 617
240  E F G   647 (increased)
269    F G H 716 (increased)
260      G H 769 (increased)
263        H 792 (increased)
```

### Result:
```text
5
```

### Analysis
Window `B` is greater than `A` if and only if measurement `4` is greater than `1`, because measurements `2` and `3` are added to both windows.
So in order to compare the first window to the second one, we have to compare measurement `1` to measurement `4`.
To compare the second window to the third one, we have to compare measurement `2` to measurement `5`, and so on.

### Plan
1. Create a list with the last window.
2. Read one more measurement from the file and compare it with the first element in the window to check if there is an increase or not.
3. Remove first element of the window list, and insert last read element at the end of the list.

Data structure to use for the window list: **doubly-linked list** (`LinkedList<Integer>`).

### Execute

```shell
mvn package
java -jar target/day-01-1.0-SNAPSHOT.jar -p two -i path/to/input/file -s 3
```