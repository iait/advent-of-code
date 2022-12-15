# Day 23: Amphipod

Input: diagram of the initial situation.

```
#############
#...........#
###B#C#B#D###
  #A#D#C#A#
  #########
```

Objective: organize every amphipod into side rooms in order.

```
#############
#...........#
###A#B#C#D###
  #A#B#C#D#
  #########
```

Each amphipod requires a different amount of energy:

* A - 1
* B - 10
* C - 100
* D - 1000

Extra rules:

* Amphipods cannot stop on the space immediately outside any room.
* An amphipod cannot move from hallway into a room unless that room is his destination room and only contains amphipods of his type.
* Once an amphipod stops in the hallway, it will stay until it can move into his room.
