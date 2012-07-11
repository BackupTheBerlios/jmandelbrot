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
	static double xmax;
	static double xmin;
	static double ymax;
	static double ymin;
	static boolean clicktotoggle=false;
	static int wizard;
	static double xmintemp;
	static double ymintemp;
	static double xmaxtemp;
	static double ymaxtemp;
	static JFrame framer = new JFrame();
	static JLabel bleeking = new JLabel("  ----   PLEASE WAIT!");
	static JLabel zoomfactor = new JLabel("Zoom factor: 1x    ");
	static JLabel xlabel = new JLabel("J-MODE");
	static JLabel blerk = new JLabel("z => z^" + DrawPanel.power + " + " + (DrawPanel.julia? DrawPanel.balrogex + (DrawPanel.balrogey > 0? " + ": " ") + DrawPanel.balrogey + "i" : "c") + "    " + wizard + " iterations    ");
	public static void main(String[] args) {
		wizard = 1;
		Benoit.xmax=1D;
		Benoit.xmin=-2D;
		Benoit.ymax=1.5D;
		Benoit.ymin=-1.5D;
		
		final JFrame framer = new JFrame();
		JPanel bob = new JPanel();
		final JPanel buttonpane = new JPanel();
		final JPanel labelpane = new JPanel();
		final JButton evolve = new JButton("evolve");
		final JButton devolve = new JButton("devolve");
		FlowLayout blargh = new FlowLayout();
		final Random demons = new Random();
		//bob.setLayout(blargh);
		framer.getContentPane().add(BorderLayout.SOUTH, buttonpane);
		framer.getContentPane().add(BorderLayout.NORTH, labelpane);
		framer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		labelpane.setSize(100, 800);
		buttonpane.add(bob);
		labelpane.add(blerk);
		labelpane.add(zoomfactor);
		framer.setTitle("Mandelbrot 2.3");
		labelpane.add(xlabel);
		labelpane.add(bleeking);
		bleeking.setVisible(false);
		bob.setLayout(blargh);
		bob.setMaximumSize(new Dimension(70,300));
		bob.add(devolve);
		bob.add(evolve);
		xlabel.setVisible(false);
		bob.add(xlabel);
		final DrawPanel mandelbrot = new DrawPanel();
		mandelbrot.setSize(800,800);
		framer.getContentPane().add(mandelbrot);
		framer.setVisible(true);
		framer.setSize(900,900);
		class clicklistener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource().equals(evolve)){
					wizard = (int) ((int) wizard*1.5);
					mandelbrot.repaint();
					framer.requestFocus();
				}
				if (arg0.getSource().equals(devolve)){
					wizard = (int) ((int) wizard/1.5);
					mandelbrot.repaint();
					framer.requestFocus();
				}
			}
			
		}
		class MouseAction implements MouseListener{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clicktotoggle=!clicktotoggle;
				xlabel.setVisible(clicktotoggle);
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
				if(clicktotoggle){
					DrawPanel.julia = true;
					mousex=arg0.getX();
					mousey=arg0.getY();
					DrawPanel.balrogex=(float) DrawPanel.conXToUnit(mousex);
					DrawPanel.balrogey=(float) DrawPanel.conYToUnit(mousey);
					blerk.setText("z => z^" + DrawPanel.power +(DrawPanel.balrogex > 0? " + ": " ") + (DrawPanel.julia? DrawPanel.balrogex + (DrawPanel.balrogey > 0? " + ": " ") + DrawPanel.balrogey + "i" : " + c") + "    " + wizard + " iterations    ");
					mandelbrot.repaint();
					framer.requestFocus();
				}
			}
			
		}
		bleeke blEeke = new bleeke();
		class KeyboardAction implements KeyListener{

			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println(arg0.getKeyCode());
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
					wizard = (int) ((int) wizard*1.6);
				}
				if(arg0.getKeyCode()==68){
					if(wizard >2){
						wizard = (int) ((int) wizard/1.5);
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
				blerk.setText("z => z^" + DrawPanel.power +(DrawPanel.balrogex > 0? " + ": " ") + (DrawPanel.julia? DrawPanel.balrogex + (DrawPanel.balrogey > 0? " + ": " ") + DrawPanel.balrogey + "i" : " + c") + "    " + wizard + " iterations    ");
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
		wizard=32;
		MouseAction demon = new MouseAction();
		mandelbrot.addMouseListener(demon);
		mandelbrot.addMouseMotionListener(blEeke);
		clicklistener balrog = new clicklistener();
		evolve.addActionListener(balrog);
		devolve.addActionListener(balrog);
		framer.addKeyListener(vizard);
		framer.setFocusable(true);
		framer.setFocusableWindowState(true);
		framer.requestFocus();
	}
}
