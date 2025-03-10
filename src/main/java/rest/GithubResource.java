package rest;

import client.GithubService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ErrorMessage;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.SummaryResponse;
import response.github.BranchResponse;
import response.github.RepositoryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/github")
public class GithubResource {

    private static final Logger log = LoggerFactory.getLogger(GithubResource.class);
    private String currentLogin;

    @Inject
    @RestClient
    GithubService githubService;

    @GET
    @Path("/users/{login}/repos")
    public List<RepositoryResponse> getRepos(@PathParam("login") String login) {
        return githubService.getByLogin(login);
        //return githubService.getByLogin("bartoszOlewinski");
    }

    @GET
    @Path("/repos/{login}/{repository}/branches")
    public List<BranchResponse> getBranches(@PathParam("login") String login, @PathParam("repository") String repo) {
        return githubService.getBranchesByRepoAndOwner(login, repo);
    }

    @GET
    @Path("/users/{login}/repos/TEST")
    public List<SummaryResponse> getReposTEST(String login) throws JsonProcessingException {
        //this.myService.getCośTam(login)
        
        List<SummaryResponse> responses = new ArrayList<>();

        List<RepositoryResponse> repoResponses = this.githubService.getByLogin(login);
        if (repoResponses.isEmpty()) {
            // throw new customException
            // i potem ten CustomException ma być obsłużony przez ten CustomExceptionHandler

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessage(HttpResponseStatus.NOT_FOUND.code(), "User not found!")).build());
        }

        repoResponses.forEach(repositoryResponse -> {
            String ownerLogin = repositoryResponse.getOwner().getLogin();
            String repositoryName = repositoryResponse.getName();
            List<BranchResponse> branchResponses = this.githubService.getBranchesByRepoAndOwner(ownerLogin, repositoryName);

            Map<String, String> branchNameToLastCommitShaMap = branchResponses.stream()
                    .collect(Collectors.toMap(BranchResponse::getName, branchResponse -> branchResponse.getCommit().getSha()));

            responses.add(new SummaryResponse(repositoryName, ownerLogin, branchNameToLastCommitShaMap));
        });

        return responses;
    }

    @GET
    @Path("users/{username}")
   public Boolean getUserExists(String username) {
        return githubService.getUserExists(username);
    }


}
