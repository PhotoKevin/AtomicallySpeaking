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

/**
 * Class to describe an Atom. Its name and physical properties. 
 * @author Kevin Nickerson
 */
public class Atom
{
   /**
    * Atomic Number of the atom
    */
   protected int AtomicNo;
   /**
    * Atomic Symbol of the atom
    */
   private String Symbol;
   /**
    * Name of the atom
    */
   protected String Name;
   /**
    * Atomic Weight of the atom
    */
   protected Float AtomicWeight;

   /**
    * Create a new atom that is a copy of an existing one.
    * @param atom
    */
   public Atom (Atom atom)
   {
      this.AtomicNo = atom.AtomicNo;
      this.Symbol = atom.Symbol;
      this.Name = atom.Name;
      this.AtomicWeight = atom.AtomicWeight;
   }

   /**
    * Create a new atom
    * @param number The Atomic Number
    * @param symbol The Atomic Symbol
    * @param name The Name
    * @param weight The Atomic Weight
    */
   public Atom (int number, String symbol, String name, Float weight)
   {
      this.AtomicNo = number;
      this.Symbol = symbol.trim ();
      this.Name = name.trim ();
      this.AtomicWeight = weight;
   }

   /**
    * Get the Atomic Number of the atom
    * @return The Atomic Number
    */
   public int getAtomicNumber ()
   {
      return this.AtomicNo;
   }

   /**
    * The the Atomic Symbol of the atom
    * @return The Atomic Symbol
    */
   public String getSymbol ()
   {
      if (Symbol.length () == 2)
      {
         String s1 = Symbol.substring (0, 1);
         String s2 = Symbol.substring (1, 2);
         return s1.toUpperCase () + s2.toLowerCase ();
      }

      return this.Symbol.toUpperCase ();
   }


   /**
    * The the Name of the atom
    * @return The Name of the atom
    */
   public String getName ()
   {
      return this.Name;
   }


   /**
    * The the Atomic Weight of the atom
    * @return The Atomic Weight
    */
   public Float getAtomicWeight ()
   {
      return this.AtomicWeight;
   }

   /**
    * Convert the atom to a String
    * @return The atom represented as a String
    */
   @Override
   public String toString ()
   {
      return this.Name;
   }
}
