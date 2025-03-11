package rest;

import client.GithubService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import response.SummaryResponse;
import response.github.BranchResponse;
import response.github.RepositoryResponse;

import java.time.Duration;
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
    @Path("/users/{login}/repos/TEST")
    public List<RepositoryResponse> getRepos(@PathParam("login") String login) {
        return githubService.getByLogin(login);
    }

    @GET
    @Path("/repos/{login}/{repository}/branches/TEST")
    public List<BranchResponse> getBranches(@PathParam("login") String login, @PathParam("repository") String repo) {
        return githubService.getBranchesByRepoAndOwner(login, repo);
    }


    @GET
    @Blocking
    @Path("/repositories/{login}")
    public Uni<List<SummaryResponse>> getRepositories(String login) throws Exception {
        //this.myService.getCośTam(login)

        List<SummaryResponse> responses = new ArrayList<>();

        List<RepositoryResponse> repoResponses = this.githubService.getByLogin(login);

        if (repoResponses.isEmpty()) {
            throw new WebApplicationException(
                    Response.status(404)
                            .entity("{\"status\":\"404\",\"error\":\"User does not exist\"}")
                            .type("application/json")
                            .build()
            );
        }

        repoResponses.forEach(repositoryResponse -> {
            String ownerLogin = repositoryResponse.getOwner().getLogin();
            String repositoryName = repositoryResponse.getName();
            List<BranchResponse> branchResponses = this.githubService.getBranchesByRepoAndOwner(ownerLogin, repositoryName);
            Map<String, String> branchNameToLastCommitShaMap = branchResponses.stream()
                    .collect(Collectors.toMap(BranchResponse::getName, branchResponse -> branchResponse.getCommit().getSha()));


            responses.add(new SummaryResponse(repositoryName, ownerLogin, branchNameToLastCommitShaMap));
        });

        return Uni.createFrom().item(responses);
    }


}
