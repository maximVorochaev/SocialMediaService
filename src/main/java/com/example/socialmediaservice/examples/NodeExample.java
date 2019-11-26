package com.example.socialmediaservice.examples;

import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class NodeExample {

    @Setter
    static class Node {
        String id;
        List<Node> out;

        public Node(String id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");


        n1.setOut(Arrays.asList(n2));
        n2.setOut(Arrays.asList(n3, n4));
        n3.setOut(Arrays.asList(n5));

        List<Node> nodes = Arrays.asList(n1, n2, n3);

        System.out.println(hasCycle(nodes));
    }

    public static boolean hasCycle(List<Node> nodes) {
        Deque<Node> nodeStack = new ArrayDeque<>();

        for (Node node : nodes) {
            if (hasDuplicate(node, nodeStack)) {
                return true;
            }
            nodeStack.clear();
        }
        return false;
    }


    private static boolean hasDuplicate(Node node, Deque<Node> nodeStack) {
        if (nodeStack.contains(node)) {
            return true;
        } else {
            nodeStack.push(node);
            if (!CollectionUtils.isEmpty(node.out)) {
                for (Node outNode : node.out) {
                    return hasDuplicate(outNode, nodeStack);
                }
            }
        }

        return false;
    }
}
