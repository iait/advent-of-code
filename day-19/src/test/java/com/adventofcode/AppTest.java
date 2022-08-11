package com.adventofcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.adventofcode.Rotation.Axis.*;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;

public class AppTest {

    @Test
    void testTranslation() {

        /*
         *  y
         *  |       p
         *  |____x
         * 1   y
         *     |
         *     |____x
         *    2
         * p is the point (5,4) viewed from the coordinate frame 2.
         * The same point viewed from the coordinate frame 1 is q=(8,1).
         * The translation t=(-3,3) is the location of the frame 1 viewed from the frame 2.
         */

        Point p = new Point(5, 4, 0);
        Translation t = new Translation(-3, 3, 0);
        Point q = t.applyTranslation(p);
        Point expected = new Point(8, 1, 0);
        assertThat(q, equalTo(expected));
    }

    @Test
    void testRotation() {

        /*
         *          p                     p
         *
         *                           y
         *                           |
         *    2 ____y                |____x
         *     |                    1
         *     |
         *     x
         *
         * p is the point (-4,5) viewed from the coordinate frame 2.
         * The same point viewed from the coordinate frame 1 is q=(5,4).
         * The rotation to apply is r=(-Y,X), coordinates of the frame 1 viewed from frame 2.
         *
         */

        Point p = new Point(-4, 5, 0);
        Rotation rotation = Rotation.of(_Y, X, Z);
        Point q = rotation.applyRotation(p);
        Point expected = new Point(5, 4, 0);
        assertThat(q, equalTo(expected));
    }

    @Test
    void printAllRotations() {

        // Print all the 24 possible rotations each one with its matrix and the inverse
        for (Rotation rotation : Rotation.rotations) {
            System.out.println(rotation);
            System.out.println(rotation.toMatrix());
            System.out.println(rotation.inverse());
            System.out.println(rotation.inverse().toMatrix());
            System.out.println("-------------");
        }
    }

    @Test
    void testTransformation() {

        /*
         *  y
         *  |       p
         *  |____x
         * 1
         *
         *    2 ____y
         *     |
         *     |
         *     x
         * p is the point (-4,5) viewed from the coordinate frame 2.
         * The same point viewed from the coordinate frame 1 is q=(8,1).
         * The transformation is built a translation of t=(-3,3) and the rotation (Y,-X).
         */

        Point p = new Point(-4, 5, 0);
        Rotation rotation = Rotation.of(_Y, X, Z);
        Translation translation = new Translation(-3, 3, 0);
        Transformation t = new Transformation(rotation, translation);
        Point q = t.applyTransformation(p);
        Point expected = new Point(8, 1, 0);
        assertThat(q, equalTo(expected));
    }

    @Test
    void testOceanMapBuilder() {

        OceanMapBuilder oceanMapBuilder = spy(OceanMapBuilder.class);

        /*
         * 0____3
         *  \___5____6____1
         *   \        \___2____4
         *    \_7
         */
        Transformation dummyTransformation =
                new Transformation(Rotation.of(X, Y, Z), new Translation(0, 0, 0));
        doAnswer(a -> {
            Scanner s1 = a.getArgument(0, Scanner.class);
            Scanner s2 = a.getArgument(1, Scanner.class);
            if (s1.name() == 0 && (s2.name() == 3 || s2.name() == 5 || s2.name() == 7)) {
                return new Transformation(null, null);
            }
            if (s1.name() == 5 && s2.name() == 6) {
                return dummyTransformation;
            }
            if (s1.name() == 6 && (s2.name() == 1 || s2.name() == 2)) {
                return dummyTransformation;
            }
            if (s1.name() == 2 && s2.name() == 4) {
                return dummyTransformation;
            }
            return null;
        }).when(oceanMapBuilder).findTransformation(any(), any());

        List<Scanner> scanners = Arrays.asList(
                new Scanner(0, emptyList()),
                new Scanner(1, emptyList()),
                new Scanner(2, emptyList()),
                new Scanner(3, emptyList()),
                new Scanner(4, emptyList()),
                new Scanner(5, emptyList()),
                new Scanner(6, emptyList()),
                new Scanner(7, emptyList()));

        oceanMapBuilder.buildMap(scanners);
    }
}
