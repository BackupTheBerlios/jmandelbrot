import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import java.util.Random;

import javax.swing.*;


public class Benoit {
	/**
	 * @param args
	 */
	//Variables to hold the area to graph
	static double xmax;
	static double xmin;
	static double ymax;
	static double ymin;
	static double xmaxtemp;
	static double xmintemp;
	static double ymaxtemp;
	static double ymintemp;
	//Tell whether we are graphing Julia fractals or not
	static boolean juliamode=false;
	static int iterationcount;
	static JFrame framer = new JFrame();
	static JLabel zoomfactor = new JLabel("Zoom factor: 1x    ");
	static JLabel xlabel = new JLabel("J-MODE");
	static JLabel blerk = new JLabel("z => z^" + DrawPanel.power + " + " + (DrawPanel.julia? DrawPanel.balrogex + (DrawPanel.balrogey > 0? " + ": " ") + DrawPanel.balrogey + "i" : "c") + "    " + iterationcount + " iterations    ");
	public static void main(String[] args) {
		iterationcount = 1;
		Benoit.xmax=1D;
		Benoit.xmin=-2D;
		Benoit.ymax=1.5D;
		Benoit.ymin=-1.5D;
		JPanel bob = new JPanel();
		final JPanel labelpane = new JPanel();
		FlowLayout blargh = new FlowLayout();
		final Random demons = new Random();
		//bob.setLayout(blargh);
		framer.getContentPane().add(BorderLayout.NORTH, labelpane);
		framer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labelpane.setSize(100, 800);
		labelpane.add(blerk);
		labelpane.add(zoomfactor);
		framer.setTitle("JMandelbrot prealpha");
		labelpane.add(xlabel);
		bob.setLayout(blargh);
		bob.setMaximumSize(new Dimension(70,300));
		xlabel.setVisible(false);
		bob.add(xlabel);
		final DrawPanel mandelbrot = new DrawPanel();
		mandelbrot.setSize(800,800);
		framer.getContentPane().add(mandelbrot);
		framer.setVisible(true);
		framer.setSize(802,900);
		class MouseAction implements MouseListener{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				juliamode=!juliamode;
				xlabel.setVisible(juliamode);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		}
		class bleeke implements MouseMotionListener{
			int mousex;
			int mousey;
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(juliamode){
					DrawPanel.julia = true;
					mousex=arg0.getX();
					mousey=arg0.getY();
					DrawPanel.balrogex=(float) DrawPanel.conXToUnit(mousex);
					DrawPanel.balrogey=(float) DrawPanel.conYToUnit(mousey);
					blerk.setText("z => z^" + DrawPanel.power +(DrawPanel.balrogex > 0? " + ": " ") + (DrawPanel.julia? DrawPanel.balrogex + (DrawPanel.balrogey > 0? " + ": " ") + DrawPanel.balrogey + "i" : " + c") + "    " + iterationcount + " iterations    ");
					mandelbrot.repaint();
					framer.requestFocus();
				}
			}
			
		}
		bleeke blEeke = new bleeke();
		class KeyboardAction implements KeyListener{

			@Override
			public void keyPressed(KeyEvent arg0) {
				//For development
				//System.out.println(arg0.getKeyCode());
				xmaxtemp = xmax;
				xmintemp = xmin;
				ymaxtemp = ymax;
				ymintemp = ymin;
				DrawPanel.lowres=true;
				mandelbrot.repaint();
				if(arg0.getKeyCode()==37){
					xmin-=(xmaxtemp-xmintemp)/10;
					xmax-=(xmaxtemp-xmintemp)/10;
				}
				if(arg0.getKeyCode()==39){
					xmin+=(xmaxtemp-xmintemp)/10;
					xmax+=(xmaxtemp-xmintemp)/10;
				}
				if(arg0.getKeyCode()==40){
					ymin+=(ymaxtemp-ymintemp)/10;
					ymax+=(ymaxtemp-ymintemp)/10;
				}
				if(arg0.getKeyCode()==38){
					ymin-=(ymaxtemp-ymintemp)/10;
					ymax-=(ymaxtemp-ymintemp)/10;
				}
				if(arg0.getKeyCode()==32){
					DrawPanel.lowres=false;
				}
				if(arg0.getKeyCode()==69){
					iterationcount = (int) ((int) iterationcount*1.6);
				}
				if(arg0.getKeyCode()==68){
					if(iterationcount >2){
						iterationcount = (int) ((int) iterationcount/1.5);
					}
				}
				if(arg0.getKeyCode()==83){
					try{
						DrawPanel.save(JOptionPane.showInputDialog("Please enter the filename:"), Integer.parseInt(JOptionPane.showInputDialog("Please enter the x res:")), Integer.parseInt(JOptionPane.showInputDialog("Please enter the y res:")));
					}
					catch(Exception e){
						
					}
				}
				if(arg0.getKeyCode()==88){
					xmaxtemp = xmax;
					xmintemp = xmin;
					ymaxtemp = ymax;
					ymintemp = ymin;

					xmin+=(xmaxtemp-xmintemp)/4;
					xmax-=(xmaxtemp-xmintemp)/4;
					ymin+=(ymaxtemp-ymintemp)/4;
					ymax-=(ymaxtemp-ymintemp)/4;
					zoomfactor.setText("Zoom factor: " + (int)( 3/(Benoit.xmax - Benoit.xmin) )+ "x    ");
					Benoit.framer.requestFocus();
				}
				if(arg0.getKeyCode()==90){
						xmaxtemp = xmax;
						xmintemp = xmin;
						ymaxtemp = ymax;
						ymintemp = ymin;
						xmin-=(xmaxtemp-xmintemp)/2;
						xmax+=(xmaxtemp-xmintemp)/2;
						ymin-=(ymaxtemp-ymintemp)/2;
						ymax+=(ymaxtemp-ymintemp)/2;
						zoomfactor.setText("Zoom factor: " + (int)( 3/(Benoit.xmax - Benoit.xmin) )+ "x    ");
						framer.requestFocus();
				}
				if(arg0.getKeyCode()==46){
					DrawPanel.curcolour++;
					if(DrawPanel.curcolour==DrawPanel.maxcolours){
						DrawPanel.curcolour=0;
					}
				}
				if(arg0.getKeyCode()==44){
					DrawPanel.curcolour--;
					if(DrawPanel.curcolour==-1){
						DrawPanel.curcolour=(DrawPanel.maxcolours-1);
					}
				}
				if(arg0.getKeyCode()==77){
					DrawPanel.julia=false;
				}
				if(arg0.getKeyCode()==74){
					DrawPanel.julia=true;
					DrawPanel.balrogex=(float) (demons.nextBoolean()? demons.nextDouble()*1.5 : -demons.nextDouble()*3);
					DrawPanel.balrogey=(float) (demons.nextBoolean()? demons.nextDouble()*1.5 : -demons.nextDouble()*3);
					
				}
				if(arg0.getKeyCode()==91){
					DrawPanel.power-=0.01;
				}
				if(arg0.getKeyCode()==93){
					DrawPanel.power+=0.01;
				}
				blerk.setText("z => z^" + DrawPanel.power +(DrawPanel.balrogex > 0? " + ": " ") + (DrawPanel.julia? DrawPanel.balrogex + (DrawPanel.balrogey > 0? " + ": " ") + DrawPanel.balrogey + "i" : " + c") + "    " + iterationcount + " iterations    ");
				mandelbrot.repaint();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		}
		KeyboardAction vizard =  new KeyboardAction();
		//balrog2.run();
		iterationcount=32;
		MouseAction demon = new MouseAction();
		mandelbrot.addMouseListener(demon);
		mandelbrot.addMouseMotionListener(blEeke);
		framer.addKeyListener(vizard);
		framer.setFocusable(true);
		framer.setFocusableWindowState(true);
		framer.requestFocus();
	}
}
