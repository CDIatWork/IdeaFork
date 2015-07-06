package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller;

import at.irian.cdiatwork.ideafork.core.api.data.view.CategoryView;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import org.apache.deltaspike.core.api.config.view.controller.PreRenderView;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ViewController
public class IndexViewCtrl implements Serializable {
    @Inject
    private IdeaManager ideaManager;

    private List<CategoryView> categories;
    private int categoryCount;

    @PreRenderView
    public void onPreRenderView() {
        categories = ideaManager.getHighestRatedCategories();
        categoryCount = categories.size();
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public List<CategoryView> getCategories() {
        return categories;
    }
}
