package org.example.presenter;

import org.example.model.Note;
import org.example.model.Notebook;
import org.example.view.NotebookView;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NotebookPresenter {
    private Notebook model;
    private NotebookView view;

    public NotebookPresenter(Notebook model, NotebookView view) {
        this.model = model;
        this.view = view;
    }

    public void addNote() {
        LocalDateTime dateTime = view.getDateTimeInput();
        String description = view.getDescriptionInput();
        model.addNote(new Note(dateTime, description));
        view.showNotes("Note added.");
    }

    public void showNotesForDay() {
        LocalDateTime dateTime = view.getDateTimeInput();
        List<Note> notes = model.getNotesForDay(dateTime);
        view.showNotes(notes);
    }

    public void showNotesForWeek() {
        LocalDateTime startOfWeek = view.getDateTimeInput();
        List<Note> notes = model.getNotesForWeek(startOfWeek);
        view.showNotes(notes);
    }

    public void saveNotes() {
        String fileName = view.getFileNameInput();
        try {
            model.saveToFile(fileName);
            view.showMessage("Notes saved to " + fileName);
        } catch (IOException e) {
            view.showMessage("Failed to save notes: " + e.getMessage());
        }
    }

    public void loadNotes() {
        String fileName = view.getFileNameInput();
        try {
            model.loadFromFile(fileName);
            view.showMessage("Notes loaded from " + fileName);
            List<Note> notes = model.getNotes();
            view.showNotes(notes);
        } catch (IOException e) {
            view.showMessage("Failed to load notes: " + e.getMessage());
        }
    }

    public void deleteNote(){
        List<Note> notes = model.getNotes(); // Получаем список всех заметок
        if (notes.isEmpty()) {
            view.showMessage("No notes available to delete.");
            return;
        }

        // Показываем список заметок
        view.showNotes(notes);

        int index = view.getIndexInput(); // Запрашиваем индекс заметки для удаления
        if (index < 0 || index >= notes.size()) {
            view.showMessage("Invalid index. Please try again.");
            return;
        }

        // Удаляем заметку
        model.deleteNote(index);
        view.showMessage("Note deleted.");

        // Показываем оставшиеся заметки после удаления
        view.showNotes(model.getNotes());
    }

}
