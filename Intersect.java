// Intersect.java
/**
* @author: Josimar H. Lopes
* @year: 2010/04/12
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class Intersect extends JFrame implements Runnable
{
	private ImageIcon switchImages;
	private Dimension screenSize;
	private File imageFolder;

	public Intersect()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		switchImages = new ImageIcon( "intersect.jpg" );

		add( new IntersectLoad() );

		setUndecorated( true );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize( (int)screenSize.getWidth(), (int)screenSize.getHeight() );
		setVisible( true );
	}

	public void run()
	{
		imageFolder = new File( "/home/lopes/Projects/Intersect/sample" );

		String path = imageFolder.getPath() + "/";
		System.out.println( path );

		String[] directory = imageFolder.list();

		for( String file : directory )
		{
			try
			{
				Thread.sleep( 20 );
			}
			catch( InterruptedException exception )
			{
				exception.printStackTrace();
			}
			switchImages = new ImageIcon( "" + path + file );
			repaint();
		}
		System.exit( 1 );
	}

	private class IntersectLoad extends JPanel
	{
		public void paintComponent( Graphics g )
		{
			super.paintComponent( g );

			if( switchImages.getIconWidth() > getWidth() || switchImages.getIconHeight() > getHeight() )
			{
				g.drawImage( switchImages.getImage(), 0, 0, (int)getWidth(), 
					(int)getHeight(),this );
			}
			else
			{
				g.drawImage( switchImages.getImage(), 
					(int)(getWidth()-switchImages.getIconWidth())/2, 
					(int)(getHeight()-switchImages.getIconHeight())/2,this );
			}
		}
	}
	public static void main( String[] args )
	{
		new Thread( new Intersect() ).start();
	}
}
