import javax.imageio.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;
import java.sql.*;
public class A1063321_GUI extends JFrame implements ActionListener{

    ////TODO: GUI ////
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;
    public static final int[] ORIGIN_X_LOCATION = {500,540,500,540};
    public static final int[] ORIGIN_Y_LOCATION = {570,570,610,610}; 
    public int dicenumber = 0;
    public static int playernumber = 1;
    private JButton button_exit,save,load,rollDice;
    private JLabel CC2J1,CC2J2,CSJ1;
    private static JLabel NJ1,NJ2,NJ3,NJ4,NJ5,NJ6,SJ1,SJ2,SJ3,SJ4,SJ5,SJ6,CLJ1,CLJ2,CLJ3,CLJ4,CRJ1,CRJ2,CRJ3,CRJ4;
    private static JLabel playmoney1,playmoney2,playmoney3,playmoney4;
    public JPanel allmap;
    public static Image[] playerimg;
    public static int[] xlocation,ylocation;
    public static int[] direction;
    public static int horizonWidth,verticalWidth,horizonLength,verticalLength;
    public static int xdirection,ydirection;
    public static int allmovedistance,turnwho;
    public Double moveSpeed;
    public A1063321_GUI(){
        super();
        //thread for wait
        Wait wt = new Wait();
        wt.start();
        //jframe set
        
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(0,0));
        //map describe
        allmap = new JPanel();
        JPanel northmap = new JPanel();
        JPanel centermap = new JPanel();
        JPanel southmap = new JPanel();

        allmap.setLayout(new BorderLayout(0,0));
        allmap.add(northmap, BorderLayout.NORTH);
        allmap.add(centermap, BorderLayout.CENTER);
        allmap.add(southmap, BorderLayout.SOUTH);

        add(allmap, BorderLayout.CENTER);
        //northmap set
        NJ1 = new JLabel();
        NJ2 = new JLabel();
        NJ3 = new JLabel();
        NJ4 = new JLabel();
        NJ5 = new JLabel();
        NJ6 = new JLabel();

        ImageIcon north1 = new ImageIcon("10.png");
        ImageIcon north2 = new ImageIcon("11.png");
        ImageIcon north3 = new ImageIcon("12.png");
        ImageIcon north4 = new ImageIcon("13.png");
        ImageIcon north5 = new ImageIcon("14.png");
        ImageIcon north6 = new ImageIcon("15.png");

        NJ1.setIcon(north1);
        NJ2.setIcon(north2);
        NJ3.setIcon(north3);
        NJ4.setIcon(north4);
        NJ5.setIcon(north5);
        NJ6.setIcon(north6);   

        northmap.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        northmap.add(NJ1);
        northmap.add(NJ2);
        northmap.add(NJ3);
        northmap.add(NJ4);
        northmap.add(NJ5);
        northmap.add(NJ6);
        //southmap set
        SJ1 = new JLabel();
        SJ2 = new JLabel();
        SJ3 = new JLabel();
        SJ4 = new JLabel();
        SJ5 = new JLabel();
        SJ6 = new JLabel();

        ImageIcon south1 = new ImageIcon("5.png");
        ImageIcon south2 = new ImageIcon("4.png");
        ImageIcon south3 = new ImageIcon("3.png");
        ImageIcon south4 = new ImageIcon("2.png");
        ImageIcon south5 = new ImageIcon("1.png");
        ImageIcon south6 = new ImageIcon("0.png");

        SJ1.setIcon(south1);
        SJ2.setIcon(south2);
        SJ3.setIcon(south3);
        SJ4.setIcon(south4);
        SJ5.setIcon(south5);
        SJ6.setIcon(south6);

        southmap.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        southmap.add(SJ1);
        southmap.add(SJ2);
        southmap.add(SJ3);
        southmap.add(SJ4);
        southmap.add(SJ5);
        southmap.add(SJ6);
        //centermap descibe
        JPanel cenleftmap = new JPanel();
        JPanel centerarea = new JPanel();
        JPanel cenrightmap = new JPanel();

        centermap.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        centermap.add(cenleftmap);
        centermap.add(centerarea);
        centermap.add(cenrightmap);
        //centerleftmap set
        CLJ1 = new JLabel();
        CLJ2 = new JLabel();
        CLJ3 = new JLabel();
        CLJ4 = new JLabel();

        ImageIcon cenleft1 = new ImageIcon("9.png");
        ImageIcon cenleft2 = new ImageIcon("8.png");
        ImageIcon cenleft3 = new ImageIcon("7.png");
        ImageIcon cenleft4 = new ImageIcon("6.png");

        CLJ1.setIcon(cenleft1);
        CLJ2.setIcon(cenleft2);
        CLJ3.setIcon(cenleft3);
        CLJ4.setIcon(cenleft4);

        cenleftmap.setLayout(new GridLayout(4,1));
        cenleftmap.add(CLJ1);
        cenleftmap.add(CLJ2);
        cenleftmap.add(CLJ3);
        cenleftmap.add(CLJ4);
        //centerrightmap set
        CRJ1 = new JLabel();
        CRJ2 = new JLabel();
        CRJ3 = new JLabel();
        CRJ4 = new JLabel();

        ImageIcon cenright1 = new ImageIcon("16.png");
        ImageIcon cenright2 = new ImageIcon("17.png");
        ImageIcon cenright3 = new ImageIcon("18.png");
        ImageIcon cenright4 = new ImageIcon("19.png");

        CRJ1.setIcon(cenright1);
        CRJ2.setIcon(cenright2);
        CRJ3.setIcon(cenright3);
        CRJ4.setIcon(cenright4);

        cenrightmap.setLayout(new GridLayout(4,1));
        cenrightmap.add(CRJ1);
        cenrightmap.add(CRJ2);
        cenrightmap.add(CRJ3);
        cenrightmap.add(CRJ4);
        //centerarea describe        
        JPanel CN = new JPanel();
        JPanel CC = new JPanel();
        JPanel CS = new JPanel();

        centerarea.setLayout(new BorderLayout());
        centerarea.add(CN, BorderLayout.NORTH);
        centerarea.add(CC, BorderLayout.CENTER);
        centerarea.add(CS, BorderLayout.SOUTH);
        //centernortharea set
        JLabel CNJ = new JLabel();
        ImageIcon title = new ImageIcon("title.png");
        CNJ.setIcon(title);
        CN.add(CNJ);
        //centercenterarea set
        JPanel CC1 = new JPanel();
        JPanel CC2 = new JPanel();
        CC.setLayout(new FlowLayout());
        CC.add(CC1);
        CC.add(CC2);

        ImageIcon Dice = new ImageIcon("Dice.png");
        rollDice = new JButton(Dice);
        rollDice.addActionListener(this);
        rollDice.setBorder(null);
        rollDice.setContentAreaFilled(false);
        CC1.add(rollDice);

        CC2.setLayout(new GridLayout(2,1));
        CC2J1 = new JLabel(Integer.toString(dicenumber),JLabel.CENTER);
        CC2J2 = new JLabel("Round " + A1063321_Checkpoint6.round);
        ImageIcon displayDice = new ImageIcon("display_dicenum.png");
        CC2J1.setIcon(displayDice);
        CC2J1.setVerticalTextPosition(JLabel.CENTER);
        CC2J1.setHorizontalTextPosition(JLabel.CENTER);
        CC2J1.setFont(new Font(Font.DIALOG,Font.BOLD,35));
        CC2J2.setFont(new Font(Font.DIALOG,Font.PLAIN,35));
        CC2.add(CC2J1);
        CC2.add(CC2J2);
        //centersoutharea set
        playernumber = A1063321_Checkpoint6.checkstatus(A1063321_Checkpoint6.allcharacter);
        A1063321_Checkpoint6.turn = playernumber;
        CSJ1 = new JLabel("Turn Character "+ Integer.toString(playernumber));
        CS.add(CSJ1);
        CSJ1.setFont(new Font(Font.DIALOG,Font.PLAIN,35));
        //tool describe
        JPanel topinfo = new JPanel();
        JPanel exitarea = new JPanel();
        add(topinfo,BorderLayout.NORTH);
        add(exitarea,BorderLayout.SOUTH);
        //topinfo set
        topinfo.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        JPanel top1 = new JPanel();
        JPanel empty_JP = new JPanel();
        empty_JP.setPreferredSize(new Dimension(50,50));
        JPanel top2 = new JPanel();
        topinfo.add(top1);
        topinfo.add(empty_JP);
        topinfo.add(top2);

        save = new JButton("Save");
        load = new JButton("Load");
        save.addActionListener(this);
        load.addActionListener(this);
        top1.setLayout(new GridLayout(1,2));
        top1.add(save);
        top1.add(load);
        
        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();
        JPanel player3 = new JPanel();
        JPanel player4 = new JPanel();
        top2.setLayout(new FlowLayout());
        top2.add(player1);
        top2.add(player2);
        top2.add(player3);
        top2.add(player4);

        player1.setLayout(new GridLayout(2,1));
        player2.setLayout(new GridLayout(2,1));
        player3.setLayout(new GridLayout(2,1));
        player4.setLayout(new GridLayout(2,1));
        JLabel playnum1 = new JLabel("Character1");       
        JLabel playnum2 = new JLabel("Character2");
        JLabel playnum3 = new JLabel("Character3");
        JLabel playnum4 = new JLabel("Character4");
        playmoney1 = new JLabel("0");
        playmoney2 = new JLabel("0");
        playmoney3 = new JLabel("0");
        playmoney4 = new JLabel("0");
        playnum1.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playnum2.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playnum3.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playnum4.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playmoney1.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playmoney2.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playmoney3.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playmoney4.setFont(new Font(Font.DIALOG,Font.PLAIN,16));
        playmoney1.setHorizontalAlignment(JLabel.CENTER);
        playmoney2.setHorizontalAlignment(JLabel.CENTER);
        playmoney3.setHorizontalAlignment(JLabel.CENTER);
        playmoney4.setHorizontalAlignment(JLabel.CENTER);



        int count = 0;
        while(count < A1063321_Checkpoint6.amountof_player){
            for(int i =0;i< A1063321_Checkpoint6.character_info.size();i++){
                if( i % 5 == 2){
                    if(count == 0){
                        playmoney1.setText(A1063321_Checkpoint6.character_info.get(i));
                        count += 1;
                    }
                    else if(count == 1){
                        playmoney2.setText(A1063321_Checkpoint6.character_info.get(i));
                        count += 1;
                    }
                    else if(count == 2){
                        playmoney3.setText(A1063321_Checkpoint6.character_info.get(i));
                        count += 1;
                    }
                    else if(count == 3){
                        playmoney4.setText(A1063321_Checkpoint6.character_info.get(i));
                        count += 1;
                    }
                }
            }
        }
        
        player1.add(playmoney1,JLabel.CENTER);
        player1.add(playnum1,JLabel.CENTER);
        player2.add(playmoney2,JLabel.CENTER);
        player2.add(playnum2,JLabel.CENTER);
        player3.add(playmoney3,JLabel.CENTER);
        player3.add(playnum3,JLabel.CENTER);
        player4.add(playmoney4,JLabel.CENTER);
        player4.add(playnum4,JLabel.CENTER);
        
        //exitarea set
        button_exit = new JButton("exit");
        button_exit.addActionListener(this);
        button_exit.setSize(10,10);
        exitarea.add(button_exit);
        button_exit.setFont(new Font(Font.DIALOG,Font.PLAIN,10));
        button_exit.setPreferredSize(new Dimension(150, 20));

        //delete border
        allmap.setBorder(null);
        northmap.setBorder(null);
        centerarea.setBorder(null);
        southmap.setBorder(null);
        cenleftmap.setBorder(null);
        cenrightmap.setBorder(null);
        centermap.setBorder(null);

        //delete label border
        NJ1.setBorder(null);
        NJ2.setBorder(null);
        NJ3.setBorder(null);
        NJ4.setBorder(null);
        NJ5.setBorder(null);
        NJ6.setBorder(null);


        //total dimension
        topinfo.setPreferredSize(new Dimension(700,55));
        allmap.setPreferredSize(new Dimension(700,625));
        exitarea.setPreferredSize(new Dimension(250,20));
        northmap.setPreferredSize(new Dimension(700,120));
        centermap.setPreferredSize(new Dimension(700,379));
        cenleftmap.setPreferredSize(new Dimension(120,359));
        centerarea.setPreferredSize(new Dimension(360,359));
        cenrightmap.setPreferredSize(new Dimension(120,359));
        southmap.setPreferredSize(new Dimension(600,120));
        CN.setPreferredSize(new Dimension(250,50));
        CC.setPreferredSize(new Dimension(350,120));
        CS.setPreferredSize(new Dimension(300,50));
    
        LandOwnerSet();
    }

    public void paint(Graphics g){
        super.paint(g);               
        for(int j = 0;j < A1063321_Checkpoint6.amountof_player;j++){
            g.drawImage(playerimg[j], xlocation[j], ylocation[j], null);
        }
        
    }

    private class Move extends Thread{
        public Double delaytime;
        public void run(){
            delaytime = timeMove();
            moveSpeed = Double.valueOf(allmovedistance) / 90.0;
            //System.out.println("this time move alldistance : " + allmovedistance + " time : " + delaytime + " speed : " + moveSpeed);
            //Graphics g = allmap.getGraphics();
            while(allmovedistance > 0){
                changeDirection();
                xlocation[turnwho-1] += Math.round(moveSpeed) * xdirection;
                ylocation[turnwho-1] += Math.round(moveSpeed) * ydirection;
                //paint(g);
                EdgeCount();
                repaint();
                delay();
                allmovedistance -= Math.round(moveSpeed);
            }
            LandEvent();
            PlaymoneyLabelSet();
            CheckTurn();
            rollDice.setEnabled(true);
            /*for(int i = 0;i < A1063321_Checkpoint6.amountof_land;i++){
                System.out.println("Land information : P_N = " + A1063321_Checkpoint6.allLand[i].PLACE_NUMBER + " owner = " + A1063321_Checkpoint6.allLand[i].owner + " L_P = " + A1063321_Checkpoint6.allLand[i].LAND_PRICE + " toll = " + A1063321_Checkpoint6.allLand[i].TOLLS);
            }
            System.out.println("===================================");*/
        }

        public void delay(){
            try{
                Thread.sleep(Math.round(delaytime));
            }catch(InterruptedException e){
                System.out.println("Something is error!");
                System.exit(0);
            }
        }

        public Double timeMove(){
            if(dicenumber > 3){
                return 3000.0 / 90.0;
            }
            else{
                return 2000.0 / 90.0;
            }
        }
    }

    public void Distance(){
        allmovedistance = dicenumber * horizonWidth;
    }

    public static void IdentifyLocation(){
        xlocation = new int[A1063321_Checkpoint6.amountof_player];
        ylocation = new int[A1063321_Checkpoint6.amountof_player];
        try{
            BufferedImage hoimg = ImageIO.read(new File("1.png"));
            BufferedImage veimg = ImageIO.read(new File("6.png"));
            horizonWidth = hoimg.getWidth();
            verticalWidth = veimg.getHeight();
        }catch(IOException e){
            System.err.println(e.getMessage());
            e.getStackTrace();
            System.exit(0);
        }
        horizonLength = 5 * horizonWidth;
        verticalLength = 5 * verticalWidth;
        int[] location_all = new int[A1063321_Checkpoint6.amountof_player];
        int count_location = 0;
        for(int i = 0;i < A1063321_Checkpoint6.character_info.size();i++){
            if(i % 5 == 0){
				location_all[count_location] = Integer.parseInt(A1063321_Checkpoint6.character_info.get(i));
				count_location += 1;
			}
        }
        /*for(int k = 0;k < location_all.length;k++){
            System.out.println("player"+ (k+1) + " location : " + location_all[k]);
        }*/
        for(int j = 0;j < location_all.length;j++){
            if(location_all[j] % 20 < 5){
                xlocation[j] = ORIGIN_X_LOCATION[j] + -1 * horizonWidth * (location_all[j] % 5);
                ylocation[j] = ORIGIN_Y_LOCATION[j];
                
            }
            if(location_all[j] % 20 < 10 && location_all[j] % 20 >= 5){
                xlocation[j] = ORIGIN_X_LOCATION[j] + -1 * horizonLength;
                ylocation[j] = ORIGIN_Y_LOCATION[j] + -1 * verticalWidth * (location_all[j] % 5);
            }
            if(location_all[j] % 20 < 15 && location_all[j] % 20 >= 10){
                xlocation[j] = ORIGIN_X_LOCATION[j] + -1 * horizonLength + 1 * horizonWidth * (location_all[j] % 5);
                ylocation[j] = ORIGIN_Y_LOCATION[j] + -1 * verticalLength;
            }
            if(location_all[j] % 20 < 20 && location_all[j] % 20 >= 15){
                xlocation[j] = ORIGIN_X_LOCATION[j];
                ylocation[j] = ORIGIN_Y_LOCATION[j] + -1 * verticalLength + 1 * verticalWidth * (location_all[j] % 5);
            }
        }
        /*for(int k = 0;k < xlocation.length;k++){
            System.out.println("player"+ (k+1) + " xlocation : " + xlocation[k] + " ylocation : " + ylocation[k]);

        }*/
    }

    public static void IdentifyDirection(){
        direction = new int[A1063321_Checkpoint6.amountof_player];
        int[] location_all = new int[A1063321_Checkpoint6.amountof_player];
        int count_location = 0;
        for(int i = 0;i < A1063321_Checkpoint6.character_info.size();i++){
            if(i % 5 == 0){
				location_all[count_location] = Integer.parseInt(A1063321_Checkpoint6.character_info.get(i));
				count_location += 1;
			}
        }
        /*for(int k = 0;k < location_all.length;k++){
            System.out.println("player"+ (k+1) + " location : " + location_all[k]);
        }*/
        for(int j = 0;j < A1063321_Checkpoint6.amountof_player;j++){
            if(location_all[j] % 20 < 5 || location_all[j] == 0){
                direction[j] = 1;
            }
            if(location_all[j] % 20 < 10 && location_all[j] % 20 >= 5){
                direction[j] = 2;
            }
            if(location_all[j] % 20 < 15 && location_all[j] % 20 >= 10){
                direction[j] = 3;
            }
            if(location_all[j] % 20 < 20 && location_all[j] % 20 >= 15){
                direction[j] = 4;
            }
        }
        /*for(int k = 0;k < direction.length;k++){
            System.out.println("player"+ (k+1) + " direction : " + direction[k]);
        }*/
    }

    public void EdgeCount(){
        
        int arrcount = turnwho - 1;
        if(xlocation[arrcount] > ORIGIN_X_LOCATION[arrcount]){
            direction[arrcount] = 4;
            xlocation[arrcount] = ORIGIN_X_LOCATION[arrcount];
            //System.out.println("now turn to down");
            changeDirection();
        }
        else if(xlocation[arrcount] < ORIGIN_X_LOCATION[arrcount] - horizonLength){
            direction[arrcount] = 2;
            xlocation[arrcount] = ORIGIN_X_LOCATION[arrcount] - horizonLength;
            //System.out.println("now turn to up");
            changeDirection();
        }
        if(ylocation[arrcount] < ORIGIN_Y_LOCATION[arrcount] - verticalLength){
            direction[arrcount] = 3;
            ylocation[arrcount] = ORIGIN_Y_LOCATION[arrcount] - verticalLength;
            //System.out.println("now turn to right");
            changeDirection();
        }
        else if(ylocation[arrcount] > ORIGIN_Y_LOCATION[arrcount]){
            direction[arrcount] = 1;
            ylocation[arrcount] = ORIGIN_Y_LOCATION[arrcount];
            //System.out.println("now turn to left");
            changeDirection();
        }
    }

    public static void changeDirection(){
        if(direction[turnwho-1] == 1){
            xdirection = -1;
            ydirection = 0;
        }
        else if(direction[turnwho-1] == 2){
            xdirection = 0;
            ydirection = -1;
        }
        else if(direction[turnwho-1] == 3){
            xdirection = 1;
            ydirection = 0;
        }
        else if(direction[turnwho-1] == 4){
            xdirection = 0;
            ydirection = 1;
        }
    
        
    }

    public static void ImgLoad() throws IOException{
        playerimg = new Image[A1063321_Checkpoint6.amountof_player];
        int i = 0;
        for(i = 0;i < A1063321_Checkpoint6.amountof_player;i++){
            String imgname = "Character_" + (i + 1) + ".png";
            //System.out.println(imgname);
            playerimg[i] = ImageIO.read(new File(imgname));
            
        }
    }

    public static void PlaymoneyLabelSet(){
        for(int i = 0; i < A1063321_Checkpoint6.amountof_player; i++){
            if(i == 0){
                playmoney1.setText(String.valueOf(A1063321_Checkpoint6.allcharacter[i].money));
            }
            else if(i == 1){
                playmoney2.setText(String.valueOf(A1063321_Checkpoint6.allcharacter[i].money));
            }
            else if(i == 2){
                playmoney3.setText(String.valueOf(A1063321_Checkpoint6.allcharacter[i].money));
            }
            else if(i == 3){
                playmoney4.setText(String.valueOf(A1063321_Checkpoint6.allcharacter[i].money));
            }
        }
    }

    public void LandEvent(){
        //System.out.println(A1063321_Checkpoint6.allcharacter[turnwho-1].location);
        int nowplayer = turnwho;
        int landno = ConfirmLandObject(A1063321_Checkpoint6.allcharacter[nowplayer-1].location);
        if(landno != 50){
            //System.out.println(A1063321_Checkpoint6.allLand[landno].owner);
            if(A1063321_Checkpoint6.allLand[landno].owner == 0){
                if(A1063321_Checkpoint6.allcharacter[nowplayer-1].money >= A1063321_Checkpoint6.allLand[landno].LAND_PRICE){
                    int buyresult = AskBuyPane(landno);
                    if(buyresult == JOptionPane.YES_OPTION){
                        A1063321_Checkpoint6.allLand[landno].owner = nowplayer;
                        A1063321_Checkpoint6.allcharacter[nowplayer-1].money -= A1063321_Checkpoint6.allLand[landno].LAND_PRICE;
                        IdentifyLabel(A1063321_Checkpoint6.allcharacter[nowplayer-1].location).setText(String.valueOf(turnwho));
                        IdentifyLabel(A1063321_Checkpoint6.allcharacter[nowplayer-1].location).setHorizontalTextPosition(JLabel.CENTER);
                        IdentifyLabel(A1063321_Checkpoint6.allcharacter[nowplayer-1].location).setVerticalTextPosition(JLabel.CENTER);
                        IdentifyLabel(A1063321_Checkpoint6.allcharacter[nowplayer-1].location).setFont(new Font(Font.DIALOG,Font.PLAIN,35));
                        PlaymoneyLabelSet();
                        A1063321_Checkpoint6.Object_toArray(A1063321_Checkpoint6.allcharacter);
                        repaint();
                    }
                }
                
            }
            else{
                if(A1063321_Checkpoint6.allLand[landno].owner != nowplayer){
                    TollsPane(landno, nowplayer);
                    A1063321_Checkpoint6.allcharacter[nowplayer-1].money -= A1063321_Checkpoint6.allLand[landno].TOLLS;
                    A1063321_Checkpoint6.allcharacter[(A1063321_Checkpoint6.allLand[landno].owner-1)].money += A1063321_Checkpoint6.allLand[landno].TOLLS;
                    PlaymoneyLabelSet();
                    A1063321_Checkpoint6.Object_toArray(A1063321_Checkpoint6.allcharacter);
                    repaint();
                }         
            }
        }
        
    }

    public void LandOwnerSet(){
        LandOwnerReset();
        for(int i = 0; i < A1063321_Checkpoint6.amountof_land; i++){
            if(A1063321_Checkpoint6.allLand[i].owner != 0){
                int L_owner = A1063321_Checkpoint6.allLand[i].owner;
                int L_num = A1063321_Checkpoint6.allLand[i].PLACE_NUMBER;
                IdentifyLabel(L_num).setText(String.valueOf(L_owner));
                IdentifyLabel(L_num).setHorizontalTextPosition(JLabel.CENTER);
                IdentifyLabel(L_num).setVerticalTextPosition(JLabel.CENTER);
                IdentifyLabel(L_num).setFont(new Font(Font.DIALOG,Font.PLAIN,35));
            }
        }
        repaint();
    }

    public void LandOwnerReset(){
        for(int i = 0; i < 20; i++){
            IdentifyLabel(i).setText("");
            IdentifyLabel(i).setHorizontalTextPosition(JLabel.CENTER);
            IdentifyLabel(i).setVerticalTextPosition(JLabel.CENTER);
            IdentifyLabel(i).setFont(new Font(Font.DIALOG,Font.PLAIN,35));
        }
    }

    public int ConfirmLandObject(int location){
        location = location % 20;
        if(location == 0 || location == 5 || location == 10 || location == 15){
            return 50;
        }
        else if(location > 0 && location < 5){
            return location - 1;
        }
        else if(location > 5 && location < 10){
            return location - 2;
        }
        else if(location > 10 && location < 15){
            return location - 3;
        }
        else{
            return location - 4;
        }
    }

    public int AskBuyPane(int location){
        JFrame askbuy = new JFrame();
        askbuy.setSize(300,150);
        askbuy.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        int forreturn = JOptionPane.showConfirmDialog(askbuy,"Do you want to buy?\n$" + A1063321_Checkpoint6.allLand[location].LAND_PRICE,"Whether to buy!!!",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        return forreturn;
    }

    public void TollsPane(int location,int who){
        JFrame tollsnotice = new JFrame();
        tollsnotice.setSize(300,150);
        tollsnotice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(tollsnotice, "This place is held by Character " + A1063321_Checkpoint6.allLand[location].owner + ", Character " + who + " needs to pay the tolls $" + A1063321_Checkpoint6.allLand[location].TOLLS, "Pay tolls!!!", JOptionPane.OK_OPTION);
    }
    
    public void CheckTurn(){
        if(A1063321_Checkpoint6.checkstatus(A1063321_Checkpoint6.allcharacter) == 0){
            A1063321_Checkpoint6.round += 1;
            A1063321_Checkpoint6.player_status_plus();
            CC2J2.setText("Round " + A1063321_Checkpoint6.round);
        }

        playernumber = A1063321_Checkpoint6.checkstatus(A1063321_Checkpoint6.allcharacter);
        A1063321_Checkpoint6.turn = playernumber;
        CSJ1.setText("Turn Character "+ Integer.toString(playernumber));
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button_exit){
            System.exit(0);
        }

        if(e.getSource() == rollDice){
            rollDice.setEnabled(false);
            turnwho = A1063321_Checkpoint6.checkstatus(A1063321_Checkpoint6.allcharacter);
            A1063321_Checkpoint6.Random(playernumber);
            A1063321_Checkpoint6.Array_toObject(A1063321_Checkpoint6.allcharacter);
            A1063321_Checkpoint6.Object_toArray(A1063321_Checkpoint6.allcharacter);
            A1063321_Checkpoint6.LandArray_toObject();
            dicenumber = A1063321_Checkpoint6.step;
            Distance();
            Move test = new Move();
            test.start();
            CC2J1.setText(Integer.toString(dicenumber));
            

            
        }

        if(e.getSource() == save){
            A1063321_Checkpoint6.Object_toArray(A1063321_Checkpoint6.allcharacter);
            try{
                A1063321_Checkpoint6.Save("Character.txt","Land.txt");
            }catch(IOException er){
                System.out.println(er.getMessage());
            }
        }

        if(e.getSource() == load){
            try{
                A1063321_Checkpoint6.Load("Character.txt","Land.txt");
            }catch(IOException er){
                System.out.println(er.getMessage());
            }
            A1063321_Checkpoint6.LandArray_toObject();
            A1063321_Checkpoint6.Array_toObject(A1063321_Checkpoint6.allcharacter);
            A1063321_Checkpoint6.Object_toArray(A1063321_Checkpoint6.allcharacter);
            A1063321_GUI load_gui = new A1063321_GUI();
            load_gui.setVisible(true);
            dispose();
            
        }
        
    }

    private class Wait extends Thread{
        public void run(){
            try{
                sleep(500);
            }catch(InterruptedException e){
                System.err.println(e.getMessage());
                e.printStackTrace();
                System.exit(0);
            }
            repaint();
        }
    }

    public static JLabel IdentifyLabel(int location){
        JLabel forreturn = new JLabel();
        switch(location){
            case 0:
                forreturn = SJ6;
                break;
            case 1:
                forreturn = SJ5;
                break;
            case 2:
                forreturn = SJ4;
                break;
            case 3:
                forreturn = SJ3;
                break;
            case 4:
                forreturn = SJ2;
                break;
            case 5:  
                forreturn = SJ1;
                break;
            case 6:
                forreturn = CLJ4;
                break;
            case 7:
                forreturn = CLJ3;
                break;
            case 8:
                forreturn = CLJ2;
                break;
            case 9:
                forreturn = CLJ1;
                break;
            case 10:
                forreturn = NJ1;
                break;
            case 11:
                forreturn = NJ2;
                break;
            case 12:
                forreturn = NJ3;
                break;
            case 13:
                forreturn = NJ4;
                break;
            case 14:
                forreturn = NJ5;
                break;
            case 15:
                forreturn = NJ6;
                break;
            case 16:
                forreturn = CRJ1;
                break;
            case 17:
                forreturn = CRJ2;
                break;
            case 18:
                forreturn = CRJ3;
                break;
            case 19:
                forreturn = CRJ4;
                break;
        }
        return forreturn;
    }
}