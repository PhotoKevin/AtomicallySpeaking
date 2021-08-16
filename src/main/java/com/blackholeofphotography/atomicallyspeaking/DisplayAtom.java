/*
 * The MIT License
 *
 * Copyright 2021 Kevin Nickerson.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.blackholeofphotography.atomicallyspeaking;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import org.jfree.graphics2d.svg.SVGGraphics2D;

/**
 * Extension of the Atom class to add display properties and rendering.
 * This supports a special Space atom to use for white space in a display as 
 * as arbitrary fake atoms and Isotopes for making signs.
 * @author Kevin Nickerson
 */
public class DisplayAtom
   extends Atom
{
   private boolean fake;
   private boolean space;
   private final boolean isotope;
   private final Color color;

   /**
   * Create a new DisplayAtom from the existing atom and set the color
   * @param atom Base atom
   * @param _color Color of the DisplayAtom
   */
   DisplayAtom (Atom atom, Color _color)
   {
      super (atom);
      fake = false;
      space = false;
      isotope = false;
      color = _color;
   }

   /**
    * Determine if this is a fake atom
    * @return T/F
    */
   public boolean isFake ()
   {
      return fake;
   }

   /**
    * Determine if this is an Isotope
    * @return T/F
    */
   public boolean isIsotope ()
   {
      return isotope;
   }

   /**
    * Determine if this is a special white space atom.
    * @return T/F
    */
   public boolean isSpace ()
   {
      return space;
   }

   /**
    * Get the display color of the atom
    * @return The display color
    */
   public Color getColor ()
   {
      return color;
   }

   /**
    * Get the Atomic Symbol of the atom. If this is a fake atom, the
    * symbol is in quotes.
    * @return The Atomic Symbol
    */
   @Override
   public String getSymbol ()
   {
      if (isSpace ())
         return " ";
      else
         return super.getSymbol ();
   }


   /**
    * Draw a standard Periodic Table square for the atom
    * @param g2 Graphics context to draw on
    * @param x Upper left X coordinate of the enclosing box
    * @param y Upper left X coordinate of the enclosing box
    * @param width Width of the box
    */
   public void draw (SVGGraphics2D g2, int x, int y, int width)
   {
      System.out.println ("Rendering " + getSymbol ());


      // Outer rectangle
      double thickness = width * Settings.STROKE_PROPORTION;
      if (thickness < 1)
         thickness = 1;
      g2.setStroke (new BasicStroke ((int) thickness));
      g2.setColor (color);
      g2.fillRect (x, y, width, width);
      g2.setColor (Color.BLACK);
      // Take the stroke thickness into account so the final rectangle fits.
      int half = (int) Math.round (thickness / 2.0);
      g2.drawRect (x+half, y+half, width-(int)thickness, width-(int)thickness);


      // Normally to get a font of a given height, you'd do this math. But the SVGGraphics
      // is at 72.0 so that cancels out.
      //  double fontSize= h * 72.0 / Toolkit.getDefaultToolkit().getScreenResolution();
      Font f = new Font (Settings.fontName, Font.PLAIN, (int) (width * Settings.SYMBOL_PROPORTION));
      g2.setFont (f);

      // We need a TextLayout to find out how large the string ends up
      // and the various other goo
      FontRenderContext frc = g2.getFontRenderContext();

      var met = f.getLineMetrics ("Mg", frc);
      var desc = met.getDescent ();
      var symHeight = met.getAscent () + desc;
      TextLayout layout = new TextLayout (getSymbol (), f, frc);

      Rectangle2D b = layout.getBounds ();
      System.out.println (b.toString ());

      // This works, but understanding why is a mess.
      // The x,y supplied are the Upper Left of the rectangle.
      // The coordinates supplied to drawString are for the left of the Base Line.

      // As such this would put the Symbol in the lower left of the rectangle      
      //    g2.drawString (Symbol, x-(int)b.getX (),  y+width-(int)(b.getHeight ()+b.getY ()));
      // That's not where we want it though. We want it centered.


      if (!Settings.centerSymbol)
      {
         int xCenter = x+width/2;
         int yCenter = y+width/2;
         met.getLeading ();
         int ySymbol = yCenter+((int)symHeight /2);
         int xSymbol = xCenter-((int)b.getWidth ()/2);

//         g2.drawRect (xCenter-(int)b.getWidth ()/2, yCenter-(int)b.getHeight ()/2, (int) b.getWidth (), (int) b.getHeight ());
         ySymbol -= desc;
         g2.drawString (getSymbol (), xSymbol, ySymbol);
      }
      else
      {
         // Calculate the free space and divide it in half.
         int xMargin = (int) (width - b.getWidth ()) / 2 ;
         int yMargin = (int) (width - b.getHeight ()) / 2;


         // You might think you could just do this, but it's wrong. It has to
         // do with the descenders
         // int xCenter = x+width/2;
         // int yCenter = y+width/2;
         // ySymbol = yCenter+((int)b.getHeight ()/2);
         // xSymbol = xCenter-((int)b.getWidth ()/2);

         // But it does get the bounding box correct.
         // g2.drawRect (xCenter-(int)b.getWidth ()/2, yCenter-(int)b.getHeight ()/2, (int) b.getWidth (), (int) b.getHeight ());


         int xSymbol = x-(int)b.getX ()+xMargin;
         int ySymbol = y+width-(int)(b.getHeight ()+b.getY ())-yMargin;
         System.out.println (ySymbol);



         System.out.println (getSymbol () +" "+ xSymbol  +" "+  ySymbol);
         g2.drawString (getSymbol (), xSymbol, ySymbol);

         System.out.println (ySymbol);
   //      g2.drawString (getSymbol (), xSymbol, ySymbol);
      }


      if (!fake)
      {
         var nameMetrics = f.getLineMetrics ("Mag", frc);

         // Now do the same kind of thing for the Name
         f = new Font (Settings.fontName, Font.PLAIN, (int) (width*Settings.NAME_PROPORTION));
         g2.setFont (f);

         frc = g2.getFontRenderContext();
         layout = new TextLayout (Name, f, frc);

         b = layout.getBounds ();

         int xMargin = (int) (width - b.getWidth ()) / 2 ;
         //yMargin = (int) (width - b.getHeight ()) / 2;

         int xSymbol = x-(int)b.getX ()+xMargin;
         int ySymbol = (int) (y + width 
//- b.getHeight () 
- nameMetrics.getDescent ()); // - nameMetrics.getLeading ());

         g2.drawString (Name, xSymbol, ySymbol);
      }

      if (!fake)
      {
         // And the Atomic Weight
         f = new Font (Settings.fontName, Font.PLAIN, (int) (width*Settings.NAME_PROPORTION));
         g2.setFont (f);
         frc = g2.getFontRenderContext();
         String s = String.format ("%3.1f", this.AtomicWeight);
         int zeroWidth = (int) new TextLayout ("0", f, frc).getBounds ().getWidth ();

         layout = new TextLayout (s, f, frc);
         b = layout.getBounds ();

         int xSymbol = x+width - (int)b.getWidth () - (int) b.getX () - 1*zeroWidth;
         int ySymbol = (int) (y + 2* b.getHeight ());

         g2.drawString (s, xSymbol, ySymbol);
      }

      // Finally the atomic Number in the Upper Left
      if (!fake)
      {
         var numberMetrics = f.getLineMetrics ("000", frc);
         f = new Font (Settings.fontName, Font.PLAIN, (int) (width*Settings.NAME_PROPORTION));
         g2.setFont (f);

         frc = g2.getFontRenderContext();
         String s = String.format ("%d", this.AtomicNo);
         int zeroWidth = (int) new TextLayout ("0", f, frc).getBounds ().getWidth ();

         layout = new TextLayout (s, f, frc);
         b = layout.getBounds ();

         int xSymbol = x + (int) b.getX () + 1*zeroWidth;
         int ySymbol = (int) (y + 2* b.getHeight () + numberMetrics.getLeading ());

//         ySymbol =  (int) (y + numberMetrics.getAscent () + numberMetrics.getLeading ());

         g2.drawString (""+this.AtomicNo, xSymbol, ySymbol);
      }
   }

   /**
    * Return a special space atom to use as white space in a display
    * @return A space atom
    */
   static public DisplayAtom getSpaceAtom ()
   {
      DisplayAtom atom = new DisplayAtom (new Atom (0, "", "Space", 0.0F), Color.WHITE);
      atom.space = true;

      return atom;
   }

   /**
    * Return a special fake atom with the specified symbol.
    * @param symbol Text to use as the Atomic Symbol
    * @return A Fake atom
    */
   static public DisplayAtom getFakeAtom (String symbol)
   {
      DisplayAtom atom = new DisplayAtom (new Atom (0, symbol, "Fake", 0.0F), Color.PINK);
      atom.fake = true;

      return atom;
   }

}
