import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class DrawPanel extends JPanel {
		static double xpp;
		static double ypp;
		static double currentx;
		static double currenty;
		static double currentxt;
		static double newx;
		static double newy;
		static double wizaredx;
		static double wizaredy;
		static double mu;
		static float balrogex = 0;
		static float balrogey = 0;
		static double count;
		static float power = 2;
		static int currentcolourr;
		static int currentcolourg;
		static int maxcolours=9;
		static int curcolour=0;
		static int currentcolourb;
		static boolean fin = false;
		static boolean lowres=true;
		static boolean julia = false;
		static boolean block;
		static BufferedImage bimage;
		public void paintComponent(Graphics g) {
			Random bob = new Random();
			Graphics2D g2d = (Graphics2D)g;
			xpp = (Benoit.xmax - Benoit.xmin)/800;
				ypp = (Benoit.ymax - Benoit.ymin)/800;
				g2d.setColor(new Color(255,255,255));
				g2d.fillRect(0, 0, 800, 800);
				g2d.setColor(new Color(0,0,0));
					for(int d = 0;d<800;){
						for(int e = 0;e<800;){
							g2d.setColor(new Color(0,0,0));
							block = isInSet(conXToUnit(d),conYToUnit(e));
							if (count == Benoit.wizard) fin = true;
							currentcolourr=(int) (4*count*count % 255);
							currentcolourg=(int) (2*count*count*count % 255);
							currentcolourb=(int) (4*count*count*count % 255);
								g2d.setColor(new Color((int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) *255)));
									mu = (count+1) - Math.log10(Math.log10(Math.sqrt(wizaredx*wizaredx + wizaredy*wizaredy)))/Math.log10(power);
									try{
										if(curcolour==0){
											g2d.setColor(new Color(0,(int) (mu/Benoit.wizard * 254),(int) (mu/Benoit.wizard * 254)));
										}
										if(curcolour==1){
											g2d.setColor(new Color(0,(int) (mu/Benoit.wizard * 254),0));
										}
										if(curcolour==2){
											g2d.setColor(new Color((int) ((mu+1)/Benoit.wizard * 254),0,(int) (mu/Benoit.wizard * 254)));
										}
										if(curcolour==3){
											g2d.setColor(new Color((int) (mu/Benoit.wizard * 254),0,0));
										}
										if(curcolour==4){
											g2d.setColor(new Color(currentcolourr,currentcolourg,currentcolourb));
										}
										if(curcolour==5){
											g2d.setColor(new Color((int) (Math.sin(count) * 127 + 127),0,0));
										}
										if(curcolour==8){
											g2d.setColor(new Color((int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) *255)));
										}
									}
									catch(Exception ex){
										
									}
								if(curcolour!=6 && curcolour !=7){
									g2d.fillRect(d, e, lowres? 8:1, lowres? 8:1);
								}
								else{
									if(block){
										if(curcolour == 6){
											g2d.setColor(new Color(bob.nextInt(255),bob.nextInt(255),bob.nextInt(255)));
											g2d.fillRect(d, e, lowres? 8:1, lowres? 8:1);
										}
										if(curcolour == 7){
											String bleke = String.valueOf((char) bob.nextInt(127));
											g2d.setColor(new Color(bob.nextInt(255),bob.nextInt(255),bob.nextInt(255)));
											g2d.drawString(bleke, d-3,e-3);
										}
										
									}
								}
								
								if(lowres){
									e+=8;
								}
								else{
									e++;
								}
						}
						if(lowres){
							d+=8;
						}
						else{
							d++;
						}
						
					}
				}
		public static boolean isInSet(double x, double y){
			count=0;
			currentx = x;
			currenty = y;
			if(julia){
				y = balrogey;
				x = balrogex;
			}
			for(int i = 0; i<Benoit.wizard; i++){
				currentxt = (powerComplexReal(currentx, currenty, power)) + x;
				currenty = (powerComplexImaj(currentx, currenty,power)) + y;
				currentx=currentxt;
				count++;
				if(Math.pow(currentx, 2) + Math.pow(currenty, 2) > 4){
					wizaredx=currentx;
					wizaredy=currenty;
					return false;
				}
				currentcolourr=4*i*i % 255;
				currentcolourg=2*i*i*i % 255;
				currentcolourb=4*i*i*i % 255;
			}
			return true;
		}
		public static double conXToUnit(double xpix){
			return (xpix * xpp) + Benoit.xmin;
		}
		public static double conYToUnit(double ypix){
			return (ypix * ypp) + Benoit.ymin;
		}
		public static void multiplyComplexNumber(double x1, double y1, double x2, double y2){
			newx = x1*x2 - y1*y2;
			newy = x1*y2 + x2*y1;
		}
		public static double powerComplexReal(double a, double b, double n){
			if(n==2){
				return a*a - b*b;
			}
			return Math.pow(a*a + b*b, n/2)*Math.cos(n*Math.atan(b/a));
		}
		public static double powerComplexImaj(double a, double b, double n){
			if(n==2){
				return 2*a*b;
			}
			return Math.pow(a*a + b*b, n/2)*Math.sin(n*Math.atan(b/a));
		}
		static void save(String filename,int x, int y){
			Random bob = new Random();
		    BufferedImage bimage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		    Graphics2D g2d = bimage.createGraphics();
		    xpp = (Benoit.xmax - Benoit.xmin)/x;
			ypp = (Benoit.ymax - Benoit.ymin)/y;
			g2d.setColor(new Color(255,255,255));
			g2d.fillRect(0, 0, x, y);
			g2d.setColor(new Color(0,0,0));
			for(int d = 0;d<x;d++){
				for(int e = 0;e<y;e++){
					g2d.setColor(new Color(0,0,0));
					block = isInSet(conXToUnit(d),conYToUnit(e));
					if (count == Benoit.wizard) fin = true;
					currentcolourr=(int) (4*count*count % 255);
					currentcolourg=(int) (2*count*count*count % 255);
					currentcolourb=(int) (4*count*count*count % 255);
						g2d.setColor(new Color((int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) *255)));
							mu = (count+1) - Math.log10(Math.log10(Math.sqrt(wizaredx*wizaredx + wizaredy*wizaredy)))/Math.log10(power);
							try{
								if(curcolour==0){
									g2d.setColor(new Color(0,(int) (mu/Benoit.wizard * 254),(int) (mu/Benoit.wizard * 254)));
								}
								if(curcolour==1){
									g2d.setColor(new Color(0,(int) (mu/Benoit.wizard * 254),0));
								}
								if(curcolour==2){
									g2d.setColor(new Color((int) ((mu+1)/Benoit.wizard * 254),0,(int) (mu/Benoit.wizard * 254)));
								}
								if(curcolour==3){
									g2d.setColor(new Color((int) (mu/Benoit.wizard * 254),0,0));
								}
								if(curcolour==4){
									g2d.setColor(new Color(currentcolourr,currentcolourg,currentcolourb));
								}
								if(curcolour==5){
									g2d.setColor(new Color((int) (Math.sin(count) * 127 + 127),0,0));
								}
								if(curcolour==8){
									g2d.setColor(new Color((int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) * 255),(int) ((count/(Benoit.wizard)) *255)));
								}
							}
							catch(Exception ex){
								
							}
						//g2d.setColor(new Color(bob.nextInt(255),bob.nextInt(255),bob.nextInt(255)));
						if(curcolour!=6){
							g2d.fillRect(d, e, 1, 1);
						}
						else{
							if(block){
								g2d.fillRect(d, e, 1, 1);
							}
						}
						
					
				}
			}g2d.dispose();			    
			    RenderedImage rendImage = bimage;
			    File file = new File(filename + ".png");
			    try {
					ImageIO.write(rendImage, "png", file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}
