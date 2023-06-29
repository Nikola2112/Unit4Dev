package ClassForQuery;

import java.util.Date;

public class ProjectDuration {
    private int projectId;
    private Date startDate;
    private Date finishDate;
    private int durationMonths;

    public ProjectDuration(int projectId, Date startDate, Date finishDate, int durationMonths) {
        this.projectId = projectId;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.durationMonths = durationMonths;
    }

    public int getProjectId() {
        return projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }
}
