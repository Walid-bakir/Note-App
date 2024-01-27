public class test{
  public static void main(String[] args) {
        NoteHandler play = new NoteHandler();

        Note note1 = new Note("Note 1", "Call Mom!");
        Note note2 = new Note("Note 2", "Go to the supermarket!");
        Note note3 = new Note("Note 3", "Revise a bit ot the holy Quran!");


        play.displayNotes();

        // add a note than Display
        play.addNote(note1);
        play.addNote(note2);

        //
        play.displayNotes();

        //
        System.out.println("Adding note 3: ");
        play.addNote(note3);

        System.out.println("Display after addition: ");
        play.displayNotes();

       // Empty the text file after the testing. We will not keep this forever.
       play.deleteAllNotes();
    }
}
