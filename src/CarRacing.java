import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class CarRacing 
{
	public static void main(String args[])
	{
		JFrame frame=new JFrame("Car Racing");
		WorkShop w=new WorkShop();
		frame.setBounds(100,0,515,720);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(w);
		
	}
}
class WorkShop extends JPanel implements ActionListener,KeyListener
{
	public Image backGround,road,blackCar,blueCar,redCar,yellowCar,Coin;
	public int count=0;
	Timer t;
	public int[] xline=new int[8];
	public int[] yline=new int[8];
	public int[] xline2=new int[8];
	public int[] yline2=new int[8];
	public int[] xbar=new int[4];
	public int[] ybar=new int[4];
	public int xBlue,yBlue;
	public int[] xenemy=new int[4];
	public int[] yenemy=new int[4];
	public int[] xenemy2=new int[4];
	public int[] yenemy2=new int[4];
	public int[] enemyColor=new int[4];
	public int[] enemyColor2=new int[4];
	public int xcoin;
	public int ycoin;
	Random rand=new Random();
	public int Position;
	public int c=0,move=10;
	public int Score=0,flag=0;
	public WorkShop()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setVisible(true);
		setLayout(null);
		t=new Timer(8,this);
		t.start();
	}
	
	public void paintComponent(Graphics g)
	{
		if(count==0)
		{
			xline[0]=205;
			yline[0]=-140;
			for(int i=1;i<8;i++)
			{
				xline[i]=205;
				yline[i]=yline[i-1]+140;
			}
			xline2[0]=293;
			yline2[0]=-140;
			for(int i=1;i<8;i++)
			{
				xline2[i]=293;
				yline2[i]=yline2[i-1]+140;
			}
			xbar[0]=140;
			ybar[0]=70;
			xbar[1]=346;
			ybar[1]=70;
			
			xbar[2]=140;
			ybar[2]=600;
			xbar[3]=346;
			ybar[3]=600;
			xcoin=178;
			ycoin=-120;
			c=0;
			xBlue=168;
			yBlue=635;
			xenemy[0]=168;
			yenemy[0]=-44;
			for(int i=1;i<4;i++)
			{
				xenemy[i]=168;
				yenemy[i]=yenemy[i-1]-180;
			}
			xenemy2[0]=255;
			yenemy2[0]=-66;
			for(int i=1;i<4;i++)
			{
				xenemy2[i]=255;
				yenemy2[i]=yenemy2[i-1]-180;
			}
			enemyColor[0]=rand.nextInt(3);
			enemyColor2[0]=rand.nextInt(3);
		}
		ImageIcon bg=new ImageIcon(getClass().getResource("/image/bg.png"));
		backGround=bg.getImage();
		g.drawImage(backGround, 0, 0, this);
		
		ImageIcon ro=new ImageIcon(getClass().getResource("/image/newroad.png"));
		road=ro.getImage();
		g.drawImage(road, 125, 0, this);
		
		g.setColor(Color.WHITE);
		g.fillRect(248, 0, 4, 700);
		for(int i=0;i<8;i++)
		{
			g.fillRect(xline[i], yline[i], 4, 120);
			g.fillRect(xline2[i], yline2[i], 4, 120);
		}
		g.setColor(Color.GRAY);
		g.fillRect(138, 0, 20, 700);
		g.fillRect(344, 0, 19, 700);
		g.setColor(Color.DARK_GRAY);
		for(int i=0;i<2;i++) {
			g.fillRoundRect(xbar[i], ybar[i], 15, 15, 15, 15);
			g.fillRoundRect(xbar[i+2], ybar[i+2], 15, 15, 15, 15);
		}
		ImageIcon coin=new ImageIcon(getClass().getResource("/image/Coin.png"));
		Coin=coin.getImage();
		g.drawImage(Coin, xcoin, ycoin, this);
		
		ImageIcon bc=new ImageIcon(getClass().getResource("/image/Blue.png"));	
		ImageIcon rc=new ImageIcon(getClass().getResource("/image/Red.png"));
		ImageIcon bkc=new ImageIcon(getClass().getResource("/image/Black.png"));
		ImageIcon yc=new ImageIcon(getClass().getResource("/image/Yellow.png"));
		redCar=rc.getImage();
		yellowCar=yc.getImage();
		blackCar=bkc.getImage();
		for(int i=0;i<4;i++)
		{
			if(enemyColor[i]==0)
				g.drawImage(redCar, xenemy[i], yenemy[i], this);
			if(enemyColor[i]==1)
				g.drawImage(blackCar, xenemy[i], yenemy[i], this);
			if(enemyColor[i]==2)
				g.drawImage(yellowCar, xenemy[i], yenemy[i], this);
		}
		for(int i=0;i<4;i++)
		{
			if(enemyColor2[i]==0)
				g.drawImage(redCar, xenemy2[i], yenemy2[i], this);
			if(enemyColor2[i]==1)
				g.drawImage(blackCar, xenemy2[i], yenemy2[i], this);
			if(enemyColor2[i]==2)
				g.drawImage(yellowCar, xenemy2[i], yenemy2[i], this);
		}
		blueCar=bc.getImage();
		g.drawImage(blueCar, xBlue, yBlue, this);
		for(int i=0;i<4;i++)
		{
			if((xBlue>=xenemy[i]-5 && xBlue<xenemy[i]+30) && (yBlue-35==yenemy[i]) && (xBlue>=165 && xBlue<204))
			{
				GameOver(g);
			}
			if((xBlue>=xenemy[i]-30 && xBlue<xenemy[i]+30) && (yBlue-35==yenemy[i]) && (xBlue>=170 && xBlue<246))
			{
				GameOver(g);
			}
			if(xBlue<xenemy[i]+30 && (yenemy[i]+35>=yBlue && yenemy[i]<yBlue+35) && xenemy[i]+35<208)
			{
				GameOver(g);
			}
			if(xenemy[i]==210 && (xBlue>=210 && xBlue<xenemy[i]+35) && (yenemy[i]+35>=yBlue && yenemy[i]<yBlue+35))
			{
				GameOver(g);
			}
			if(xenemy[i]==210 && (xBlue+30>=xenemy[i] && xBlue<xenemy[i]+30) && (yenemy[i]+35>=yBlue && yenemy[i]<yBlue+35))
			{
				GameOver(g);
			}
			
			//Second 
			
			if((xBlue>=xenemy2[i]-30 && xBlue<=xenemy2[i]+30) && (yBlue-35==yenemy2[i]) && (xBlue>=220 && xBlue<=295))
			{
				GameOver(g);
			}
			if((xBlue>=xenemy2[i]-40 && xBlue<xenemy2[i]+35) && (yBlue-35==yenemy2[i]) && (xBlue>=265 && xBlue<340))
			{
				GameOver(g);
			}
			if(xenemy2[i]==255 && (xBlue>=256 && xBlue<xenemy2[i]+35) && (yenemy2[i]+35>=yBlue && yenemy2[i]<yBlue+35))
			{
				GameOver(g);
			}
			if(xenemy2[i]==255 && (xBlue+30>=xenemy2[i] && xBlue<xenemy2[i]+30) && (yenemy2[i]+35>=yBlue && yenemy2[i]<yBlue+35))
			{
				GameOver(g);
			}
			if(xBlue+35>=xenemy2[i] && (yenemy2[i]+35>=yBlue && yenemy2[i]<yBlue+35) && xenemy2[i]==299)
			{
				GameOver(g);
			}
		}
		
		if((xcoin>=xBlue && xcoin<=xBlue+35) && (ycoin+10>yBlue && ycoin<yBlue+45))
		{
			Score++;
			ycoin=-20;
		}
		if((xcoin+10>=xBlue && xcoin+10<=xBlue+35) && (ycoin+10>yBlue && ycoin<yBlue+45))
		{
			Score++;
			ycoin=-20;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Tahoma",Font.BOLD,14));
		g.drawString("Score: "+Score, 5, 10);
	}
	public void GameOver(Graphics g2)
	{
		t.stop();
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Tahoma", Font.BOLD, 40));
		g2.drawString("GAME OVER", 150, 300);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(ycoin+1<=665)
		{
			ycoin++;
		}
		if(ycoin==-15)
		{
			Position=rand.nextInt(4);
			if(Position==0)
			{
				xcoin=178;
			}
			if(Position==1)
			{
				xcoin=220;
			}
			if(Position==2)
			{
				xcoin=265;
			}
			if(Position==3)
			{
				xcoin=310;
			}
		}
		if(ycoin+1>665)
		{
			ycoin=-20;
		}
		for(int i=0;i<8;i++)
		{
			yline[i]+=1;
			yline2[i]+=1;
		}
		for(int i=0;i<8;i++)
		{
			if(yline[i]>700)
			{
				yline[i]=(yline[i]%700)-140;
				yline2[i]=(yline2[i]%700)-140;
			}
		}
		for(int i=0;i<4;i++)
		{
			ybar[i]+=1;
		}
		for(int i=0;i<2;i++)
		{
			if(ybar[i+2]>700 && ybar[i]>550)
			{
				ybar[i+2]=-14;
			}
			if(ybar[i]>700 && ybar[i+2]>550)
			{
				ybar[i]=-14;
			}
		}
		for(int i=0;i<4;i++)
		{
			int x;
			yenemy[i]++;
			if(yenemy[i]>675)
				yenemy[i]=-44;
			for(int j=3;j>=0;j--)
			{
				if(yenemy[i]==-44 && yenemy[i]!=yenemy[j])
				{
					x=rand.nextInt(2);
					enemyColor[i]=rand.nextInt(3);
					if(x==0 && xenemy[j]!=168)
						xenemy[i]=168;
					else if(x==1 && xenemy[j]!=210)
						xenemy[i]=210;
				}
			}
		}
		for(int i=0;i<4;i++)
		{
			int x;
			yenemy2[i]++;
			if(yenemy2[i]>675)
				yenemy2[i]=-66;
			for(int j=3;j>=0;j--)
			{
				if(yenemy2[i]==-44 && yenemy2[i]!=yenemy2[j])
				{
					x=rand.nextInt(2);
					enemyColor2[i]=rand.nextInt(3);
					if(x==1 && xenemy2[j]!=255)
						xenemy2[i]=255;
					else if(x==0 && xenemy2[j]!=299)
						xenemy2[i]=299;
				}
			}
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_UP)
		{
			count++;
			if(yBlue-move<0)
			{
				yBlue=0;
			}
			else
			{
				yBlue=yBlue-move;
			}
		}
		if(key==KeyEvent.VK_DOWN)
		{
			count++;
			if(yBlue+move>635)
			{
				yBlue=635;
			}
			else
			{
				yBlue=yBlue+move;
			}
		}
		if(key==KeyEvent.VK_RIGHT)
		{
			count++;
			if(xBlue+move>300)
			{
				xBlue=300;
			}
			else
			{
				xBlue=xBlue+move;
			}
		}
		if(key==KeyEvent.VK_LEFT)
		{
			count++;
			if(xBlue-move<166)
			{
				xBlue=166;
			}
			else
			{
				xBlue=xBlue-move;
			}
		}
		if(key==KeyEvent.VK_SPACE)
		{
			flag++;
			if(flag==1) {
				t.stop();
			}
			if(flag==2)
			{
				t.start();
				flag=0;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}