package com.rrr.IASearch.controller;

import com.rrr.IASearch.service.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/s")
@CrossOrigin("*")
public class Controller {

    private Searcher searcher;

    @Autowired
    public Controller(Searcher searcher) {
        this.searcher = searcher;
        this.reset();
    }

    @GetMapping
    public List<String> test(@RequestParam(value = "t", required = false) String term ) {
        return searcher.search(term);
    }

    @GetMapping(value = "/start")
    public void findSuggestions() {
        this.searcher.startSearch();
    }

    @GetMapping(value = "/debug")
    public List<String> debug() {
        return this.searcher.displayTrees();
    }

    @GetMapping(value = "reset")
    public void reset() {
        this.searcher.reset();

        this.searcher.startSearch();
        this.searcher.search("A");
        this.searcher.search("B");
        this.searcher.search("C");

        this.searcher.startSearch();
        this.searcher.search("A");
        this.searcher.search("C");
        this.searcher.search("F");

        this.searcher.startSearch();
        this.searcher.search("A");
        this.searcher.search("B");
        this.searcher.search("D");

        this.searcher.startSearch();
        this.searcher.search("R");
        this.searcher.search("A");
        this.searcher.search("F");
        this.searcher.search("A");
        this.searcher.search("E");
        this.searcher.search("L");

        this.searcher.startSearch();
        this.searcher.search("R");
        this.searcher.search("A");
        this.searcher.search("F");
        this.searcher.search("A");
        this.searcher.search("E");
        this.searcher.search("L");

        this.searcher.startSearch();
        this.searcher.search("X");
        this.searcher.search("Y");
        this.searcher.search("Z");
    }
}
