/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Clases.Canal;
import Clases.Imagen;
import java.util.Vector;

/**
 *
 * @author francootero
 */
public class Ejercicio4 extends javax.swing.JFrame {
    
        private Vector<Imagen> imagenes;
  

    /**
     * Creates new form Ejercicio4
     */
    public Ejercicio4(javax.swing.JFrame parent, boolean modal, Vector<Imagen> imagenes) {
        initComponents();
        
        this.imagenes = imagenes;
        
        
     
        
        
    }
    
    
   
    

    private Ejercicio4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRuidoCanal2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtRuidoCanal8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRuidoCanal10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Euphemia UCAS", 0, 18)); // NOI18N
        jLabel1.setText("Canales de Información:");

        jLabel2.setText("Canal 2");

        jLabel3.setText("Canal 8");

        jLabel4.setText("Canal 10");

        jLabel5.setText("Ruido por muestreo:");

        txtRuidoCanal2.setText("1");

        jLabel7.setText("Ruido por muestreo:");

        txtRuidoCanal8.setText("1");

        jLabel9.setText("Ruido por muestreo:");

        txtRuidoCanal10.setText("1");

        jButton1.setText("Obtener Matriz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Obtener Matriz");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Obtener Matriz");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel2)
                .addGap(267, 267, 267)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(95, 95, 95))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtRuidoCanal2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRuidoCanal8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtRuidoCanal10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(165, 165, 165)
                        .addComponent(jButton3)))
                .addGap(67, 67, 67))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRuidoCanal2)
                    .addComponent(jLabel7)
                    .addComponent(txtRuidoCanal8)
                    .addComponent(jLabel9)
                    .addComponent(txtRuidoCanal10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        Canal c3 = new Canal (imagenes.get(0).getSecuencia(),imagenes.get(3).getSecuencia(),imagenes.get(0).getSimbolos());
        double[] []  conjunta3 = c3.getMatrizConjunta();
        double [] [] transicion3 = c3.getMatrizdeTransicion(conjunta3, imagenes.get(0).getProbabilidades());
       Matriz matriz3 = new Matriz (transicion3,imagenes.get(0).getSimbolos());
        matriz3.setVisible(true);
        matriz3.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        matriz3.setLocationRelativeTo(this);
        matriz3.toFront();
        double ruidoCanal3 = c3.ruidoPorMuestreo(transicion3, imagenes.get(0).getProbabilidades());
        System.out.print("Ruido: "+ ruidoCanal3+"\n");// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        Canal c1 = new Canal (imagenes.get(0).getSecuencia(),imagenes.get(1).getSecuencia(),imagenes.get(0).getSimbolos());
        double[] []  conjunta1 = c1.getMatrizConjunta();
        double [] [] transicion1 = c1.getMatrizdeTransicion(conjunta1, imagenes.get(0).getProbabilidades());
        Matriz matriz1 = new Matriz (transicion1,imagenes.get(0).getSimbolos());
        matriz1.setVisible(true);
        matriz1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        matriz1.setLocationRelativeTo(this);
        matriz1.toFront(); 
        double ruidoCanal1 = c1.ruidoPorMuestreo(transicion1, imagenes.get(0).getProbabilidades());
        System.out.print("Ruido: "+ ruidoCanal1+"\n");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        Canal c2 = new Canal (imagenes.get(0).getSecuencia(),imagenes.get(2).getSecuencia(),imagenes.get(0).getSimbolos());
        double[] []  conjunta2 = c2.getMatrizConjunta();
        double [] [] transicion2 = c2.getMatrizdeTransicion(conjunta2, imagenes.get(0).getProbabilidades());
        Matriz matriz1 = new Matriz (transicion2,imagenes.get(0).getSimbolos());
        matriz1.setVisible(true);
        matriz1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        matriz1.setLocationRelativeTo(this);
        matriz1.toFront();
        double ruidoCanal2 = c2.ruidoPorMuestreo(transicion2, imagenes.get(0).getProbabilidades());
        System.out.print("Ruido: "+ ruidoCanal2+"\n");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ejercicio4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ejercicio4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ejercicio4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ejercicio4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ejercicio4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtRuidoCanal10;
    private javax.swing.JLabel txtRuidoCanal2;
    private javax.swing.JLabel txtRuidoCanal8;
    // End of variables declaration//GEN-END:variables
}
