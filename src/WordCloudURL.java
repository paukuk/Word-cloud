import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.Label;
import java.applet.*;
import java.applet.Applet;
import java.net.URL;


public class WordCloudURL extends JApplet {
	
	static final int WIDTH = 400;
	static final int HEIGHT = 600;
		
	public class display extends JPanel{
		
	/*	public display(){
			
		//	Container container;
		
		} */
		
	/*	public void go() {
			
			try {
				File myFile = new File("MyText.txt");
				FileReader fileReader = new FileReader(myFile);
				BufferedReader reader = new BufferedReader(fileReader);
				String line = null;
				while ((line = reader.readLine()) != null) {
				countWords(line);			
				}
				reader.close();
			
			} catch(Exception ex) {
				ex.printStackTrace();
			}		
		} */
		
		public void go() {
			
			String line = null;
			
			try {
				
				URL textURL = new URL (getCodeBase(), "MyText.txt");
				BufferedReader reader = new BufferedReader( new InputStreamReader(textURL.openStream()));
				
				while ( (line = reader.readLine() ) != null){
					
					countWords(line);
					System.out.println(line);
				}
				reader.close();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		public Map<String, Integer> countWords(String s) {
			
			Map<String, Integer> count = new HashMap<String, Integer>();
			HashSet<String> unique = new LinkedHashSet<String>();
			Container container;
			container = getContentPane();
			
			boolean b = true;
			String[] strs = s.split(" ");
			
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs));
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();){
				String value = (String)iterator.next();
						
				if(b==unique.add(value)){
					unique.add(value);
				}
			}
			
			Object[] objArray = unique.toArray();
			
			for (String str: strs) {
				if (count.containsKey(str)) {
					count.put(str, count.get(str) + 1);
				} else {
					count.put(str, 1);
				}	
			}
			
			JLabel[] lotOfLabels = new JLabel[strs.length];	
					
			for (int i=0; i<objArray.length; i++){
				lotOfLabels[i] = new JLabel((String)objArray[i]);
				container.add(lotOfLabels[i]);
			}
			
			for (int j=0; j<objArray.length; j++){
				Integer n = count.get(objArray[j]);
				if (n > 11){
							Font bigFont = new Font("serif", Font.BOLD, 28);	
							lotOfLabels[j].setFont(bigFont);
				}
				
				}
					
		//	getContentPane().add(BorderLayout.CENTER, panel);
			
			return count;
		}
	}
	
	public void init(){
		
		display d = new display();
		d.go();
        getContentPane().add(BorderLayout.CENTER, d);
				
	}
	public void start(){}
	public void stop(){}
	public void destroy(){}
	
	public static void main (String[] args) {
		
		JFrame frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        WordCloudURL cloud = new WordCloudURL();
        cloud.init();               
        
        
        frame.setSize(WIDTH,HEIGHT);         
        frame.setVisible(true);         
	}
}
