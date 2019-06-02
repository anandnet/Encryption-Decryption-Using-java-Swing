import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.border.Border;


//Home class
class Home implements ActionListener
{
    JFrame frame;
    JLabel l1;
    JButton btn2,btn1;
    Color homeBg= Color.decode("#21618C");
    Color l1Fg= Color.decode("#D5DBDB");

    Home()
    {
        frame=new JFrame("Home");
        Container c= frame.getContentPane();
        c.setBackground(homeBg);

        Font f1=new Font("Lucida Handwriting",Font.BOLD,35);
        Font f2=new Font("Lucida Handwriting",Font.BOLD,20);

        l1=new JLabel("Welcome");
        l1.setBounds(170,50,300,30);
        l1.setFont(f1);
        l1.setForeground(l1Fg);
        frame.add(l1);

        btn1=new JButton("Encryption");
        btn1.addActionListener(this);
        btn1.setBounds(155,210,190,50);
        btn1.setFont(f2);
        frame.add(btn1);

        btn2=new JButton("Decryption");
        btn2.addActionListener(this);
        btn2.setBounds(155,290,190,50);
        btn2.setFont(f2);
        frame.add(btn2);
        
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==btn1)
        {
            frame.setVisible(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new Encryption();
        }
        if(e.getSource()==btn2)
        {
            frame.setVisible(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new Decryption();
        }

    }
}




class Encryption implements ActionListener,ItemListener
{
    JFrame frame;
    JButton homeBtn,browseBtn,encryptBtn,saveBtn,viewBtn; 
    JLabel titleLabel,textInputLabel,fileNameLabel,consoleLabel,passwordInputLabel;
    JTextArea console,textInputByFile,textInput;
    JTextField passwordInput;
    JComboBox<String> dropdown;
    JScrollPane ti,tiByFile;
    int accessKey=-1;
    String consoletext="";
    String fileName="";
    String encryptedText;

    Color consoleBg= Color.decode("#010034");
    Color titleBorderBg= Color.decode("#AED6F1");
    Color titleFg= Color.decode("#D5DBDB");
    Color encBg= Color.decode("#34495E");
    Color otherLableFg= Color.decode("#AED6F1");

    Font titleFont=new Font("Comic Sans MS",Font.BOLD,28);
    Font labelFont=new Font("Trebuchet MS",Font.BOLD,20);
    Font textInputFont=new Font("Trebuchet MS",Font.BOLD,15);
    ImageIcon water = new ImageIcon("home.png");
    Border border = BorderFactory.createLineBorder(titleBorderBg,2);

    Encryption()
    {
        frame=new JFrame("Encryption/Decryption");
        Container c= frame.getContentPane();
        c.setBackground(encBg);

        homeBtn=new JButton(water);
        homeBtn.setBounds(0,0,50,50);
        homeBtn.addActionListener(this);
        frame.add(homeBtn);

        titleLabel=new JLabel("                                                                 Encryption");
        titleLabel.setBounds(50,0,1316,50);
        titleLabel.setFont(titleFont);
        titleLabel.setBorder(border);
        titleLabel.setForeground(titleFg);
        frame.add(titleLabel);


        //Dropdown
        String list[]={"Select method","By Text","By File"};
        dropdown=new JComboBox<>(list);
        dropdown.setBounds(40,70,200,30);
        dropdown.addItemListener(this);
        frame.add(dropdown); 


        //Console Label
        consoleLabel=new JLabel("Console");
        consoleLabel.setBounds(800,70,300,40);
        consoleLabel.setFont(labelFont);
        consoleLabel.setForeground(otherLableFg);
        frame.add(consoleLabel);
        //Console
        console=new JTextArea();
        console.setBackground(consoleBg);
        console.setForeground(Color.GREEN);
        console.setEditable(false);
        console.setLineWrap(true);
        console.setWrapStyleWord(true);
        console.setBorder(border);
        JScrollPane scroll = new JScrollPane(console);
        scroll.setBounds(800,110,500,510);
        console.setFont(new Font("Consolas",Font.BOLD,15));
        frame.add(scroll);


        //---------------if By File method Selcted-------------------
        //FileBrowserBtn
        browseBtn=new JButton("Browse");
        browseBtn.addActionListener(this);
        browseBtn.setBounds(270,70,180,40);
        frame.add(browseBtn);
        browseBtn.setVisible(false);

        //label
        fileNameLabel=new JLabel();
        fileNameLabel.setBounds(40,130,300,40);
        fileNameLabel.setFont(labelFont);
        fileNameLabel.setForeground(otherLableFg);
        frame.add(fileNameLabel);
        fileNameLabel.setVisible(false);

        textInputByFile=new JTextArea();
        
        textInputByFile.setFont(textInputFont);
        textInputByFile.setLineWrap(true);
        textInputByFile.setWrapStyleWord(true);
        textInputByFile.setEditable(false);
        textInputByFile.setBorder(border);
        tiByFile = new JScrollPane(textInputByFile);
        tiByFile.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tiByFile.setBounds(40,170,400,400);
        frame.add(tiByFile);
        tiByFile.setVisible(false);



        //----------------if By text method Selected----------------------
        //label
        textInputLabel=new JLabel("Enter Text Here:");
        textInputLabel.setBounds(40,130,300,40);
        textInputLabel.setFont(labelFont);
        textInputLabel.setForeground(otherLableFg);
        frame.add(textInputLabel);
        textInputLabel.setVisible(false);

        //Input Text Area
        textInput=new JTextArea();
        textInput.setFont(textInputFont);
        textInput.setLineWrap(true);
        textInput.setWrapStyleWord(true);
        textInput.setBorder(border);
        ti = new JScrollPane(textInput);
        ti.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ti.setBounds(40,170,400,400);
        frame.add(ti);
        ti.setVisible(false);


        //key or Password input
        passwordInputLabel=new JLabel("Password:");
        passwordInputLabel.setBounds(70,600,150,40);
        passwordInputLabel.setFont(labelFont);
        passwordInputLabel.setForeground(otherLableFg);
        frame.add(passwordInputLabel);
        passwordInputLabel.setVisible(false);

        passwordInput=new JTextField();
        passwordInput.setFont(textInputFont);
        passwordInput.setBounds(200,600,200,40);
        frame.add(passwordInput);
        passwordInput.setVisible(false);



        encryptBtn=new JButton("Encrypt");
        encryptBtn.setBounds(540,400,150,40);
        encryptBtn.addActionListener(this);
        frame.add(encryptBtn);
        encryptBtn.setVisible(false);

        viewBtn=new JButton("View");
        viewBtn.setBounds(540,460,150,40);
        viewBtn.addActionListener(this);
        frame.add(viewBtn);
        viewBtn.setVisible(false);


        saveBtn=new JButton("Save");
        saveBtn.setBounds(980,630,150,40);
        saveBtn.addActionListener(this);
        frame.add(saveBtn);
        saveBtn.setVisible(false);



        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


    //itemStateChanged for dropdown override this method from interface ItemListener
    public void itemStateChanged(ItemEvent e)
    {

        int state = e.getStateChange();
        if(e.getItem().equals("By Text") && state==1)
        {
            accessKey=0;
            //-------initial state of contents--------
            saveBtn.setVisible(false);
            passwordInput.setText("");
            browseBtn.setVisible(false);
            tiByFile.setVisible(false);
            viewBtn.setVisible(false);
            fileNameLabel.setVisible(false);
            textInputLabel.setVisible(true);
            ti.setVisible(true);
            passwordInput.setVisible(true);
            passwordInputLabel.setVisible(true);
            encryptBtn.setVisible(true);
            //----------------------------------------

            consoletext=consoletext+"System: 'By Text' Method Selected\n";
            console.setText(consoletext);

           // System.out.println(consoletext);
        }


        if(e.getItem().equals("By File") && state==1)
        {
            accessKey=1;
            //-------initial state of contents--------
            textInput.setText("");
            passwordInput.setText("");
            textInputLabel.setVisible(false);
            saveBtn.setVisible(false);
            encryptBtn.setVisible(false);
            viewBtn.setVisible(false);
            fileNameLabel.setVisible(false);
            ti.setVisible(false);
            passwordInput.setVisible(false);
            passwordInputLabel.setVisible(false);
            tiByFile.setVisible(false);
            browseBtn.setVisible(true);
            //----------------------------------------

            consoletext+="System: 'By File' Method Selected\n";
            console.setText(consoletext);

        }
        
    }



    public void actionPerformed(ActionEvent e)
    {

        //When Browse Button selected
        if(e.getSource()==browseBtn)
        {
            JFileChooser fc=new JFileChooser();
            int r=fc.showOpenDialog(null);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                passwordInputLabel.setVisible(true);
                passwordInput.setVisible(true);
                tiByFile.setVisible(true);
                encryptBtn.setVisible(true);
                String text=this.getFileContent(fc.getSelectedFile().getAbsolutePath()); //Calling function getFileContent(path)
                fileName= fc.getSelectedFile().getName();
                consoletext+="System: "+fileName+" opened\n";
                fileNameLabel.setText(fileName);
                fileNameLabel.setVisible(true);
                console.setText(consoletext);
                textInputByFile.setText(text);
            }
        }



        //When Encrypt Button Clicked
        if(e.getSource()==encryptBtn)
        {
            encryptBtn.setVisible(true);
            if(accessKey==1)
            {
                if(passwordInput.getText().equals(""))
                {
                    consoletext+="System: Please Enter Password\n";
                    console.setText(consoletext);
                    saveBtn.setVisible(false);
                    viewBtn.setVisible(false);
                }
                else
                {
                    tiByFile.setVisible(true);
                    Vigenere v2=new Vigenere();
                    encryptedText=v2.encrypt(textInputByFile.getText(),passwordInput.getText());
                    if(encryptedText.equals(""))
                    {
                        consoletext+="System: Password can't be longer than text\n";
                        console.setText(consoletext);
                        viewBtn.setVisible(false);
                        saveBtn.setVisible(false);
                    }
                    else
                    {
                        viewBtn.setVisible(true);
                        saveBtn.setVisible(true);
                        consoletext+="System: File is encrypted\n";
                        console.setText(consoletext);
                        consoletext+="\n\nPassword/key:"+passwordInput.getText();
                        //consoletext+="\nEncryptedText:"+encryptedText;
                        consoletext+="\n\nText     : "+textInputByFile.getText();
                        consoletext+="\nBig key: "+v2.bigKey;
                        consoletext+="\n\nVigenere table:"+v2.vigenereTable;
                        consoletext+=v2.shortInfo;
                        consoletext+=v2.exploreEnc;
                        v2.exploreEnc="";
                        console.setText(consoletext);
                    }
                }
                
            }
            else
            {
                if(textInput.getText().equals(""))
                {
                    consoletext+="System: Please Enter Text\n";
                    console.setText(consoletext);
                    saveBtn.setVisible(false);
                    viewBtn.setVisible(false);
                }
                else if(passwordInput.getText().equals(""))
                {
                    consoletext+="System: Please Enter Password\n";
                    console.setText(consoletext);
                    saveBtn.setVisible(false);
                    viewBtn.setVisible(false);
                }
                else
                {
                    Vigenere v1=new Vigenere();
                    encryptedText=v1.encrypt(textInput.getText(),passwordInput.getText());
                    if(encryptedText.equals(""))
                    {
                        consoletext+="System: Password can't be longer than text\n";
                        console.setText(consoletext);
                        viewBtn.setVisible(false);
                        saveBtn.setVisible(false);
                    }
                    else
                    {
                        saveBtn.setVisible(true);
                        viewBtn.setVisible(true);
                        consoletext+="System: File is encrypted\n";
                        console.setText(consoletext);
                        consoletext+="\n\nPassword/key:"+passwordInput.getText();
                        //consoletext+="\nEncryptedText:"+encryptedText;
                        consoletext+="\n\nText     : "+textInput.getText();
                        consoletext+="\nBig key: "+v1.bigKey;
                        consoletext+="\n\nVigenere table:"+v1.vigenereTable;
                        consoletext+=v1.shortInfo;
                        consoletext+=v1.exploreEnc;
                        v1.exploreEnc="";
                        console.setText(consoletext);

                    }
                }
            }
        }

        //When View Button Clicked
        if(e.getSource()==viewBtn)
        {
            if(accessKey==1)
            {
                console.setText(encryptedText);
            }
            else
            {
                console.setText(encryptedText);
            }
        }



        //When Save Button Clicked
        if(e.getSource()==saveBtn)
        {
            viewBtn.setVisible(true);
            saveBtn.setVisible(true);
            encryptBtn.setVisible(true);
            if(accessKey==1)
            {
                tiByFile.setVisible(true);
                SaveFileChooser s1= new SaveFileChooser(encryptedText);
                s1.save();
                if (s1.processTrue==true)
                {
                    consoletext+="System: File Saved\n";
                    console.setText(consoletext);
                }
                //System.out.println("by file");//for testing 
            }
            else
            {
                SaveFileChooser s1= new SaveFileChooser(encryptedText);
                s1.save();
                if (s1.processTrue==true)
                {
                    consoletext+="System: File Saved\n";
                    console.setText(consoletext);
                }
                //System.out.println("by text");//for testing
            }

        }


        //When Home Button Clicked
        if(e.getSource()==homeBtn)
        {
            frame.setVisible(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new Home();
        }

    }

    // Method to get File Content of from the file
    String getFileContent(String path)
    {
        String filetext="";
        try
        {
            String line;
            File file1=new File(path);
            BufferedReader br =new BufferedReader(new FileReader(file1));
            while((line=br.readLine())!=null)
            {
                filetext+=line+"\r\n";
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return filetext;

    }
    
}


class Decryption extends Encryption
{
    String decryptedText;
    Decryption()
    {
        titleLabel.setText("                                                                 Decryption");
        encryptBtn.setText("Decrypt");
    }

    public void actionPerformed(ActionEvent e)
    {

        //When Browse Button selected
        if(e.getSource()==browseBtn)
        {
            JFileChooser fc=new JFileChooser();
            int r=fc.showOpenDialog(null);
            if(r==JFileChooser.APPROVE_OPTION)
            {
                tiByFile.setVisible(true);
                encryptBtn.setVisible(true);
                passwordInputLabel.setVisible(true);
                passwordInput.setVisible(true);
                String text=this.getFileContent(fc.getSelectedFile().getAbsolutePath()); //Calling function getFileContent(path)
                fileName= fc.getSelectedFile().getName();
                consoletext+="System: "+fileName+" opened\n";
                fileNameLabel.setText(fileName);
                fileNameLabel.setVisible(true);
                console.setText(consoletext);
                textInputByFile.setText(text);
            }
        }



        //When decrypt Button Clicked
        if(e.getSource()==encryptBtn)
        {
            encryptBtn.setVisible(true);
            if(accessKey==1)
            {
                if(passwordInput.getText().equals(""))
                {
                    consoletext+="System: Please Enter Password\n";
                    console.setText(consoletext);
                    saveBtn.setVisible(false);
                    viewBtn.setVisible(false);
                }
                else
                {
                    tiByFile.setVisible(true);
                    Vigenere v2=new Vigenere();
                    decryptedText=v2.decrypt(textInputByFile.getText(),passwordInput.getText());
                    if(decryptedText.equals(""))
                    {
                        consoletext+="System: Password can't be longer than text\n";
                        console.setText(consoletext);
                        viewBtn.setVisible(false);
                        saveBtn.setVisible(false);
                    }
                    else
                    {
                        viewBtn.setVisible(true);
                        saveBtn.setVisible(true);
                        consoletext+="System: File is decrypted\n";
                        console.setText(consoletext);
                        consoletext+="\n\nPassword/key: "+passwordInput.getText();
                        //consoletext+="\nDecryptedText:"+deccryptedText;
                        consoletext+="\n\nEncrypted Text: "+textInputByFile.getText();
                        consoletext+="\nBig key: "+v2.bigKey;
                        consoletext+="\n\nVigenere table: "+v2.vigenereTable;
                        consoletext+=v2.shortInfo;
                        consoletext+=v2.exploreDec;
                        v2.exploreDec="";
                        console.setText(consoletext);
                    }
                }
                
            }
            else
            {
                if(textInput.getText().equals(""))
                {
                    consoletext+="System: Please Enter Text\n";
                    console.setText(consoletext);
                    saveBtn.setVisible(false);
                    viewBtn.setVisible(false);
                }
                else if(passwordInput.getText().equals(""))
                {
                    consoletext+="System: Please Enter Password\n";
                    console.setText(consoletext);
                    saveBtn.setVisible(false);
                    viewBtn.setVisible(false);
                }
                else
                {
                    Vigenere v1=new Vigenere();
                    decryptedText=v1.decrypt(textInput.getText(),passwordInput.getText());
                    if(decryptedText.equals(""))
                    {
                        consoletext+="System: Password can't be longer than text\n";
                        console.setText(consoletext);
                        viewBtn.setVisible(false);
                        saveBtn.setVisible(false);
                    }
                    else
                    {
                        saveBtn.setVisible(true);
                        viewBtn.setVisible(true);
                        consoletext+="System: File is decrypted\n";
                        console.setText(consoletext);
                        consoletext+="\n\nPassword/key: "+passwordInput.getText();
                        //consoletext+="\nDecryptedText:"+dencryptedText;
                        consoletext+="\n\nEncrypted Text: "+textInput.getText();
                        consoletext+="\nBig key: "+v1.bigKey;
                        consoletext+="\n\nVigenere table: "+v1.vigenereTable;
                        consoletext+=v1.shortInfo;
                        consoletext+=v1.exploreDec;
                        v1.exploreDec="";
                        console.setText(consoletext);

                    }
                }
            }
        }

        //When View Button Clicked
        if(e.getSource()==viewBtn)
        {
            if(accessKey==1)
            {
                console.setText(decryptedText);
            }
            else
            {
                console.setText(decryptedText);
            }
        }



        //When Save Button Clicked
        if(e.getSource()==saveBtn)
        {
            viewBtn.setVisible(true);
            saveBtn.setVisible(true);
            encryptBtn.setVisible(true);
            if(accessKey==1)
            {
                tiByFile.setVisible(true);
                SaveFileChooser s1= new SaveFileChooser(decryptedText);
                s1.save();
                if (s1.processTrue==true)
                {
                    consoletext+="System: File Saved\n";
                    console.setText(consoletext);
                }
                //System.out.println("by file");//for testing 
            }
            else
            {
                SaveFileChooser s1= new SaveFileChooser(decryptedText);
                s1.save();
                if (s1.processTrue==true)
                {
                    consoletext+="System: File Saved\n";
                    console.setText(consoletext);
                }
                //System.out.println("by text");//for testing
            }

        }
        //When Home Button Clicked
        if(e.getSource()==homeBtn)
        {
            frame.setVisible(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            new Home();
        }


    }
}





//class for open save file Chooser

class SaveFileChooser
{
    String fileContent;
    boolean processTrue=false;
    SaveFileChooser(String text)
    {
        this.fileContent=text;
    }
    void save()
    {
        JFileChooser jfc=new JFileChooser();
        int r=jfc.showSaveDialog(null);
        if(r==JFileChooser.APPROVE_OPTION)
        {
            processTrue=true;
            try
            {
                File file=new File(jfc.getSelectedFile().getAbsolutePath());
                FileWriter fw=new FileWriter(file);
                fw.write(fileContent);
                fw.close();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
}


class Vigenere
{
  
  public static String lowerCase= "abcdefghijklmnopqrstuvwxyz";
  public static String upperCase= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public static String nums="0123456789";
  public static String symbs=" =.,;()/\\%[]|:&?!@^+*-_`{}<>#$";
  public static String alph= lowerCase+upperCase+symbs+nums;
  
  public static String bigKey="";
  
  //store the steps of encryption/decryption
  //public static String exploreEnc="msg * key = enciphered text\n";
  public static String exploreEnc="";
  public static String exploreDec="";
  
  //vigenere table hint
  final public static String vigenereTable= "\n\n  |ABCD....XYZ\n  ------------------\nA|ABCD....XYZ\nB|BCDE....YZA\nC|CDEF....ZAB\nD|DEFG....ABC\n.         .\n.         .\n.         .\nX|XYZA....UVW\nY|YZAB....VWX\nZ|ZABC....WXY\n\n";
  
  //short info about vigenere table
  public static String shortInfo= "The letter at the intersection of [key-row, msg-col] is the enciphered letter.\n";
  
  public static String newAlph(String ch){
  
    /*
    args:
      ch: String of length 1
      
    return:
      Row corresponding to ch from the Vigenere table
    */
    
    String new_alph= alph;
    
    if(alph.indexOf(ch) != -1){
        new_alph= alph.substring(alph.indexOf(ch))+ alph.substring(0,alph.indexOf(ch));
    }
        
    return new_alph;
  }
  
  public static String encrypt(String text, String key){

   // exploreEnc="msg * key = enciphered text\n";
  
    /*
    Method to encrypt a given text with provided key
    
    args:
      text: String to be encrypted
      key: String key to encrypt text with
    
    return:
      encrypted text using key
      
    */
  
    bigKey= genBigKey(text, key);
    String res= "";
    
    for(int i=0;i<bigKey.length();i++){
      
      String ch= Character.toString(bigKey.charAt(i));
      String txtCh= Character.toString(text.charAt(i));
      
      exploreEnc+= txtCh+" * "+ch+" = ";
      
      String new_alph= newAlph(ch);
      
        if(alph.indexOf(txtCh) != -1){
          
          String newCh= Character.toString(new_alph.charAt(alph.indexOf(txtCh)));
          res += newCh;
          
          exploreEnc+= newCh+"\n";          
        }
        else{
          res+= txtCh;
          
          exploreEnc+= txtCh+"\n";
        }
       
    }
    
    return res;
    
  }
  
  
  public static String decrypt(String text, String key){
  
    /*
    Method to decrypt a given text with provided key
    
    args:
      text: String to be decrypted
      key: String key to decrypt text with
    
    return:
      decrypted text using key
      
    */
    //exploreDec="enc * key = deciphered text\n";
  
    bigKey= genBigKey(text, key);
    
    String res= "";
    for(int i=0;i<bigKey.length();i++){
      
      String ch= Character.toString(bigKey.charAt(i));
      String txtCh= Character.toString(text.charAt(i));
      
      exploreDec+= txtCh+" * "+ch+" = ";
      
      String new_alph= newAlph(ch);
      
        if(alph.indexOf(txtCh) != -1){
          
          String newCh= Character.toString(alph.charAt(new_alph.indexOf(txtCh)));
          res += newCh;
          
          exploreDec+= newCh+"\n";
        }
        else{
          res+= txtCh;
          exploreDec+= txtCh+"\n";
        }
       
    }
    
    return res; 
  }
  
  
  public static String genBigKey(String text, String key){
    
    /*
    Method to generate a big key from a given key for a given text
    
    args:
      text: String, the big key is generated for
      key: String using which the big key is generated
    
    return:
      'key' is repeated till it reaches a length equals to the length of the given text
    */
    
    if(text.length()>=key.length())
      return repeat(text.length()/key.length(), key)+key.substring(0,text.length()%key.length());
    
    else
      return "";
    
  }
  
  public static String repeat(int count, String with){
    /*
    Method to repeat a string specified number of times
    
    args:
      count: integer, the number of times string to be repeated
      with: String to be repeated
    
    return:
      repeated string
      ex: repeat(3,'hello')--> 'hellohellohello'
      
    */
    
    return new String(new char[count]).replace("\0", with);
    
    }
  }

//Main Class
public class enc
{
    public static void main(String[] args)
    {
        Home a=new Home();
    }
}