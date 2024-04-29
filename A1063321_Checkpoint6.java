import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class A1063321_Checkpoint6 {
	public static ArrayList<String> character_info = new ArrayList<String>();
	public static int amountof_player = 0;
	public static int amountof_land = 0;
	public static Character[] allcharacter;
	public static Land[] allLand;
	public static int step = 0;
	public static int round,turn;
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String protocol = "jdbc:mysql:";
	public static int[] place_number_array;
	public static int[] owner_array;
	public static int[] land_price_array;
	public static int[] tolls_array;
	public static String annotation;
	public static void main(String[] args) throws IOException{
		
		//// TODO: Announce your GUI object to make the GUI ////
		//// TODO: This time we won't give you a function to realize our demands (Please look for demands on the document).
		//// TODO: So, you can make it in anyway whatever you like. 
		
		/*for(int i = 0;i < amountof_land;i++){
			System.out.println("Land information : P_N = " + allLand[i].PLACE_NUMBER + " owner = " + allLand[i].owner + " L_P = " + allLand[i].LAND_PRICE + " toll = " + allLand[i].TOLLS);
		}*/
		startFrame newframe = new startFrame();
		newframe.setVisible(true);
		//System.out.println(allcharacter.length);
		/*for(int j =0;j < amountof_player;j++){
			System.out.println(allcharacter[j].toString());
		}*/
		/*for(String s : character_info){
			System.out.print(s + " ");
		}*/
		
		//System.out.println(amountof_player);
        
	}
	
	public static void Array_toObject(Character[] convert_Character){
		int count_array = 0;
		for(int i = 0; i < amountof_player;i++){
			convert_Character[i] = new Character(0, 0, 0, 0, "0");
			convert_Character[i].location = Integer.parseInt(character_info.get(count_array));
			count_array +=1;
			convert_Character[i].CHARACTER_NUMBER = Integer.parseInt(character_info.get(count_array));
			count_array +=1;
			convert_Character[i].money = Integer.parseInt(character_info.get(count_array));
			count_array +=1;
			convert_Character[i].status = Integer.parseInt(character_info.get(count_array));
			count_array +=1;
			convert_Character[i].IMAGE_FILENAME = character_info.get(count_array);
			count_array +=1;
		}
		
	}

	public static void LandArray_toObject(){
		for(int j = 0; j < amountof_land; j++){
			allLand[j] = new Land(place_number_array[j],owner_array[j],land_price_array[j],tolls_array[j]);
		}
	}

	public static void Object_toArray(Character[] convert_Character){
		String temp;
		character_info.clear();
		for(int j = 0;j < amountof_player;j++){			
			temp = String.valueOf(convert_Character[j].location);
			character_info.add(temp);
			temp = String.valueOf(convert_Character[j].CHARACTER_NUMBER);
			character_info.add(temp);
			temp = String.valueOf(convert_Character[j].money);
			character_info.add(temp);
			temp = String.valueOf(convert_Character[j].status);
			character_info.add(temp);
			temp = convert_Character[j].IMAGE_FILENAME;
			character_info.add(temp);
		}
		for(int i = 0; i < amountof_land; i++){
			place_number_array[i] = allLand[i].PLACE_NUMBER;
			owner_array[i] = allLand[i].owner;
			land_price_array[i] = allLand[i].LAND_PRICE;
			tolls_array[i] = allLand[i].TOLLS;
		}
	}

	private static void ConnectDatabase(){
		try{
			Class.forName(driver).newInstance();
		}catch(Exception err){
			System.err.println("Unable load the driver!");
			err.printStackTrace(System.err);
			System.exit(0);
		}
		String url = "//140.127.220.220:3306/";
		String dbName = "CHECKPOINT";
		String username = "checkpoint";
		String password = "ckppwd";
        Connection conn = null;
        try{
			conn = DriverManager.getConnection(protocol + url + dbName, username, password);
			Statement s = conn.createStatement();
			ResultSet rs = null;
			rs = s.executeQuery("SELECT * FROM LAND WHERE 1");
			int i = 0;
            while(rs.next()){
				land_price_array[i] = rs.getInt("LAND_PRICE");
                tolls_array[i] = rs.getInt("TOLLS");
				i++;
			}
			rs.close();
			conn.close();
		}catch(SQLException err){
			System.err.println("SQL error");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}

	public static void Load(String filename, String filename2) throws IOException {
		//// TODO: You should load the variables from the files. ////
		character_info.clear();
		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Scanner linecounter = new Scanner(br);
			String linetop = br.readLine();
			String[] topinfo = linetop.split(",");
			String[] roundset = topinfo[0].split(":");
			round = Integer.parseInt(roundset[1]);
			String[] turnset = topinfo[1].split(":");
			turn = Integer.parseInt(turnset[1]);
			//System.out.println("from txts round : "+ round + " turn : " + turn);
			amountof_player = 0;
			while(linecounter.hasNextLine()){
				amountof_player++;
				linecounter.nextLine();
			}
			linecounter.close();
			br.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		try{
			BufferedReader brload = new BufferedReader(new FileReader(filename));
			String line;
			brload.readLine();
			int i= 0;
			while(i < amountof_player){
				line = brload.readLine();
				String[] info = line.split(",");
				for(int j = 0;j < info.length;j++){
					character_info.add(info[j]);
				}
				i++;
			}

			brload.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		A1063321_GUI.IdentifyLocation();
		A1063321_GUI.IdentifyDirection();
		try{
			A1063321_GUI.ImgLoad(); 
		}catch(IOException e){
			 System.err.println(e.getMessage());
			 e.printStackTrace();
			 System.exit(0);
		}
		try{
			BufferedReader brland =  new BufferedReader(new FileReader(filename2));			
			Scanner landlinecounter = new Scanner(brland);
			amountof_land = 0;
			brland.readLine();
			while(landlinecounter.hasNextLine()){
				amountof_land++;
				landlinecounter.nextLine();				
			}
			landlinecounter.close();
			brland.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		place_number_array = new int[amountof_land];
		owner_array = new int[amountof_land];
		land_price_array = new int[amountof_land];
		tolls_array = new int[amountof_land];
		try{
			BufferedReader brlandload =  new BufferedReader(new FileReader(filename2));			
			String landline;
			int i = 0;
			annotation = brlandload.readLine();
			while(i < amountof_land){
				landline = brlandload.readLine();
				String[] landinfo = landline.split(",");
				place_number_array[i] = Integer.parseInt(landinfo[0]);
				owner_array[i] = Integer.parseInt(landinfo[1]);
				i++;
			}
			brlandload.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		/*for(int m = 0;m < amountof_land;m++){
			System.out.println("land number : " + place_number_array[m] + ", owner : " + owner_array[m]);
		}*/
		ConnectDatabase();
		/*for(int m = 0;m < amountof_land;m++){
			System.out.println("land number : " + place_number_array[m] + ", owner : " + owner_array[m] + ", land price : " + land_price_array[m] + ", tolls : " + tolls_array[m]);
		}*/
	}

	public static void Save(String filename, String filename2) throws IOException {
		//// TODO: You should save the changed variables into original data (filename). ////
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			String topinfo = "Round:" + String.valueOf(round) + ",Turn:" + String.valueOf(turn);
			bw.write(topinfo);
			bw.newLine();
			int count_array = 0;
			for(int i = 0;i< amountof_player;i++){
				String line ="";
				for(int j = 0;j < 5;j++){
					if(j == 4){
						line += character_info.get(count_array);
						count_array +=1;
					}
					else{
						line += character_info.get(count_array)+",";
						count_array +=1;
					}
					
				}
				bw.write(line);
				bw.newLine(); 
			}

			bw.flush();
			bw.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		try{
			BufferedWriter bwland = new BufferedWriter(new FileWriter(filename2));
			bwland.write(annotation);
			bwland.newLine();
			for(int j = 0; j < amountof_land; j++){
				String landline="";
				landline += place_number_array[j] + "," + owner_array[j];
				bwland.write(landline);
				bwland.newLine();
			}
			bwland.flush();
			bwland.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	private static class startFrame extends JFrame implements ActionListener{
		private JButton start,load,exit;
		public startFrame(){
			super();
			setSize(200,200);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new GridLayout(3,1));
			setResizable(false);
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			start = new JButton("Start");
			load = new JButton("Load");
			exit = new JButton("Exit");
			p1.add(start);
			p2.add(load);
			p3.add(exit);
			add(p1);
			add(p2);
			add(p3);
			start.addActionListener(this);
			load.addActionListener(this);
			exit.addActionListener(this);

		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == start){
				try{
					createnewFile();
				}catch(IOException err){
					System.err.println(err.getMessage());
				}
				try{
					Load("Character.txt","Land.txt");
				}catch(IOException err){
					System.out.println(err.getMessage());
				}
				allcharacter = new Character[amountof_player];
				allLand = new Land[amountof_land];
				Array_toObject(allcharacter);
				LandArray_toObject();
				Object_toArray(allcharacter);
				A1063321_GUI frame = new A1063321_GUI();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource() == load){
				if(isFile()){
					try{
						Load("Character.txt","Land.txt");
					}catch(IOException err){
						System.out.println(err.getMessage());
					}
					allcharacter = new Character[amountof_player];
					allLand = new Land[amountof_land];
					Array_toObject(allcharacter);
					LandArray_toObject();
					Object_toArray(allcharacter);
					A1063321_GUI frame = new A1063321_GUI();
					frame.setVisible(true);
					dispose();
				}
				else{
					NoFile nofile = new NoFile();
					nofile.setVisible(true);
				}
			}
			if(e.getSource() == exit){
				System.exit(0);
			}
		}
		private class NoFile extends JFrame implements ActionListener{
			private JButton confirm;
			public NoFile(){
				super();
				setSize(150,100);
				setResizable(false);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				setLayout(new FlowLayout());
				JLabel notice = new JLabel("Without record");
				add(notice);
				confirm = new JButton("Confirm");
				confirm.addActionListener(this);
				add(confirm);
			}
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == confirm){
					dispose();
				}
			}
		}
		public Boolean isFile(){
			File c = new File("Character.txt");
			File l = new File("Land.txt");
			if(c.exists() && l.exists()){
				return true;
			}
			else{
				return false;
			}
		}
		public void createnewFile() throws IOException{
			try{
				BufferedWriter bw = new BufferedWriter(new FileWriter("Character.txt"));
				String top = "Round:1,Turn:1";
				String body = "0,1,2000,1,Character_1.png\n0,2,2000,1,Character_2.png\n0,3,2000,1,Character_3.png\n0,4,2000,1,Character_4.png";
				bw.write(top);
				bw.newLine();
				bw.write(body);
				bw.flush();
				bw.close();
			}catch(IOException e){
				System.err.println(e.getMessage());
			}
			try{
				BufferedWriter bwLand = new BufferedWriter(new FileWriter("Land.txt"));
				String topL = "LOCATION_NUMBER, owner";
				String bodyL = "1,0\n2,0\n3,0\n4,0\n6,0\n7,0\n8,0\n9,0\n11,0\n12,0\n13,0\n14,0\n16,0\n17,0\n18,0\n19,0";
				bwLand.write(topL);
				bwLand.newLine();
				bwLand.write(bodyL);
				bwLand.flush();
				bwLand.close();
			}catch(IOException e){
				System.err.println(e.getMessage());
			}
		}
	}

	public static int checkstatus(Character[] check_Character){
		int temp = 0;
		for(int i = 0;i < amountof_player;i++){
			if(check_Character[i].status > 0){
				temp = i+1;
				break;
			}
		}
		return temp;
	}

	public static void player_status_plus(){
		for(int i = 0;i < character_info.size();i++){
			if(i % 5 == 3){
				character_info.set(i, character_info.get(i)+1);
			}
		}
		Array_toObject(allcharacter);

	}

	public static void StartPlaceTreasure(){
		for(int i = 0; i < amountof_player; i++){
			if(allcharacter[i].location >= 20){
				allcharacter[i].money += 2000;
				allcharacter[i].location = allcharacter[i].location % 20;
			}
		}
		A1063321_GUI.PlaymoneyLabelSet();
		Object_toArray(allcharacter);
	}

	public static void Random(int rollnum) {
		//// TODO: while calling the Random function, Character.location should plus the random value, and Character.status should minus one.
		//// TODO: While Character.status more than zero(not include zero), Character can move(plus the random value).
		int[] status_all = new int[amountof_player];
		int[] location_all = new int[amountof_player];
		int count_location = 0;
		int count_status = 0;
		for(int i = 0;i < character_info.size();i++){
			if(i % 5 == 0){
				location_all[count_location] = Integer.parseInt(character_info.get(i));
				count_location += 1;
			}
			if(i % 5 == 3){
				status_all[count_status] = Integer.parseInt(character_info.get(i));
				count_status += 1;
			}
			
		}
		rollnum -= 1;
		if(status_all[rollnum] > 0){
			step = ((int)(Math.random() * 6) + 1);
			if(step == 7){
				step = 6;
			}
			location_all[rollnum] += step;
			status_all[rollnum] -= 1;
		}
		/*for(int j = 0;j < amountof_player;j++){
			if(status_all[j] > 0){
				step = ((int)(Math.random() * 6) + 1);
				if(step == 7){
					step = 6;
				}
				location_all[j] += step;
				status_all[j] -= 1;
			}
		}*/
		count_location = 0;
		count_status = 0;
		for(int k = 0;k < character_info.size();k++){
			if(k % 5 == 0){
				character_info.set(k,String.valueOf(location_all[count_location]));
				count_location += 1;
			}
			if(k % 5 == 3){
				character_info.set(k,String.valueOf(status_all[count_status]));
				count_status += 1;
			}
		}
		Array_toObject(allcharacter);
		StartPlaceTreasure();
		/*for(int s = 0;s < amountof_player;s++){
			System.out.println(location_all[s]+", "+status_all[s]);
		}*/
	}

}