package client;


import entity.Branch;
import entity.Repository;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import java.util.Set;


@RegisterRestClient
public interface GithubService {

    @GET
    @Path("/users/{login}/repos")
    Set<Repository> getByLogin(@PathParam("login") String login);

    @GET
    //@Path("/repos/{login}/{repo}/branches")
    Set<Branch> getByRepo(@PathParam("login") String owner, @PathParam("repo") String repo);
}