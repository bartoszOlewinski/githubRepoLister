package dto;

import java.util.List;

public class RepositoryDTO {

    private String repositoryName;

    private String ownerLogin;

    private List<String> branchList;

    private String lastCommitHash;

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public List<String> getBranchList() {
        return branchList;
    }

    public String getLastCommitHash() {
        return lastCommitHash;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public void setBranchList(List<String> branchList) {
        this.branchList = branchList;
    }

    public void setLastCommitHash(String lastCommitHash) {
        this.lastCommitHash = lastCommitHash;
    }
}
