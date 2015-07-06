package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.search;

import at.irian.cdiatwork.ideafork.ee.backend.service.SearchService;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@ViewController
public class SearchViewCtrl implements Serializable {

    private final String searchTextPlaceholder = "Search";

    @Inject
    private SearchService searchService;

    private String searchText = searchTextPlaceholder;

    private List<Idea> searchResult;

    public Class<? extends Pages.Search> search() {
        searchResult = searchService.searchIdea(searchText);
        return Pages.Search.Fork.class;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchTextPlaceholder() {
        return searchTextPlaceholder;
    }

    public List<Idea> getSearchResult() {
        return searchResult;
    }
}
