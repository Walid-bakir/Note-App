import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

public class NoteHandler{

      private final String file_name = "notes.txt";

      // add a note to the text file
      public void addNote(Note note){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, true))){
          writer.write(note.getTitle() + "||" + note.getContent());
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
                  String[] note_parts = line.split("\\|\\|");
                  if (note_parts.length == 2) {
                      notes.add(new Note(note_parts[0], note_parts[1]));
                  }
              }
          } catch (IOException e) {
              System.out.println("Error reading from file: " + e.getMessage());
          }
          return notes;
      }

      // Display all notes
      public void displayNotes(){
        int count = 0; // to know if we have no notes
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))){
          String line;
          while ((line = reader.readLine()) != null){
            String[] note_parts = line.split(("\\|\\|"));

            if (note_parts.length ==2){
              count ++;
              Note current_note = new Note(note_parts[0], note_parts[1]);
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

      // Empty the note file
      public void deleteAllNotes(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name, false))){
          writer.write(""); // override everything with empty string because we used false
        } catch (IOException error){
          System.out.println(error.getMessage());
        }
      }

}
