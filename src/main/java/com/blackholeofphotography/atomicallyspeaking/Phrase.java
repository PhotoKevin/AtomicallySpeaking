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

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.graphics2d.svg.MeetOrSlice;
import org.jfree.graphics2d.svg.PreserveAspectRatio;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.ViewBox;

/**
 *
 * @author Kevin Nickerson
 */
public class Phrase
   implements Cloneable 
{
   public String text;
   public int position;
   public ArrayList<DisplayAtom> sequence = new ArrayList<> ();

   private Phrase (String _text)
   {
      this.text = _text;
      this.position = 0;
   }

   public Phrase (String [] list)
   {
      for (var sym : list)
      {
         text += sym;
         sequence.add (PeriodicTable.getAtomBySymbol (sym));
      }
   }

   private boolean hasSymbol ()
   {
      return position < text.length ();
   }

   private boolean hasSymbol2 ()
   {
      int remaining = text.length () - position;
      if (remaining < 2)
         return false;

      String next = text.substring (position, position+2).trim ();
      // No, it's not redundant. The trim could have reduced it.
      return (next.length () == 2);
   }

   public String getSymbol1 ()
   {
      if (position < text.length ())
         return text.substring (position, position+1);

      return null;
   }

   public String getSymbol2 ()
   {
      if (position < text.length () - 1)
         return text.substring (position, position+2);

      return null;
   }

   @Override
   public String toString ()
   {
      return this.converted ();
   }

   /**
    * Close the Phrase
    * @return A clone of the Phrase
    */
   @Override
   @SuppressWarnings("CloneDeclaresCloneNotSupported")
   public Phrase clone ()
   {
      Phrase result = null;
      try
      {
         result = (Phrase) super.clone ();
         result.position = this.position;
         result.sequence = (ArrayList<DisplayAtom>) this.sequence.clone ();

         return result;
      }
      catch (CloneNotSupportedException ex)
      {
         Logger.getLogger (Phrase.class.getName()).log (Level.SEVERE, null, ex);
      }

      return result;
   }

   private Phrase advance ()
   {
      Phrase result = this.clone ();
      String symbol = this.getSymbol1 ();
      if (symbol != null)
      {
         DisplayAtom atom = PeriodicTable.getAtomBySymbol (symbol);
         if (atom == null)
            atom = PeriodicTable.getAtomBySymbol (symbol.toUpperCase ());

         result.sequence.add (atom);
         result.position += symbol.length ();
      }

      return result;
   }

   private Phrase advance2 ()
   {
      Phrase result = this.clone ();
      String symbol = this.getSymbol2 ();
      if (symbol != null)
      {
         DisplayAtom atom = PeriodicTable.getAtomBySymbol (symbol);
         result.sequence.add (atom);
         result.position += symbol.length ();
      }

      return result;
   }

   public String converted ()
   {
      String s = "/";
      for (DisplayAtom a : this.sequence)
      {
         if (a == null)
            s += "?";
         else
            s += a.getSymbol () + "/";
      }

      return s;
   }

   public int getFakeCount ()
   {
      int n = 0;
      for (var a : this.sequence)   
         if ((a  == null) || (a.isFake ()))
            n += 1;

      return n;
   }

   public int getIsotopeCount ()
   {
      int n = 0;
      for (var a : this.sequence)   
         if ((a  == null) || (a.isIsotope ()))
            n += 1;

      return n;
   }

   private static float fakeScore = 9.0F;
   public static void setFakeScore (float score)
   {
      fakeScore = score;
   }
   public static float getFakeScore ()
   {
      return fakeScore;
   }

   private static float isotopeScore = 2.0F;
   public static void setIsotopeScore (float score)
   {
      isotopeScore = score;
   }
   public static float getIsotopeScore ()
   {
      return isotopeScore;
   }


   private static float twoScore = 1.0F;
   public static void setTwoScore (float score)
   {
      twoScore = score;
   }
   public static float getTwoScore ()
   {
      return twoScore;
   }

   private static float oneScore = 1.0F;
   public static void setOneScore (float fake)
   {
      oneScore = fake;
   }
   public static float getOneScore ()
   {
      return oneScore;
   }

   public float getScore ()
   {
      int count = 0;
      float score = 0.0F;
      for (DisplayAtom atom : this.sequence)
      {
         if (atom.isSpace () || atom.isNewLine ())
            score += 0.0F;

         else if (atom.getSymbol () == null)
            score += 9990.99;
         else if (atom.isFake ())
            score += fakeScore;
         else if (atom.isIsotope ())
            score += isotopeScore;

         else if (atom.getSymbol ().length () == 2)
            score += twoScore;
         else if (atom.getSymbol ().length () == 1)
            score += oneScore;
         else
            score += 999F;
         
         if (! atom.isSpace ())
            count += 1;
//         if (atom.Symbol.length () == 2)
//            count += 1;
      }

      return score + count;
   }

   private Dimension getTileDimension ()
   {
      return new Dimension (Settings.TILE_SIZE, Settings.TILE_SIZE);
   }

   private int getTileGap ()
   {
      return (int) (Settings.GAP_PROPORTION * Settings.TILE_SIZE);
   }
   
   private int getNumberOfLines ()
   {
      int lines = 1;
      for (int i=0; i<sequence.size (); i++)
         if (sequence.get (i).isNewLine ())
            lines += 1;
      
      return lines;
   }

   public Dimension getBoundingBox ()
   {
      Dimension tileSize = getTileDimension();
      int gap = (getNumberOfLines ()-1) * getTileGap ();
      return new Dimension ((tileSize.width+getTileGap()) * sequence.size (), tileSize.height * getNumberOfLines () + gap);
   }

   /**
    * Render this phrase onto the graphics object
    * @param g2 SVGGraphics2D to render upon.
    */
   public void render (SVGGraphics2D g2)
   {
      int symWidth = Settings.TILE_SIZE;
      int xPosition = getTileGap ();
      int yPosition = 0;
      for (int i=0; i< this.sequence.size (); i++)
      {
         DisplayAtom atom = this.sequence.get (i);
         if (atom.isNewLine ())
         {
            yPosition += symWidth + getTileGap ();
            xPosition = getTileGap ();
         }
         
         else if (!atom.isSpace ())
            atom.draw (g2, xPosition, yPosition, symWidth);

         if (!atom.isNewLine ())
            xPosition += Settings.TILE_SIZE + getTileGap ();
      }
   }


   public String toSVG ()
   {
      Dimension size = getBoundingBox ();
      int docWidth = size.width + getTileGap ();
      int docHeight = size.height;

      SVGGraphics2D g2 = new org.jfree.graphics2d.svg.SVGGraphics2D (size.width, size.height);
      render (g2);

      String svgElement = g2.getSVGElement (null, true, new ViewBox (0, 0, docWidth, docHeight), PreserveAspectRatio.XMID_YMID, MeetOrSlice.MEET);
      return svgElement;
   }

   public static ArrayList<Phrase> generatePossibilities (String text)
   {
      ArrayList<Phrase> possible = new ArrayList<> ();

      possible.add (new Phrase (text));

      boolean done = false;
      while (! done)
      {
         done = true;
         int n = possible.size ();
         ArrayList<Phrase> newPossible = new ArrayList<> ();
         for (int i=0; i<n; i++)
         {
            Phrase phrase = possible.get (i);
            if (! phrase.hasSymbol ())
               newPossible.add (phrase);

            if (phrase.hasSymbol ())
            {
               newPossible.add (phrase.advance ());
               done = false;
            }

            if (phrase.hasSymbol2 ())
            {
               newPossible.add (phrase.advance2 ());
               done = false;
            }
         }

         possible = newPossible;
      }

      Collections.sort (possible, new PhraseComparator());
      return possible;
   }
}

class PhraseComparator implements Comparator<Phrase>
{
   @Override
   public int compare (Phrase o1, Phrase o2)
   {
      var score1 = o1.getScore ();
      var score2 = o2.getScore ();

      if (score1 < score2)
         return -1;
      else if (score1 > score2)
         return 1;

      return 0;
   }
}
