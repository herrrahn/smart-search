package com.rrr.IASearch.tree;

import org.springframework.stereotype.Component;

@Component
public class Tree {
    private Node root;
    private Node last;

    public Tree() {
    }

    public Node getRoot() {
        return root;
    }

    public Node getLast() {
        return last;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(String lastTerm, String term) {
        // find leaf
       this.last = doAdd(findLeafFor(lastTerm), term);
    }

    private Node findLeafFor2(String lastTerm) {
        if (root == null) return null;
        Node leaf;
        if ((lastTerm.isEmpty()) || root.getTerm().equals(lastTerm)) {
            leaf = this.root;
        } else {
            leaf = doFindfindLeafFor(root, lastTerm);
        }
        return leaf;
    }
    private Node findLeafFor(String lastTerm) {
        if (root == null) return null;
        if (this.last == null) this.last = root;

        Node leaf;
        if ((lastTerm.isEmpty()) || last.getTerm().equals(lastTerm)) {
            leaf = this.last;
        } else {
            leaf = doFindfindLeafFor(last, lastTerm);
        }
        return leaf;
    }

    private Node doFindfindLeafFor(Node node, String lastTerm) {
        if (node == null) return null;

        if (node.getTerm().equals(lastTerm)) return node;

        Node n = last.getNodes().get(lastTerm);

        if (n == null) {
            node.getNodes().values().forEach(it -> {
                System.out.println("::" + it.toString());
                doFindfindLeafFor(it, lastTerm);
            });
        }

        return n;
    }

    private Node doAdd(Node root, String term) {
        if (root == null) {
            System.out.println("adding new root for term: " + term);
            root = new Node(term, 1);
            this.root = root;
            return root; //.addTerm(term);
        } else {
            System.out.println("adding new node: " + term);
            return root.addTerm(term);
        }
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

    public void startSearch() {
        this.last = null;
    }
}
