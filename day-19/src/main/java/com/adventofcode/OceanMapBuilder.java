package com.adventofcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

public class OceanMapBuilder {
    
    public Set<Point> buildMap(List<Scanner> scanners) {

        if (scanners.isEmpty()) {
            throw new RuntimeException("There must be at least one scanner");
        }

        List<Scanner> disconnected = new ArrayList<>(scanners);
        List<Scanner> unvisited = new ArrayList<>();
        unvisited.add(disconnected.remove(0));
        while (!disconnected.isEmpty() && !unvisited.isEmpty()) {
            Scanner scanner1 = unvisited.remove(0);
            Iterator<Scanner> iterator = disconnected.iterator();
            while (iterator.hasNext()) {
                Scanner scanner2 = iterator.next();
                Transformation transformation = findTransformation(scanner1, scanner2);
                if (transformation != null) {
                    iterator.remove();
                    unvisited.add(scanner2);
                    scanner2.setParent(scanner1);
                    scanner2.setTransformation(transformation);
                }
            }
        }

        if (!disconnected.isEmpty()) {
            throw new RuntimeException("There are scanner without overlapping!");
        }

        Set<Point> beacons = new HashSet<>();
        for (Scanner scanner : scanners) {
            transformToBase(scanner, scanner.beacons().stream()).forEach(beacons::add);
        }
        return beacons;
    }

    protected Stream<Point> transformToBase(Scanner scanner, Stream<Point> beacons) {

        if (scanner.getParent() == null) {
            return beacons;
        }
        return transformToBase(scanner.getParent(),
                beacons.map(scanner.getTransformation()::applyTransformation));
    }

    protected Point transformToBase(Scanner scanner, Point point) {

        if (scanner.getParent() == null) {
            return point;
        }
        return transformToBase(scanner.getParent(),
                scanner.getTransformation().applyTransformation(point));
    }

    protected Transformation findTransformation(Scanner s1, Scanner s2) {

        for (int i = 0; i < s1.beacons().size(); i++) {
            // take one beacon from scanner 1, name in b1
            Point b1 = s1.beacons().get(i);
            for (int j = 0; j < s2.beacons().size(); j++) {
                // take one beacon from scanner 2, name it b2
                Point b2 = s2.beacons().get(j);
                // suppose that b1 and b2 are the same beacon with one rotation
                for (Rotation rotation : Rotation.rotations) {

                    // beacon seen from scanner 2 aligned to scanner 1
                    Point aligned = rotation.applyRotation(b2);
                    // calculate the translation from scanner 2 to scanner 1
                    Translation translation = new Translation(
                            aligned.x() - b1.x(),
                            aligned.y() - b1.y(),
                            aligned.z() - b1.z());
                    Transformation transformation = new Transformation(rotation, translation);
                    List<Point> bs = s2.beacons().stream()
                            .map(transformation::applyTransformation)
                            .collect(Collectors.toList());
                    // check if there are at least 12 beacons that match with this transformation
                    if (findOverlapping(s1.beacons(), bs)) {
                        return transformation;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Finds 12 points that match.
     * The first list is not modified.
     * The second list is reduced as matches are found for performance reasons.
     *
     * @param l1 first list (unmodified)
     * @param l2 second list (could be reduced)
     * @return true if there are at least 12 points in common.
     */
    protected boolean findOverlapping(List<Point> l1, List<Point> l2) {

        int count = 0;
        for (int i = 0; i < l1.size(); i++) {
            int possibleMatches = Math.min(l1.size() - i, l2.size());
            if (count + possibleMatches < 12) {
                return false;
            }
            Point p1 = l1.get(i);
            for (int j = 0; j < l2.size(); j++) {
                if (p1.equals(l2.get(j))) {
                    count++;
                    l2.remove(j);
                    break;
                }
            }
            if (count == 12) {
                return true;
            }
        }
        return false;
    }

    public long largestManhattanDistance(List<Scanner> scanners) {

        long largest = 0;
        List<Point> locations = scanners.stream()
                .map(s -> transformToBase(s, new Point(0, 0, 0))).toList();
        for (int i = 0; i < locations.size(); i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                long distance = manhattanDistance(locations.get(i), locations.get(j));
                if (distance > largest) {
                    largest = distance;
                }
            }
        }
        return largest;
    }

    protected long manhattanDistance(Point p1, Point p2) {

        return abs(p1.x() - p2.x()) + abs(p1.y() - p2.y()) + abs(p1.z() - p2.z());
    }

}
