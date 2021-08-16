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


import com.opencsv.bean.CsvToBeanBuilder;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Periodic Table of the Elements implementation
 * @author Kevin Nickerson
 */


public final class PeriodicTable 
{
   // https://stackabuse.com/reading-and-writing-csvs-in-java-with-opencsv/
   // List<Trees> treeParser = new CSVToBeanBuilder(FileReader("somefile.csv")).withType(Trees.class).build().parse();

   private static final List<DisplayAtom> periodicTable = loadAtomsFromCSV ("PeriodicTable.csv");
   private static final List<DisplayAtom> isotopeTable = loadAtomsFromCSV ("Isotopes.csv");

   private PeriodicTable ()
   {
   }

   private static List<DisplayAtom> loadAtomsFromCSV (String csvFileName)
   {
      try
      {
         List<PeriodicTableBean> tbl = null;
         if (tbl == null)
         {
            URL aboutResource = PeriodicTable.class.getClassLoader().getResource(csvFileName);
            InputStream is = aboutResource.openStream ();

            try
            {
               tbl = new CsvToBeanBuilder(new InputStreamReader (is))
                                 .withType(PeriodicTableBean.class)
                                 .withIgnoreEmptyLine(true)
                                 .withIgnoreLeadingWhiteSpace (true)
                                 .build()
                                 .parse();
            }
            catch (IllegalStateException ex)
            {
               System.err.println (ex.toString ());
            }

            List<DisplayAtom> table = new ArrayList<> ();
            if (tbl != null)
            {
               for (var pt : tbl)
                  table.add (new DisplayAtom (new Atom (pt.AtomicNo, pt.Symbol, pt.Name, pt.AtomicWeight), new Color (pt.Red, pt.Blue, pt.Green)));
            }

            return table;
         }
      }
      catch (FileNotFoundException ex)
      {
         Logger.getLogger (PeriodicTable.class.getName()).log (Level.SEVERE, null, ex);
      }
      catch (IOException ex)
      {
         Logger.getLogger (PeriodicTable.class.getName()).log (Level.SEVERE, null, ex);
      }

      return null;
   }

   public static DisplayAtom getAtomByAtomicNumber (int AtomicNumber)
   {
      var atom = periodicTable.stream ().filter (p -> p.getAtomicNumber () == AtomicNumber).findFirst ();
      if (atom != null)
         return atom.orElseThrow ();

      return null;
   }

   public static DisplayAtom getAtomBySymbol (String Symbol)
   {
      if (Symbol.isBlank ())
         return DisplayAtom.getSpaceAtom ();

      try
      {
         Optional<DisplayAtom> atom = periodicTable.stream ().filter (p -> p.getSymbol ().equalsIgnoreCase (Symbol)).findFirst ();
         if (atom.isEmpty ())
            atom = isotopeTable.stream ().filter (p -> p.getSymbol ().equalsIgnoreCase (Symbol)).findFirst ();

         if (atom.isPresent ())
            return atom.get ();

         else
            return DisplayAtom.getFakeAtom (Symbol);
      }
      catch (java.util.NoSuchElementException ex)
      {
//         System.out.println (ex.toString ());
      }

      return null;
   }

   public static DisplayAtom getAtomByName (String Name)
   {
      var atom = periodicTable.stream ().filter (p -> p.getName ().equalsIgnoreCase (Name)).findFirst ();
      if (atom != null)
         return atom.orElseThrow ();

      return null;
   }

   public static Color getColorByAtomicNumber (int AtomicNumber)
   {
      var atom = periodicTable.stream ().filter (p -> p.getAtomicNumber () == AtomicNumber).findFirst ();
      if (atom != null && atom.isPresent ())
         return atom.get ().getColor ();

      return Color.red;

   }
}

