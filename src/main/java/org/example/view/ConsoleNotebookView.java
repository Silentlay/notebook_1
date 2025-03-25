package org.example.view;

import org.example.model.Note;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleNotebookView implements NotebookView {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void showNotes(List<Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println(i + ". " + notes.get(i));
            }
        }
    }


    @Override
    public void showNotes(String notes) {
        System.out.println(notes);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public LocalDateTime getDateTimeInput() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        while (true) {
            try {
                System.out.println("Enter date and time (yyyy-MM-dd'T'HH:mm):");
                String input = scanner.nextLine();
                return LocalDateTime.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("Invalid format! Please use yyyy-MM-dd'T'HH:mm.");
            }
        }
    }

    @Override
    public String getDescriptionInput() {
        System.out.println("Enter note description:");
        return scanner.nextLine();
    }

    @Override
    public String getFileNameInput() {
        System.out.println("Enter file name:");
        return scanner.nextLine();
    }

    @Override
    public int getIndexInput() {
        System.out.println("Enter the index of the note to delete:");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine()); // Парсим индекс из ввода
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
}
