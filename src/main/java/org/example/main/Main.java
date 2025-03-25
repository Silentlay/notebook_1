package org.example.main;

import org.example.model.Notebook;
import org.example.presenter.NotebookPresenter;
import org.example.view.ConsoleNotebookView;
import org.example.view.NotebookView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Notebook model = new Notebook();
        NotebookView view = new ConsoleNotebookView();
        NotebookPresenter presenter = new NotebookPresenter(model, view);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Note");
            System.out.println("2. Show Notes for Day");
            System.out.println("3. Show Notes for Week");
            System.out.println("4. Save Notes");
            System.out.println("5. Load Notes");
            System.out.println("6. Delete Note");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    presenter.addNote();
                    break;
                case 2:
                    presenter.showNotesForDay();
                    break;
                case 3:
                    presenter.showNotesForWeek();
                    break;
                case 4:
                    presenter.saveNotes();
                    break;
                case 5:
                    presenter.loadNotes();
                    break;
                case 6:
                    presenter.deleteNote(); // Вызов метода для удаления заметки
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
