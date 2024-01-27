import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class NoteHandler{

      private final String file_name = "notes.txt";
      private int maxId;

      public NoteHandler() {
        initializeMaxId();
    }

      public void initializeMaxId() {
        List<Note> allNotes = getAllNotes();
        for (Note note : allNotes) {
            if (note.getId() > maxId) {
                maxId = note.getId();
            }
        }
    }


      public int getMaxId(){
        return maxId;
      }

      // add a note to the text file
      public void addNote(Note note){
        note.setId(++maxId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, true))){
          writer.write(note.getId() + "||" + note.getTitle() + "||" + note.getContent());
          writer.newLine();
        }catch (IOException error){
          System.out.println("Error while writing: " + error.getMessage());
        }
      }

      // Retrieve all notes from the text file
      public List<Note> getAllNotes() {
          List<Note> notes = new ArrayList<>();
          try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
              String line;
              while ((line = reader.readLine()) != null) {
                  String[] noteParts = line.split("\\|\\|");
                  if (noteParts.length == 3) {
                      int id = Integer.parseInt(noteParts[0]); // Parse the ID
                      String title = noteParts[1];
                      String content = noteParts[2];
                      notes.add(new Note(id, title, content));
                  }
              }
          } catch (IOException e) {
              System.out.println("Error reading from file: " + e.getMessage());
          } catch (NumberFormatException e) {
              System.out.println("Error parsing ID: " + e.getMessage());
          }
          return notes;
      }


      // Display all notes
      public void displayNotes(){
        int count = 0; // to know if we have no notes
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))){
          String line;
          while ((line = reader.readLine()) != null){
            count++;
            String[] noteParts = line.split("\\|\\|");
            if (noteParts.length == 3) {

              int id = Integer.parseInt(noteParts[0]); // Parse the ID
              String title = noteParts[1];
              String content = noteParts[2];
              Note current_note = new Note(id, title, content);
              System.out.println(current_note);
            }
          }
        } catch (IOException error){
          System.out.println("Error while reading: " + error.getMessage());
        }

        if (count == 0){
          System.out.println("No notes saved!");
        }
      }

      // delete a certain note
      public void deleteNote(int noteIdToDelete){

        List<String> remaining_notes = new ArrayList<>();
        int currentId;
        boolean isFound = false;

        // Now go through the file to identify the note to be deleted then update the rest
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))){
          String line;
          while ((line = reader.readLine()) != null){
            currentId = Integer.parseInt(line.split("\\|\\|")[0]);
            if (currentId == noteIdToDelete){
              isFound = true;
              continue;
            }

            if (isFound){
              line = (currentId - 1) + line.substring(line.indexOf("||"));
            }

            remaining_notes.add(line);
          }
        } catch (IOException error){
          System.out.println("Error while reading: " + error.getMessage());
        }

        if (isFound) {
        // Rewrite the file with updated content
          try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, false))) {
              for (String contentLine : remaining_notes) {
                  writer.write(contentLine);
                  writer.newLine();
              }
          } catch (IOException e) {
              System.out.println("Error writing to file: " + e.getMessage());
          }
        } else {
            System.out.println("Note with ID " + noteIdToDelete + " not found.");
        }
      }

      // Empty the note file
      public void deleteAllNotes(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, false))){
          writer.write(""); // override everything with empty string because we used false
        } catch (IOException error){
          System.out.println(error.getMessage());
        }
      }

}
