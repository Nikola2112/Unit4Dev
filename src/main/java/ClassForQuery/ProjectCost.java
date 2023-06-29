package ClassForQuery;

public class ProjectCost {
    private int projectId;
    private double projectCost;

    public ProjectCost(int projectId, double projectCost) {
        this.projectId = projectId;
        this.projectCost = projectCost;
    }

    public int getProjectId() {
        return projectId;
    }

    public double getProjectCost() {
        return projectCost;
    }
}