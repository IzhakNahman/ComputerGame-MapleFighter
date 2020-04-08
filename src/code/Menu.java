package code;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Menu extends JFrame implements KeyListener,Runnable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Image img;//����� �� ����
    Graphics offgc, g,g2;
    boolean gamestart=false;
    int presstostart=1;
    int counterplayervs=0,yplayervs=504;
    int flagenter=0,flagenter2=0,counter1=0, counter2=0,counter3=2,xbackground=420,xplayer=420,xplayer2=820;
    Character player1, player2;
    int hitcount1=0,hitcount2=0;
    int timerattack=0, timershootattack=0, timerattack2=0, timershootattack2=0,  keypress=0, keypress2=0,computerpress=0;
    int flagplayer1=1, flagplayer2=1;
    String player1name="ice", player2name="boss", levelbackground ="Blue";
    private Vector<Shoot> aShoots;
    private Vector<Shoot> aShoots2;
    private boolean flag=true;
    Image offscreen = null;
    HelpFunctions help= new HelpFunctions();

    public static void main (String [ ] args)	{

        Menu world = new Menu ();
        world.setVisible(true);
    }




    private Menu () {
        setSize (1330, 660);
        setIconImage(new ImageIcon("C:/Users/User/workspace/LearnJava/img/small.png").getImage());
        this.setVisible(true);
        offscreen=this.createImage(1330,660);
        this.setLocation(20,28);
        g2=this.getGraphics();
        offgc = offscreen.getGraphics();
        offgc.drawImage(offscreen , 0, 0, this);
        img=help.Imagemaker("images/"+levelbackground+".jpg");
        aShoots = new Vector<Shoot>();
        aShoots2 = new Vector<Shoot>();
        Thread t=new Thread(this);
        t.start();
        addKeyListener(this);
        addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
    }





    class listeningg extends Thread //����� ������� ����� ������� ������� �����1
    {
        public void run()
        {
            if(hitcount1>=10)
                hitcount1=0;
            if(hitcount2!=0)
                hitcount2++;
            if(flagplayer1==1)
            {
                if(player1.getLife()>0)
                {
                    if(player1.getSpeed()!=2)
                    {
                        if(player1.getStamina()>2)
                            player1.setStamina(player1.getStamina()-2);
                        else
                            player1.setSpeed(2);
                    }
                    if(keypress==0)//��� �����
                    {
                        if(player1.getStamina()<3000)
                            player1.setStamina(player1.getStamina()+1);
                        if(hitcount1==0)
                        {
                            player1.setHigh(106);
                            player1.setWhide(120);
                            player1.setCharacterright(help.Imagemaker("images/"+player1name+"/stand_right.png"));
                            player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/stand_left.png"));
                        }
                    }
                    else if(keypress==1&&player1.getX()<1220)//����� �����
                    {
                        if(player1.getStamina()>1)
                        {
                            player1.setStamina(player1.getStamina()-1);
                            if(player1.getStamina()>5)
                            {
                                help.FixHighWidePosition(player1name,keypress,player1);
                                player1.setKivun("right");
                                player1.setCharacterright(help.Imagemaker("images/"+player1name+"/walk_right.gif"));
                                player1.moveRight();
                            }
                        }
                        else
                            keypress=0;
                    }
                    else if(keypress==2&&player1.getX()>0)// ����� �����
                    {
                        if(player1.getStamina()>1)
                        {
                            player1.setStamina(player1.getStamina()-1);
                            if(player1.getStamina()>5)
                            {
                                help.FixHighWidePosition(player1name,keypress,player1);
                                player1.setKivun("left");
                                player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/walk_left.gif"));
                                player1.moveLeft();
                            }
                        }
                        else
                            keypress=0;
                    }
                    else if((keypress==3&&player1.getY()>260)||(keypress==4&&player1.getY()<520))// ����� ���� �� �����
                    {
                        if(player1.getStamina()>1)
                        {
                            player1.setStamina(player1.getStamina()-1);
                            if(player1.getStamina()>5)
                            {
                                help.FixHighWidePosition(player1name,keypress,player1);
                                player1.setCharacterright(help.Imagemaker("images/"+player1name+"/walk_right.gif"));
                                player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/walk_left.gif"));
                                if(keypress==3)
                                    player1.moveUp();
                                else
                                    player1.moveDown();
                            }
                        }
                        else
                            keypress=0;
                    }
                    else if(keypress==5)// ����� �����
                    {
                        if(player1.getStamina()>100)
                        {
                            timerattack++;
                            help.FixHighWidePosition(player1name,keypress,player1);
                            player1.setCharacterright(help.Imagemaker("images/"+player1name+"/attack_right.gif"));
                            player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/attack_left.gif"));
                            if(timerattack==15)
                            {
                                player1.setStamina(player1.getStamina()-100);
                                if(player1.HitOrNot(player2)==true&&player2.getLife()>0)
                                {
                                    hitcount2=1;
                                    player2.setCharacterright(help.Imagemaker("images/"+player2name+"/hited_right.png"));
                                    player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/hited_left.png"));
                                    Random d = new Random();
                                    int f=d.nextInt(player1.getKnifeDamageMax())+player1.getKnifeDamageMin();
                                    if(player2.getLife()>f)
                                        player2.setLife(player2.getLife()-f);
                                    else
                                        player2.setLife(0);
                                }

                                help.FixPlayerPosition(player1name,player1,'+',5);
                                keypress=0;
                                timerattack=0;}
                        }
                        else{
                            help.FixPlayerPosition(player1name,player1,'+',5);
                            keypress=0;
                            timerattack=0;}
                    }
                    else if(keypress==6)// ����� �����
                    {
                        if(player1.getStamina()>200)
                        {
                            timershootattack++;
                            help.FixHighWidePosition(player1name,keypress,player1);
                            player1.setCharacterright(help.Imagemaker("images/"+player1name+"/shoot_right.gif"));
                            player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/shoot_left.gif"));
                            if(timershootattack==20)
                            {
                                player1.setStamina(player1.getStamina()-200);
                                Shoot ashoot = new Shoot(player1.getX()+player1.getWhide()/2,player1.getY()+player1.getHigh()/2,6,6,g2,help.Imagemaker("images/"+player1name+"/spacial_right"+help.EndType(player1name)),help.Imagemaker("images/"+player1name+"/spacial_left"+help.EndType(player1name)), help.AttackWhide(player1name),  help.AttackHigh(player1name), player1);
                                aShoots.add(ashoot);
                                timershootattack=0;

                                help.FixPlayerPosition(player1name,player1,'+',6);
                                keypress=0;
                                timershootattack=0;
                            }

                        }
                        else{
                            help.FixPlayerPosition(player1name,player1,'+',6);
                            keypress=0;
                            timershootattack=0;}
                    }
                }
                else
                {
                    flagplayer1=0;
                    help.FixHighWidePosition(player1name,keypress,player1);
                    help.FixPlayerPosition(player1name,player1,' ',0);
                    player1.setCharacterright(help.Imagemaker("images/"+player1name+"/die_right.png"));
                    player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/die_left.png"));
                }


                for( int i3=0;i3<aShoots.size();i3++){
                    aShoots.get(i3).move();}

                for(int i3=0;i3<aShoots.size();i3++)
                {
                    if(aShoots.get(i3).StopOrNot()==true)
                        aShoots.get(i3).setVisible(false);
                    if(aShoots.get(i3).attackS(player1, player2)==true&&aShoots.get(i3).getVisible()==(true)&&player2.getLife()>0)
                    {
                        hitcount2=1;
                        player2.setCharacterright(help.Imagemaker("images/"+player2name+"/hited_right.png"));
                        player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/hited_left.png"));
                        Random d = new Random();
                        int f=d.nextInt(player1.getShootDamageMax())+player1.getShootDamageMin();
                        aShoots.get(i3).setVisible(false);
                        if(player2.getLife()>f)
                            player2.setLife(player2.getLife()-f);
                        else
                            player2.setLife(0);
                    }
                }
                for(int i3=0;i3<aShoots.size();i3++)
                {
                    if(aShoots.get(i3).getVisible()==(false))
                    {
                        aShoots.remove(i3);
                    }

                }

            }

        }
        public void start(){
            Thread t = new Thread(this);
            t.start();
        }
    }

    class listeningg2 extends Thread //����� ������� ����� ������� ������� �����1
    {
        public void run()
        {
            if(hitcount1!=0)
                hitcount1++;
            if(hitcount2>=10)
                hitcount2=0;
            if(flagplayer2==1)
            {
                if(player2.getLife()>0)
                {

                    if(player2.getSpeed()!=2)
                    {
                        if(player2.getStamina()>2)
                            player2.setStamina(player2.getStamina()-2);
                        else
                            player2.setSpeed(2);
                    }
                    if(keypress2==0)//��� �����
                    {
                        if(player2.getStamina()<3000)
                            player2.setStamina(player2.getStamina()+1);
                        if(hitcount2==0)
                        {
                            player2.setHigh(106);
                            player2.setWhide(120);
                            player2.setCharacterright(help.Imagemaker("images/"+player2name+"/stand_right.png"));
                            player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/stand_left.png"));
                        }
                    }
                    else if(keypress2==1&&player2.getX()<1220)//����� �����
                    {
                        if(player2.getStamina()>1)
                        {
                            player2.setStamina(player2.getStamina()-1);
                            if(player2.getStamina()>5)
                            {
                                help.FixHighWidePosition(player2name,keypress2,player2);
                                player2.setKivun("right");
                                player2.setCharacterright(help.Imagemaker("images/"+player2name+"/walk_right.gif"));
                                player2.moveRight();
                            }
                        }
                        else
                            keypress2=0;
                    }
                    else if(keypress2==2&&player2.getX()>0)// ����� �����
                    {
                        if(player2.getStamina()>1)
                        {
                            player2.setStamina(player2.getStamina()-1);
                            if(player2.getStamina()>5)
                            {
                                help.FixHighWidePosition(player2name,keypress2,player2);
                                player2.setKivun("left");
                                player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/walk_left.gif"));
                                player2.moveLeft();
                            }
                        }
                        else
                            keypress2=0;
                    }
                    else if((keypress2==3&&player2.getY()>260)||(keypress2==4&&player2.getY()<520))// ����� ���� �� �����
                    {
                        if(player2.getStamina()>1)
                        {
                            player2.setStamina(player2.getStamina()-1);
                            if(player2.getStamina()>5)
                            {
                                help.FixHighWidePosition(player2name,keypress2,player2);
                                player2.setCharacterright(help.Imagemaker("images/"+player2name+"/walk_right.gif"));
                                player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/walk_left.gif"));
                                if(keypress2==3)
                                    player2.moveUp();
                                else
                                    player2.moveDown();
                            }
                        }
                        else
                            keypress2=0;
                    }
                    else if(keypress2==5)// ����� �����
                    {
                        if(player2.getStamina()>100)
                        {
                            timerattack2++;
                            help.FixHighWidePosition(player2name,keypress2,player2);
                            player2.setCharacterright(help.Imagemaker("images/"+player2name+"/attack_right.gif"));
                            player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/attack_left.gif"));
                            if(timerattack2==20)
                            {
                                player2.setStamina(player2.getStamina()-100);
                                if(player2.HitOrNot(player1)==true&&player1.getLife()>0)
                                {
                                    hitcount1=1;
                                    player1.setCharacterright(help.Imagemaker("images/"+player1name+"/hited_right.png"));
                                    player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/hited_left.png"));
                                    Random d = new Random();
                                    int f=d.nextInt(player2.getKnifeDamageMax())+player2.getKnifeDamageMin();
                                    if(player1.getLife()>f)
                                        player1.setLife(player1.getLife()-f);
                                    else
                                        player1.setLife(0);
                                }


                                help.FixPlayerPosition(player2name,player2,'+',5);
                                keypress2=0;
                                timerattack2=0;
                                timerattack2=0;}
                        }
                        else{
                            help.FixPlayerPosition(player2name,player2,'+',5);
                            keypress2=0;
                            timerattack2=0;}
                    }
                    else if(keypress2==6)// ����� �����
                    {
                        if(player2.getStamina()>200)
                        {
                            timershootattack2++;
                            help.FixHighWidePosition(player2name,keypress2,player2);
                            player2.setCharacterright(help.Imagemaker("images/"+player2name+"/shoot_right.gif"));
                            player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/shoot_left.gif"));
                            if(timershootattack2==20)
                            {
                                player2.setStamina(player2.getStamina()-200);
                                Shoot ashoot2 = new Shoot(player2.getX()+player2.getWhide()/2,player2.getY()+player2.getHigh()/2,6,6,g2,help.Imagemaker("images/"+player2name+"/spacial_right"+help.EndType(player2name)),help.Imagemaker("images/"+player2name+"/spacial_left"+help.EndType(player2name)), help.AttackWhide(player2name),  help.AttackHigh(player2name), player2);
                                aShoots2.add(ashoot2);
                                timershootattack2=0;
                                help.FixPlayerPosition(player2name,player2,'+',6);
                                keypress2=0;
                                timershootattack2=0;
                            }

                        }
                        else{
                            help.FixPlayerPosition(player2name,player2,'+',6);
                            keypress2=0;
                            timershootattack2=0;}
                    }
                }
                else
                {
                    flagplayer2=0;
                    help.FixHighWidePosition(player2name,keypress2,player2);
                    help.FixPlayerPosition(player2name,player2,' ',0);
                    player2.setCharacterright(help.Imagemaker("images/"+player2name+"/die_right.png"));
                    player2.setCharacterleft(help.Imagemaker("images/"+player2name+"/die_left.png"));
                }


                for( int i3=0;i3<aShoots2.size();i3++){
                    aShoots2.get(i3).move();}

                for(int i3=0;i3<aShoots2.size();i3++)
                {
                    if(aShoots2.get(i3).StopOrNot()==true)
                        aShoots2.get(i3).setVisible(false);
                    if(aShoots2.get(i3).attackS(player2, player1)==true&&aShoots2.get(i3).getVisible()==(true)&&player1.getLife()>0)
                    {
                        hitcount1=1;
                        player1.setCharacterright(help.Imagemaker("images/"+player1name+"/hited_right.png"));
                        player1.setCharacterleft(help.Imagemaker("images/"+player1name+"/hited_left.png"));
                        Random d = new Random();
                        int f=d.nextInt(player2.getShootDamageMax())+player2.getShootDamageMin();
                        aShoots2.get(i3).setVisible(false);
                        if(player1.getLife()>f)
                            player1.setLife(player1.getLife()-f);
                        else
                            player1.setLife(0);
                    }
                }
                for(int i3=0;i3<aShoots2.size();i3++)
                {
                    if(aShoots2.get(i3).getVisible()==(false))
                    {
                        aShoots2.remove(i3);
                    }


                }
            }
        }
        public void start(){
            Thread t = new Thread(this);
            t.start();
        }
    }




    public void computerbrain()
    {
        if(gamestart==true)
        {
            if(counterplayervs==1)
            {
                keypress2=-1;
                if(player2.getLife()>=player1.getLife())
                {
                    if(player2.Player1KivunPlayer2(player1)==true)
                    {
                        if(player2.CloseX(player1)==false)
                        {

                            if(player2.getKivun()=="right")
                                keypress2=1;
                            else
                                keypress2=2;
                        }
                        if(player2.SameY(player1)==false)
                        {
                            keypress2=0;
                            if(player2.UpOrDown(player1)==true)
                                keypress2=4;
                            else
                                keypress2=3;
                        }

                    }
                    else
                    {

                        player2.setKivunToOther();
                    }
                    if(keypress2==-1)
                    {
                        //	keypress2=5;
                    }
                }


            }
        }

    }











    @Override
    // paint ��������
    public void paint(Graphics g) {
        if(offgc!=null)
        {
            offgc.drawImage(img, 0, 5, 1330, 660, this);
            if(gamestart==true)
            {
                help.Headlines(offgc);// ����� ������
                offgc.drawImage(help.Imagemaker("images/"+player1name+"/profile_right.png"),10,35,30,30,null);
                offgc.drawImage(help.Imagemaker("images/"+player2name+"/profile_right.png"),680,35,30,30,null);
                offgc.drawImage(help.Imagemaker("images/kav3.png"),148,33,205,33,null);
                offgc.drawImage(help.Imagemaker("images/kav3.png"),818,33,205,33,null);
                offgc.drawImage(help.Imagemaker("images/kav3.png"),455,33,205,33,null);
                offgc.drawImage(help.Imagemaker("images/kav3.png"),1118,33,205,33,null);
                offgc.drawImage(help.Imagemaker("images/kav1.png"),150,35,player1.getLife(),30,null);
                offgc.drawImage(help.Imagemaker("images/kav1.png"),820,35,player2.getLife(),30,null);
                offgc.drawImage(help.Imagemaker("images/kav2.png"),457,35,player1.getStamina()/15,30,null);
                offgc.drawImage(help.Imagemaker("images/kav2.png"),1120,35,player2.getStamina()/15,30,null);
                for(int i3=0;i3<aShoots.size();i3++)
                {
                    if(aShoots.get(i3).getVisible()==true)
                        aShoots.get(i3).paint(offgc);
                }
                for(int i3=0;i3<aShoots2.size();i3++)
                {
                    if(aShoots2.get(i3).getVisible()==true)
                        aShoots2.get(i3).paint(offgc);
                }
                if(player1.getY()>player2.getY())
                {
                    player2.paint(offgc);
                    player1.paint(offgc);
                }
                else
                {
                    player1.paint(offgc);
                    player2.paint(offgc);
                }
                listeningg l=new listeningg();
                l.start();
                listeningg2 l2=new listeningg2();
                if(keypress!=0)
                    computerbrain();
                l2.start();
            }
            else
            {

                offgc.drawImage(help.Imagemaker("images/choose map.png"),470,20,400,100,null);


                if(flagenter>=2)
                {
                    offgc.drawImage(help.Imagemaker("images/redcircle.png"),490,yplayervs,30,30,null);
                    offgc.drawImage(help.Imagemaker("images/playervsplayer.png"),470,485,460,95,null);
                    offgc.drawImage(help.Imagemaker("images/playervscomputer.png"),475,535,460,100,null);

                }

                if(flagenter==3)
                {
                    if(presstostart==1)
                        offgc.drawImage(help.Imagemaker("images/press enter to start.png"),520,600,350,75,null);
                    else
                        offgc.drawImage(help.Imagemaker("images/press enter to start2.png"),520,600,350,75,null);
                    presstostart*=-1;
                    //offgc.drawString("PRESS ENTER TO START", 500, 600);

                }

                offgc.setFont(new Font("TimesRoman", Font.BOLD, 20));
                offgc.drawImage(help.Imagemaker("images/ice name.png"),445,400,100,100,null);
                offgc.drawImage(help.Imagemaker("images/boss name.png"),630,400,125,60,null);
                offgc.drawImage(help.Imagemaker("images/thunder name.png"),830,400,125,60,null);
                if(flagenter>0){
                    if(counter2==counter3)
                        offgc.drawImage(help.Imagemaker("images/rnp.png"),xplayer,290,150,130,null);
                    else{
                        offgc.drawImage(help.Imagemaker("images/red.png"),xplayer,290,150,130,null);
                        offgc.drawImage(help.Imagemaker("images/purple.png"),xplayer2,290,150,130,null);}
                    offgc.drawImage(help.Imagemaker("images/choose player.png"),500,220,350,75,null);

                }
                offgc.drawImage(help.Imagemaker("images/red.png"),xbackground,85,240,136,null);
                offgc.drawImage(help.Imagemaker("images/ice/walk_right.gif"),440,300,110,106,null);
                offgc.drawImage(help.Imagemaker("images/boss/walk_right.gif"),632,300,120,106,null);
                offgc.drawImage(help.Imagemaker("images/thunder/walk_right.gif"),832,300,120,106,null);
                offgc.drawImage(help.Imagemaker("images/ice/spacial_right.gif"),460,460,50,27,null);
                offgc.drawImage(help.Imagemaker("images/boss/spacial_right.png"),660,460,60,27,null);
                offgc.drawImage(help.Imagemaker("images/thunder/spacial_right.png"),860,460,60,27,null);
                offgc.drawImage(help.Imagemaker("images/background.jpg"),440,100,200,106,null);
                offgc.drawImage(help.Imagemaker("images/background2.jpg"),740,100,200,106,null);

            }

            g.drawImage(offscreen , 0, 0, 1330, 660, null);
        }
    }

    public void run()
    {
        while(flag){

            repaint();//���� �������� paint
            try  {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(flag!=true)
            System.exit(0);
    }

    public void start(){
        Thread t = new Thread(this);
        t.start();

    }


    public void keyPressed(KeyEvent arg0)
    {
        if(gamestart==true)
        {
            //player1:
            if(player1.getLife()>0)
            {
                if(arg0.getKeyCode()==KeyEvent.VK_RIGHT&&keypress==0)
                {

                    keypress=1;

                }
                else if(arg0.getKeyCode()==KeyEvent.VK_LEFT&&keypress==0)
                {

                    keypress=2;

                }
                else if(arg0.getKeyCode()==KeyEvent.VK_UP&&keypress==0)
                {

                    keypress=3;

                }
                else if(arg0.getKeyCode()==KeyEvent.VK_DOWN&&keypress==0)
                {

                    keypress=4;

                }
                else if(arg0.getKeyCode()==KeyEvent.VK_ENTER&&keypress==0&&player1.getStamina()>100)
                {
                    help.FixPlayerPosition(player1name,player1,'-',5);
                    keypress=5;
                }
                else if(arg0.getKeyCode()==KeyEvent.VK_CONTROL&&keypress==0&&player1.getStamina()>200)
                {
                    help.FixPlayerPosition(player1name,player1,'-',6);
                    keypress=6;
                }
                if(keypress!=0&&arg0.getKeyCode()==KeyEvent.VK_SHIFT&&player1.getStamina()>2)
                {
                    player1.setSpeed(5);
                }
            }
            //playre2:
            if(player2.getLife()>0)
            {
                if((arg0.getKeyCode()==KeyEvent.VK_D&&keypress2==0))
                {

                    keypress2=1;

                }
                else if((arg0.getKeyCode()==KeyEvent.VK_A&&keypress2==0))
                {

                    keypress2=2;

                }
                else if((arg0.getKeyCode()==KeyEvent.VK_W&&keypress2==0))
                {

                    keypress2=3;

                }
                else if((arg0.getKeyCode()==KeyEvent.VK_S&&keypress2==0))
                {

                    keypress2=4;

                }
                else if((arg0.getKeyCode()==KeyEvent.VK_1)&&keypress2==0&&player2.getStamina()>100)
                {
                    help.FixPlayerPosition(player2name,player2,'-',5);
                    keypress2=5;
                }
                else if((arg0.getKeyCode()==KeyEvent.VK_3)&&keypress2==0&&player2.getStamina()>200)
                {
                    help.FixPlayerPosition(player2name,player2,'-',6);
                    keypress2=6;
                }
                if(keypress2!=0&&(arg0.getKeyCode()==KeyEvent.VK_2)&&player2.getStamina()>2)
                {
                    player2.setSpeed(5);
                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE)
            {

                flag=false;

            }
        }
        else
        {
            if(arg0.getKeyCode()==KeyEvent.VK_D)
            {
                if(flagenter==1)
                {
                    if(counter3==0||counter3==1){xplayer2+=200;counter3+=1;}
                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_A)
            {
                if(flagenter==1)
                {
                    if(counter3==1||counter3==2){xplayer2-=200;counter3-=1;}
                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_1)
            {
                if(flagenter==1)
                {
                    flagenter2=1;
                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                if(flagenter==0)
                {
                    if(counter1==0){xbackground+=300; counter1=1;}
                }
                if(flagenter==1)
                {
                    if(counter2==0||counter2==1){xplayer+=200;counter2+=1;}
                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_LEFT)
            {
                if(flagenter==0)
                {
                    if(counter1==1){xbackground-=300; counter1=0;}
                }
                if(flagenter==1)
                {
                    if(counter2==1||counter2==2){xplayer-=200;counter2-=1;}
                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_UP)
            {
                if(flagenter==2)
                {
                    if(counterplayervs>0)
                    {
                        yplayervs-=57;
                        counterplayervs-=1;
                    }

                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_DOWN)
            {
                if(counterplayervs<1)
                {
                    yplayervs+=57;
                    counterplayervs+=1;
                }
            }
            if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
            {
                if(flagenter==1){
                    if(flagenter2==1)
                        flagenter+=1;}
                else
                    flagenter+=1;
                if(flagenter==4)
                {
                    if(counter1==0)
                        levelbackground="background";
                    else
                        levelbackground="background2";
                    img=help.Imagemaker("images/"+levelbackground+".jpg");
                    gamestart=true;
                    if(counter2==0)
                    {
                        player1name="ice";
                    }
                    else if(counter2==1)
                    {
                        player1name="boss";
                    }
                    else
                    {
                        player1name="thunder";
                    }
                    if(counter3==0)
                    {
                        player2name="ice";
                    }
                    else if(counter3==1)
                    {
                        player2name="boss";
                    }
                    else
                    {
                        player2name="thunder";
                    }
                    player1=new Character ( 100,500,30,30,this.getGraphics(),120,106,help.Imagemaker("images/"+player1name+"/stand_right.png"),(help.Imagemaker("images/"+player1name+"/stand_left.png")),4,8,7,12,"right");
                    player2=new Character ( 1000,500,30,30,this.getGraphics(),120,106,help.Imagemaker("images/"+player2name+"/stand_right.png"),help.Imagemaker("images/"+player2name+"/stand_left.png"),4,8,7,12,"left");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

        if(gamestart==true)
        {
            if(player1.getLife()>0)
            {
                if(arg0.getKeyCode()==KeyEvent.VK_RIGHT||arg0.getKeyCode()==KeyEvent.VK_LEFT||arg0.getKeyCode()==KeyEvent.VK_UP||arg0.getKeyCode()==KeyEvent.VK_DOWN)
                    keypress=0;
	/*
	if(arg0.getKeyCode()==KeyEvent.VK_ENTER&&keypress==5)
	{
		if(timerattack==0)
		{
		help.FixPlayerPosition(player1name,player1,'+',5);
		keypress=0;
		timerattack=0;
		}
	}
	*/
	/*
	if(arg0.getKeyCode()==KeyEvent.VK_CONTROL&&keypress==6)
	{
		help.FixPlayerPosition(player1name,player1,'+',6);
		keypress=0;
		timershootattack=0;
	}
	*/
                if(arg0.getKeyCode()==KeyEvent.VK_SHIFT)
                    player1.setSpeed(2);
            }

            if(player2.getLife()>0)
            {
                if(arg0.getKeyCode()==KeyEvent.VK_D||arg0.getKeyCode()==KeyEvent.VK_A||arg0.getKeyCode()==KeyEvent.VK_W||arg0.getKeyCode()==KeyEvent.VK_S)
                    keypress2=0;
	/*
	if((arg0.getKeyCode()==KeyEvent.VK_1)&&keypress2==5)
	{
	help.FixPlayerPosition(player2name,player2,'+',5);
	keypress2=0;
	timerattack2=0;
	}
	if((arg0.getKeyCode()==KeyEvent.VK_3)&&keypress2==6)
	{
		help.FixPlayerPosition(player2name,player1,'+',6);
		keypress2=0;
		timershootattack2=0;
	}
	*/
                if((arg0.getKeyCode()==KeyEvent.VK_2))
                    player2.setSpeed(2);
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    class mouseL implements MouseListener {

        public void mousePressed(MouseEvent e) {


        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub


        }
    }





}












