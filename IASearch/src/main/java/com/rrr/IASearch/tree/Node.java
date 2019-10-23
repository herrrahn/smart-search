package com.rrr.IASearch.tree;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private String term;
    private Integer weight;
    private Map<String, Node> nodes;

    public Node(String term, Integer weight) {
        this.term = term;
        this.weight = weight;
        nodes = new HashMap<>();
    }

    public Node addTerm(String term) {
        Node node;
        if (this.getTerm().equals(term)) {
            node = this;
        } else {
            node = nodes.get(term);
        }
        if (node != null) {
            System.out.println("updating term weight " + term + "to: " + node.getWeight() + 1);
            node.setWeight(node.getWeight() + 1);
            return node;
        }

        System.out.println("creating new node for term:" + term);
        node = new Node(term, 1);
        nodes.put(term, node);

        return node;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "term='" + term + '\'' +
                ", weight=" + weight +
                ", nodes=" + nodesToString() +
                '}';
    }

    private String nodesToString() {
        StringBuilder sb = new StringBuilder();

        nodes.forEach((key, node) -> {
            sb.append(key).append("[").append(node.getWeight()).append("]").append(" ").append(node.toString());
        });

        return sb.toString();

    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public void setNodes(Map<String, Node> nodes) {
        this.nodes = nodes;
    }
}
