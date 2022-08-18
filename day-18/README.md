# Day 18 - Snailfish

## Part One

### Context

**Snailfish number**:
Ordered pair where each element can be either a regular number or another pair.

Example:
```text
[1,2]
[[3,4],5]
```

**Addition**:
To add two snailfish numbers, form a new pair with the left and right operands.

Example:
```text
[1,2] + [[3,4],5] = [[1,2],[[3,4],5]]
```

**Reduced snailfish number**:
Repeat until no one applies:
1. If any pair is nested inside four pairs, the leftmost such pair **explodes**.
2. If any number is 10 or greater, the leftmost such number **splits**.

To **explode** a pair, the pair's left value is added to the first regular number to the left of the exploding pair (if any), and the pair's right value is added to the first regular number to the right of the exploding pair (if any).
Exploding pairs will always consist of two regular numbers. 
Then, the entire exploding pair is replaced with the regular number 0.

To **split** a regular number, replace it with a pair; the left element of the pair should be the regular number divided by two and rounded down, while the right element of the pair should be the regular number divided by two and rounded up.

The **magnitude** of a pair is 3 times the magnitude of its left element plus 2 times the magnitude of its right element. 
The magnitude of a regular number is just that number.

### Problem

Add up a list of snailfish numbers.
The snailfish numbers are each listed on a separate line.
Add the first snailfish number and the second, then add that result and the third, then add that result and the fourth, and so on until all numbers in the list have been used once.

Compute the magnitude of the final sum.

### Example
```text
[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
[[[5,[2,8]],4],[5,[[9,9],0]]]
[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
[[[[5,4],[7,7]],8],[[8,3],8]]
[[9,3],[[9,9],[6,[4,9]]]]
[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]
```

### Result
```text
final sum: [[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]
magnitude: 4140
```

### Execute
```shell
mvn package
java -jar target/day-18-1.0-SNAPSHOT.jar -p one -i path/to/input/file
```

---

## Part Two

### Problem

Compute the largest magnitude you can get from adding only two of the snailfish numbers.

### Result

With the input of the previous example:
```text
sum: [[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]] + [[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
result: [[[[7,8],[6,6]],[[6,0],[7,7]]],[[[7,8],[8,8]],[[7,9],[0,6]]]]
magnitude: 3993
```

### Execute

```shell
mvn package
java -jar target/day-18-1.0-SNAPSHOT.jar -p two -i path/to/input/file
```