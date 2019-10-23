package com.rrr.IASearch.service;

import com.rrr.IASearch.tree.Node;
import com.rrr.IASearch.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Searcher {

    List<String> terms;

    Map<String, Tree> trees = new HashMap<>();

    private Tree tree;
    private boolean inSearch;
    private String lastTerm = "";


    @Autowired
    public Searcher() {
        System.out.println("Searcher.. constructor");
        //      this.tree = tree;

        terms = new ArrayList<>();

        for (int i = 65; i <= 90; i++) {
            terms.add(String.valueOf((char) i));
        }
    }

    public void reset() {
        this.trees.clear();
        this.terms.clear();
        this.lastTerm = "";
    }

    public void startSearch() {
        if (this.tree != null) {
            this.tree.startSearch();
        }
        this.tree = null;
        this.lastTerm = "";
        this.inSearch = true;
    }

    public List<String> search(String term) {
        if (this.tree == null) {
            this.tree = trees.get(term);
        }

        if (this.tree == null) {
            System.out.println("new tree for term: " + term);
            this.tree = new Tree();
            this.trees.put(term, tree);
        }

        this.tree.add(lastTerm, term);
        this.lastTerm = term;
        //return displayTrees();
        return findSugestions(this.tree);
    }

    public List<String> displayTrees() {

        ArrayList<String> ts = new ArrayList<>();
        trees.forEach((term, tree) -> {
                    ts.add(term + " = " + tree.toString());
                }
        );
        return ts;
    }

    public List<String> findSugestions(Tree tree) {
    //    Tree tree = trees.get(term);
        if (tree == null) return Collections.emptyList();

        Collection<Node> nodes;
        if (tree.getLast() != null) {
            nodes = tree.getLast().getNodes().values();
        } else {
            nodes = tree.getRoot().getNodes().values();
        }
        return nodes.stream().sorted(Comparator.comparingInt(Node::getWeight)).map(Node::getTerm).collect(Collectors.toList());
    }
}
