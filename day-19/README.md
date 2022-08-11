# Day 19 - Beacon Scanner

## Context

There are **scanners** and **beacons**.

Each scanner can detect beacons in a cube centered on the scanner at most `1,000` units away in each of the three axes `x`, `y` and `z`.

By establishing at least `12` commons beacons to two scanners, you can determine where the scanners are relative to each other.

---

## Example in 2 dimensions

```text
--- scanner 0 ---
0,2
4,1
3,3

--- scanner 1 ---
-1,-1
-5,0
-2,1
```

```text
--- scanner 0 ---
...B.
B....
....B
S....

--- scanner 1 ---
...B..
B....S
....B.
```

In this simplified example suppose scanner only need `3` overlapping beacons.
So you can determine the relative position of the scanners: `scanner 1` is at position `(5,2)` relative to `scanner 0`.

```text
...B..
B....S
....B.
S.....
```
---

The scanners also don't know their facing direction.
Each axel can be rotated some integer number of 90-degree.
So there are `24` different orientations.

```text
 1)  x, y, z
 2)  x,-y,-z
 3)  x, z,-y
 4)  x,-z, y
 5) -x, z, y
 6) -x,-z,-y
 7) -x, y,-z
 8) -x,-y, z
 9)  y, z, x
10)  y,-z,-x
11)  y, x,-z
12)  y,-x, z
13) -y, x, z
14) -y,-x,-z
15) -y, z,-x
16) -y,-z, x
17)  z, x, y
18)  z,-x,-y
19)  z, y,-x
20)  z,-y, x
21) -z, y, x
22) -z,-y,-x
23) -z, x,-y
24) -z,-x, y
```

### Problem

Find `12` matching beacons between two scanners after a transformation, which consists of a translation of the origin and one of the 24 possible rotations.

### Solution

