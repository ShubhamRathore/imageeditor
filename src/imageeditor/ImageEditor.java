/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageeditor;
/**
 *
 * @author Shubham
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import java.awt.geom.AffineTransform;
import javax.swing.KeyStroke;

public class ImageEditor extends Component implements ActionListener{

    /**
     * @param args the command line arguments........
     */
    
   static JFrame home=new JFrame("Image Editor");
   ImageIcon title_image = new ImageIcon(getClass().getResource("images/title_image.png"));
   
   static JInternalFrame image_frame;
   ImageIcon frame_image = new ImageIcon(getClass().getResource("images/frame_image.jpg"));
   JMenuBar menu_bar=new JMenuBar();
   
   
    ///////////////////////////Setting FILE MENUUUU/////////////////////////
    JMenu file=new JMenu("File");
    //ImageIcon iconNew = new ImageIcon(getClass().getResource("new.png"));
    ImageIcon iconOpen = new ImageIcon(getClass().getResource("images/Folder Open.png"));
    ImageIcon iconSaveas = new ImageIcon(getClass().getResource("images/Save-as.png"));  
    ImageIcon iconExit = new ImageIcon(getClass().getResource("images/cancel.png"));
    ImageIcon iconSave = new ImageIcon(getClass().getResource("images/save.png"));
   
    
    JMenuItem open=new JMenuItem("Open",iconOpen);
    
    JMenuItem save=new JMenuItem("Save",iconSave);
    JMenuItem saveas=new JMenuItem("Save As",iconSaveas);
    JMenuItem exit=new JMenuItem("Exit",iconExit);
    
    
    
    ////////////////////////////Edit menu//////////////////////////////////
    JMenu edit=new JMenu("Edit");
    ImageIcon iconUndo = new ImageIcon(getClass().getResource("images/Undo-icon.png"));
    ImageIcon iconRedo = new ImageIcon(getClass().getResource("images/Redo-icon.png"));
    JMenuItem undo=new JMenuItem("Undo",iconUndo);
    JMenuItem redo=new JMenuItem("Redo",iconRedo);
    /////////////////////////// Picture menu//////////////////////////////
    JMenu picture=new JMenu("Picture");
    JMenu effects=new JMenu("Effects");
    JMenuItem grayscale=new JMenuItem("Grayscale");
    JMenuItem negative=new JMenuItem("Negative");
    JMenuItem brightness=new JMenuItem("Brightness");
    JMenuItem sharpen=new JMenuItem("Sharpening");
    JMenuItem rotation=new JMenuItem("Rotation");
    JMenuItem blur=new JMenuItem("Blur");
    JMenuItem reset=new JMenuItem("Reset Image");
    
    ///////////////////////////View Menu///////////////////////////////////
    JMenu view=new JMenu("View");
    JMenuItem zoom_in=new JMenuItem("Zoom In");
    JMenuItem zoom_out=new JMenuItem("Zoom Out");
    JMenuItem full_screen=new JMenuItem("Full Screen");
    ///////////// Help Menu////////////////////////////////////
    JMenu help=new JMenu("Help");
    JMenuItem view_help=new JMenuItem("View Help");
    JMenuItem about_us=new JMenuItem("About us");
    
     // creating Tooll barr1........................................... 
    JToolBar tool_bar=new JToolBar("Toolbar", JToolBar.HORIZONTAL);
     ImageIcon iconOpenButton = new ImageIcon(getClass().getResource("toolbar_images/Folder Open.png"));
      ImageIcon iconUndoButton = new ImageIcon(getClass().getResource("toolbar_images/Undo-icon.png"));
       ImageIcon iconRedoButton = new ImageIcon(getClass().getResource("toolbar_images/Redo-icon.png"));
        ImageIcon iconCutButton = new ImageIcon(getClass().getResource("toolbar_images/cut-xxl.png"));
         ImageIcon iconCopyButton = new ImageIcon(getClass().getResource("toolbar_images/Copy.png"));
          ImageIcon iconPasteButton = new ImageIcon(getClass().getResource("toolbar_images/Paste.png"));
    JButton openButton=new JButton(iconOpenButton);
    JButton undoButton=new JButton(iconUndoButton);
    JButton redoButton=new JButton(iconRedoButton);
    JButton cutButton=new JButton(iconCutButton);
    JButton copyButton=new JButton(iconCopyButton);
    JButton pasteButton=new JButton(iconPasteButton);
    ////////////////Creating toolbox 2 vertical//////////////////////////
   JPanel toolbox=new JPanel();
   ImageIcon toolbox_blur = new ImageIcon(getClass().getResource("tool_box_images/blur.png"));
   ImageIcon toolbox_crop = new ImageIcon(getClass().getResource("tool_box_images/crop.png"));
   ImageIcon toolbox_ellipse = new ImageIcon(getClass().getResource("tool_box_images/ellipse.png"));
   ImageIcon toolbox_eraser = new ImageIcon(getClass().getResource("tool_box_images/eraser.png"));
   ImageIcon toolbox_extrcfil = new ImageIcon(getClass().getResource("tool_box_images/extrcfil.png"));
   ImageIcon toolbox_eyedropr = new ImageIcon(getClass().getResource("tool_box_images/eyedropr.png"));
   ImageIcon toolbox_lasso = new ImageIcon(getClass().getResource("tool_box_images/lasso.png"));
   ImageIcon toolbox_line = new ImageIcon(getClass().getResource("tool_box_images/line.png"));
   ImageIcon toolbox_pencil = new ImageIcon(getClass().getResource("tool_box_images/pencil.png"));
   ImageIcon toolbox_pntbrush = new ImageIcon(getClass().getResource("tool_box_images/pntbrush.png"));
   ImageIcon toolbox_polygon = new ImageIcon(getClass().getResource("tool_box_images/polygon.png"));
   ImageIcon toolbox_polylaso = new ImageIcon(getClass().getResource("tool_box_images/polylaso.png"));
   ImageIcon toolbox_sampcolr = new ImageIcon(getClass().getResource("tool_box_images/sampcolr.png"));
   ImageIcon toolbox_select = new ImageIcon(getClass().getResource("tool_box_images/select.png"));
    
   JButton blur_button=new JButton("",toolbox_blur);
   JButton crop_button=new JButton("",toolbox_crop);
   JButton ellipse_button=new JButton("",toolbox_ellipse);
   JButton eraser_button=new JButton("",toolbox_eraser);
   JButton extrcfil_button=new JButton("",toolbox_extrcfil);
   JButton eyedropr_button=new JButton("",toolbox_eyedropr);
   JButton lasso_button=new JButton("",toolbox_lasso);
   JButton line_button=new JButton("",toolbox_line);
   JButton pencil_button=new JButton("",toolbox_pencil);
   JButton pntbrush_button=new JButton("",toolbox_pntbrush);
   JButton polygon_button=new JButton("",toolbox_polygon);
   JButton polylaso_button=new JButton("",toolbox_polylaso);
   JButton sampcolr_button=new JButton("",toolbox_sampcolr);
   JButton select_button=new JButton("",toolbox_select);

                        
         
  
   
   
   
      
          //------------------------------------------------
   JPanel toolbox1=new JPanel();
   //-----------------------------
   static String file_name=null;
   
   //---------------------------------------

    ImageEditor()
    {
        
       home.setJMenuBar(menu_bar); 
       menu_bar.setBackground(Color.BLACK);
       //Creating Tool bar1.................................
       openButton.setToolTipText("open");
       tool_bar.add(openButton);
      // tool_bar1.add(Box.createHorizontalStrut(10));
       tool_bar.add(undoButton);
       undoButton.setToolTipText("undo");
       
       tool_bar.add(redoButton);
       redoButton.setToolTipText("redo");
       
       tool_bar.add(pasteButton);
       pasteButton.setToolTipText("paste");
       
       tool_bar.add(cutButton);
       cutButton.setToolTipText("cut");
       
       tool_bar.add(copyButton);
       copyButton.setToolTipText("copy");
       // Creating Toolbar2///////////////////////////////////////////
      
        
       
       // Menu items for FILE menu/////////////////////////////////////
       file.setForeground(Color.BLACK);
       file.setMnemonic('F');
       menu_bar.add(file);
       file.add(open);
       file.add(save);
       file.add(saveas);
       file.addSeparator();
       file.add(exit);
       //Menu items for EDIT menu /////////////////////////////////////
       edit.setForeground(Color.BLACK);
       edit.setMnemonic('E');
       menu_bar.add(edit);
       edit.add(undo);
       edit.add(redo);
       //Menu items for Picture menu//////////////////////////////////
       picture.setForeground(Color.BLACK);
       picture.setMnemonic('P');
       menu_bar.add(picture);
       picture.add(effects);
       effects.add(grayscale);
       effects.add(negative);
       effects.add(sharpen);
       effects.add(blur);
       effects.add(rotation);
       effects.add(brightness);
       sharpen.addActionListener(this);
       blur.addActionListener(this);
       negative.addActionListener(this);
       rotation.addActionListener(this);
       brightness.addActionListener(this);
       picture.add(reset);
       reset.addActionListener(this);
       grayscale.addActionListener(this);
       // Menu Items for VIEW Menu///////////////////////////////////
       view.setForeground(Color.BLACK);
       view.setMnemonic('V');
       menu_bar.add(view);
       view.add(zoom_in);
       view.add(zoom_out);
       view.addSeparator();
       view.add(full_screen);
       //Menu items for Help menu////////////////////////////////////
       help.setForeground(Color.BLACK);
       help.setMnemonic('H');
       menu_bar.add(help);
       help.add(view_help);
       help.add(about_us);
       
       //////////////////////////@Shubham Rathore///////////////////////////
       open.addActionListener(this);
       open.setAccelerator(KeyStroke.getKeyStroke('O', CTRL_DOWN_MASK));
       openButton.addActionListener(this);
       exit.addActionListener(this);
       exit.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
       ////////////////////////////////////////////////
       saveas.addActionListener(this);
       saveas.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
       //////////////////////////////////////////////////tool_bar_panel....................
       
       
       home.setSize(1366, 730);
       blur_button.setPreferredSize(new Dimension(30, 30));
       crop_button.setPreferredSize(new Dimension(30, 30));
       ellipse_button.setPreferredSize(new Dimension(30, 30));
       eraser_button.setPreferredSize(new Dimension(30, 30));
       extrcfil_button.setPreferredSize(new Dimension(30, 30));
       eyedropr_button.setPreferredSize(new Dimension(30, 30));
       lasso_button.setPreferredSize(new Dimension(30, 30));
       line_button.setPreferredSize(new Dimension(30, 30));
       pencil_button.setPreferredSize(new Dimension(30, 30));
       pntbrush_button.setPreferredSize(new Dimension(30, 30));
       polygon_button.setPreferredSize(new Dimension(30, 30));
       polylaso_button.setPreferredSize(new Dimension(30, 30));
       sampcolr_button.setPreferredSize(new Dimension(30, 30));
       select_button.setPreferredSize(new Dimension(30, 30));
       

              
       FlowLayout flow = new FlowLayout();
       toolbox.setLayout(flow);
       flow.setHgap(2);
       flow.setVgap(2);
       toolbox.add(blur_button);
       toolbox.add(crop_button);
       toolbox.add(ellipse_button);
       toolbox.add(eraser_button);
       toolbox.add(extrcfil_button);
       toolbox.add(eyedropr_button);
       toolbox.add(lasso_button);
       toolbox.add(line_button);
       toolbox.add(pencil_button);
       toolbox.add(pntbrush_button);
       toolbox.add(polygon_button);
       toolbox.add(polylaso_button);
       toolbox.add(sampcolr_button);
       toolbox.add(select_button);
      // toolbox.setBackground(Color.gray);
       
       
       toolbox.setPreferredSize(new Dimension(70, 68));
       toolbox.setMinimumSize(new Dimension(70, 68));
       toolbox.setMaximumSize(new Dimension(70, 68));
       home.getContentPane().add(toolbox,BorderLayout.WEST);
       
       toolbox1.setLayout(new FlowLayout( ) ); 
       toolbox1.setPreferredSize(new Dimension(150, 68));
       toolbox1.setMinimumSize(new Dimension(150, 68));
       toolbox1.setMaximumSize(new Dimension(150, 68));
      // toolbox1.setBackground(Color.GRAY);
       home.getContentPane().add(toolbox1,BorderLayout.EAST);
       
       home.getContentPane().add(tool_bar,BorderLayout.NORTH);
       
       
       home.setIconImage(title_image.getImage());
       home.setVisible(true);
       home.addWindowListener(new WindowAdapter(){
       public void windowClosing(WindowEvent we){
       System.exit(0);
          }
       });
    }
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==open || ae.getSource()==openButton)
        {
            
                JFileChooser openFile = new JFileChooser();
                openFile.setFileFilter(new File_Filtering());
                int opt=openFile.showOpenDialog(null);
                if(opt==JFileChooser.APPROVE_OPTION)
                {
                file_name=openFile.getSelectedFile().toString();
               /* image_frame=new JInternalFrame(file_name,true,true);
                image_frame.getContentPane().add(new LoadImageApp(file_name));
                image_frame.setFrameIcon(frame_image);
                image_frame.setVisible(true);
                image_frame.pack();
                home.add(image_frame);*/
                ImageEditor obj1=new ImageEditor();
                obj1.openImage();
               }
                
                image_frame.addInternalFrameListener(new InternalFrameAdapter() {
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                       JOptionPane.showConfirmDialog(null, "Do you want to save changes??","Image Editor",1,1);
                    }
                });
        }
        else if(ae.getSource()==saveas)
        {
            SaveAs saveas_obj=new SaveAs();
            saveas_obj.save_image(LoadImageApp.img);
        }
        else if(ae.getSource()==exit)
        {
            System.exit(0);
        }
        else if(ae.getSource()==grayscale)
        {
            GrayScale g1=new GrayScale();
            try
            {
            g1.grayConversion(LoadImageApp.img);
            }
            catch(Exception e)
            {
                
            }
        }
        else if(ae.getSource()==sharpen)
        {
            Sharpening obj=new Sharpening();
            LoadImageApp.img=obj.createSharpenImage(LoadImageApp.img);
        }
        else if(ae.getSource()==reset)
        {
            image_frame.getContentPane().add(new LoadImageApp(file_name));
           
        }
        else if(ae.getSource()==negative)
        {
            Negative obj1=new Negative();
           LoadImageApp.img=Negative.getNegativeImage(LoadImageApp.img);
           
           
        }
        else if(ae.getSource()==blur)
        {
            Blur obj=new Blur();
            LoadImageApp.img=obj.createBlurredImage(LoadImageApp.img);
        }
        else if(ae.getSource()==rotation)
        {
            try
            {
            final JSlider slider = new JSlider();
            slider.setMinimum(0);
            slider.setMaximum(360);
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(5);
            slider.setMajorTickSpacing(10);
            slider.setValue(0);
            image_frame.add(slider, BorderLayout.EAST);
            final Rotation2 r1=new Rotation2();
            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    try
                    {
                     LoadImageApp.img=r1.rotate(LoadImageApp.img,slider.getValue());
                    
                    }
                    catch(Exception ei)
                    {
                        
                    }
                }
            });
           
            }
            catch(Exception e)
            {
                
            }
        }
        else if(ae.getSource()==brightness)
        {
            final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50,25);
            slider.setMinorTickSpacing(1);
            slider.setMajorTickSpacing(10);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
           image_frame.add(slider, BorderLayout.EAST);
            final Brightness bright_obj=new Brightness();
            slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
               
               bright_obj.changeBrightness((slider.getValue())/10,LoadImageApp.img);
                
            }
        });
            
        }
    }
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        try
        {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new ImageEditor();
        }
        catch(Exception e)
        {
            
        }

        
    }
    public void openImage()
    {
        image_frame=new JInternalFrame(file_name,true,true);
        image_frame.getContentPane().add(new LoadImageApp(file_name));
        image_frame.setFrameIcon(frame_image);
        image_frame.setVisible(true);
        image_frame.pack();
        home.add(image_frame);
    }
} 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class LoadImageApp extends Component 
{
    static BufferedImage img,imgcopy;
    public void paint(Graphics g)
    {
        g.drawImage(img, 0, 0, null);
        repaint();
    }
    public LoadImageApp(String selected_file) 
    {
       try
       {
           img = ImageIO.read(new File(selected_file));
           imgcopy=img;
          
       }
       catch (IOException e)
       {
       }
    }
    /*  public Dimension getPreferredSize() 
     {
        if (img == null) 
        {
             return new Dimension(100,100);
        }
        else 
        {
           return new Dimension(img.getWidth(null), img.getHeight(null));
        }
     }*/
}
////////////////////////////////////////////////////////////////////////////////////

class SaveAs
{
    void save_image(BufferedImage img)
    {
        if (img == null)
	return;
		BufferedImage bImg = new BufferedImage(img.getWidth(), img.getHeight (), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bImg.createGraphics ();
		g.drawImage (img, 0, 0, null);
		JFileChooser jf_save = new JFileChooser (new File ("."));
                jf_save.setFileFilter(new File_Filtering());
		int opt = jf_save.showSaveDialog (null);

		if (opt == JFileChooser.APPROVE_OPTION) {
			try {
				ImageIO.write (bImg, "jpg", new File (jf_save.getSelectedFile ().toString ()));
			}
			catch (Exception e) {
				System.out.println ("Error saving file.");
			}
		}
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////
class File_Filtering extends javax.swing.filechooser.FileFilter {
	public boolean accept (File f) {
		return f.toString ().endsWith (".gif") || f.toString ().endsWith (".jpg")||f.toString().endsWith(".png")||f.toString().endsWith(".bmp");
	}

	public String getDescription () {
		return "All Picture Files";
	}
}
/////////////////////////////////////////@Shubham rAthore///////////////////////////////////////////////////////////

class GrayScale
{
    void grayConversion(BufferedImage img)throws IOException
    {
        int w=0,h=0,width,height;
        width=img.getWidth();
        height=img.getHeight();
        for(w = 0; w <width ; w++)		
        {				
            for(h = 0 ; h <height ; h++)			
		{				
                    // BufferedImage.getRGB() saves the colour of the pixel as a single integer.				
         	    // use Color(int) to grab the RGB values individually.				
                    Color color = new Color(img.getRGB(w, h));								
                    // get the luminosity value as an integer				
            	    int luminosity = (int) (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());				
		    // create a new Color object using the luminosity colour as the red, green and blue				
                    // colour values				
                    Color lum = new Color(luminosity, luminosity, luminosity);		
                    
        	    // set the pixel at that position to the new Color object using Color.getRGB().				
                    img.setRGB(w, h, lum.getRGB());			
                }		
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////
class Brightness
{
     public void changeBrightness(int increasingFactor,BufferedImage img)  {
       //size of input image
        try
        {
        int w = img.getWidth();
        int h = img.getHeight();
       //Pixel by pixel navigation loop
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
               //get the RGB component of input imge pixel
                Color color = new Color(img.getRGB(i, j));
                int r, g, b;
               //change the value of each component
                r = color.getRed() + increasingFactor;
                g = color.getGreen() + increasingFactor;
                b = color.getBlue() + increasingFactor;
               //r,g,b values which are out of the range 0 to 255 should set to 0 or 255
                if (r >= 256) {
                    r = 255;
                } else if (r < 0) {
                    r = 0;
                }
 
                if (g >= 256) {
                    g = 255;
                } else if (g < 0) {
                    g = 0;
                }
 
                if (b >= 256) {
                    b = 255;
                } else if (b < 0) {
                    b = 0;
                }
                
                //set output image pixel component
                img.setRGB(i, j, new Color(r, g, b).getRGB());
            }
            
            }
        
        }
        catch(Exception e)
            {
                
            }
        
    }
}
  
class Rotation2 extends Component
{
    public static BufferedImage rotate(BufferedImage img,double degrees)throws IOException
    {
        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(degrees), img.getWidth() / 2, img.getHeight() / 2);
        
        Graphics2D g2 = newImage.createGraphics();
        g2.drawImage(img, tx, null);

        img=newImage;
        g2.dispose();
        return img;
    }
}

class Negative
{
    public static BufferedImage getNegativeImage(BufferedImage img) {
        int w1 = img.getWidth();
        int h1 = img.getHeight();
        // int value[][] = new int[w1][h1];
        BufferedImage neg = new BufferedImage(w1, h1, 1);
        int value, alpha, r, g, b;
        for (int i = 0; i < w1; i++) {
            for (int j = 0; j < h1; j++) {
                value = img.getRGB(i, j); // store value
                alpha = getAlpha(value);
                r = 255 - getRed(value);
                g = 255 - getGreen(value);
                b = 255 - getBlue(value);

                value = createRGB(alpha, r, g, b);
                neg.setRGB(i, j, value);
            }
        }
        return neg;
    }

    public static int createRGB(int alpha, int r, int g, int b) {
        int rgb = (alpha << 24) + (r << 16) + (g << 8) + b;
        return rgb;
    }

    public static int getAlpha(int rgb) {
        return (rgb >> 24) & 0xFF;
    }

    public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }
}  


//Blurrr effect.......................................................
class Blur
{
    public static BufferedImage createBlurredImage(BufferedImage img) {
        
       BufferedImage newimg = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_INT_BGR);

        Graphics g = newimg.getGraphics();
        g.drawImage(img, 455, 255, null);

        float[] blurKernel = {
            1 / 9f, 1 / 9f, 1 / 9f,
            1 / 9f, 1 / 9f, 1 / 9f,
            1 / 9f, 1 / 9f, 1 / 9f
        };

        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, blurKernel));
        img = blur.filter(img, new BufferedImage(img.getWidth(), img.getHeight(), img.getType()));
        g.dispose();
        return img;
    }
}
//Sharpening....................................................................
class Sharpening
{
    public static BufferedImage createSharpenImage(BufferedImage img) {
        
       BufferedImage newimg = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_INT_BGR);

        Graphics g = newimg.getGraphics();
        g.drawImage(img, 455, 255, null);

        float[] sharpKernel = {0.0f, -1.0f, 0.0f,
                                      -1.0f, 5.0f, -1.0f,
                                      0.0f, -1.0f, 0.0f};

        BufferedImageOp blur = new ConvolveOp(new Kernel(3, 3, sharpKernel));
        img = blur.filter(img, new BufferedImage(img.getWidth(), img.getHeight(), img.getType()));
        g.dispose();
        return img;
    }
}