package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import modelos.Envio;

public class EnvioVista extends JFrame {

    private JButton btnAgregar, btnRetirar, btnGuardar, btnCancelar;
    private JTextField txtCodigo, txtCliente, txtPeso, txtDistancia;
    private JComboBox<String> cmbTipo;
    private JTable tblEnvios;
    private DefaultTableModel modeloTabla;

    public EnvioVista() {

        setTitle("Operador Logístico");
        setSize(720, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tb = new JToolBar();



        btnAgregar = new JButton("Agregar envío");
        btnAgregar.setToolTipText("Agregar envío");
        tb.add(btnAgregar);

        btnRetirar = new JButton("Retirar envío");
        btnRetirar.setToolTipText("Retirar envío");
        tb.add(btnRetirar);

        // Contenedor con BoxLayout (vertical)
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));

        JPanel pnlDatos = new JPanel();
        pnlDatos.setPreferredSize(new Dimension(pnlDatos.getWidth(), 115));
        pnlDatos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 115));
        pnlDatos.setLayout(null);

        JLabel lblCodigo = new JLabel("Número");
        lblCodigo.setBounds(10, 10, 100, 25);
        pnlDatos.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 10, 100, 25);
        pnlDatos.add(txtCodigo);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(230, 10, 100, 25);
        pnlDatos.add(lblTipo);

        cmbTipo = new JComboBox<>();
        cmbTipo.setBounds(340, 10, 120, 25);
        pnlDatos.add(cmbTipo);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 45, 100, 25);
        pnlDatos.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 45, 100, 25);
        pnlDatos.add(txtCliente);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(230, 45, 110, 25);
        pnlDatos.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(340, 45, 120, 25);
        pnlDatos.add(txtDistancia);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 80, 100, 25);
        pnlDatos.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 80, 100, 25);
        pnlDatos.add(txtPeso);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(230, 80, 105, 25);
        pnlDatos.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(340, 80, 120, 25);
        pnlDatos.add(btnCancelar);

        String[] columnas = { "Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo" };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tblEnvios = new JTable(modeloTabla);
        JScrollPane spEnvios = new JScrollPane(tblEnvios);

        // Agregar componentes
        pnlEnvios.add(pnlDatos);
        pnlEnvios.add(spEnvios);

        getContentPane().add(tb, BorderLayout.NORTH);
        getContentPane().add(pnlEnvios, BorderLayout.CENTER);

        habilitarFormulario(false);
    }

    // getters

    public String getCodigo() {
        return txtCodigo.getText();
    }

    public String getCliente() {
        return txtCliente.getText();
    }

    public String getPeso() {
        return txtPeso.getText();
    }

    public String getDistancia() {
        return txtDistancia.getText();
    }

    public String getTipoSeleccionado() {
        return (String) cmbTipo.getSelectedItem();
    }

    // setters
    public void setAgregarClick(ActionListener evento) {
        btnAgregar.addActionListener(evento);
    }

    public void setRetirarClick(ActionListener evento) {
        btnRetirar.addActionListener(evento);
    }

    public void setGuardarClick(ActionListener evento) {
        btnGuardar.addActionListener(evento);
    }

    public void setCancelarClick(ActionListener evento) {
        btnCancelar.addActionListener(evento);
    }

    // metodos publicos
    public void cargarTipos(Collection<String> tipos) {
        cmbTipo.removeAllItems();
        for (var tipo : tipos) {
            cmbTipo.addItem(tipo);
        }
    }

    public void habilitarFormulario(boolean habilitado) {
        txtCodigo.setEnabled(habilitado);
        txtCliente.setEnabled(habilitado);
        txtPeso.setEnabled(habilitado);
        txtDistancia.setEnabled(habilitado);
        cmbTipo.setEnabled(habilitado);
        btnGuardar.setEnabled(habilitado);
        btnCancelar.setEnabled(habilitado);
    }

    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        if (cmbTipo.getItemCount() > 0) {
            cmbTipo.setSelectedIndex(0);
        }
    }

    public void mostrarEnvios(List<Envio> envios) {
        modeloTabla.setRowCount(0);
        for (var envio : envios) {
            modeloTabla.addRow(new Object[] {
                    envio.getTipo(),
                    envio.getCodigo(),
                    envio.getCliente(),
                    envio.getPeso(),
                    envio.getDistancia(),
                    envio.calcularTarifa() 
            });
        }
    }

    public String solicitarCodigo() {
        return JOptionPane.showInputDialog(this, "Código del envío a retirar:");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
