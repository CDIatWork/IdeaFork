package at.irian.cdiatwork.ideafork.ee.frontend.servlet;

import at.irian.cdiatwork.ideafork.ee.backend.service.FileUploadService;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigDescriptor;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.spi.scope.window.WindowContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/idea/import")
@MultipartConfig
public class IdeaImportServlet extends HttpServlet {
    @Inject
    private ActiveUserHolder userHolder;

    @Inject
    private FileUploadService fileUploadService;

    @Inject
    private ViewConfigResolver viewConfigResolver;

    @Inject
    private WindowContext windowContext;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fileUploadService.storeUploadedFiles(request.getParts(), userHolder.getAuthenticatedUser());

        ViewConfigDescriptor viewConfigDescriptor = viewConfigResolver.getViewConfigDescriptor(Pages.Import.Summary.class);
        request.getRequestDispatcher(viewConfigDescriptor.getViewId() + "?dswid=" + request.getParameter("dswid")).forward(request, response);
    }
}