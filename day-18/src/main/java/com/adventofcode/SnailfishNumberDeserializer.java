package com.adventofcode;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

public class SnailfishNumberDeserializer extends StdDeserializer<SnailfishNumber> {

    protected SnailfishNumberDeserializer() {
        super(SnailfishNumber.class);
    }

    @Override
    public SnailfishNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        return parseNode(p.getCodec().readTree(p));
    }

    private SnailfishNumber parseNode(JsonNode node) {

        if (node instanceof IntNode intNode) {
            return RegularSnailfishNumber.regularOf(node.intValue());
        }
        if (node instanceof ArrayNode arrayNode) {
            if (arrayNode.size() != 2) {
                throw new RuntimeException("Array node with size " + arrayNode.size());
            }
            return PairSnailfishNumber.pairOf(parseNode(arrayNode.get(0)), parseNode(arrayNode.get(1)));
        }
        throw new RuntimeException("Invalid node type");
    }
}
