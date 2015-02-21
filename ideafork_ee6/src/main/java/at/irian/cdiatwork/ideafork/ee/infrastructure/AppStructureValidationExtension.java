package at.irian.cdiatwork.ideafork.ee.infrastructure;

import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessManagedBean;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AppStructureValidationExtension implements Extension {
    private static final Logger LOG = Logger.getLogger(AppStructureValidationExtension.class.getName());

    private List<String> violations = new ArrayList<String>();

    public void validateArtifacts(@Observes ProcessManagedBean pmb) {
        if (pmb.getAnnotatedBeanClass().getJavaClass().isAnnotationPresent(ViewController.class)) {
            validateViewController(pmb.getAnnotatedBeanClass());
        }

        if (pmb.getAnnotatedBeanClass().getJavaClass().getPackage().getName().endsWith(".service")) {
            validateService(pmb.getAnnotatedBeanClass());
        }

        if (pmb.getAnnotatedBeanClass().isAnnotationPresent(Singleton.class)) {
            validateSingletonEjb(pmb.getAnnotatedBeanClass());
        }
    }

    public void checkAndAddViolations(@Observes AfterDeploymentValidation afterDeploymentValidation) {
        if (this.violations.isEmpty()) {
            LOG.info("The rules defined in " + getClass().getName() + " passed!");
            return;
        }

        StringBuilder violationMessage = new StringBuilder();

        violationMessage.append(this.violations.size()).append(" violations found: ");
        for (String violation : this.violations) {
            violationMessage.append(System.getProperty("line.separator")).append(violation);
        }
        this.violations.clear();
        afterDeploymentValidation.addDeploymentProblem(new IllegalStateException(violationMessage.toString()));
    }

    private void validateViewController(AnnotatedType annotatedType) {
        String beanClassName = annotatedType.getJavaClass().getName();

        for (Annotation annotation : annotatedType.getAnnotations()) {
            if (annotation.annotationType().getPackage().getName().equals("javax.ejb")) {
                this.violations.add(beanClassName + " is annotated with @" + annotation.annotationType().getName() +
                    " and with @" + ViewController.class.getName());
            }
        }

        if (!beanClassName.endsWith("ViewCtrl")) {
            LOG.warning(beanClassName + " is annotated with @" + ViewController.class.getName() +
                " but doesn't follow the naming convention *ViewCtrl");
        }
    }

    private void validateService(AnnotatedType annotatedType) {
        if (!annotatedType.isAnnotationPresent(Stateless.class)) {
            this.violations.add(annotatedType.getJavaClass().getName() + " is a service, but not a stateless EJB.");
        }
    }

    private void validateSingletonEjb(AnnotatedType annotatedType) {
        String beanClassName = annotatedType.getJavaClass().getName();
        ConcurrencyManagement cmAnnotation = annotatedType.getAnnotation(ConcurrencyManagement.class);

        if (cmAnnotation == null || ConcurrencyManagementType.CONTAINER == cmAnnotation.value()) {
            LOG.warning(beanClassName + " is annotated with @" + Singleton.class.getName() +
                " but not with " + ConcurrencyManagement.class.getName() +
                ". Please check that it doesn't lead to a bottleneck.");
        } else if (ConcurrencyManagementType.BEAN == cmAnnotation.value()) {
            LOG.warning(beanClassName + " is annotated with @" + Singleton.class.getName() +
                " and with " + ConcurrencyManagement.class.getName() +
                "#" + ConcurrencyManagementType.BEAN.name() +
                ". Please be careful with manual concurrency-management.");
        }
    }
}
