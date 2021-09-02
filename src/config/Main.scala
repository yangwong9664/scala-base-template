
package config

import org.apache.commons.imaging.Imaging

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.util.{Failure, Success, Try}

object Main extends App {

  def printMetadata(file: File) = Try {
    Imaging.getMetadata(file).toString(".")
  } match {
    case Success(value) => println(value)
    case Failure(_) => println("no exif data.")
  }

  val name = "small_jpg"
  val ext = "jpg"
  val fileName = s"$name.$ext"

  val originalFile = new File(fileName)
  val jpgFile = new File("cleaned.jpg")

  printMetadata(originalFile)

  println("converting to JPG...")
  val nonJPGImage = ImageIO.read(originalFile)
  val jpegImage = new BufferedImage(nonJPGImage.getWidth, nonJPGImage.getHeight, BufferedImage.TYPE_INT_RGB)
  jpegImage.createGraphics.drawImage(nonJPGImage, 0, 0, Color.WHITE, null)

  ImageIO.write(jpegImage, "jpg", jpgFile)

  printMetadata(jpgFile)
  println("completed exif purge")

}
