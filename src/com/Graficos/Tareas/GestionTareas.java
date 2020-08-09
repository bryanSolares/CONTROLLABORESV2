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
import com.Recursos.ValidadoresFormas;
import com.toedter.calendar.JDateChooser;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class GestionTareas extends javax.swing.JInternalFrame {

    private final ModeloComboParametros modeloCbPrioridad, modeloCbEstado, modeloCbResponsable;
    private final ModeloComboClientes modeloCbClientes;
    private final ModeloTablaDetalleTareas modeloTBDetallesTareas;
    private final DAOManager manager;
    private Tarea tarea;
    private TareaDetalle tareaDetalle;
    private final ValidadoresFormas validaForma;

    public GestionTareas(DAOManager manager) {
        initComponents();
        this.manager = manager;

        modeloCbPrioridad = new ModeloComboParametros();
        modeloCbEstado = new ModeloComboParametros();
        modeloCbResponsable = new ModeloComboParametros();
        modeloCbClientes = new ModeloComboClientes(manager.getDAOClientes());
        modeloTBDetallesTareas = new ModeloTablaDetalleTareas(manager.getDAOTareas());

        validaForma = new ValidadoresFormas(this);

        cargarModelos();
        agregarValidadoresACampos();
    }

    private void cargarModelos() {
        try {

            JT_nombre.setText("");
            JT_detalleDescripcion.setText("");
            JT_tiempo_transcurrido.setText("00:00:00:000");

            modeloCbClientes.actualizar();
            modeloCbPrioridad.actualizarCombo(ModeloComboParametros.TIPO_PRIORIDAD);
            modeloCbEstado.actualizarCombo(ModeloComboParametros.TIPO_ESTADO_TAREA);
            modeloCbResponsable.actualizarCombo(ModeloComboParametros.TIPO_REPONSABLE_SOPORTE);
            modeloTBDetallesTareas.actualizarModelo(0L);

            JC_cliente.setModel(modeloCbClientes);
            JC_tipoPrioridad.setModel(modeloCbPrioridad);
            JC_tipoEstado.setModel(modeloCbEstado);
            JC_tipoResponsable.setModel(modeloCbResponsable);
            JT_datos.setModel(modeloTBDetallesTareas);

            JD_fecha_inicio.setDate(new Date());
            JD_fecha_fin.setDate(new Date());
        } catch (DAOException ex) {
            GestionarRecursos.propagarError(ex);
        }
    }

    public void cargarDatos() {
        if (tarea != null) {
            JT_nombre.setText(tarea.getTitulo());
            JC_cliente.setSelectedItem(modeloCbClientes.obtenerElementoPorId(tarea.getCliente()));
            JC_tipoEstado.setSelectedItem(Parametros.devuelveValorParametro(tarea.getEstado(), Parametros.DEVUELVE_PARAMETRO));
            JC_tipoResponsable.setSelectedItem(Parametros.devuelveValorParametro(tarea.getAreaResponsable(), Parametros.DEVUELVE_PARAMETRO));
            JC_tipoPrioridad.setSelectedItem(Parametros.devuelveValorParametro(tarea.getPrioridad(), Parametros.DEVUELVE_PARAMETRO));

            JD_fecha_inicio.setDate(java.sql.Date.valueOf(tarea.getFechaInicio().toLocalDate()));
            JD_fecha_fin.setDate(java.sql.Date.valueOf(tarea.getFechaFin().toLocalDate()));
            JT_tiempo_transcurrido.setText(tarea.getDuracionTarea());
            try {
                modeloTBDetallesTareas.actualizarModelo(tarea.obtenerListaDetalles());
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex);
            }
            
            JC_cliente.setEnabled(false);
        } else {
            cargarModelos();
        }
    }

    private void guardarDatos() {
        if (tarea == null) {
            tarea = new Tarea();
        }

        tarea.setTitulo(JT_nombre.getText());
        tarea.setCliente(obtenerClienteSeleccionado());
        tarea.setPrioridad(obtenerParametroSeleccionad(JC_tipoPrioridad));
        tarea.setEstado(obtenerParametroSeleccionad(JC_tipoEstado));
        tarea.setAreaResponsable(obtenerParametroSeleccionad(JC_tipoResponsable));
        tarea.setFechaInicio(obtenerFecha(JD_fecha_inicio));
        tarea.setFechaFin(obtenerFecha(JD_fecha_fin));
        tarea.setDuracionTarea(JT_tiempo_transcurrido.getText());
        tarea.obtenerListaDetalles().clear();
        tarea.setListaDetalles(modeloTBDetallesTareas.getDataElements());
    }

    private Long obtenerClienteSeleccionado() {
        return ((Cliente) JC_cliente.getSelectedItem()).getId();
    }

    private int obtenerParametroSeleccionad(JComboBox combo) {
        return ((Parametros) combo.getSelectedItem()).getId();
    }

    private LocalDateTime obtenerFecha(JDateChooser elemento) {
        return elemento.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
        JT_datos = new javax.swing.JTable();
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
        jButton1 = new javax.swing.JButton();
        JT_tiempo_transcurrido = new javax.swing.JFormattedTextField();

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("GESTION DE TAREAS");
        setFont(new java.awt.Font("Ubuntu", 1, 13)); // NOI18N

        jToolBar4.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar4.setFloatable(false);
        jToolBar4.setRollover(true);
        jToolBar4.add(jSeparator2);

        JB_guardar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
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

        JB_salir.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
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

        JT_datos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.darkGray, null, null));
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

        JT_detalleDescripcion.setColumns(20);
        JT_detalleDescripcion.setLineWrap(true);
        JT_detalleDescripcion.setRows(5);
        JT_detalleDescripcion.setWrapStyleWord(true);
        jScrollPane2.setViewportView(JT_detalleDescripcion);

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel9.setText("Detalles");

        JB_agregarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/plus.png"))); // NOI18N
        JB_agregarDetalle.setFocusable(false);
        JB_agregarDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JB_agregarDetalle.setOpaque(true);
        JB_agregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_agregarDetalleActionPerformed(evt);
            }
        });

        JB_eliminarDetalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Iconos/minus.png"))); // NOI18N
        JB_eliminarDetalle.setFocusable(false);
        JB_eliminarDetalle.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JB_eliminarDetalle.setOpaque(true);
        JB_eliminarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_eliminarDetalleActionPerformed(evt);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setText("Titulo Tarea");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setText("Cliente");

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setText("Estado");

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel4.setText("Prioridad");

        JT_nombre.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        JC_tipoEstado.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        JC_tipoPrioridad.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel5.setText("Fecha Inicio");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setText("Fecha Finalización");

        JC_cliente.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        JD_fecha_inicio.setDateFormatString("dd/MM/yyyy");
        JD_fecha_inicio.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        JD_fecha_fin.setDateFormatString("dd/MM/yyyy");
        JD_fecha_fin.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel7.setText("Responsable Tarea");

        JC_tipoResponsable.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel8.setText("Tiempo Transcurrido:");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JT_tiempo_transcurrido.setEditable(false);
        JT_tiempo_transcurrido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm:ss"))));
        JT_tiempo_transcurrido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JT_tiempo_transcurrido.setText("00:00:00:000");
        JT_tiempo_transcurrido.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        JT_tiempo_transcurrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JT_tiempo_transcurridoActionPerformed(evt);
            }
        });

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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(JC_tipoEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JC_tipoResponsable, 0, 450, Short.MAX_VALUE))
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JT_tiempo_transcurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton1)))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JT_tiempo_transcurrido, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jButton1)
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

    private void JT_datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JT_datosMouseClicked

    }//GEN-LAST:event_JT_datosMouseClicked

    private void JT_datosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JT_datosKeyPressed

    }//GEN-LAST:event_JT_datosKeyPressed

    private void JB_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_guardarActionPerformed
        if (validaForma.compruebaCamposValidados()) {
            try {
                guardarDatos();
                
                if (tarea.getId() == null) {
                    if (!comprobarEstadoFinalizado() && !comprobarTiempoDistintoAInicial()) {
                        manager.getDAOTareas().crear(tarea);
                    } else {
                        GestionarRecursos.generarMensaje("No puede crear una tarea si el estado es finalizado o sin tiempo definido", "Creación no admitida", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                } else {
                    if (comprobarEstadoFinalizado()) {
                        if (!comprobarTiempoDistintoAInicial()) {
                            GestionarRecursos.generarMensaje("No puede finalizar la tarea sin indicar tiempo invertido", "Finalización de Tarea", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    manager.getDAOTareas().editar(tarea);
                }
                cargarModelos();
            } catch (DAOException ex) {
                GestionarRecursos.propagarError(ex);
            }
        }
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println(JC_cliente.getSelectedItem());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JT_tiempo_transcurridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JT_tiempo_transcurridoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JT_tiempo_transcurridoActionPerformed

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
        if (JT_datos.getRowCount() > 0) {
            Long idSeleccionado;
            if (JT_datos.getSelectedRow() >= 0) {
                idSeleccionado = (Long) modeloTBDetallesTareas.getValueAt(JT_datos.getSelectedRow(), 1);
            } else {
                idSeleccionado = (Long) modeloTBDetallesTareas.getValueAt(modeloTBDetallesTareas.getRowCount() - 1, 1);
            }
            tareaDetalle = modeloTBDetallesTareas.getElementById(idSeleccionado);
            modeloTBDetallesTareas.removeElementWithId(idSeleccionado);
            JT_detalleDescripcion.setText(tareaDetalle.getDescripcion());
        } else {
            JOptionPane.showMessageDialog(this, "No elementos para eliminar", "Eliminar detalle", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void agregarValidadoresACampos() {
        validaForma.agregarCampoParaValidar(JT_nombre);
        validaForma.agregarComboParaValidar(JC_cliente);
        validaForma.agregarComboParaValidar(JC_tipoPrioridad);
        validaForma.agregarComboParaValidar(JC_tipoEstado);
        validaForma.agregarComboParaValidar(JC_tipoResponsable);
        validaForma.agregarFechaParaValidar(JD_fecha_inicio);
        validaForma.agregarFechaParaValidar(JD_fecha_fin);
    }

    private boolean comprobarEstadoFinalizado() {
        return ((Parametros) modeloCbEstado.getSelectedItem()).equals(Parametros.FINAL);
    }

    private boolean comprobarTiempoDistintoAInicial() {
        return JT_tiempo_transcurrido.equals("00:00:00:000");
    }
    
    private boolean comprobarEstadoInicial(){
        return ((Parametros) modeloCbEstado.getSelectedItem()).equals(Parametros.INICIAL);
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
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
    private javax.swing.JTable JT_datos;
    private javax.swing.JTextArea JT_detalleDescripcion;
    private javax.swing.JTextField JT_nombre;
    private javax.swing.JFormattedTextField JT_tiempo_transcurrido;
    private javax.swing.JButton jButton1;
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
