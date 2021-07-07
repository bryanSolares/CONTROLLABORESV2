
package com.Graficos.Principal;

import com.DAO.DAOException;
import com.DAO.Managers.DAOManager;
import com.Graficos.Clientes.GestionClientes;
import com.Graficos.Tareas.GestionTareas;
import com.Modelos.Tarea;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class MenuPrincipal extends javax.swing.JFrame {

    private DAOManager manager;
    
    public MenuPrincipal(DAOManager manager) {
        initComponents();
        this.manager = manager;
        iniciarForma();
    }

    private void iniciarForma(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        JM_clientes = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        JM_creaerTarea = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        JM_salir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU PRINCIPAL");

        fileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/configuracion.png"))); // NOI18N
        fileMenu.setMnemonic('f');
        fileMenu.setText("CONFIGURACIONES");
        fileMenu.setBorderPainted(true);
        fileMenu.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N

        JM_clientes.setMnemonic('o');
        JM_clientes.setText("CLIENTES");
        JM_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JM_clientesActionPerformed(evt);
            }
        });
        fileMenu.add(JM_clientes);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("RESPONSABLES");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("...");
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/salir.png"))); // NOI18N
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("SALIR");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/gestion.png"))); // NOI18N
        editMenu.setMnemonic('e');
        editMenu.setText("GESTION");
        editMenu.setBorderPainted(true);
        editMenu.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N

        JM_creaerTarea.setMnemonic('t');
        JM_creaerTarea.setText("ADMINISTRAR TAREAS");
        JM_creaerTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JM_creaerTareaActionPerformed(evt);
            }
        });
        editMenu.add(JM_creaerTarea);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Crear Pendientes");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("VISUALIZAR TAREAS");
        pasteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("...");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/reportes.png"))); // NOI18N
        helpMenu.setMnemonic('h');
        helpMenu.setText("REPORTES");
        helpMenu.setBorderPainted(true);
        helpMenu.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("...");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("...");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        JM_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/salir.png"))); // NOI18N
        JM_salir.setMnemonic('h');
        JM_salir.setText("SALIR");
        JM_salir.setBorderPainted(true);
        JM_salir.setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N
        JM_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JM_salirMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JM_salirMouseClicked(evt);
            }
        });
        JM_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JM_salirActionPerformed(evt);
            }
        });
        menuBar.add(JM_salir);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1016, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void JM_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JM_clientesActionPerformed
        GestionClientes gc = new GestionClientes(manager.getDAOClientes());
        this.desktopPane.add(gc);
        gc.show();
    }//GEN-LAST:event_JM_clientesActionPerformed

    private void JM_creaerTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JM_creaerTareaActionPerformed
        
        try {
            Tarea tarea = manager.getDAOTareas().buscarUno(40385L);
            GestionTareas gt = new GestionTareas(manager);
            this.desktopPane.add(gt);
            gt.setTarea(tarea);
            gt.cargarDatos();
            gt.show();
        } catch (DAOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_JM_creaerTareaActionPerformed

    private void JM_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JM_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JM_salirActionPerformed

    private void JM_salirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JM_salirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_JM_salirMouseClicked

    private void JM_salirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JM_salirMousePressed
        System.exit(0);
    }//GEN-LAST:event_JM_salirMousePressed

    private void pasteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuItemActionPerformed
        ListadoTareas lt = new ListadoTareas(this.manager);
        this.desktopPane.add(lt);
        lt.show();
    }//GEN-LAST:event_pasteMenuItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JM_clientes;
    private javax.swing.JMenuItem JM_creaerTarea;
    private javax.swing.JMenu JM_salir;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}
