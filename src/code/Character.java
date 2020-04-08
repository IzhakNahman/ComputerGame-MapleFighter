package code;


import java.awt.*;


public class Character extends GameObjects{
protected int whide;
protected int high;
private Image characterright;
private Image characterleft;
private int ShootDamageMin;
private int ShootDamageMax;
private int KnifeDamageMin;
private int KnifeDamageMax;
private int life;
private int stamina;
private String kivun;
private int speed;

public Character (int x, int y, int dx, int dy, Graphics g,int whide,int high,Image Right,Image Left,int ShootDamageMin,int ShootDamageMax,int KnifeDamageMin,int KnifeDamageMax, String kivun)
{
	super(x,y,dx,0,g);
	this.life=200;
	this.stamina=3000;
	this.whide=whide;
	this.high=high;
	this.ShootDamageMin=ShootDamageMin;
	this.ShootDamageMax=ShootDamageMax;
	this.KnifeDamageMin=KnifeDamageMin;
	this.KnifeDamageMax=KnifeDamageMax;
	this.kivun=kivun;
	this.characterright=Right;
    this.characterleft=Left;
    this.speed =2;
}
public void setSpeed(int n)
{
	this.speed=n;
}
public int getSpeed()
{
	return this.speed;
}


public void setKivun(String s)
{
	this.kivun=s;
}

public void setKivunToOther()
{
	if(this.kivun=="right")
		this.kivun="left";
	else
		this.kivun="right";
}

public String getKivun()
{
	return this.kivun;
}


public void setShootDamageMin(int a)
{
	this.ShootDamageMin=a;
}
public int getShootDamageMin()
{
	return this.ShootDamageMin;
}


public void setShootDamageMax(int a)
{
	this.ShootDamageMax=a;
}
public int getShootDamageMax()
{
	return this.ShootDamageMax;
}


public void setKnifeDamageMin(int a)
{
	this.KnifeDamageMin=a;
}
public int getKnifeDamageMin()
{
	return this.KnifeDamageMin;
}


public void setKnifeDamageMax(int a)
{
	this.KnifeDamageMax=a;
}
public int getKnifeDamageMax()
{
	return this.KnifeDamageMax;
}


public void setLife(int a)
{
	this.life=a;
}
public int getLife()
{
	return this.life;
}

public void setStamina(int a)
{
	this.stamina=a;
}
public int getStamina()
{
	return this.stamina;
}

public void setCharacterright(Image i)
{
	this.characterright=i;
}
public void setCharacterleft(Image i)
{
	this.characterleft=i;
}
public Image getCharacterrigt()
{
	return this.characterright;
}
public Image getCharacterleft()
{
	return this.characterleft;
}	

public void setMotion (int ndx, int ndy)
			{ super.setDx(ndx); super.setDy(ndy); }

	
public int x ()
			{ return x + this.whide; }

public int y ()
			{ return y + this.high; }

public int getWhide ()
{ return  this.whide; }

public int getHigh ()
{ return this.high; }

public void setWhide (int whide)
{   this.whide=whide; }

public void setHigh (int high)
{ this.high=high; }


public void moveRight ()
			{ x+=speed;}

public void moveLeft()
{ x-=speed;}

public void moveUp ()
{ y-=speed;}

public void moveDown()
{ y+=speed;}

public Boolean HitOrNot(Character player2)
{
	if(this.kivun=="right")
	{
		if((this.getX()+this.getWhide()-20>=player2.getX())&&(this.getX()+this.getWhide()<=player2.getX()+player2.getWhide()))
		{
			if((this.getY()+this.getHigh()>=player2.getY()+player2.getHigh()-10)&&(this.getY()+this.getHigh()<=player2.getY()+player2.getHigh()+30))
				return true;
		}
	}
	else
	{
		if((player2.getX()+player2.getWhide()-20>=this.getX())&&(player2.getX()+player2.getWhide()<=this.getX()+this.getWhide()))
		{
			if((this.getY()+this.getHigh()>=player2.getY()+player2.getHigh()-10)&&(this.getY()+this.getHigh()<=player2.getY()+player2.getHigh()+30))
				return true;
		}	
	}
	return false;
}

public Boolean SameY(Character player2)
{
	if((this.getY()+this.getHigh()>=player2.getY()+player2.getHigh()-10)&&(this.getY()+this.getHigh()<=player2.getY()+player2.getHigh()+30))
		return true;
	else 
		return false;
}

public Boolean SameX(Character player2)
{
	if((this.getX()+this.getWhide()>=player2.getX()+player2.getWhide()-10)&&(this.getX()+this.getWhide()<=player2.getX()+player2.getWhide()+30))
		return true;
	else 
		return false;
}
public Boolean CloseX(Character player2)
{
	if(player2.getKivun()=="left")
	{
	if((player2.getX()-this.x()<20))
		return true;
	}
	else
	{	
		if(this.getX()-player2.x()<20)
			return true;
	}
	 
		return false;
}
public Boolean UpOrDown(Character player2)
{
	if(player2.getY()>this.getY())
		return true;//down
	else return false;
}

public Boolean Player1KivunPlayer2(Character player2)
{
	if(this.kivun=="right")
	{
			if(player2.getX()>this.getX())
				return true;
	}
	if(this.kivun=="left")
	{
			if(player2.x()<this.x())
				return true;
	}
	return false;
		
}



public void paint (Graphics g)
{
	if(this.kivun=="right")
	g.drawImage(this.characterright, x, y, whide, high, null);
	else
		g.drawImage(this.characterleft, x, y, whide, high, null);
		
	
}


}







