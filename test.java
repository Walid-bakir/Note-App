import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        NoteHandler play = new NoteHandler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions: \n1. Add Note \n2. Delete Note \n3. Display Notes \n4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add Note
                    scanner.nextLine(); // Consume the remaining newline
                    System.out.print("Enter Note Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Note Content: ");
                    String content = scanner.nextLine();
                    int id = play.getMaxId(); // Assuming getNextId() method gives the next available ID
                    play.addNote(new Note(id, title, content));
                    break;

                case 2: // Delete Note
                    System.out.print("Enter Note ID to delete: ");
                    int noteId = scanner.nextInt();
                    play.deleteNote(noteId);
                    break;

                case 3: // Display Notes
                    play.displayNotes();
                    break;

                case 4: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
