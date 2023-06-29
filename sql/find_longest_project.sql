SELECT project.ID, project.START_DATE, project.FINISH_DATE,
       EXTRACT(MONTH FROM project.FINISH_DATE) - EXTRACT(MONTH FROM project.START_DATE) AS duration_months
FROM project
WHERE EXTRACT(MONTH FROM project.FINISH_DATE) - EXTRACT(MONTH FROM project.START_DATE) = (
    SELECT MAX(duration_months)
    FROM (
             SELECT EXTRACT(MONTH FROM project.FINISH_DATE) - EXTRACT(MONTH FROM project.START_DATE) AS duration_months
             FROM project
         ) AS project_durations
);
