package TImetableGA;

/**
 * 这是一个gui的工具类，主要用来是图形窗口置于屏幕的中心
 */
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class GUIUtil {

	public static void toCenter(Component comp) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rec = ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
		comp.setLocation(((int) rec.getWidth() - comp.getWidth()) / 2, ((int) rec.getHeight() - comp.getHeight()) / 2);
	}

}
