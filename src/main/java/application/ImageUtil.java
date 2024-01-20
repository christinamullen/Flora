package application;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.sqlite.SQLiteDataSource;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageUtil {

   public static final String DB_FILE = "Flora.db";
   SQLiteDataSource db;

   public ImageUtil() {
      db = new SQLiteDataSource();
      db.setUrl("jdbc:sqlite:");

   }

   public Connection getConnection() {

      try {
         return db.getConnection();
      } catch (SQLException e) {
         System.out.println("Failed getting connection: " + e);
         e.printStackTrace();
         throw new RuntimeException("Failed to get SQL Connection");
      }
   }

   private Image getImage(String filename) {
      try {
         InputStream instream = this.getClass().getResourceAsStream(filename);
         Image theImage = new Image(instream);
         instream.close();
         return theImage;
      } catch (IOException ex) {
         ex.printStackTrace();
         throw new ExceptionInInitializerError(ex);
      }
   }

   /**
    * scale image
    *
    * @param sbi image to scale
    * @param imageType type of image
    * @param dWidth width of destination image
    * @param dHeight height of destination image
    * @param fWidth x-factor for transformation / scaling
    * @param fHeight y-factor for transformation / scaling
    * @return scaled image
    */
   public static Image scale(Image anImage,
                             int imageType,
                             int dWidth,
                             int dHeight,
                             double fWidth,
                             double fHeight) {

      BufferedImage sbi = SwingFXUtils.fromFXImage(anImage, null);
      BufferedImage dbi = null;
      if (sbi != null) {
         dbi = new BufferedImage(dWidth, dHeight, imageType);
         Graphics2D g = dbi.createGraphics();
         AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
         g.drawRenderedImage(sbi, at);
      }

      return SwingFXUtils.toFXImage(dbi, null);
   }

   public static Image bytesToImage(byte[] bytes) {
      Image anImage = null;
      try {
         InputStream is = new ByteArrayInputStream(bytes);
         anImage = new Image(is);
         is.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return anImage;
   }

   public static byte[] imageToBytes(Image anImage) {
      byte[] bytes = null;
      try {
         BufferedImage bImage = SwingFXUtils.fromFXImage(anImage, null);
         ByteArrayOutputStream s = new ByteArrayOutputStream();
         ImageIO.write(bImage, "png", s);
         bytes  = s.toByteArray();
         s.close(); //especially if you are using a different output stream.

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return bytes;
   }

   public int insertFromFile(String fileName) {

      int imageId = -1;
      try {
         byte[] bytes = imageToBytes(getImage(fileName));
         ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

         String sql = "INSERT INTO Images image () VALUES(?,?)";

         Connection conn = getConnection();
         PreparedStatement insertStmt = conn.prepareStatement(sql);
         insertStmt.setBlob(1, bis);
         insertStmt.executeUpdate();

         PreparedStatement stmt = conn.prepareStatement("select last_insert_rowid()");
         ResultSet rs = stmt.executeQuery();
         imageId = rs.getInt(1);

         conn.close();

         return imageId;

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         throw new RuntimeException("Image insert failed");
      }

   }

}
