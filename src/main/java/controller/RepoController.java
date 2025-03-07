package controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;

@Path("https://api.github.com")
public class RepoController {

    @Path("/users/{username}/repos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRepositoryList(@RestPath String username) {
        return "Hello from Quarkus REST";
    }

    @Path("/repos/{owner}/{repo}/branches")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRepoBranchList(@RestPath String owner, @RestPath String repo) {
        return "branch list endpoint";
    }
}
