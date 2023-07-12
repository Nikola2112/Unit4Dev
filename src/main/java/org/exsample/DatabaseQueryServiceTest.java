package org.exsample;

import ClassForQuery.*;

import java.util.List;

public class DatabaseQueryServiceTest {
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();

        // Запрос на поиск клиентов с максимальным количеством проектов
        List<MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();
        if (maxProjectCountClients != null) {
            System.out.println("Клиенты с максимальным количеством проектов:");
            for (MaxProjectCountClient client : maxProjectCountClients) {
                System.out.println("Имя: " + client.getName() + ", Количество проектов: " + client.getProjectCount());
            }
        } else {
            System.out.println("Ошибка при выполнении запроса findMaxProjectsClient");
        }

        System.out.println();

        // Запрос на поиск проектов по длительности
        List<ProjectDuration> projectsByDuration = queryService.findProjectsByDuration();
        if (projectsByDuration != null) {
            System.out.println("Проекты по длительности:");
            for (ProjectDuration project : projectsByDuration) {
                System.out.println("ID проекта: " + project.getProjectId() + ", Дата начала: " + project.getStartDate()
                        + ", Дата окончания: " + project.getFinishDate() + ", Продолжительность (месяцы): " + project.getDurationMonths());
            }
        } else {
            System.out.println("Ошибка при выполнении запроса findProjectsByDuration");
        }

        System.out.println();

        // Запрос на поиск работников с максимальной зарплатой
        List<Worker> maxSalaryWorkers = queryService.findMaxSalaryWorker();
        if (maxSalaryWorkers != null) {
            System.out.println("Работники с максимальной зарплатой:");
            for (Worker worker : maxSalaryWorkers) {
                System.out.println("Имя: " + worker.getName() + ", Зарплата: " + worker.getSalary());
            }
        } else {
            System.out.println("Ошибка при выполнении запроса findMaxSalaryWorker");
        }

        System.out.println();

        // Запрос на поиск работников с экстремальными днями рождения
        List<ExtremeBirthdayWorker> extremeBirthdays = queryService.findExtremeBirthdays();
        if (extremeBirthdays != null) {
            System.out.println("Работники с экстремальными днями рождения:");
            for (ExtremeBirthdayWorker worker : extremeBirthdays) {
                System.out.println("Тип: " + worker.getType() + ", Имя: " + worker.getName() + ", День рождения: " + worker.getBirthday());
            }
        } else {
            System.out.println("Ошибка при выполнении запроса findExtremeBirthdays");
        }

        System.out.println();

        // Запрос на расчет стоимости проектов
        List<ProjectCost> projectCosts = queryService.calculateProjectCost();
        if (projectCosts != null) {
            System.out.println("Стоимость проектов:");
            for (ProjectCost project : projectCosts) {
                System.out.println("ID проекта: " + project.getProjectId() + ", Стоимость проекта: " + project.getProjectCost());
            }
        } else {
            System.out.println("Ошибка при выполнении запроса calculateProjectCost");
        }
    }
}
