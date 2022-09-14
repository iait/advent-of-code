package com.adventofcode;

import static com.adventofcode.OceanImage.createOceanImage;

public class TestApp {

    public static void main(String[] args) {

        String enhancementStr = """
                ..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##\
                #..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###\
                .######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#.\
                .#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#.....\
                .#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#..\
                ...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.....\
                ..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#""";
        String imageStr = """
                #..#.
                #....
                ##..#
                ..#..
                ..###""";

        System.out.println(enhancementStr);
        var enhancementAlgorithm = new EnhancementAlgorithm(enhancementStr, 3);
        System.out.println(enhancementAlgorithm);

        System.out.println(imageStr);
        var oceanImage = createOceanImage(imageStr, '.');
        System.out.println(oceanImage);

        var enhancedOceanImage = oceanImage.applyEnhancement(enhancementAlgorithm);
        System.out.println(enhancedOceanImage);

        enhancedOceanImage = enhancedOceanImage.applyEnhancement(enhancementAlgorithm);
        System.out.println(enhancedOceanImage);
        System.out.println(enhancedOceanImage.lightCount());

        for (int i = 0; i < 50; i++) {
            oceanImage = oceanImage.applyEnhancement(enhancementAlgorithm);
        }
        System.out.println(oceanImage);
        System.out.println(oceanImage.lightCount());
    }
}
