package com.adventofcode;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.adventofcode.SnailfishMath.add;
import static com.adventofcode.SnailfishParser.parse;

public class SnailfishTest {

    @Test
    void testExplode() {

        String input = "[[[[[9,8],1],2],3],4]";
        SnailfishNumber n = parse(input);
        System.out.println("original: " + n);
        n.explode();
        System.out.println("exploded: " + n);
    }

    @Test
    void testAddition() {

        SnailfishNumber result = add(parse("[[[[4,3],4],4],[7,[[8,4],9]]]"), parse("[1,1]"));
        System.out.println("result: " + result);
    }

    @Test
    void testMagnitude() {

        long result = parse("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").magnitude();
        System.out.println("magnitude: " + result);
    }

    @Test
    void testListAddition() {

        String input = """
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
                """;
        InputStream is = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        App.addListNumbers(App.parseInput(is));
    }

}
