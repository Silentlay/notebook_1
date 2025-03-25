package org.example.model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook {

    private List<Note> notes = new ArrayList<>();

    public void addNote(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public List<Note> getNotesForDay(LocalDateTime dateTime) {
        return notes.stream()
                .filter(note -> note.getDateTime().toLocalDate().isEqual(dateTime.toLocalDate())
                        && note.getDateTime().toLocalTime().equals(dateTime.toLocalTime())) // Сравниваем и время
                .sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }



    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Note note : notes) {
                writer.write(note.toString());
                writer.newLine();
                System.out.println("Saved: " + note); // Выводим сохраненные заметки
            }
        }
    }


    public void loadFromFile(String fileName) throws IOException {
        notes.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ", 2);
                LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
                String description = parts[1];
                notes.add(new Note(dateTime, description));
                System.out.println("Loaded: " + dateTime + " " + parts[1]); // Выводим загруженные заметки
            }
        }
    }


    public List<Note> getNotesForWeek(LocalDateTime dateTime) {
        LocalDateTime startOfWeek = dateTime.with(java.time.DayOfWeek.MONDAY);
        LocalDateTime endOfWeek = startOfWeek.plusDays(6);

        return notes.stream()
                .filter(note -> !note.getDateTime().isBefore(startOfWeek) && !note.getDateTime().isAfter(endOfWeek))
                .sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }

    public void deleteNote(int index){
        if (index >= 0 && index < notes.size()) {
            notes.remove(index); // Удаляем заметку по индексу
        } else {
            System.out.println("Invalid index.");
        }
    }

}
