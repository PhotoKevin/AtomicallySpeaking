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

import java.awt.Font;
import java.util.prefs.Preferences;

/**
 * Simple class to save/load the user preferences.
 * Computer\HKEY_CURRENT_USER\SOFTWARE\JavaSoft\Prefs\com\blackholeofphotography\atomicallyspeaking
 *
 * @author Kevin Nickerson
 */
public final class Settings
{
   public static boolean allowHydrogenIsotopes;
   public static boolean allowFakeElements;
   public static String lastPhrase;
   public static WindowPrefs WindowPreferences;
   public static String fontName;
   public static float weightBaseline;
   public static int symbolBaseline;
   public static float nameBaseline;
   public static boolean centerSymbol;
   public static boolean centerName;
   public static String lastSaveDirectory;


   public final static double SYMBOL_PROPORTION = 0.5;
   public final static double NAME_PROPORTION = 0.15;
   public final static double STROKE_PROPORTION = 0.03;
   public final static double GAP_PROPORTION = 0.06;

   public final static int TILE_SIZE = 1000;

   private static final String KEY_LAST_PHRASE = "lastPhrase";
   private static final String KEY_FONT_NAME = "fontName";
   private static final String KEY_ALLOW_FAKES = "allowFakes";
   private static final String KEY_ALLOW_ISOTOPES = "allowIsotopes";
   private static final String KEY_LAST_DIRECTORY = "lastSaveDirectory";


   private static final String COLUMN = "column";
   private static final String WIDTH = "width";
   private static final String HEIGHT = "height";
   private static final String X = "x";
   private static final String Y = "y";

   private static final int DEFAULT_MAINWND_WIDTH = 474;
   private static final int DEFAULT_MAINWND_HEIGHT = 332;

   


   /**
    * Get the Preferences root node.
    * @return The root node.
    */
   private static Preferences getRootNode ()
   {
      return Preferences.userNodeForPackage (AtomicallySpeaking.class);
   }


   static void load ()
   {
      Preferences prefs = getRootNode ();
      lastPhrase = prefs.get (KEY_LAST_PHRASE, "");
      fontName = prefs.get (KEY_FONT_NAME, Font.SANS_SERIF);
      allowFakeElements = prefs.getBoolean (KEY_ALLOW_FAKES, true);
      allowHydrogenIsotopes = prefs.getBoolean (KEY_ALLOW_ISOTOPES, true);
      lastSaveDirectory = prefs.get (KEY_LAST_DIRECTORY, System.getProperty("user.home"));

      nameBaseline = 1;
      symbolBaseline = 650;
      weightBaseline = 3;

      centerSymbol = false;
      centerName = true;

      WindowPreferences = getWindowPrefs ("main");
   }

   static void save ()
   {
      Preferences prefs = getRootNode ();
      prefs.put (KEY_LAST_PHRASE, lastPhrase);
      prefs.put (KEY_FONT_NAME, fontName);
      prefs.putBoolean (KEY_ALLOW_FAKES, allowFakeElements);
      prefs.putBoolean (KEY_ALLOW_ISOTOPES, allowHydrogenIsotopes);
      prefs.put (KEY_LAST_DIRECTORY, lastSaveDirectory);


      saveWindowPrefs ("main", WindowPreferences);
   }

   private static Preferences getWindowNode (String windowname)
   {
      return getRootNode ().node ("window").node (windowname);
   }

   /**
    * Load the WindowPrefs from persistent storage.
    * @param windowName The name of the window to load
    * @return WindowPrefs
    */
   public static WindowPrefs getWindowPrefs (String windowName)
   {
      Preferences p = getWindowNode (windowName);
      WindowPrefs prefs = new WindowPrefs ();
       
      prefs.size.width =  p.getInt (WIDTH, DEFAULT_MAINWND_WIDTH);
      prefs.size.height = p.getInt (HEIGHT, DEFAULT_MAINWND_HEIGHT);

      prefs.location.x = p.getInt (X, 0);
      prefs.location.y = p.getInt (Y, 0);

      for (int i=0; ; i++)
      {
         int width = p.getInt (COLUMN+i, -1);
         if (width < 0)
            break;
         else
         {
            while (prefs.columnWidths.size () <= i)
               prefs.columnWidths.add (20);

            prefs.columnWidths.set (i, p.getInt (COLUMN+i, 20));
         }
      }
      
      return prefs;
   }
   
   /**
    * Write the WindowPrefs to persistent storage.
    * @param windowName The name of the window to save.
    * @param prefs The WindowPrefs.
    */
   public static void saveWindowPrefs (String windowName, WindowPrefs prefs)
   {
      Preferences p = getWindowNode (windowName);
      p.putInt (WIDTH, prefs.size.width);
      p.putInt (HEIGHT, prefs.size.height);
      
      p.putInt (X, prefs.location.x);
      p.putInt (Y, prefs.location.y);

      for (int i=0; i<prefs.columnWidths.size (); i++)
         p.putInt (COLUMN+i, prefs.columnWidths.get (i));
   }
}
