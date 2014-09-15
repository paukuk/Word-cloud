/*
 WORD CLOUD
 
 Word cloud is a basic visual representation composed of words used in particular text, 
 in which the size of each word indicates its frequency. It has been programmed as an 
 JApplet which is being appended to *.php file.
 */

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.net.URL;

public class WCURL extends JApplet {
	
		
	public class display extends JPanel{
		
		public void go() {
			
			String line = null;
			
			try {
				// Reads text file;  
				URL textURL = new URL (getCodeBase(), "http://localhost/WCURL/MyText.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(textURL.openStream()));
				
				while ( (line = reader.readLine() ) != null){
					// Invokes countWords(String s) method;
					countWords(line);
				}
				reader.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		/* A method countWords(String s) is used to count total words,
		   to count unique words, compare unique words with chosen
		   numbers in order to make more common words in bigger fonts 
		   and to create labels of unique words; 
		*/
		public Map<String, Integer> countWords(String s) {  

			// Using HashMap in order to get sets of words+number_of_times_word_was_used;
            Map<String, Integer> count = new HashMap<String, Integer>();  
            
            // Using LinkedHashSet in order to get unique words; 
            HashSet<String> unique = new LinkedHashSet<String>();  
            
            JPanel listPane = new JPanel();
            listPane.setPreferredSize(new Dimension(500, 300));                   
            listPane.setLayout(new FlowLayout());
            
         // ---> FONTS DEFINED HERE:
            Font bigFont = new Font("serif", Font.BOLD, 28);
            Font smallFont = new Font("serif", Font.PLAIN, 14);
            
            Container container;
            container = getContentPane();
            
            // Split string into words and assign to String strs[] array;
            String[] strs = s.split(" ");
            
            // Overwrite strs[] array to a ArrayList list in order to get
            // easier data sorting functionality;
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs));
          
            boolean b = true;
           
           // Run through ArrayList list and assign unique values to unique 
           // LinkedHashSet;
           for (Iterator iterator = list.iterator(); iterator.hasNext();){  
                String value = (String)iterator.next();  
                          
                if(b==unique.add(value)){
                    unique.add(value);
                }  
            }  
            
           // Run through all words, count how many each unique word is used
           // and put data into HashMap count;
            for (String str: strs) {  
                if (count.containsKey(str)) {  
                    count.put(str, count.get(str) + 1);  
                } else {  
                    count.put(str, 1);  
                }
         
            }
            
            // Overwrite unique LinkedHashSet to newly created String strArray[];
            // which will be used for assigning labels and setting font sizes;
            String[] strArray = new String[unique.size()];
            unique.toArray(strArray);
              
            JLabel[] lotOfLabels = new JLabel[strArray.length];
                      
            for (int i=0; i<strArray.length; i++){  
                lotOfLabels[i] = new JLabel(strArray[i]);
                
                // int n is the number representing the amount of times the unique
                // word is used in a file/text;
                int n = count.get(strArray[i]);
                if (n > 11){ 
      // ---> FONTS ARE BEING ASSIGNED HERE:          	
                			lotOfLabels[i].setFont(bigFont);                			
                            n = 0;
                }
                else lotOfLabels[i].setFont(smallFont);
                listPane.add(lotOfLabels[i]);
            }
            
            container.setLayout(new FlowLayout());
            container.add(listPane);
                      
            return count;  
        }
	}
	 
	public void init(){
		
		display d = new display();
		d.go();
		getContentPane().add(d);
		setSize(600, 270);
				
	}
	public void start(){}
	public void stop(){}
	public void destroy(){}
	
	public static void main (String[] args) {
		
		JFrame frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        WCURL cloud = new WCURL();
        cloud.init();        
        
        frame.setSize(1100,600);         
        frame.setVisible(true);         
	}
}
