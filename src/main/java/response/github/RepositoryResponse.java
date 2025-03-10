package response.github;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RepositoryResponse {

    private String name;
    private Owner owner;
    private Boolean fork;
}
