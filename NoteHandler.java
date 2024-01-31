import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;


public class NoteHandler {
    private List<Note> notes;
    private final String file_name = "notes.txt";
    private int maxId;

    public NoteHandler() {
        notes = new ArrayList<>();
        loadNotesFromFile();
        maxId = notes.size();
    }

    public List<Note> getNotes() {
      return this.notes;
  }

  public int getMaxId(){
    return maxId;
  }


    private void loadNotesFromFile() {
      try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
          String line;
          while ((line = reader.readLine()) != null) {
              String[] noteParts = line.split("\\|\\|");
              if (noteParts.length == 3) {
                  int id = Integer.parseInt(noteParts[0]); // Parse the ID
                  String title = noteParts[1];
                  String content = noteParts[2];
                  notes.add(new Note(id, title, content)); // Use the class member 'notes'
              }
          }
      } catch (IOException e) {
          System.out.println("Error reading from file: " + e.getMessage());
      } catch (NumberFormatException e) {
          System.out.println("Error parsing ID: " + e.getMessage());
      }
  }


    public void addNote(Note note) {
        note.setId(++maxId);
        notes.add(note);
    }

  public void deleteNote(int noteIdToDelete) {
      notes.removeIf(note -> note.getId() == noteIdToDelete);
  }

  public void saveNotesToFile() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, false))) {
        for (Note note : notes) {
            writer.write(note.getId() + "||" + note.getTitle() + "||" + note.getContent());
            writer.newLine();
        }
    } catch (IOException error) {
        System.out.println("Error while writing: " + error.getMessage());
    }
  }
}
