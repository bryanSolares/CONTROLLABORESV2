package com.Graficos.Tareas;

import com.DAO.DAOException;
import com.DAO.Managers.DAOManager;
import com.Modelos.Cliente;
import com.Modelos.Combos.ModeloComboClientes;
import com.Modelos.Combos.ModeloComboParametros;
import com.Modelos.Parametros;
import com.Modelos.Tablas.ModeloTablaDetalleTareas;
import com.Modelos.Tarea;
import com.Modelos.TareaDetalle;
import com.Recursos.GestionarRecursos;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class GestionTareas extends javax.swing.JInternalFrame {

    private ModeloComboParametros modeloCbPrioridad, modeloCbEstado, modeloCbResponsable;
    private ModeloComboClientes modeloCbClientes;
    private ModeloTablaDetalleTareas modeloTBDetallesTareas;
    private final DAOManager manager;
    private Tarea tarea;
    private TareaDetalle tareaDetalle;

    public GestionTareas(DAOManager manager) {
        initComponents();
        this.manager = manager;

        modeloCbPrioridad = new ModeloComboParametros();
        modeloCbEstado = new ModeloComboParametros();
        modeloCbResponsable = new ModeloComboParametros();
        modeloCbClientes = new ModeloComboClientes(manager.getDAOClientes());
        modeloTBDetallesTareas = new ModeloTablaDetalleTareas(manager.getDAOTareas());
        cargarModelos();
    }

    private void cargarModelos() {
        try {
            modeloCbClientes.actualizar();
            modeloCbPrioridad.actualizarCombo(ModeloComboParametros.TIPO_PRIORIDAD);
            modeloCbEstado.actualizarCombo(ModeloComboParametros.TIPO_ESTADO_TAREA);
            modeloCbResponsable.actualizarCombo(ModeloComboParametros.TIPO_REPONSABLE_SOPORTE);
            modeloTBDetallesTareas.actualizarModelo();

            JC_cliente.setModel(modeloCbClientes);
            JC_tipoPrioridad.setModel(modeloCbPrioridad);
            JC_tipoEstado.setModel(modeloCbEstado);
            JC_tipoResponsable.setModel(modeloCbResponsable);
            JT_detallesTarea.setModel(modeloTBDetallesTareas);
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    private void cargarDatos() {

    }

    private void guardarDatos() {

    }

    private Long obtenerClienteSeleccionado() {
        return ((Cliente) JC_cliente.getSelectedItem()).getId();
    }

    private int obtenerParametroSeleccionad(JComboBox combo) {
        return ((Parametros) combo.getSelectedItem()).getId();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar4 = new javax.swing.JToolBar();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        JB_guardar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        JB_salir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JT_detallesTarea = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        JT_detalleDescripcion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        JB_agregarDetalle = new javax.swing.JButton();
        JB_eliminarDetalle = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JT_nombre = new javax.swing.JTextField();
        JC_tipoEstado = new javax.swing.JComboBox<>();
        JC_tipoPrioridad = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JC_cliente = new javax.swing.JComboBox<>();
        JD_fecha_inicio = new com.toedter.calendar.JDateChooser();
        JD_fecha_fin = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        JC_tipoResponsable = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        JT_tiempoTranscurrido = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("GESTION DE TAREAS");

        jToolBar4.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar4.setRollover(true);
        jToolBar4.add(jSeparator2);

        JB_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/guardar.png"))); // NOI18N
        JB_guardar.setText("Guardar");
        JB_guardar.setFocusable(false);
        JB_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JB_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_guardarActionPerformed(evt);
            }
        });
        jToolBar4.add(JB_guardar);
        jToolBar4.add(jSeparator1);

        JB_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/salir.png"))); // NOI18N
        JB_salir.setText("Salir");
        JB_salir.setFocusable(false);
        JB_salir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JB_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_salirActionPerformed(evt);
            }
        });
        jToolBar4.add(JB_salir);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        JT_detallesTarea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.darkGray, null, null));
        JT_detallesTarea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JT_detallesTarea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_detallesTareaMouseClicked(evt);
            }
        });
        JT_detallesTarea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JT_detallesTareaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(JT_detallesTarea);

        JT_detalleDescripcion.setColumns(20);
        JT_detalleDescripcion.setLineWrap(true);
        JT_detalleDescripcion.setRows(5);
        JT_detalleDescripcion.setWrapStyleWord(true);
        jScrollPane2.setViewportView(JT_detalleDescripcion);

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel9.setText("Detalles");

        JB_agregarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/plus.png"))); // NOI18N
        JB_agregarDetalle.setFocusable(false);
        JB_agregarDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JB_agregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_agregarDetalleActionPerformed(evt);
            }
        });
        JB_agregarDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JB_agregarDetalleKeyPressed(evt);
            }
        });

        JB_eliminarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/minus.png"))); // NOI18N
        JB_eliminarDetalle.setFocusable(false);
        JB_eliminarDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JB_eliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_eliminarDetalleActionPerformed(evt);
            }
        });
        JB_eliminarDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JB_eliminarDetalleKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JB_agregarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_eliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE))
                        .addGap(10, 10, 10))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JB_agregarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JB_eliminarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setText("Titulo Tarea");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setText("Cliente");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("Estado");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel4.setText("Prioridad");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel5.setText("Fecha Inicio");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel6.setText("Fecha Finalización");

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel7.setText("Responsable Tarea");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel8.setText("Tiempo Transcurrido:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(JC_tipoPrioridad, javax.swing.GroupLayout.Alignment.LEADING, 0, 450, Short.MAX_VALUE)
                                .addComponent(JC_cliente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(JT_nombre, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JD_fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel5)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel6))
                            .addComponent(JD_fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(141, 141, 141))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JT_tiempoTranscurrido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JC_tipoEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JC_tipoResponsable, 0, 450, Short.MAX_VALUE))
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JT_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JC_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JC_tipoPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JC_tipoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JC_tipoResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JD_fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JD_fecha_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(JT_tiempoTranscurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JT_detallesTareaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_detallesTareaMouseClicked

    }//GEN-LAST:event_JT_detallesTareaMouseClicked

    private void JT_detallesTareaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_detallesTareaKeyPressed

    }//GEN-LAST:event_JT_detallesTareaKeyPressed

    private void JB_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_guardarActionPerformed


    }//GEN-LAST:event_JB_guardarActionPerformed

    private void JB_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_salirActionPerformed

    private void JB_agregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_agregarDetalleActionPerformed
        agregarADetalle();
    }//GEN-LAST:event_JB_agregarDetalleActionPerformed

    private void JB_eliminarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_eliminarDetalleActionPerformed
        eliminarDeDetalle();
    }//GEN-LAST:event_JB_eliminarDetalleActionPerformed

    private void JB_agregarDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JB_agregarDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            agregarADetalle();
        }
    }//GEN-LAST:event_JB_agregarDetalleKeyPressed

    private void JB_eliminarDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JB_eliminarDetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            eliminarDeDetalle();
        }
    }//GEN-LAST:event_JB_eliminarDetalleKeyPressed

    private void agregarADetalle() {
        if (!JT_detalleDescripcion.getText().equalsIgnoreCase("")) {
            JT_detalleDescripcion.requestFocus();

            if (tareaDetalle == null) {
                tareaDetalle = new TareaDetalle();
                tareaDetalle.setIdDetalle((Long.MAX_VALUE / 10) - Long.valueOf(modeloTBDetallesTareas.getRowCount()));
            }
            tareaDetalle.setDescripcion(JT_detalleDescripcion.getText());
            modeloTBDetallesTareas.addElementToData(tareaDetalle);
            tareaDetalle = null;
            JT_detalleDescripcion.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No puede agregar elementos vacios", "Agregar Detalle", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void eliminarDeDetalle() {
        if (JT_detallesTarea.getRowCount() > 0) {
            Long idSeleccionado;
            if (JT_detallesTarea.getSelectedRow() >= 0) {
                idSeleccionado = (Long) modeloTBDetallesTareas.getValueAt(JT_detallesTarea.getSelectedRow(), 1);
            } else {
                idSeleccionado = (Long) modeloTBDetallesTareas.getValueAt(modeloTBDetallesTareas.getRowCount() - 1, 1);
            }
            tareaDetalle = modeloTBDetallesTareas.getElementById(idSeleccionado);
            modeloTBDetallesTareas.removeElementWithId(idSeleccionado);
            JT_detalleDescripcion.setText(tareaDetalle.getDescripcion());
        } else {
            JOptionPane.showMessageDialog(this, "No existen elementos para eliminar", "Error al eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_agregarDetalle;
    private javax.swing.JButton JB_eliminarDetalle;
    private javax.swing.JButton JB_guardar;
    private javax.swing.JButton JB_salir;
    private javax.swing.JComboBox<Cliente> JC_cliente;
    private javax.swing.JComboBox<Parametros> JC_tipoEstado;
    private javax.swing.JComboBox<Parametros> JC_tipoPrioridad;
    private javax.swing.JComboBox<Parametros> JC_tipoResponsable;
    private com.toedter.calendar.JDateChooser JD_fecha_fin;
    private com.toedter.calendar.JDateChooser JD_fecha_inicio;
    private javax.swing.JTextArea JT_detalleDescripcion;
    private javax.swing.JTable JT_detallesTarea;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_tiempoTranscurrido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar4;
    // End of variables declaration//GEN-END:variables
}
