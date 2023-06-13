import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEdit implements ActionListener {
    //declaring properties of textEditor
    JFrame frame;  //we are using Jfram class here
    JMenuBar menuBar;
    //Initialize text area


    JMenu file, edit;
    //File Menu items
    JMenuItem newFile, openFile, saveFile;
    //Edit Menu items
    JMenuItem cut, copy, paste , selectAll, close;
    //text area
    JTextArea textArea;
    TextEdit(){
        //Initialize a frame
        frame = new JFrame();

        //initialize a menubar
        menuBar = new JMenuBar();

        //initialize text area
        textArea = new JTextArea();
        //initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        //Initialize file menu item
        newFile = new JMenuItem("new Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("save file");
        //add action Listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // add menu items to file name
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Initialize edit menu items
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("select All");
        close = new JMenuItem("Close");

        //add action listener to edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);
        //set menubar to frame
        frame.setJMenuBar(menuBar);

        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        //add text area to the panel
        panel.add(textArea, BorderLayout.CENTER);
        //Create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add Scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        //set Dimensions of frame
        frame.setBounds(100,  100, 400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true); //making as not hidden
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //we need to tell the action listener class
        //where action listener occur
        //add action listener to file menu
        if(actionEvent.getSource() == cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            //perform copy operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            //perform copy operation

            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            //perform close editor operation
            System.exit(0 );
        }
        if(actionEvent.getSource() == openFile){
            //Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on Open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                     //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output= "";
                    //Read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    //Set the output String to text area
                    textArea.setText(output);
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource() == saveFile){
            //initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file Writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEdit textEdit = new TextEdit();
        }
        }
    public static void main(String[] args) {
        TextEdit textEdit = new TextEdit();

    }
}