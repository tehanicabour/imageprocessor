import org.junit.Test;

import static org.junit.Assert.assertEquals;


import view.Listener;

/**
 * Test whether the emitting methods and listener successfully communicate
 * between view and controller.
 */
public class MockGUITest {
  private Appendable out = new StringBuilder();
  private MockGUIView test = new MockGUIView();
  private Listener controller = new MockGUIController(out);

  private void addControllerToListener() {
    this.test.addListener(this.controller);
  }

  @Test
  public void testEmitTransformationEvent(String transformation, String id,
                                          String newID, int field1, int field2) {
    this.addControllerToListener();
    this.test.emitTransformationEvent(transformation, id, newID, field1, field2);
    assertEquals(transformation, this.out.toString());
  }

  @Test
  public void testEmitLoadImageEvent(String path, String id) {
    this.addControllerToListener();
    this.test.emitLoadImageEvent(path, id);
    assertEquals(path + "saved as" + id, this.out.toString());
  }

  @Test
  public void testEmitSaveImageEvent(String path, String id) {
    this.addControllerToListener();
    this.test.emitSaveImageEvent(path, id);
    assertEquals(id + " saved as a " + path, this.out.toString());
  }
}
