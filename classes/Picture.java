import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }

    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int count = 0;
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    System.out.println(count);
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }

  public void myCollage() {
      Picture canyon = new Picture("images/gorge.jpg");
      Picture koala = new Picture("images/koala.jpg");
      Picture flower = new Picture("images/whiteFlower.jpg");
      Picture bwCanyon = new Picture(canyon);
      Picture eKoala = new Picture(koala);
      Picture nbFlower = new Picture(flower);
      bwCanyon.grayScale();
      eKoala.edgeDetection(10);
      nbFlower.zeroBlue();
      this.copy(canyon, 0, 0);
      this.copy(koala, 46, 69);
      this.mirrorHorizontalBotToTop();
      this.copy(flower, 146, 45);
      this.copy(bwCanyon, 246, 80);
      //this.mirrorVertical();
      this.copy(eKoala, 353, 186);
      this.copy(nbFlower, 496, 245);
      this.write("myCollage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color bottomColor = null;
    for (int row = 0; row < pixels.length - 1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col + 1];
        bottomPixel = pixels[row + 1][col];
        bottomColor = bottomPixel.getColor();
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        if(leftPixel.colorDistance(bottomColor) > edgeDist) {
            leftPixel.colorDistance(Color.BLACK);
        }
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }

  public void fixUnderwater() {
      Pixel[][] pixels = this.getPixels2D();
      for (Pixel[] rowArray : pixels)
      {
          for (Pixel pixelObj : rowArray)
          {
              if(pixelObj.getRed() < 20) {
                  pixelObj.setBlue(pixelObj.getBlue() *2);
              }
          }
      }
  }

  public void mirrorVerticalRightToLeft() {
      Pixel[][] pixels = this.getPixels2D();
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      int width = pixels[0].length;
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < width / 2; col++)
          {
              leftPixel = pixels[row][col];
              rightPixel = pixels[row][width - 1 - col];
              leftPixel.setColor(rightPixel.getColor());
          }
      }
  }

  public void mirrorHorizontal() {
      Pixel[][] pixels = this.getPixels2D();
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      int height = pixels.length;
      for (int row = 0; row < height / 2; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              leftPixel = pixels[row][col];
              rightPixel = pixels[height - 1 - row][col];
              leftPixel.setColor(rightPixel.getColor());
          }
      }
  }

    public void mirrorHorizontalBotToTop() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++)
        {
            for (int col = 0; col < pixels[0].length; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[height - 1 - row][col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

  public void negate() {
      {
          Pixel[][] pixels = this.getPixels2D();
          for (Pixel[] rowArray : pixels)
          {
              for (Pixel pixelObj : rowArray)
              {
                  pixelObj.setRed(Math.abs(255 - pixelObj.getRed()));
                  pixelObj.setGreen(Math.abs(255 - pixelObj.getGreen()));
                  pixelObj.setBlue(Math.abs(255 - pixelObj.getBlue()));
              }
          }
      }
  }

  public void mirrorArms() {
      Pixel[][] pixels = this.getPixels2D();
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      int height = pixels.length;
      for (int row = 159; row < 192; row++)
      {
          for (int col = 105; col < 171; col++)
          {
              leftPixel = pixels[row][col];
              rightPixel = pixels[row+30][col];
              rightPixel.setColor(leftPixel.getColor());
          }
      }
      for (int row = 171; row < 197; row++)
      {
          for (int col = 239; col < 294; col++)
          {
              leftPixel = pixels[row][col];
              rightPixel = pixels[row + 23][col];
              rightPixel.setColor(leftPixel.getColor());
          }
      }
  }

  public void grayScale() {
      {
          Pixel[][] pixels = this.getPixels2D();
          for (Pixel[] rowArray : pixels)
          {
              for (Pixel pixelObj : rowArray)
              {
                  pixelObj.setBlue((pixelObj.getGreen() + pixelObj.getBlue() + pixelObj.getRed())/3);
                  pixelObj.setRed((pixelObj.getGreen() + pixelObj.getBlue() + pixelObj.getRed())/3);
                  pixelObj.setGreen((pixelObj.getGreen() + pixelObj.getBlue() + pixelObj.getRed())/3);
              }
          }
      }
  }

  public void copy(Picture fromPic, int sRow, int eRow, int sCol, int eCol) {
      Pixel fromPixel = null;
      Pixel toPixel = null;
      Pixel[][] toPixels = this.getPixels2D();
      Pixel[][] fromPixels = fromPic.getPixels2D();
      for (int fromRow = sRow, toRow = eRow;
           fromRow < fromPixels.length &&
                   toRow < toPixels.length;
           fromRow++, toRow++)
      {
          for (int fromCol = sCol, toCol = eCol;
               fromCol < fromPixels[0].length &&
                       toCol < toPixels[0].length;
               fromCol++, toCol++)
          {
              fromPixel = fromPixels[fromRow][fromCol];
              toPixel = toPixels[toRow][toCol];
              toPixel.setColor(fromPixel.getColor());
          }
      }
  }

  public void mirrorGull() {
      Pixel[][] pixels = this.getPixels2D();
      Pixel leftPixel = null;
      Pixel rightPixel = null;
      int height = pixels.length;
      for (int row = 235; row < 323; row++)
      {
          for (int col = 238; col < 346; col++)
          {
              leftPixel = pixels[row][col];
              rightPixel = pixels[row][col + 120];
              rightPixel.setColor(leftPixel.getColor());
          }
      }
  }

    public void chromaKey(Picture newBack) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = newBack.getPixels2D();
        for(int row = 0; row < this.getHeight(); row++)
        {
            for(int col = 0; col < this.getWidth(); col++)
            {
                toPixel = toPixels[row][col];
                if(toPixel.getGreen() > toPixel.getRed() + 10 && toPixel.getGreen() > toPixel.getBlue() + 10)
                {
                    fromPixel = fromPixels[row][col];
                    toPixel.setColor(fromPixel.getColor());
                }
            }
        }

    }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.keepOnlyBlue();
    beach.explore();
    Picture p = new Picture("images/small.jpg");
    Picture smallP = p.scale(0.9, 0.9);
    smallP.write("smallerSmall.jpg");
  }
  
} // this } is the end of class Picture, put all new methods before this
