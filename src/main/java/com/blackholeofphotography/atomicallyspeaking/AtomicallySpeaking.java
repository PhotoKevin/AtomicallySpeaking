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

import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author Kevin Nickerson
 */
public class AtomicallySpeaking extends javax.swing.JFrame
{
   double docWidth = 2;
   double docHeight = 1;

   /**
    * Creates new form AtomicallySpeaking
    */
   public AtomicallySpeaking ()
   {
      initComponents ();
      this.txtPhrase.getDocument ().addDocumentListener (new DocumentListener ()
      {
         @Override
         public void removeUpdate (DocumentEvent e)
         {
            generatePossibilities (txtPhrase.getText ());
         }

         @Override
         public void insertUpdate (DocumentEvent e)
         {
            generatePossibilities (txtPhrase.getText ());
         }

         @Override
         public void changedUpdate (DocumentEvent e)
         {
            generatePossibilities (txtPhrase.getText ());
         }
      });

      jTable1.getSelectionModel ().addListSelectionListener (new ListSelectionListener ()
      {

         @Override
         public void valueChanged (ListSelectionEvent lse)
         {
            if (!lse.getValueIsAdjusting ())
               DisplaySelectedPhrase ();
         }
      });

      Settings.load ();
      this.setLocation (Settings.WindowPreferences.location);
      this.setSize (Settings.WindowPreferences.size);
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents()
   {

      jFileChooser1 = new javax.swing.JFileChooser();
      pnlTop = new javax.swing.JPanel();
      txtPhrase = new javax.swing.JTextField();
      pnlBottom = new javax.swing.JPanel();
      jSplitPane1 = new javax.swing.JSplitPane();
      jScrollPane2 = new javax.swing.JScrollPane();
      jTable1 = new javax.swing.JTable();
      jPanel1 = new javax.swing.JPanel();
      jSVGCanvas1 = new org.apache.batik.swing.JSVGCanvas();
      jMenuBar1 = new javax.swing.JMenuBar();
      mnuFile = new javax.swing.JMenu();
      mnuSaveAs = new javax.swing.JMenuItem();
      mnuExit = new javax.swing.JMenuItem();
      mnuEdit = new javax.swing.JMenu();
      mnuAllowIsotopes = new javax.swing.JCheckBoxMenuItem();
      mnuAllowFakeElements = new javax.swing.JCheckBoxMenuItem();
      mnuSettings = new javax.swing.JMenuItem();
      mnuHelp = new javax.swing.JMenu();
      mnuAbout = new javax.swing.JMenuItem();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("Atomically Speaking");
      setMinimumSize(new java.awt.Dimension(585, 272));
      addWindowListener(new java.awt.event.WindowAdapter()
      {
         public void windowClosing(java.awt.event.WindowEvent evt)
         {
            formWindowClosing(evt);
         }
         public void windowOpened(java.awt.event.WindowEvent evt)
         {
            formWindowOpened(evt);
         }
      });

      pnlTop.setBackground(new java.awt.Color(0, 255, 0));

      javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
      pnlTop.setLayout(pnlTopLayout);
      pnlTopLayout.setHorizontalGroup(
         pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(pnlTopLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtPhrase, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addContainerGap())
      );
      pnlTopLayout.setVerticalGroup(
         pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(pnlTopLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(txtPhrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );

      getContentPane().add(pnlTop, java.awt.BorderLayout.PAGE_START);

      pnlBottom.setBackground(new java.awt.Color(255, 255, 51));

      jSplitPane1.setDividerLocation(200);

      jScrollPane2.setMinimumSize(new java.awt.Dimension(123, 123));
      jScrollPane2.setPreferredSize(new java.awt.Dimension(120, 122));

      jTable1.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][]
         {

         },
         new String []
         {
            "Atom", "Score"
         }
      )
      {
         Class[] types = new Class []
         {
            java.lang.Object.class, java.lang.Float.class
         };
         boolean[] canEdit = new boolean []
         {
            false, false
         };

         public Class getColumnClass(int columnIndex)
         {
            return types [columnIndex];
         }

         public boolean isCellEditable(int rowIndex, int columnIndex)
         {
            return canEdit [columnIndex];
         }
      });
      jTable1.setMinimumSize(new java.awt.Dimension(60, 63));
      jTable1.getTableHeader().setReorderingAllowed(false);
      jScrollPane2.setViewportView(jTable1);

      jSplitPane1.setLeftComponent(jScrollPane2);

      jSVGCanvas1.setBackground(new java.awt.Color(255, 0, 102));
      jSVGCanvas1.setBorder(new javax.swing.border.MatteBorder(null));
      jSVGCanvas1.setPreferredSize(new java.awt.Dimension(50, 50));
      jSVGCanvas1.addComponentListener(new java.awt.event.ComponentAdapter()
      {
         public void componentResized(java.awt.event.ComponentEvent evt)
         {
            jSVGCanvas1ComponentResized(evt);
         }
      });

      javax.swing.GroupLayout jSVGCanvas1Layout = new javax.swing.GroupLayout(jSVGCanvas1);
      jSVGCanvas1.setLayout(jSVGCanvas1Layout);
      jSVGCanvas1Layout.setHorizontalGroup(
         jSVGCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 436, Short.MAX_VALUE)
      );
      jSVGCanvas1Layout.setVerticalGroup(
         jSVGCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 178, Short.MAX_VALUE)
      );

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 438, Short.MAX_VALUE)
         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSVGCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
      );
      jPanel1Layout.setVerticalGroup(
         jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 180, Short.MAX_VALUE)
         .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSVGCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
      );

      jSplitPane1.setRightComponent(jPanel1);

      javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
      pnlBottom.setLayout(pnlBottomLayout);
      pnlBottomLayout.setHorizontalGroup(
         pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(pnlBottomLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jSplitPane1)
            .addContainerGap())
      );
      pnlBottomLayout.setVerticalGroup(
         pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(pnlBottomLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jSplitPane1)
            .addContainerGap())
      );

      getContentPane().add(pnlBottom, java.awt.BorderLayout.CENTER);

      mnuFile.setMnemonic('F');
      mnuFile.setText("File");

      mnuSaveAs.setText("Save As");
      mnuSaveAs.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            mnuSaveAsActionPerformed(evt);
         }
      });
      mnuFile.add(mnuSaveAs);

      mnuExit.setMnemonic('x');
      mnuExit.setText("Exit");
      mnuExit.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            mnuExitActionPerformed(evt);
         }
      });
      mnuFile.add(mnuExit);

      jMenuBar1.add(mnuFile);

      mnuEdit.setMnemonic('E');
      mnuEdit.setText("Edit");

      mnuAllowIsotopes.setSelected(true);
      mnuAllowIsotopes.setText("Allow Hydrogen Isotopes");
      mnuAllowIsotopes.addChangeListener(new javax.swing.event.ChangeListener()
      {
         public void stateChanged(javax.swing.event.ChangeEvent evt)
         {
            mnuAllowIsotopesStateChanged(evt);
         }
      });
      mnuEdit.add(mnuAllowIsotopes);

      mnuAllowFakeElements.setSelected(true);
      mnuAllowFakeElements.setText("Allow Fake Elements");
      mnuAllowFakeElements.addChangeListener(new javax.swing.event.ChangeListener()
      {
         public void stateChanged(javax.swing.event.ChangeEvent evt)
         {
            mnuAllowFakeElementsStateChanged(evt);
         }
      });
      mnuEdit.add(mnuAllowFakeElements);

      mnuSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
      mnuSettings.setMnemonic('s');
      mnuSettings.setText("Settings");
      mnuEdit.add(mnuSettings);

      jMenuBar1.add(mnuEdit);

      mnuHelp.setMnemonic('h');
      mnuHelp.setText("Help");

      mnuAbout.setMnemonic('a');
      mnuAbout.setText("About");
      mnuHelp.add(mnuAbout);

      jMenuBar1.add(mnuHelp);

      setJMenuBar(jMenuBar1);

      pack();
   }// </editor-fold>//GEN-END:initComponents

  
   private void generatePossibilities (String text)
   {
      System.out.println ("Start generatePossibilities");
      var dtm = (DefaultTableModel) this.jTable1.getModel ();
      dtm.setRowCount (0);
      ArrayList<Phrase> possible = Phrase.generatePossibilities (text);

      for (int i=0; i<possible.size (); i++)
      {
         Phrase p = possible.get (i);
         int numFakes = p.getFakeCount ();
         int numIsotopes = p.getIsotopeCount ();
         if ((numFakes == 0 && numIsotopes == 0) ||
             (numFakes == 0 && Settings.allowHydrogenIsotopes) ||
             (Settings.allowFakeElements && numIsotopes == 0) ||
             (Settings.allowFakeElements && Settings.allowHydrogenIsotopes))
            dtm.addRow(new Object[] {p, p.getScore ()});
      }
      if (jTable1.getRowCount () > 0)
         jTable1.setRowSelectionInterval (0, 0);

      System.out.println ("End generatePossibilities");
   }

   private void DisplaySelectedPhrase ()
   {
      var dtm = (DefaultTableModel) jTable1.getModel ();

      int row = jTable1.getSelectedRow ();
      if (row < 0)
         this.jSVGCanvas1.setDocument (null);

      if (row >= 0)
      {
         Phrase phrase = (Phrase) dtm.getValueAt (row, 0);
         DisplayPhrase (phrase);
      }
   }


   private void DisplayPhrase (Phrase p)
   {
      String svgElement = p.toSVG ();

      StringReader reader = new StringReader(svgElement);
      String uri = "";
      try 
      {
         String parser = XMLResourceDescriptor.getXMLParserClassName();
         SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
         SVGDocument doc = f.createSVGDocument(uri,reader);
         this.jSVGCanvas1.setDocument (doc);
      } 
      catch (IOException ex) 
      {
      } 
      finally 
      {
         reader.close();
      }  
   }

   private void rescale ()
   {
//      AffineTransform at = this.jSVGCanvas1.getRenderingTransform ();
//      System.out.print (this.jSVGCanvas1.getWidth () +"x"+this.jSVGCanvas1.getHeight ());
//      var rectSize = this.jSVGCanvas1.getVisibleRect ();
//      
//
//
//      double scaleFactorX = rectSize.getWidth () / docWidth;
//      double scaleFactorY = this.jSVGCanvas1.getHeight () / docHeight;
//
//      if (this.jSVGCanvas1.getHeight () < docHeight)
//         scaleFactorY = 1.0;
//
//      double scaleFactor = Math.min (scaleFactorX, scaleFactorY);
//
//      System.out.println (" -> " + scaleFactor);
//      at.setToScale (scaleFactor, scaleFactor);
//      this.jSVGCanvas1.setRenderingTransform (at, true);
   }



   private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
   {//GEN-HEADEREND:event_formWindowOpened
      this.txtPhrase.setText (Settings.lastPhrase);
      this.mnuAllowFakeElements.setState (Settings.allowFakeElements);
      this.mnuAllowIsotopes.setState (Settings.allowHydrogenIsotopes);
      this.mnuSettings.setVisible (false);

   }//GEN-LAST:event_formWindowOpened

   private void jSVGCanvas1ComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_jSVGCanvas1ComponentResized
   {//GEN-HEADEREND:event_jSVGCanvas1ComponentResized
      //DrawMap ();
      rescale ();
   }//GEN-LAST:event_jSVGCanvas1ComponentResized

   private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
   {//GEN-HEADEREND:event_formWindowClosing
      Settings.lastPhrase = txtPhrase.getText ();
      if (!this.isMaximumSizeSet ())
      {
         Settings.WindowPreferences.location = this.getLocation ();
         Settings.WindowPreferences.size = this.getSize ();
      }
      Settings.save ();
   }//GEN-LAST:event_formWindowClosing

   private void mnuExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuExitActionPerformed
   {//GEN-HEADEREND:event_mnuExitActionPerformed
      this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
   }//GEN-LAST:event_mnuExitActionPerformed

   private void mnuSaveAsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mnuSaveAsActionPerformed
   {//GEN-HEADEREND:event_mnuSaveAsActionPerformed
      int row = this.jTable1.getSelectedRow ();
      if (row >= 0)
      {
         var dtm = (DefaultTableModel) jTable1.getModel ();

         Phrase phrase = (Phrase) dtm.getValueAt (row, 0);
         FileNameExtensionFilter filter = new FileNameExtensionFilter ("SVG Images", "svg");

         jFileChooser1.setFileFilter (filter);

         jFileChooser1.setCurrentDirectory (new File (Settings.lastSaveDirectory));
         int result = this.jFileChooser1.showSaveDialog (this);
         File f = jFileChooser1.getSelectedFile ();
         Settings.lastSaveDirectory = f.getParent ();

         if (result == JFileChooser.APPROVE_OPTION)
         {
            String parent = f.getParent ();
            String name = f.getName ();

            if (name.contains (".") && !name.endsWith (".svg"))
            {
               int index = name.lastIndexOf (".");
               name = name.substring (0, index) + ".svg";   
            }
            else if (!name.contains ("."))
            {
               name += ".svg";   
            }

            String fullSaveName = Path.of (parent, name).toString ();

            String svg = phrase.toSVG ();
            File outFile = new File (fullSaveName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile)))
            {
               writer.write (svg);
            }
            catch (IOException ex)
            {
               Logger.getLogger (AtomicallySpeaking.class.getName()).log (Level.SEVERE, null, ex);
            }
         }
      }
   }//GEN-LAST:event_mnuSaveAsActionPerformed

   private void mnuAllowFakeElementsStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_mnuAllowFakeElementsStateChanged
   {//GEN-HEADEREND:event_mnuAllowFakeElementsStateChanged
      Settings.allowFakeElements = mnuAllowFakeElements.getState ();
      this.generatePossibilities (this.txtPhrase.getText ());
   }//GEN-LAST:event_mnuAllowFakeElementsStateChanged

   private void mnuAllowIsotopesStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_mnuAllowIsotopesStateChanged
   {//GEN-HEADEREND:event_mnuAllowIsotopesStateChanged
      Settings.allowHydrogenIsotopes = mnuAllowIsotopes.getState ();
      this.generatePossibilities (this.txtPhrase.getText ());
   }//GEN-LAST:event_mnuAllowIsotopesStateChanged

   /**
    * @param args the command line arguments
    */
   public static void main (String args[])
   {
      /* Set the Nimbus look and feel */
      //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
      /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
       */
      try
      {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels ())
         {
            if ("Nimbus".equals (info.getName ()))
            {
               javax.swing.UIManager.setLookAndFeel (info.getClassName ());
               break;
            }
         }
      }
      catch (ClassNotFoundException ex)
      {
         java.util.logging.Logger.getLogger (AtomicallySpeaking.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      catch (InstantiationException ex)
      {
         java.util.logging.Logger.getLogger (AtomicallySpeaking.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      catch (IllegalAccessException ex)
      {
         java.util.logging.Logger.getLogger (AtomicallySpeaking.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      catch (javax.swing.UnsupportedLookAndFeelException ex)
      {
         java.util.logging.Logger.getLogger (AtomicallySpeaking.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater (new Runnable ()
      {
         public void run ()
         {
            new AtomicallySpeaking ().setVisible (true);
         }
      });
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JFileChooser jFileChooser1;
   private javax.swing.JMenuBar jMenuBar1;
   private javax.swing.JPanel jPanel1;
   private org.apache.batik.swing.JSVGCanvas jSVGCanvas1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JSplitPane jSplitPane1;
   private javax.swing.JTable jTable1;
   private javax.swing.JMenuItem mnuAbout;
   private javax.swing.JCheckBoxMenuItem mnuAllowFakeElements;
   private javax.swing.JCheckBoxMenuItem mnuAllowIsotopes;
   private javax.swing.JMenu mnuEdit;
   private javax.swing.JMenuItem mnuExit;
   private javax.swing.JMenu mnuFile;
   private javax.swing.JMenu mnuHelp;
   private javax.swing.JMenuItem mnuSaveAs;
   private javax.swing.JMenuItem mnuSettings;
   private javax.swing.JPanel pnlBottom;
   private javax.swing.JPanel pnlTop;
   private javax.swing.JTextField txtPhrase;
   // End of variables declaration//GEN-END:variables
}