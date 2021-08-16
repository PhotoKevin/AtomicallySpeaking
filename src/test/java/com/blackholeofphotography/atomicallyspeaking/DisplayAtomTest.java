/*
 * The MIT License
 *
 * Copyright 2021 Kevin Nickerson <kevin@blackholeofphotography.com>.
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

import java.awt.Color;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kevin Nickerson <kevin@blackholeofphotography.com>
 */
public class DisplayAtomTest
{
   
   public DisplayAtomTest ()
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
    * Test of isFake method, of class DisplayAtom.
    */
   @Test
   public void testIsFake ()
   {
      System.out.println ("isFake");
      String symbol = "E";
      Boolean expResult = true;
      Boolean result = DisplayAtom.getFakeAtom (symbol).isFake ();
      assertEquals (expResult, result);
   }

   /**
    * Test of isIsotope method, of class DisplayAtom.
    */
   @Test
   public void testIsIsotope ()
   {
      System.out.println ("isIsotope");
      DisplayAtom instance = PeriodicTable.getAtomByAtomicNumber (1);
      boolean expResult = false;
      boolean result = instance.isIsotope ();
      assertEquals (expResult, result);
   }

   /**
    * Test of isSpace method, of class DisplayAtom.
    */
   @Test
   public void testIsSpace ()
   {
      System.out.println ("isSpace");
      DisplayAtom instance = PeriodicTable.getAtomByAtomicNumber (1);
      boolean expResult = false;
      boolean result = instance.isSpace ();
      assertEquals (expResult, result);
   }

   /**
    * Test of getColor method, of class DisplayAtom.
    */
   @Test
   public void testGetColor ()
   {
      System.out.println ("getColor");
      DisplayAtom instance = PeriodicTable.getAtomByAtomicNumber (1);
      Color expResult = Color.WHITE;
      Color result = instance.getColor ();
      assertEquals (expResult, result);
   }

   /**
    * Test of getSymbol method, of class DisplayAtom.
    */
   @Test
   public void testGetSymbol ()
   {
      System.out.println ("getSymbol");
      DisplayAtom instance = PeriodicTable.getAtomByAtomicNumber (1);
      String expResult = "H";
      String result = instance.getSymbol ();
      assertEquals (expResult, result);
   }

   /**
    * Test of getSpaceAtom method, of class DisplayAtom.
    */
   @Test
   public void testGetSpaceAtom ()
   {
      System.out.println ("getSpaceAtom");
      Boolean expResult = true;
      Boolean result = DisplayAtom.getSpaceAtom ().isSpace ();
      assertEquals (expResult, result);
   }

   /**
    * Test of getFakeAtom method, of class DisplayAtom.
    */
   @Test
   public void testGetFakeAtom ()
   {
      System.out.println ("getFakeAtom");
      String symbol = "E";
      Boolean expResult = true;
      Boolean result = DisplayAtom.getFakeAtom (symbol).isFake ();
      assertEquals (expResult, result);
   }
   
}
