package service;

import client.GithubService;
import response.github.BranchResponse;

import java.util.List;

public class BranchService {


    public List<BranchResponse> getBranchesByLoginAndRepo (String login, String repo, GithubService githubService) {
        return githubService.getBranchesByRepoAndOwner(login, repo);
    }

}
