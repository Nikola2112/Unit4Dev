package org.exsample;

import dto.*;
import dto.tables.Worker;

import java.util.List;

public class DatabaseQueryServiceTest {
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();

        List<MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();
        if (maxProjectCountClients != null) {
            System.out.println("Max Project Count Clients:");
            for (MaxProjectCountClient client : maxProjectCountClients) {
                System.out.println("Name: " + client.getName() + ", Project Count: " + client.getProjectCount());
            }
        } else {
            System.out.println("Error executing findMaxProjectsClient");
        }

        System.out.println();

        List<ProjectDuration> projectsByDuration = queryService.findProjectsByDuration();
        if (projectsByDuration != null) {
            System.out.println("Projects By Duration:");
            for (ProjectDuration project : projectsByDuration) {
                System.out.println("Project ID: " + project.getProjectId() + ", Start Date: " + project.getStartDate()
                        + ", Finish Date: " + project.getFinishDate() + ", Duration Months: " + project.getDurationMonths());
            }
        } else {
            System.out.println("Error executing findProjectsByDuration");
        }

        System.out.println();

        List<Worker> maxSalaryWorkers = queryService.findMaxSalaryWorker();
        if (maxSalaryWorkers != null) {
            System.out.println("Max Salary Workers:");
            for (Worker worker : maxSalaryWorkers) {
                System.out.println("Name: " + worker.getName() + ", Salary: " + worker.getSalary());
            }
        } else {
            System.out.println("Error executing findMaxSalaryWorker");
        }

        System.out.println();

        List<ExtremeBirthdayWorker> extremeBirthdays = queryService.findExtremeBirthdays();
        if (extremeBirthdays != null) {
            System.out.println("Extreme Birthdays:");
            for (ExtremeBirthdayWorker worker : extremeBirthdays) {
                System.out.println("Type: " + worker.getType() + ", Name: " + worker.getName() + ", Birthday: " + worker.getBirthday());
            }
        } else {
            System.out.println("Error executing findExtremeBirthdays");
        }

        System.out.println();

        List<ProjectCost> projectCosts = queryService.calculateProjectCost();
        if (projectCosts != null) {
            System.out.println("Project Costs:");
            for (ProjectCost project : projectCosts) {
                System.out.println("Project ID: " + project.getProjectId() + ", Project Cost: " + project.getProjectCost());
            }
        } else {
            System.out.println("Error executing calculateProjectCost");
        }
    }
}
