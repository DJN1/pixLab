/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  public static void testKeepOnlyBlue() {
      Picture test = new Picture("images/gorge.jpg");
      test.explore();
      test.keepOnlyBlue();
      test.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }

  public static void testMirrorVerticalRightToLeft() {
      Picture caterpillar = new Picture("images/caterpillar.jpg");
      caterpillar.explore();
      caterpillar.mirrorVerticalRightToLeft();
      caterpillar.explore();
  }

  public static void testMirrorGull() {
      Picture caterpillar = new Picture("images/seagull.jpg");
      caterpillar.explore();
      caterpillar.mirrorGull();
      caterpillar.explore();
  }

  public static void testMirrorHorizontal() {
      Picture caterpillar = new Picture("images/blueMotorcycle.jpg");
      caterpillar.explore();
      caterpillar.mirrorHorizontal();
      caterpillar.explore();
  }
    public static void testMirrorHorizontalBotToTop() {
        Picture caterpillar = new Picture("images/blueMotorcycle.jpg");
        caterpillar.explore();
        caterpillar.mirrorHorizontalBotToTop();
        caterpillar.explore();
    }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }

  public static void testMyCollage()
  {
      Picture canvas = new Picture("images/640x480.jpg");
      canvas.myCollage();
      canvas.explore();
  }

  public static void testNegate() {
      Picture canvas = new Picture("images/640x480.jpg");
      canvas.negate();
      canvas.explore();
  }

  public static void testCopyNew() {
      Picture test = new Picture("images/redMotorcycle.jpg");
      Picture robot = new Picture("images/robot.jpg");
      test.explore();
      test.copy(robot, 0, 25, 0, 10);
      test.explore();
      robot.explore();
  }

  public static void testMirrorArms() {
      Picture SM = new Picture("images/snowman.jpg");
      SM.explore();
      SM.mirrorArms();
      SM.explore();
  }

  public static void testGrayscale() {
      Picture beach = new Picture("images/beach.jpg");
      beach.explore();
      beach.grayScale();
      beach.explore();
  }

  public static void testFixUnderwater() {
      Picture beach = new Picture("images/water.jpg");
      beach.explore();
      beach.fixUnderwater();
      beach.explore();
  }

  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorVerticalRightToLeft();
    //testMirrorArms();
    //testMirrorHorizontal();
    //testMirrorHorizontalBotToTop();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
      testMyCollage();
    //testCopy();
    //testCopyNew();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
    //testEdgeDetection();
  }
}