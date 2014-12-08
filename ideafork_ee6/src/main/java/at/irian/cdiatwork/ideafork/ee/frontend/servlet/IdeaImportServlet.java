package at.irian.cdiatwork.ideafork.ee.frontend.servlet;

import at.irian.cdiatwork.ideafork.backend.service.FileUploadService;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fileUploadService.storeUploadedFiles(request.getParts(), userHolder.getAuthenticatedUser());
        request.getRequestDispatcher("/pages/import/summary.xhtml").forward(request, response);
    }
}