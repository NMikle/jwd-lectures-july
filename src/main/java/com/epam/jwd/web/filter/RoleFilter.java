package com.epam.jwd.web.filter;

import com.epam.jwd.web.command.Command;
import com.epam.jwd.web.command.CommandRegistry;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
public class RoleFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(RoleFilter.class);
    private static final String COMMAND_PARAM_NAME = "command";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String ERROR_PAGE_URL = "/controller?command=show_error";

    private final Map<Role, Set<Command>> commandsByRoles;

    public RoleFilter() {
        commandsByRoles = new EnumMap<>(Role.class);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        for (CommandRegistry command : CommandRegistry.values()) {
            for (Role allowedRole : command.getAllowedRoles()) {
                final Set<Command> commands = commandsByRoles.computeIfAbsent(allowedRole, k -> new HashSet<>());
                commands.add(command.getCommand());
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final String commandName = req.getParameter(COMMAND_PARAM_NAME);
        LOGGER.trace("Checking permissions for command. commandName: {}", commandName);
        if (currentUserHasPermissionForCommand(commandName, req)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(ERROR_PAGE_URL);
        }
    }

    private boolean currentUserHasPermissionForCommand(String commandName, HttpServletRequest request) {
        Role currentUserRole = retrieveCurrentUserRole(request);
        final Command command = Command.of(commandName);
        final Set<Command> allowedCommands = commandsByRoles.get(currentUserRole);
        return allowedCommands.contains(command);
    }

    private Role retrieveCurrentUserRole(HttpServletRequest request) {
        return Optional.ofNullable(request.getSession(false))
                .map(s -> (User) s.getAttribute(USER_SESSION_ATTRIBUTE_NAME))
                .map(User::getRole)
                .orElse(Role.UNAUTHORIZED);
    }

}
