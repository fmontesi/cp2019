package cp;

import java.nio.file.Path;

public interface Result
{
  /**
   * The file ({@link Path}) of this result
   * @return file ({@link Path}) of this result
   */
  public Path path();
  
  public int number();
}