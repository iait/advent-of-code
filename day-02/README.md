# Day 2 - Dive!

## Part One

### Problem

Calculate the final horizontal position and depth after following the instructions in the form:
* `forward X` increases the horizontal position by `X` units,
* `down X` increases the depth by `X` units,
* `up X` decreases the depth by `X` units.

Then multiply the horizontal position and the final depth.

---

## Part Two

### Problem

In addition to horizontal position and depth, you'll also need to track a third value, **aim**, which also starts at `0`.

The commands mean:
* `down X` increases your aim by `X` units.
* `up X` decreases your aim by `X` units.
* `forward X` does two things:
  * increases your horizontal position by `X` units.
  * increases your depth by your aim multiplied by `X`.

Then multiply the horizontal position and the final depth.
