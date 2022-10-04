//package org.softwire.training.api.routes.v1;
//
//import org.junit.jupiter.api.Test;
//import org.softwire.training.api.core.PermissionsVerifier;
//import org.softwire.training.api.models.ErrorCode;
//import org.softwire.training.api.models.FailedRequestException;
//import org.softwire.training.api.testutils.RequestGenerationHelper;
//import org.softwire.training.db.daos.AgentsDao;
//import org.softwire.training.db.daos.UsersDao;
//import org.softwire.training.models.Agent;
//import org.softwire.training.models.User;
//import spark.Request;
//import spark.Response;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class AgentsRoutesTest {
//
//    private AgentsDao agentsDao = mock(AgentsDao.class);
//    private UsersDao usersDao = mock(UsersDao.class);
//    private PermissionsVerifier permissionsVerifier = new PermissionsVerifier(usersDao);
//    private AgentsRoutes agentsRoutes = new AgentsRoutes(agentsDao, permissionsVerifier);
//    private Response response = mock(Response.class);
//
//    @Test
//    public void readAgentFailsIfAgentDoesNotExist() {
//        // Given
//        int agentId = 17;
//        Agent agent = new Agent();
//        agent.setAgentId(agentId);
//        agent.setFirstName("bob");
//        agent.setDateOfBirth(LocalDate.now());
//
//        int userId = 15;
//        User admin = new User();
//        admin.setUserId(userId);
//        admin.setAgentId(agentId);
//        admin.setAdmin(true);
//
//        Request request = RequestGenerationHelper.makeRequestWithJSONBodyForObject(agent);
//
//        when(agentsDao.getAgent(agentId)).thenReturn(Optional.empty());
//        when(request.attribute("user_id")).thenReturn(0);
//        when(usersDao.getUser(0)).thenReturn(Optional.of(admin));
//
//        // When
//        FailedRequestException exception = assertThrows(
//                FailedRequestException.class,
//                () -> agentsRoutes.readAgent(request, response, agentId));
//
//        // Then
//        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
//    }
//
//    @Test
//    public void readAgentReturnsAgentIfAgentExists() {
//        // Given
//        int agentId = 17;
//        Agent agent = new Agent();
//        agent.setAgentId(agentId);
//        agent.setFirstName("bob");
//        agent.setDateOfBirth(LocalDate.now());
//
//        int userId = 15;
//        User admin = new User();
//        admin.setUserId(userId);
//        admin.setAgentId(agentId);
//        admin.setAdmin(true);
//
//        Request request = RequestGenerationHelper.makeRequestWithJSONBodyForObject(agent);
//
//        when(agentsDao.getAgent(agentId)).thenReturn(Optional.of(agent));
//        when(request.attribute("user_id")).thenReturn(0);
//        when(usersDao.getUser(0)).thenReturn(Optional.of(admin));
//
//        // When
//        Agent returnedAgent = agentsRoutes.readAgent(request, response, agentId);
//
//        // Then
//        assertEquals(agent, returnedAgent);
//    }
//}
