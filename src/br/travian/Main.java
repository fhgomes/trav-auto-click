package br.travian;

import static java.time.LocalTime.now;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
  public static final int ONESEC = 1000;
  static final int[] myArray = {59, 56, 62, 58, 54, 60};
  static final Random random = new Random();

  public static final int MINUTES = 4;

  public static void main(String[] args) throws AWTException {
    waitForMilis(5 * ONESEC);

    getCurrentPos();
    doRun(-771, 662); //oasis
//    doRun(-815, 759); //all
  }

  private static void doRun(int x, int y) throws AWTException {
    Robot robot = new Robot(MouseInfo.getPointerInfo().getDevice());

    while (true) {

      getCurrentPos();
      // Calcular a diferença de posição para mover o mouse

      robot.mouseMove(x, y);
      getCurrentPos();

      System.out.println("Clicando: " + now());
      robot.mousePress(InputEvent.BUTTON1_MASK);
      robot.mouseRelease(InputEvent.BUTTON1_MASK);

      System.out.println("Movendo");
      robot.mouseMove(x - 100, y + 100);

      int delay = MINUTES * getAnInt() * ONESEC; // 5 minutes in milliseconds
      waitForMilis(delay);
    }
  }

  private static void getCurrentPos() {
    GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
    Rectangle monitor1Bounds = devices[0].getDefaultConfiguration().getBounds();

    // Obter a posição atual do mouse
    Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();

    System.out.println("Device: " + MouseInfo.getPointerInfo().getDevice());
    System.out.println("Posicao atual: " + MouseInfo.getPointerInfo().getLocation() );
  }

  private static void waitForMilis(int delay) {
    System.out.println("Dormir por:" + (delay/1000));
    try {
      TimeUnit.MILLISECONDS.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static int getAnInt() {

    int randomIndex = random.nextInt(myArray.length);

    return myArray[randomIndex];
  }
}
