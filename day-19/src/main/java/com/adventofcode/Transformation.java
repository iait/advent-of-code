package com.adventofcode;

public record Transformation(Rotation rotation, Translation translation) {

    public Point applyTransformation(Point p) {
        return translation.applyTranslation(rotation.applyRotation(p));
    }
}
