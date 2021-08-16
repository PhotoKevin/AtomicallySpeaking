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

import java.util.ArrayList;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kevin Nickerson
 */
public class PhraseTest
{
   
   public PhraseTest ()
   {
   }
   
   @BeforeClass
   public static void setUpClass ()
   {
   }
   
   @AfterClass
   public static void tearDownClass ()
   {
   }
   
   @Before
   public void setUp ()
   {
   }
   
   @After
   public void tearDown ()
   {
   }

   /**
    * Test of getSymbol1 method, of class Phrase.
    */
   @Test
   public void testGetSymbol1 ()
   {
      System.out.println ("getSymbol1");
      Phrase instance = new Phrase (new String [] {"He"});
      String expResult = "H";
      String result = instance.getSymbol1 ();
      assertEquals (expResult, result);

      instance = new Phrase (new String [] {"C"});
      result = instance.getSymbol1 ();
      expResult = "C";
      assertEquals (expResult, result);
   }


   private String[] split (String word)
   {
      ArrayList<String> symbols = new ArrayList<> ();
      String cursym = null;
      for (var ch : word.toCharArray ())
      {
         if (java.lang.Character.isUpperCase (ch) || java.lang.Character.isSpaceChar (ch))
         {
            if (cursym != null)
               symbols.add (cursym);
            
            cursym = "";
         }
         cursym += ch;
      }

      if (cursym != null)
         symbols.add (cursym);

      String[] ss = new String[symbols.size ()];

      return (String[]) symbols.toArray (ss);
   }

   String message = null;
   private boolean check ()
   {

      Phrase[] scoreTest = 
      {
         new Phrase (split ("SeNd NUDEs")), // Only one Isotope
         new Phrase (split ("SeND NUDEs")), // Two Isotopes
         new Phrase (split ("SeNd NUDeS")), // Fake (De)
         new Phrase (split ("SeND NUDeS")), // Fake (De) plus one Isotope
         new Phrase (split ("SeND NUdEs")), // Fake (Ud) plus one Isotope
         new Phrase (split ("SEnD NUDEs")), // Fake (En) plus two Isotopes
         new Phrase (split ("SENd NUDEs")), // Fake (E) plus one Isotope
         new Phrase (split ("SeNd NUDES")), // Feke (E) plus one Isotope
         new Phrase (split ("SEND NUDEs")), // Fake (E) plus 2 Isotopes
         new Phrase (split ("SeND NUDES")), // Fake (E) plus two Isotopes
      };

      for (int i=1; i<scoreTest.length; i++)
      {
         float a = scoreTest[i-1].getScore ();
         float b = scoreTest[i].getScore ();

         if (a > b)
         {
            message = String.format ("%s (%d) with score %f should be less than %s (%d) score %f", scoreTest[i-1].toString (), i-1, a,
                        scoreTest[i].toString (), i, b);
            return false;
         }
      }

      return true;
   }

   private void findFactors ()
   {
      System.out.println ("findFactors");
      boolean result = check ();

      for (float one=1.0F; one < 2.0F; one += 0.10)
      {
         Phrase.setOneScore (one);
         for (float two=1.0F; two < 2.5F; two += 0.10)
         {
            Phrase.setTwoScore (two);
            for (float fake=1.0F; fake < 5.0F; fake += 0.10F)
            {
               Phrase.setFakeScore (fake);
               for (float isotope=1.0F; isotope < 5.0F; isotope += 0.10F)
               {
                  Phrase.setIsotopeScore (isotope);
                  result = check ();
                  if (result)
                  {
                     message = String.format ("Got one One:%f Two:%f Isotope:%f Fake:%f", one, two, isotope, fake);
                     System.out.println (message);
                  }
               }
            }

         }
      }
      assertTrue (message, result);
      System.out.println ("Done");
   }


//   @Test
   public void testScoring ()
   {
      System.out.println ("testScoring");
      boolean result = check ();

      assertTrue (message, result);
   }
}
