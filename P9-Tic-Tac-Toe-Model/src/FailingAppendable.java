import java.io.IOException;

/**
 * A mock to simulate a failure to write to the Appendable.
 */
public class FailingAppendable implements Appendable {

  /**
   * Append appendable.
   *
   * @param csq the csq
   * @return the appendable
   * @throws IOException the io exception
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Fail!");
  }

  /**
   * Append appendable.
   *
   * @param csq   the csq
   * @param start the start
   * @param end   the end
   * @return the appendable
   * @throws IOException the io exception
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Fail!");
  }

  /**
   * Append appendable.
   *
   * @param c the c
   * @return the appendable
   * @throws IOException the io exception
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Fail!");
  }
}
