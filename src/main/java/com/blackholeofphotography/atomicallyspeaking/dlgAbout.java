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
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author Kevin Nickerson <kevin@blackholeofphotography.com>
 */
public final class dlgAbout extends javax.swing.JDialog
{

   /**
    * Creates new form dlgAbout
    */
   public dlgAbout (java.awt.Frame parent, boolean modal)
   {
      super (parent, modal);
      initComponents ();
      displayImage ();
//      this.jTextPane1.setText ("<html>&copyr;2021 Kevin Nickerson<bt/><a href=\"https://wwww.github.com/PhotoKevin\"</a></html>");

      var btnBackground = this.pnlButtons.getBackground ();
      this.jSVGCanvas1.setBackground (btnBackground);
      this.jEditorPane1.setBackground (btnBackground);
      this.jScrollPane1.setBackground (btnBackground);
      this.jPanel3.setBackground (btnBackground);
      
   }
   private void displayImage ()
   {
      try 
      {
         var packageName = this.getClass ().getPackageName ().replace (".", "/");

         var resourcePath = String.format ("%s/%s", packageName, "Atomically Speaking.svg");
         InputStream is = PeriodicTable.class.getClassLoader().getResourceAsStream (resourcePath);
   
         String uri = "";
         String parser = XMLResourceDescriptor.getXMLParserClassName();
         SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
         SVGDocument doc = f.createSVGDocument (uri, is);
         this.jSVGCanvas1.setDocument (doc);
      } 
      catch (IOException ex) 
      {
      } 
      finally 
      {
//         reader.close();
      }  
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

      pnlButtons = new javax.swing.JPanel();
      btnOK = new javax.swing.JButton();
      jPanel3 = new javax.swing.JPanel();
      jSVGCanvas1 = new org.apache.batik.swing.JSVGCanvas();
      jScrollPane1 = new javax.swing.JScrollPane();
      jEditorPane1 = new javax.swing.JEditorPane();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setBackground(new java.awt.Color(204, 0, 51));
      setResizable(false);

      pnlButtons.setMaximumSize(new java.awt.Dimension(57, 33));
      pnlButtons.setPreferredSize(new java.awt.Dimension(57, 33));

      btnOK.setText("OK");
      btnOK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
      btnOK.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            btnOKActionPerformed(evt);
         }
      });
      pnlButtons.add(btnOK);

      jPanel3.setMaximumSize(new java.awt.Dimension(400, 262));
      jPanel3.setMinimumSize(new java.awt.Dimension(400, 262));

      jSVGCanvas1.setMinimumSize(new java.awt.Dimension(100, 300));
      jSVGCanvas1.setPreferredSize(new java.awt.Dimension(50, 300));

      javax.swing.GroupLayout jSVGCanvas1Layout = new javax.swing.GroupLayout(jSVGCanvas1);
      jSVGCanvas1.setLayout(jSVGCanvas1Layout);
      jSVGCanvas1Layout.setHorizontalGroup(
         jSVGCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );
      jSVGCanvas1Layout.setVerticalGroup(
         jSVGCanvas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 0, Short.MAX_VALUE)
      );

      jScrollPane1.setBorder(null);
      jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
      jScrollPane1.setPreferredSize(new java.awt.Dimension(297, 45));

      jEditorPane1.setEditable(false);
      jEditorPane1.setBorder(null);
      jEditorPane1.setContentType("text/html"); // NOI18N
      jEditorPane1.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body style=\"background-color:D6D9DF\"> \r\n    <p style=\"margin-top: 10; margin-left:10; \">\r\n\n©2021 Kevin Nickerson<br/> <a href=\"https://github.com/PhotoKevin/AtomicallySpeaking\">https://github.com/PhotoKevin/AtomicallySpeaking</a></html>\n      \r\n    </p>\r\n  </body>\r\n</html>\r\n");
      jEditorPane1.addHyperlinkListener(new javax.swing.event.HyperlinkListener()
      {
         public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt)
         {
            jEditorPane1HyperlinkUpdate(evt);
         }
      });
      jScrollPane1.setViewportView(jEditorPane1);

      javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
      jPanel3.setLayout(jPanel3Layout);
      jPanel3Layout.setHorizontalGroup(
         jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jSVGCanvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addContainerGap())
      );
      jPanel3Layout.setVerticalGroup(
         jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSVGCanvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0))
      );

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .addComponent(pnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(pnlButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void btnOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnOKActionPerformed
   {//GEN-HEADEREND:event_btnOKActionPerformed
      this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
   }//GEN-LAST:event_btnOKActionPerformed

   private void jEditorPane1HyperlinkUpdate(javax.swing.event.HyperlinkEvent evt)//GEN-FIRST:event_jEditorPane1HyperlinkUpdate
   {//GEN-HEADEREND:event_jEditorPane1HyperlinkUpdate
      if (HyperlinkEvent.EventType.ACTIVATED.equals (evt.getEventType ()))
      {
         System.out.println (evt.getURL ());
         try
         {
            //java.awt.Desktop.getDesktop().browse(java.net.URI.create(evt.getURL ()));
            java.awt.Desktop.getDesktop().browse(evt.getURL ().toURI ());
         }
         catch (URISyntaxException | IOException ex)
         {
            Logger.getLogger (dlgAbout.class.getName()).log (Level.SEVERE, null, ex);
         }
      }
   }//GEN-LAST:event_jEditorPane1HyperlinkUpdate

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
         java.util.logging.Logger.getLogger (dlgAbout.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      catch (InstantiationException ex)
      {
         java.util.logging.Logger.getLogger (dlgAbout.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      catch (IllegalAccessException ex)
      {
         java.util.logging.Logger.getLogger (dlgAbout.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      catch (javax.swing.UnsupportedLookAndFeelException ex)
      {
         java.util.logging.Logger.getLogger (dlgAbout.class.getName ()).log (java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the dialog */
      java.awt.EventQueue.invokeLater (new Runnable ()
      {
         public void run ()
         {
            dlgAbout dialog = new dlgAbout (new javax.swing.JFrame (), true);
            dialog.addWindowListener (new java.awt.event.WindowAdapter ()
            {
               @Override
               public void windowClosing (java.awt.event.WindowEvent e)
               {
                  System.exit (0);
               }
            });
            dialog.setVisible (true);
         }
      });
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnOK;
   private javax.swing.JEditorPane jEditorPane1;
   private javax.swing.JPanel jPanel3;
   private org.apache.batik.swing.JSVGCanvas jSVGCanvas1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JPanel pnlButtons;
   // End of variables declaration//GEN-END:variables
}
