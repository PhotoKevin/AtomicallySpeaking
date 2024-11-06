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
import java.awt.font.LineMetrics;
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
   private boolean newline;
   private boolean isotope;
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
      newline = false;
      isotope = false;
      color = _color;
   }
   
   
   /**
   * Create a new DisplayAtom from the existing atom and set the color
   * @param atom Base atom
   * @param _color Color of the DisplayAtom
   * @param _isotope T/F if this is an isotope
   */
   DisplayAtom (Atom atom, Color _color, boolean _isotope)
   {
      super (atom);
      fake = false;
      space = false;
      newline = false;
      isotope = _isotope;
      color = _color;
   }

   public void setIsIsotope ()
   {
      isotope = true;
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
    * Determine if this is a special newline atom.
    * @return T/F
    */
   public boolean isNewLine ()
   {
      return newline;
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
    * @param xTileUL Upper left X coordinate of the enclosing box
    * @param yTileUL Upper left Y coordinate of the enclosing box
    * @param width Width of the box. Height = Width
    */
   public void draw (SVGGraphics2D g2, int xTileUL, int yTileUL, int width)
   {
      // System.out.println ("Rendering " + getSymbol ());

      int xCenter = xTileUL + width/2;
      int yCenter = yTileUL + width/2;

      // Outer rectangle
      double thickness = width * Settings.STROKE_PROPORTION;
      if (thickness < 1)
         thickness = 1;
      g2.setStroke (new BasicStroke ((int) thickness));
      g2.setColor (color);
      g2.fillRect (xTileUL, yTileUL, width, width);
      g2.setColor (Color.BLACK);
      // Take the stroke thickness into account so the final rectangle fits.
      int half = (int) Math.round (thickness / 2.0);
      g2.drawRect (xTileUL+half, yTileUL+half, width-(int)thickness, width-(int)thickness);


      // Normally to get a font of a given height, you'd do this math. But the SVGGraphics
      // is at 72.0 so that cancels out.
      //  double fontSize= h * 72.0 / Toolkit.getDefaultToolkit().getScreenResolution();

      Font symbolFont = new Font (Settings.fontName, Font.PLAIN, (int) (width * Settings.SYMBOL_PROPORTION));
      g2.setFont (symbolFont);

      // Use Magnesium so we have a good Ascent and Descent
      LineMetrics symbolMetrics = symbolFont.getLineMetrics ("Mg", g2.getFontRenderContext());
      int descent = (int) symbolMetrics.getDescent ();
      float symHeight = symbolMetrics.getAscent () + descent;
      TextLayout layout = new TextLayout (getSymbol (), symbolFont, g2.getFontRenderContext());

      Rectangle2D symbolBounds = layout.getBounds ();
      //System.out.println (symbolBounds.toString ());

      // Work out the begining of the base line.
      // I'm not sure this is really the best math, but it looks good enough.

      int ySymbol = yCenter+((int)symHeight /2) - descent;
      int xSymbol = xCenter-((int)symbolBounds.getWidth ()/2);

      g2.drawString (getSymbol (), xSymbol, ySymbol);

      // Now do the same kind of thing for the Name
      if (!fake)
      {
         Font nameFont = new Font (Settings.fontName, Font.PLAIN, (int) (width*Settings.NAME_PROPORTION));
         g2.setFont (nameFont);

         LineMetrics nameMetrics = nameFont.getLineMetrics ("Magnesium", g2.getFontRenderContext());

         layout = new TextLayout (Name, nameFont, g2.getFontRenderContext());

         Rectangle2D nameBounds = layout.getBounds ();

         int xName = (int) (xCenter - nameBounds.getWidth () / 2);
         int yName = (int) (yTileUL + width - nameMetrics.getDescent () - nameMetrics.getLeading () - 2*thickness);

         g2.drawString (Name, xName, yName);
      }

      if (!fake)
      {
         Font numberFont = new Font (Settings.fontName, Font.PLAIN, (int) (width*Settings.NUMBER_PROPORTION));
         g2.setFont (numberFont);
         TextLayout zeroLayout = new TextLayout ("0", numberFont, g2.getFontRenderContext());
         int zeroWidth = (int) zeroLayout.getBounds ().getWidth ();

         int yNumbers = (int) (yTileUL + zeroLayout.getLeading () + zeroLayout.getAscent () + (int) 2*thickness);
         int numberMargin = zeroWidth + (int) thickness;

         // Atomic Number in the Upper Left
         String atomicNumber = String.format ("%d", this.AtomicNo);

         layout = new TextLayout (atomicNumber, numberFont, g2.getFontRenderContext());
         Rectangle2D numberBounds = layout.getBounds ();

         int xNumber = xTileUL + numberMargin - (int) numberBounds.getX ();

         g2.drawString (atomicNumber, xNumber, yNumbers);


         // Atomic Weight in Upper Right
         String atomicWeight = String.format ("%3.1f", this.AtomicWeight);
         double fraction  = AtomicWeight - Math.floor (AtomicWeight);
         if (fraction == 0.0)
            atomicWeight = String.format ("[%d]", (int) Math.floor (AtomicWeight));

         layout = new TextLayout (atomicWeight, numberFont, g2.getFontRenderContext());
         Rectangle2D weightBounds = layout.getBounds ();

         int xWeight = xTileUL+width - (int)weightBounds.getWidth () - numberMargin;

         g2.drawString (atomicWeight, xWeight, yNumbers);
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
    * Return a special newline atom to use as white space in a display
    * @return A space atom
    */
   static public DisplayAtom getNewLineAtom ()
   {
      DisplayAtom atom = new DisplayAtom (new Atom (0, "", "Space", 0.0F), Color.WHITE);
      atom.newline = true;

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
