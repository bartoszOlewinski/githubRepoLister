package client;


import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import response.github.BranchResponse;
import response.github.RepositoryResponse;

import java.lang.reflect.Type;
import java.util.List;

@RegisterRestClient
public interface GithubService {

    @GET
    @Path("/users/{login}/repos")
    List<RepositoryResponse> getByLogin(@PathParam("login") String login);

    @GET
    @Path("/repos/{login}/{repo}/branches")
    List<BranchResponse> getBranchesByRepoAndOwner(@PathParam("login") String owner, @PathParam("repo") String repo);

}