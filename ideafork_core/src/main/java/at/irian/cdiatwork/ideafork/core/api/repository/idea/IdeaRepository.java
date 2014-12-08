package at.irian.cdiatwork.ideafork.core.api.repository.idea;

import at.irian.cdiatwork.ideafork.core.api.data.view.CategoryView;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.GenericRepository;

import java.util.List;

public interface IdeaRepository extends GenericRepository<Idea> {

    List<Idea> loadAllOfAuthor(User author);

    List<Idea> search(String searchText);

    List<CategoryView> getHighestRatedCategories();
}