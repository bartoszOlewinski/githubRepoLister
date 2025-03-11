package service;


import client.GithubService;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import response.RepositorySummaryResponse;
import response.github.BranchResponse;
import response.github.RepositoryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryService {


    public List<RepositoryResponse> getRepositoriesByLogin(String login, GithubService githubService) {
        return githubService.getByLogin(login);
    }


    public List<RepositorySummaryResponse> getSummaryResponse(String login, GithubService githubService){
        List<RepositorySummaryResponse> responses = new ArrayList<>();

        List<RepositoryResponse> repoResponses = githubService.getByLogin(login);

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
            List<BranchResponse> branchResponses = githubService.getBranchesByRepoAndOwner(ownerLogin, repositoryName);
            Map<String, String> branchNameToLastCommitShaMap = branchResponses.stream()
                    .collect(Collectors.toMap(BranchResponse::getName, branchResponse -> branchResponse.getCommit().getSha()));


            responses.add(new RepositorySummaryResponse(repositoryName, ownerLogin, branchNameToLastCommitShaMap));
        });

        return responses;
    }
}
