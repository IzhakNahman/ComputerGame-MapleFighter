package code;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class HelpFunctions {	
	public String EndType(String name)
	{
		if(name=="ice"){return ".gif";}
		else return ".png";
	}
	
	public int AttackWhide(String name){
		if(name=="ice"){return 27;}
		if(name=="thunder"){return 54;}
		if(name=="boss"){return 40;}return 0;}
	
	public int AttackHigh(String name){
		if(name=="ice"){return 10;}
		if(name=="thunder"){return 14;}
		if(name=="boss"){return 15;}return 0;}
	
	
	public void FixPlayerPosition(String name,Character player1,char a,int keypress)
	{
		if(player1.getLife()>0)
		{
		if(keypress==5)
		{
		if(name=="ice")
		{
			if(a=='+'){
				player1.setX(player1.getX()+30);
				player1.setY(player1.getY()+20);}
			else{
				player1.setX(player1.getX()-30);
				player1.setY(player1.getY()-20);}
		}
		if(name=="thunder")
		{
			if(a=='+')
				player1.setX(player1.getX()+60);
			else
				player1.setX(player1.getX()-60);
		}
		if(name=="boss")
		{
			if(a=='+')
			{
				if(player1.getKivun()=="right"){
				player1.setX(player1.getX()+30);
				player1.setY(player1.getY()+40);}
				else{
					player1.setX(player1.getX()+60);
					player1.setY(player1.getY()+40);}
			}
			else
			{
				if(player1.getKivun()=="right"){
				player1.setX(player1.getX()-30);
				player1.setY(player1.getY()-40);}
				else{
					player1.setX(player1.getX()-60);
					player1.setY(player1.getY()-40);}	
			}
		}
		}
		else if(keypress==6)
		{
			if(name=="ice"){
				if(a=='+')
					player1.setX(player1.getX()+30);
				else
					player1.setX(player1.getX()-30);}
			if(name=="boss"&&player1.getKivun()=="left"){
				if(a=='+')
					player1.setX(player1.getX()+50);
				else
					player1.setX(player1.getX()-50);}
		}
		}
		else
		{
			player1.setY(player1.getY()+40);
		}
		

	}
	
	public void FixHighWidePosition(String name,int keypress,Character player1)//מסדר את גודל השחקן
	{
		if(player1.getLife()<=0)
		{
			player1.setHigh(80);
			player1.setWhide(130);
		}
		else{
		if(name=="ice")
		{
			if(keypress==0){
				player1.setHigh(106);
				player1.setWhide(120);}
			else if(keypress==1||keypress==2||keypress==3||keypress==4){
				player1.setHigh(110);
				player1.setWhide(106);	}
			else if(keypress==5)
			{
				player1.setHigh(140);
				player1.setWhide(210);	
			}
			else if(keypress==6)
			{
				player1.setHigh(112);
				player1.setWhide(210);	
			}
			
		}
		if(name=="thunder")
		{
			if(keypress==0||keypress==1||keypress==2||keypress==3||keypress==4)
			{
				player1.setHigh(106);
				player1.setWhide(120);
			}
			else if(keypress==5)
			{
				player1.setHigh(130);
				player1.setWhide(240);	
			}
			else if(keypress==6)
			{
				player1.setHigh(108);
				player1.setWhide(120);	
			}
			
		}
		if(name=="boss")
		{
			if(keypress==0||keypress==1||keypress==2||keypress==3||keypress==4)
			{
				player1.setHigh(106);
				player1.setWhide(120);
			}
			else if(keypress==5)
			{
				player1.setHigh(160);
				player1.setWhide(240);	
			}
			else if(keypress==6)
			{
				player1.setHigh(130);
				player1.setWhide(200);	
			}
		}
		}
	}
	public Image Imagemaker(String s)// מקבל את מיקום בסטרינג התמונה ומחזיר את התמונה
	{
		ImageIcon icon;
		Image i;
		icon=new ImageIcon(s);
		i=icon.getImage();
	    return i;	
	}	
	public void Headlines(Graphics offgc)// הדפסת כותרות 
	{
		offgc.setFont(new Font("TimesRoman", Font.BOLD, 20));
		offgc.drawString("Hp:", 105, 58);
		offgc.drawString("Stamina:", 360, 58);
		offgc.drawString("Hp:", 775, 58);
		offgc.drawString("Stamina:", 1030, 58);
	}
	
	
}
