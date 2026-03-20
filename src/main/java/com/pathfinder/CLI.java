package com.pathfinder;

import com.pathfinder.service.RecommendationService;
import com.pathfinder.data.DataStore;
import com.pathfinder.model.*;

import java.util.Scanner;

public class CLI {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        RecommendationService service = new RecommendationService();

        while (true) {
            System.out.println("\n=== PATHFINDER AI ===");
            System.out.println("1. Ver skills");
            System.out.println("2. Agregar skill");
            System.out.println("3. Ver recomendaciones");
            System.out.println("4. Salir");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                var dev = DataStore.developers.get(0);
                System.out.println("Skills:");
                dev.skills.forEach(s -> System.out.println("- " + s.name));
            }

            if (option == 2) {
                System.out.print("Nueva skill: ");
                String skill = scanner.nextLine();

                var dev = DataStore.developers.get(0);
                dev.skills.add(new Skill(skill));

                System.out.println("Skill agregada.");
            }

            if (option == 3) {
                var recs = service.recommendNextSkills(1L);
                System.out.println("Recomendaciones:");
                recs.forEach(System.out::println);
            }

            if (option == 4) {
                break;
            }
        }
    }
}