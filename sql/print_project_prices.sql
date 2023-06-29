SELECT project.ID AS PROJECT_ID,
       SUM(worker.SALARY) * (DATE_PART('month', project.FINISH_DATE) - DATE_PART('month', project.START_DATE)) AS PROJECT_COST
FROM project
         JOIN project_worker ON project.ID = project_worker.PROJECT_ID
         JOIN worker ON project_worker.WORKER_ID = worker.ID
GROUP BY project.ID, project.START_DATE, project.FINISH_DATE;







