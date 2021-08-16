/*
 * The MIT License
 *
 * Copyright 2021 Kevin Nickerson
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

import static org.junit.Assert.assertEquals;

/**
 *
 * @author kevin
 */
public class PeriodicTableTest
{
   
   public PeriodicTableTest ()
   {
   }
   

   /**
    * Test of getAtomByAtomicNumber method, of class PeriodicTable.
    */
   @org.junit.Test
   public void testGetAtomByAtomicNumber ()
   {
      System.out.println ("getAtomByAtomicNumber");
      int AtomicNumber = 1;
      String expResult = "H";
      Atom result = PeriodicTable.getAtomByAtomicNumber (AtomicNumber);
      assertEquals (expResult, result.getSymbol ());
   }

   /**
    * Test of getAtomBySymbol method, of class PeriodicTable.
    */
   @org.junit.Test
   public void testGetAtomBySymbol ()
   {
      System.out.println ("getAtomBySymbol");
      String Symbol = "C";
      int expResult = 6;
      Atom result = PeriodicTable.getAtomBySymbol (Symbol);
      assertEquals (expResult, result.getAtomicNumber ());

      result = PeriodicTable.getAtomBySymbol ("X");

   }

   /**
    * Test of getAtomByName method, of class PeriodicTable.
    */
   @org.junit.Test
   public void testGetAtomByName ()
   {
      System.out.println ("getAtomByName");
      String Name = "Boron";
      String expResult = "B";
      Atom result = PeriodicTable.getAtomByName (Name);
      assertEquals (expResult, result.getSymbol ());
   }
}
