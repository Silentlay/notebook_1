package org.example.view;

import org.example.model.Note;

import java.time.LocalDateTime;
import java.util.List;

public interface NotebookView {
    void showNotes(List<Note> notes);
    void showNotes(String notes);
    void showMessage(String message);
    LocalDateTime getDateTimeInput();
    String getDescriptionInput();
    String getFileNameInput();
    int getIndexInput();
}
