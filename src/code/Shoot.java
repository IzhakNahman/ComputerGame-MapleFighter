package code;


	

	import java.awt.*;

	public class Shoot extends GameObjects {
	protected int whide;
	protected int high;
	private boolean visible;
	private Image ImageShootRight;
	private Image ImageShootLeft;
	private String kivun;
	private int ShootDamageMin;
	private int ShootDamageMax;


	public Shoot (int x, int y,int dy,int dx, Graphics g,Image ImageShootRight,Image ImageShootLeft,int whide,int high,Character player1)
	{
		super(x,y,dx,dy,g);
		this.whide=whide;//27
		this.high=high;//10
		visible=true;
		this.kivun=player1.getKivun();
		this.ImageShootRight=ImageShootRight;
		this.ImageShootLeft=ImageShootLeft;
		this.ShootDamageMin=player1.getShootDamageMin();
		this.ShootDamageMax=player1.getShootDamageMax();
	}

		// functions that set attributes
	public int getShootDamageMin()
	{
		return this.ShootDamageMin;
	}

	public int getShootDamageMax()
	{
		return this.ShootDamageMax;
	}
	public void setImageShootRight(Image ImageShootRight)
	{
		this.ImageShootRight=ImageShootRight;
	}
	public void setImageShootLeft(Image ImageShootLeft)
	{
		this.ImageShootLeft=ImageShootLeft;
	}
	public boolean getVisible()
	{
	return this.visible;
	}
	public void setVisible(boolean visible)
	{
		this.visible=visible;
	}
	public void setKivun(String s)
	{
		this.kivun=s;
	}
	public String getKivun()
	{
		return this.kivun;
	}
	public int x ()
				{ return x + this.whide; }

	public int y ()
				{ return y + this.high; }

	public int whide ()
	{ return  this.whide; }

	public int high ()
	{ return this.high; }
	
	public void moveRight ()
	{ x+=dx;}

	public void moveLeft()
	{ x-=dx;}
	public void move()
	{
		if(kivun=="right")
			this.moveRight();
		else
			this.moveLeft();
	}

	public boolean StopOrNot()
	{
		if(this.x()>=1330)
			return true;
		if(this.getX()<=0)
			return true;
		return false;
	}
	public boolean SameLineOrNot(Character player2)
	{
		if((this.getY()>=player2.getY()+45&&this.getY()<player2.y()-5)||(this.y()>=player2.getY()+45&&this.getY()<player2.y()-5))
			return true;
		else
			return false;
	}
	public boolean attackS(Character player1,Character player2)
	{
		if(this.SameLineOrNot(player2)==true)
		{
			if(this.kivun=="right"){
				if(this.x()>=player2.getX()&&this.x()<=player2.x())
					return true;}
			else{
				if(this.getX()<=player2.x()&&this.getX()>=player2.getX())
					return true;}	
		}
	    return false;
			
	}
	


		
	public void moveTo (int x, int y)
				{ this.x=x;
				this.y=y;}


	

	public void paint (Graphics g)
	{
		if(kivun=="right")
		g.drawImage(ImageShootRight, x, y, whide, high, null);
		else
		g.drawImage(ImageShootLeft, x, y, whide, high, null);
			
	}




	}




	
	

