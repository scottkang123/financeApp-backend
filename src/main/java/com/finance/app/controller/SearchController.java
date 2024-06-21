package com.finance.app.controller;

import com.finance.app.service.SearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
@Tag(name = "Search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/stock/{query}")
    public List<?> getSearchStockResults(@PathVariable String query) {
        return searchService.getSearchResults(query);
    }

}
