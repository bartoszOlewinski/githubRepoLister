package response.github;

import lombok.Data;

@Data
public class BranchResponse {

    private String name;
    private Commit commit;
}
