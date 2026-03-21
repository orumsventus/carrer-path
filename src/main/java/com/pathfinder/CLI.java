package com.pathfinder;

import com.pathfinder.service.RecommendationService;
import com.pathfinder.data.DataStore;
import com.pathfinder.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        RecommendationService service = new RecommendationService();
        Developer currentDev = DataStore.developers.get(0);

        while (true) {
            System.out.println("\n==============================");
            System.out.println("👤 Developer actual: " + currentDev.name);
            System.out.println("==============================");
            System.out.println("\n=== PATHFINDER AI ===");
            System.out.println("1. Ver mis skills");
            System.out.println("2. Aprender nueva skill");
            System.out.println("3. Ver recomendaciones");
            System.out.println("4. Ver mejor camino a vacante");
            System.out.println("5. Cambiar developer");
            System.out.println("6. Crear developer");
            System.out.println("0. Salir");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("\n📚 Tus skills:");
                currentDev.skills.forEach(s -> System.out.println("- " + s.name));
            }

            if (option == 2) {
                System.out.print("\nNueva skill: ");
                String skill = scanner.nextLine();

                currentDev.skills.add(new Skill(skill));
                System.out.println("✅ Skill agregada.");
            }

            if (option == 3) {
                var recs = service.recommendNextSkills(currentDev.id);

                System.out.println("\n🧠 Recomendaciones:");
                recs.forEach(r -> System.out.println("→ " + r));
            }

            if (option == 4) {

                System.out.println("\n🎯 Vacantes disponibles:");
                for (int i = 0; i < DataStore.jobs.size(); i++) {
                    System.out.println(i + ". " + DataStore.jobs.get(i).name);
                }

                int jobIndex = scanner.nextInt();
                scanner.nextLine();

                Job job = DataStore.jobs.get(jobIndex);

                List<String> path = service.getBestPath(currentDev, job);
                double prob = service.calculateProbability(path);

                System.out.println("\n🧠 Mejor camino:");

                for (int i = 0; i < path.size(); i++) {
                    if (i > 0) System.out.print(" → ");
                    System.out.print(path.get(i));
                }

                System.out.println("\n📊 Probabilidad: " + (int)(prob * 100) + "%");
            }

            if (option == 5) {

                System.out.println("\n👥 Developers disponibles:");

                for (int i = 0; i < DataStore.developers.size(); i++) {
                    System.out.println(i + ". " + DataStore.developers.get(i).name);
                }

                int index = scanner.nextInt();
                scanner.nextLine();

                currentDev = DataStore.developers.get(index);

                System.out.println("✅ Ahora eres: " + currentDev.name);
            }

            if (option == 6) {

                System.out.print("\nNombre del nuevo developer: ");
                String name = scanner.nextLine();

                Long id = (long) (DataStore.developers.size() + 1);

                Developer newDev = new Developer(id, name, new ArrayList<>());
                DataStore.developers.add(newDev);

                System.out.println("✅ Developer creado: " + name);
            }

            if (option == 0) {
                break;
            }
        }
    }
}