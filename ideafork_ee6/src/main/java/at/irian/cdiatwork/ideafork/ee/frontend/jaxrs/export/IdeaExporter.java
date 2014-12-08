package at.irian.cdiatwork.ideafork.ee.frontend.jaxrs.export;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.role.UserManager;
import at.irian.cdiatwork.ideafork.core.api.util.CdiUtils;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;

@Path("/idea/")
@Produces(MediaType.APPLICATION_JSON)
public class IdeaExporter {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String CONTENT_DISPOSITION_VALUE_PREFIX = "attachment; filename=all_ideas";
    private static final String FILE_EXTENSION = ".json.txt";

    @Inject
    private IdeaManager ideaManager;

    @Inject
    private UserManager userManager;

    @Inject
    private ActiveUserHolder userHolder;

    @Context
    private HttpServletResponse response;

    @GET
    @Path("/export/all")
    public Response allIdeasOfCurrentUser() {
        lazyInit();

        User authenticatedUser = userHolder.getAuthenticatedUser();

        if (authenticatedUser == null) {
            try {
                return Response.temporaryRedirect(UriBuilder.fromPath("../pages/user/login.xhtml").build()).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
        return Response.ok(ideaManager.loadAllOfAuthor(authenticatedUser))
                .header(CONTENT_DISPOSITION, CONTENT_DISPOSITION_VALUE_PREFIX + FILE_EXTENSION)
                .build();
    }

    @GET
    @Path("/export/{nickname}")
    public List<Idea> allIdeasOfUser(@PathParam("nickname") String nickName) {
        lazyInit();

        response.setHeader(CONTENT_DISPOSITION, CONTENT_DISPOSITION_VALUE_PREFIX + "_of_" + nickName + FILE_EXTENSION);
        User loadedUser = userManager.loadByNickName(nickName);
        return ideaManager.loadAllOfAuthor(loadedUser);
    }

    /*
    * the following part is needed for some ee-servers.
    * it looks portable, but it isn't.
    */

    private void lazyInit() {
        if (ideaManager == null) {
            init();
        }
    }

    private synchronized void init() {
        if (ideaManager == null) {
            CdiUtils.injectFields(this);
        }
    }
}
