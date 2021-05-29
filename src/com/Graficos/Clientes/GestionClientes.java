package com.Graficos.Clientes;

import com.DAO.DAOClientes;
import com.DAO.DAOException;
import com.Modelos.Cliente;
import com.Modelos.Combos.ModeloComboParametros;
import com.Modelos.Parametros;
import com.Modelos.Tablas.ModeloTablaClientes;
import com.Recursos.GestionarRecursos;
import com.Recursos.ValidadoresFormas;
import javax.swing.JOptionPane;

public class GestionClientes extends javax.swing.JInternalFrame {

    private ModeloTablaClientes modeloTablaClientes;
    ModeloComboParametros modeloCbTipoCliente, modeloCbTipoSistema;
    private final DAOClientes manager;
    private Cliente cliente;
    private ValidadoresFormas validaFormas;

    public GestionClientes(DAOClientes manager) {
        initComponents();
        this.manager = manager;
        validaFormas = new ValidadoresFormas(this);
        modeloTablaClientes = new ModeloTablaClientes(manager);
        modeloCbTipoCliente = new ModeloComboParametros();
        modeloCbTipoSistema = new ModeloComboParametros();
        cargarModelos();
        agregarValidadoresACampos();
    }

    private void cargarModelos() {
        try {

            modeloTablaClientes.actualizarModelo();
            JT_datos.setModel(modeloTablaClientes);

            modeloCbTipoCliente.actualizarCombo(ModeloComboParametros.TIPO_CLIENTE);
            modeloCbTipoSistema.actualizarCombo(ModeloComboParametros.TIPO_SISTEMA);

            JC_tipoCliente.setModel(modeloCbTipoCliente);
            JC_tipoSistema.setModel(modeloCbTipoSistema);

            JT_datos.clearSelection();
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex, this.title);
        }
    }

    private void cargarDatos() {
        if (cliente == null) {
            JT_nombre.setText("");
            JT_telefono.setText("");
            JT_nombreContacto.setText("");
            JR_activo.setSelected(true);
        } else {
            JT_nombre.setText(cliente.getNombre());
            JT_telefono.setText(cliente.getTelefono());
            JC_tipoCliente.setSelectedItem((Parametros) Parametros.devuelveValorParametro(cliente.gettCliente(), Parametros.DEVUELVE_PARAMETRO));
            JC_tipoSistema.setSelectedItem((Parametros) Parametros.devuelveValorParametro(cliente.getId_sistema(), Parametros.DEVUELVE_PARAMETRO));
            JT_nombreContacto.setText(cliente.getContacto());
            if (cliente.getEstado() == Parametros.ACTIVO.getId()) {
                JR_activo.setSelected(true);
            } else {
                JR_inactivo.setSelected(true);
            }
        }
    }

    private void guardarDatos() {
        if (cliente == null) {
            cliente = new Cliente();
        }

        cliente.setNombre(this.JT_nombre.getText());
        cliente.setTelefono(this.JT_telefono.getText());
        cliente.settCliente(((Parametros) JC_tipoCliente.getSelectedItem()).getId());
        cliente.setId_sistema(((Parametros) JC_tipoSistema.getSelectedItem()).getId());
        cliente.setContacto(JT_nombreContacto.getText());

        if (JR_activo.isSelected()) {
            cliente.setEstado(Parametros.ACTIVO.getId());
        } else {
            cliente.setEstado(Parametros.INACTIVO.getId());
        }
    }

    private Long obtenerIdElementoSeleccionado() {
        if (JT_datos.getSelectedRow() != -1) {
            return (Long) JT_datos.getValueAt(JT_datos.getSelectedRow(), 0);
        } else {
            return 0L;
        }
    }

    private void agregarValidadoresACampos() {
        validaFormas.agregarCampoParaValidar(JT_nombre);
        validaFormas.agregarCampoParaValidar(JT_telefono);
        validaFormas.agregarCampoParaValidar(JT_nombreContacto);
        validaFormas.agregarComboParaValidar(JC_tipoCliente);
        validaFormas.agregarComboParaValidar(JC_tipoSistema);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JT_telefono = new javax.swing.JTextField();
        JT_nombre = new javax.swing.JTextField();
        JC_tipoSistema = new javax.swing.JComboBox<>();
        JC_tipoCliente = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        JT_nombreContacto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        JR_activo = new javax.swing.JRadioButton();
        JR_inactivo = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JT_datos = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        JB_nuevo = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        JB_guardar = new javax.swing.JButton();
        JB_editar = new javax.swing.JButton();
        JB_eliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        JB_cancelar = new javax.swing.JButton();
        JB_salir = new javax.swing.JButton();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("GESTION DE CLIENTES");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setText("Teléfono");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setText("Tipo de Sistema");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel4.setText("Tipo de Cliente");

        JT_telefono.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        JT_telefono.setEnabled(false);

        JT_nombre.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        JT_nombre.setEnabled(false);

        JC_tipoSistema.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        JC_tipoSistema.setEnabled(false);

        JC_tipoCliente.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        JC_tipoCliente.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel5.setText("Nombre de Contacto");

        JT_nombreContacto.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        JT_nombreContacto.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setText("Estado del Cliente");

        jPanel4.setBackground(new java.awt.Color(173, 167, 167));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        JR_activo.setBackground(new java.awt.Color(173, 167, 167));
        buttonGroup1.add(JR_activo);
        JR_activo.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        JR_activo.setText("Activo");
        JR_activo.setEnabled(false);

        JR_inactivo.setBackground(new java.awt.Color(173, 167, 167));
        buttonGroup1.add(JR_inactivo);
        JR_inactivo.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        JR_inactivo.setText("Inactivo");
        JR_inactivo.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(JR_activo)
                .addGap(96, 96, 96)
                .addComponent(JR_inactivo)
                .addGap(132, 132, 132))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JR_activo)
                    .addComponent(JR_inactivo))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JC_tipoSistema, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JT_telefono, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JT_nombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JC_tipoCliente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JT_nombreContacto)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addComponent(JT_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JC_tipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JC_tipoSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JT_nombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        JT_datos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.darkGray, null, null));
        JT_datos.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        JT_datos.setModel(new javax.swing.table.DefaultTableModel(
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
        JT_datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JT_datosMouseClicked(evt);
            }
        });
        JT_datos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JT_datosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(JT_datos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1)
                .addGap(10, 10, 10))
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jToolBar1.setDoubleBuffered(true);
        jToolBar1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jToolBar1.setOpaque(false);

        JB_nuevo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        JB_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/nuevo.png"))); // NOI18N
        JB_nuevo.setText("Nuevo");
        JB_nuevo.setFocusable(false);
        JB_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_nuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(JB_nuevo);
        jToolBar1.add(jSeparator2);

        JB_guardar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        JB_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/guardar.png"))); // NOI18N
        JB_guardar.setText("Guardar");
        JB_guardar.setEnabled(false);
        JB_guardar.setFocusable(false);
        JB_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_guardarActionPerformed(evt);
            }
        });
        jToolBar1.add(JB_guardar);

        JB_editar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        JB_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/editar.png"))); // NOI18N
        JB_editar.setText("Editar");
        JB_editar.setEnabled(false);
        JB_editar.setFocusable(false);
        JB_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_editarActionPerformed(evt);
            }
        });
        jToolBar1.add(JB_editar);

        JB_eliminar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        JB_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/borrar.png"))); // NOI18N
        JB_eliminar.setText("Eliminar");
        JB_eliminar.setEnabled(false);
        JB_eliminar.setFocusable(false);
        JB_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_eliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(JB_eliminar);
        jToolBar1.add(jSeparator1);

        JB_cancelar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        JB_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/cancelar.png"))); // NOI18N
        JB_cancelar.setText("Cancelar");
        JB_cancelar.setEnabled(false);
        JB_cancelar.setFocusable(false);
        JB_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_cancelarActionPerformed(evt);
            }
        });
        jToolBar1.add(JB_cancelar);

        JB_salir.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        JB_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/salir.png"))); // NOI18N
        JB_salir.setText("Salir");
        JB_salir.setFocusable(false);
        JB_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_salirActionPerformed(evt);
            }
        });
        jToolBar1.add(JB_salir);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_guardarActionPerformed

        if (validaFormas.compruebaCamposValidados()) {
            guardarDatos();
            try {
                manager.crear(cliente);
                cliente = null;
                cargarDatos();
                cargarModelos();
                bloquearDesbloquerBotones(true, false, false, false, false);
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }//GEN-LAST:event_JB_guardarActionPerformed

    private void JB_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_JB_salirActionPerformed

    private void JT_datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_datosMouseClicked
        if (JT_datos.getSelectedRow() != -1) {
            Long id = obtenerIdElementoSeleccionado();
            cliente = modeloTablaClientes.getElementById(id);
            cargarDatos();
            bloquearDesbloquerBotones(false, false, true, true, true);
            bloquearDesbloquerCampos(true, true, true, true, true, true);
        }
    }//GEN-LAST:event_JT_datosMouseClicked

    private void JT_datosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_datosKeyPressed

    }//GEN-LAST:event_JT_datosKeyPressed

    private void JB_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_editarActionPerformed
        if (validaFormas.compruebaCamposValidados()) {
            guardarDatos();
            try {
                manager.editar(cliente);
                cliente = null;
                cargarDatos();
                cargarModelos();
                bloquearDesbloquerBotones(true, false, false, false, false);
                bloquearDesbloquerCampos(false, false, false, false, false, false);
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }//GEN-LAST:event_JB_editarActionPerformed

    private void JB_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_eliminarActionPerformed

        if (JOptionPane.showConfirmDialog(rootPane, "Seguro que quiere eliminar este regitros", "Eliminación de Cliente",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                Long id = obtenerIdElementoSeleccionado();
                manager.borrar(id);
                cliente = null;
                cargarDatos();
                modeloTablaClientes.removeElementWithId(id);
                bloquearDesbloquerBotones(true, false, false, false, false);
                bloquearDesbloquerCampos(false, false, false, false, false, false);
                JT_datos.clearSelection();
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
    }//GEN-LAST:event_JB_eliminarActionPerformed

    private void JB_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_cancelarActionPerformed
        cliente = null;
        cargarDatos();
        JT_datos.clearSelection();
        bloquearDesbloquerBotones(true, false, false, false, false);
        bloquearDesbloquerCampos(false, false, false, false, false, false);
    }//GEN-LAST:event_JB_cancelarActionPerformed

    private void JB_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_nuevoActionPerformed
        bloquearDesbloquerBotones(true, true, false, false, true);
        bloquearDesbloquerCampos(true, true, true, true, true, true);
        JT_nombre.requestFocus();
    }//GEN-LAST:event_JB_nuevoActionPerformed

    private void bloquearDesbloquerBotones(boolean nuevo, boolean guardar, boolean editar, boolean eliminar, boolean cancelar) {
        JB_nuevo.setEnabled(nuevo);
        JB_guardar.setEnabled(guardar);
        JB_editar.setEnabled(editar);
        JB_eliminar.setEnabled(eliminar);
        JB_cancelar.setEnabled(cancelar);
    }

    private void bloquearDesbloquerCampos(boolean nombre, boolean telefono, boolean tipoCliente, boolean tipoSistema, boolean nombreContacto, boolean estadoCliente) {
        JT_nombre.setEnabled(nombre);
        JT_telefono.setEnabled(telefono);
        JC_tipoCliente.setEnabled(tipoCliente);
        JC_tipoSistema.setEnabled(tipoSistema);
        JT_nombreContacto.setEnabled(nombreContacto);
        JR_activo.setEnabled(estadoCliente);
        JR_inactivo.setEnabled(estadoCliente);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_cancelar;
    private javax.swing.JButton JB_editar;
    private javax.swing.JButton JB_eliminar;
    private javax.swing.JButton JB_guardar;
    private javax.swing.JButton JB_nuevo;
    private javax.swing.JButton JB_salir;
    private javax.swing.JComboBox<Parametros> JC_tipoCliente;
    private javax.swing.JComboBox<Parametros> JC_tipoSistema;
    private javax.swing.JRadioButton JR_activo;
    private javax.swing.JRadioButton JR_inactivo;
    private javax.swing.JTable JT_datos;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JTextField JT_nombreContacto;
    private javax.swing.JTextField JT_telefono;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

}
