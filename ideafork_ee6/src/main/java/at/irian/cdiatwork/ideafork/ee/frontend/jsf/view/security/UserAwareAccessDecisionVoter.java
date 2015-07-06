package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.security;

import at.irian.cdiatwork.ideafork.ee.frontend.jsf.message.UserMessage;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import org.apache.deltaspike.security.api.authorization.AbstractAccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Set;

@RequestScoped
public class UserAwareAccessDecisionVoter extends AbstractAccessDecisionVoter {
    @Inject
    private ActiveUserHolder activeUserHolder;

    @Inject
    private UserMessage userMessage;

    @Override
    protected void checkPermission(AccessDecisionVoterContext accessDecisionVoterContext,
                                   Set<SecurityViolation> securityViolations) {
        if (!activeUserHolder.isLoggedIn()) {
            securityViolations.add(newSecurityViolation(userMessage.pleaseLogin()));
        }
    }
}
