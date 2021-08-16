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

import com.opencsv.bean.CsvBindByName;
import java.awt.Color;

/**
 * This class is strictly for loading the Periodic Table data from CSV.
 * @author Kevin Nickerson <kevin@blackholeofphotography.com>
 */

public class PeriodicTableBean
{
   @CsvBindByName 
   protected int AtomicNo;

   @CsvBindByName
   protected String Symbol;

   @CsvBindByName
   protected String Name;

   @CsvBindByName
   protected Float AtomicWeight;

   @CsvBindByName
   protected int Red;
   @CsvBindByName
   protected int Green;
   @CsvBindByName
   protected int Blue;

   /**
    * Public constructor required by opencsv
    */
   public PeriodicTableBean ()
   {

   }
}
