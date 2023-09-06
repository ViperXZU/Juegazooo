package Juegazo;

import com.sun.source.tree.ReturnTree;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Background {
    private Point position;

    public Background(String imagefile){
        loadImage(imagefile);

        position= new Point(0,Background.getHeight()-Juegazoo.FRAME_HEIGHT);
    }
    private BufferedImage Background;
    private BufferedImage Background1;
    private BufferedImage Background2;
    private BufferedImage BackgroundTotal;

    private void loadImage(String imagefile) {
        try {
            Background = ImageIO.read(new File(imagefile));
        }catch (Exception e){
            System.err.println(e);
        }
    }

    public boolean isTwoImage = false;



    public void updateBackground(){

        if((position.x+Juegazoo.FRAME_WIDTH)+1 < Background.getWidth()){
            position.x++;
            isTwoImage= false;
        } else {
            if (position.x < Background.getWidth()) {
                position.x= 0;
                isTwoImage = false;
            }
        }
    }

    public BufferedImage getImageBackground() {
        if (!isTwoImage) {
            return Background.getSubimage(position.x, position.y, Juegazoo.FRAME_WIDTH, Juegazoo.FRAME_HEIGHT);
        } else {
            int xMax1 = Background.getWidth() - position.x;
            int xMax2 = (position.x + Juegazoo.FRAME_WIDTH) - Background.getWidth();

            Background2 = Background.getSubimage(0,position.y,xMax2,Juegazoo.FRAME_WIDTH);

            if (xMax1 > 0){
                Background1= Background.getSubimage(position.x,position.y,xMax1,Juegazoo.FRAME_HEIGHT);

                return joinImages(Background1,Background2,xMax1);
            } else {
                return Background;
            }
        }

    }

    private BufferedImage joinImages(BufferedImage image1, BufferedImage image2, int xMin){
        BackgroundTotal = new BufferedImage(Juegazoo.FRAME_WIDTH,Juegazoo.FRAME_HEIGHT,Background.getType());

        Graphics g = BackgroundTotal.getGraphics();

        g.drawImage(image1,0,0,null);
        g.drawImage(image2,xMin,0,null);

        return BackgroundTotal;
    }
}
