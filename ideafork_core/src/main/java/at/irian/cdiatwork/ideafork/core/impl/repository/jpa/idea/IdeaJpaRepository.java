package at.irian.cdiatwork.ideafork.core.impl.repository.jpa.idea;

import at.irian.cdiatwork.ideafork.core.api.data.view.CategoryView;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.idea.IdeaRepository;
import at.irian.cdiatwork.ideafork.core.impl.config.ApplicationConfig;
import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.core.impl.repository.jpa.GenericJpaRepository;

import javax.inject.Inject;
import java.util.*;

@Repository
public class IdeaJpaRepository extends GenericJpaRepository<Idea> implements IdeaRepository {
    private static final long serialVersionUID = -2577028101342086615L;

    @Inject
    private ApplicationConfig applicationConfig;

    @Override
    public List<Idea> loadAllOfAuthor(User author) {
        return entityManager.createQuery("select i from Idea i where i.author =:author", Idea.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Override
    public List<CategoryView> getHighestRatedCategories() {
        return entityManager.createQuery("select new at.irian.cdiatwork.ideafork.core.api.data.view.CategoryView(i.category, count(i.category)) from Idea i group by i.category order by count(i.category) desc")
                .setMaxResults(applicationConfig.maxNumberOfHighestRatedCategories())
                .getResultList();
    }

    @Override
    public List<Idea> search(String searchText) {
        //instead of using a framework like lucene
        return entityManager.createQuery("select i from Idea i where i.topic like :searchText or i.category like :searchText", Idea.class)
                .setParameter("searchText", "%" + searchText + "%")
                .getResultList();
    }
}
