import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;


public class NoteApp {
    private JFrame frame;
    private NoteHandler noteHandler;

    public NoteApp() {
        noteHandler = new NoteHandler();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Creating the main frame
        frame = new JFrame("NoteApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Welcome phrase
        JLabel welcomeLabel = new JLabel("Welcome to NoteApp");
        frame.add(welcomeLabel);

        // Add Note button
        JButton addNoteButton = new JButton("Add Note");
        addNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddNoteDialog();
            }
        });
        frame.add(addNoteButton);

        // View Saved Notes button
        JButton viewNotesButton = new JButton("View Saved Notes");
        viewNotesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSavedNotes();
            }
        });
        frame.add(viewNotesButton);

        // Layout
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        //
        frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            noteHandler.saveNotesToFile();
        }
        });

    }


    // Add note dialog
    private void showAddNoteDialog() {
    JDialog dialog = new JDialog(frame, "Add Note", true);
    dialog.setLayout(new FlowLayout());
    dialog.setSize(300, 200);

    JTextField titleField = new JTextField(20);
    JTextArea contentArea = new JTextArea(5, 20);
    JButton saveButton = new JButton("Save");

    saveButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Note note = new Note(noteHandler.getMaxId() + 1, titleField.getText(), contentArea.getText());
            noteHandler.addNote(note);
            dialog.dispose();
        }
    });

    dialog.add(new JLabel("Title:"));
    dialog.add(titleField);
    dialog.add(new JLabel("Content:"));
    dialog.add(contentArea);
    dialog.add(saveButton);
    dialog.setVisible(true);
  }

  // Show Saved Notes dialog
  private void showSavedNotes() {
    JDialog dialog = new JDialog(frame, "Saved Notes", true);
    dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
    dialog.setSize(400, 300);

    for (Note note : noteHandler.getNotes()) {
        JButton noteButton = new JButton(note.getTitle());
        noteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement note editing or deletion
            }
        });
        dialog.add(noteButton);
    }

    dialog.setVisible(true);
  }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NoteApp();
            }
        });
    }
}
