package com.unab.test;

import com.unab.domain.*;
import com.unab.dao.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import java.util.*;

public class Main extends JFrame{
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    public Main(){
        setTitle("Registro Contactos");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initComponents();
    }

    private void initComponents() {
        initPanels();
        initLabels();
        initTextFields();
        initButtons();
        initTables();
        cargarContactos();
    }

    private void initPanels(){
        panel = new JPanel();
        panel.setLayout(null);

        this.add(panel);
    }

    private void initLabels(){
        lblNombre = new JLabel();
        lblNombre.setText("Nombre:");
        lblNombre.setBounds(50, 50, 100, 30);
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 15));

        lblEdad = new JLabel();
        lblEdad.setText("Edad:");
        lblEdad.setBounds(50, 130, 100, 30);
        lblEdad.setFont(new Font("Arial", Font.PLAIN, 15));

        lblEmail = new JLabel();
        lblEmail.setText("Email:");
        lblEmail.setBounds(375, 50, 100, 30);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));

        lblTelefono = new JLabel();
        lblTelefono.setText("Telefono:");
        lblTelefono.setBounds(375, 130, 100, 30);
        lblTelefono.setFont(new Font("Arial", Font.PLAIN, 15));

        panel.add(lblNombre);
        panel.add(lblEdad);
        panel.add(lblEmail);
        panel.add(lblTelefono);
    }

    private void initTextFields(){
        txtNombre = new JTextField();
        txtNombre.setBounds(50, 80, 275, 30);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 15));

        txtEdad = new JTextField();
        txtEdad.setBounds(50, 160, 275, 30);
        txtEdad.setFont(new Font("Arial", Font.PLAIN, 15));

        txtEmail = new JTextField();
        txtEmail.setBounds(375, 80, 275, 30);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));

        txtTelefono = new JTextField();
        txtTelefono.setBounds(375, 160, 275, 30);
        txtTelefono.setFont(new Font("Arial", Font.PLAIN, 15));

        panel.add(txtNombre);
        panel.add(txtEdad);
        panel.add(txtEmail);
        panel.add(txtTelefono);
    }

    private void initButtons(){
        btnInsertar = new JButton();
        btnInsertar.setText("Insertar");
        btnInsertar.setBounds(50, 210, 100, 30);
        btnInsertar.setFont(new Font("Arial", Font.PLAIN, 15));

        btnSeleccionar = new JButton();
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setBounds(50, 575, 120, 30);
        btnSeleccionar.setFont(new Font("Arial", Font.PLAIN, 15));

        btnActualizar = new JButton();
        btnActualizar.setText("Actualizar");
        btnActualizar.setBounds(450, 575, 100, 30);
        btnActualizar.setFont(new Font("Arial", Font.PLAIN, 15));

        btnEliminar = new JButton();
        btnEliminar.setText("Eliminar");
        btnEliminar.setBounds(550, 575, 100, 30);
        btnEliminar.setFont(new Font("Arial", Font.PLAIN, 15));
        
        panel.add(btnInsertar);
        panel.add(btnSeleccionar);
        panel.add(btnActualizar);
        panel.add(btnEliminar);

        // Llamando a los eventos
        btnInsertarActionPerformed();
        btnSeleccionarActionPerformed();
        btnActualizarActionPerformed();
        btnEliminarActionPerformed();
    }

    private void initTables(){
        String titulos[] = {"Id", "Nombre" ,"Edad", "Email", "Telefono"};
        modeloTabla = new DefaultTableModel(null, titulos);
        
        tblContactos = new JTable(modeloTabla);
        tblContactos.setBounds(50, 260, 600, 300);
        tblContactos.setFont(new Font("Arial", Font.PLAIN, 15));

        tblContactos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblContactos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblContactos.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblContactos.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblContactos.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        panel.add(tblContactos);
        
        JScrollPane scroll = new JScrollPane(tblContactos, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(50, 260, 600, 300);
        
        panel.add(scroll);
    }

    private void btnSeleccionarActionPerformed(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSeleccionado = true;

                try{
                    int row = tblContactos.getSelectedRow();
                    id = Integer.parseInt(tblContactos.getValueAt(row, 0).toString());
                    txtNombre.setText(tblContactos.getValueAt(row, 1).toString());
                    txtEdad.setText(tblContactos.getValueAt(row, 2).toString());
                    txtEmail.setText(tblContactos.getValueAt(row, 3).toString());
                    txtTelefono.setText(tblContactos.getValueAt(row, 4).toString());

                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        btnSeleccionar.addActionListener(actionListener);
    }

    private void btnInsertarActionPerformed(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ContactosDao contactos = new ContactosDao();
                    
                    String nombre = txtNombre.getText();
                    int edad = Integer.parseInt(txtEdad.getText());
                    String email = txtEmail.getText();
                    String telefono = txtTelefono.getText();
                    
                    Contactos contacto = new Contactos(nombre, edad, email, telefono);
                    contactos.guardarContacto(contacto);

                    isSeleccionado = false;
                    
                    cargarContactos();

                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        
        btnInsertar.addActionListener(actionListener);
    }

    private void btnActualizarActionPerformed(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(isSeleccionado == true){
                        ContactosDao contactos = new ContactosDao();

                        int idContacto = id;
                        String nombre = txtNombre.getText();
                        int edad = Integer.parseInt(txtEdad.getText());
                        String email = txtEmail.getText();
                        String telefono = txtTelefono.getText();
                        
                        int row = tblContactos.getSelectedRow();

                        tblContactos.setValueAt(nombre, row, 1);
                        tblContactos.setValueAt(edad, row, 2);
                        tblContactos.setValueAt(email, row, 3);
                        tblContactos.setValueAt(telefono, row, 4);

                        Contactos contacto = new Contactos(idContacto, nombre, edad, email, telefono);
                        contactos.actualizarContacto(contacto);

                        isSeleccionado = false;

                        cargarContactos();
                    }

                    else{
                        JOptionPane.showMessageDialog(null, "Seleccione una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error" + ex.getMessage());
                }
            }
        };
        
        btnActualizar.addActionListener(actionListener);
    }

    private void btnEliminarActionPerformed(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(isSeleccionado == true){
                        ContactosDao contactos = new ContactosDao();

                        int idEstudiante = id;
                        Contactos contacto = new Contactos(idEstudiante);
                        contactos.eliminarContacto(contacto);

                        isSeleccionado = false;
                        cargarContactos();
                    }

                    else{
                        JOptionPane.showMessageDialog(null, "Seleccione una fila", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
                }
            }
        };
        
        btnEliminar.addActionListener(actionListener);
    }

    private void cargarContactos(){
        limpiarComponentes();

        ContactosDao contactos = new ContactosDao();
        ArrayList<Contactos> listarContactos = contactos.mostrarContactos();

        Iterator iterador = listarContactos.iterator();
        Object fila[] = new Object[5];
        
        while(iterador.hasNext()){
            Contactos filasContacto = (Contactos) iterador.next();
            fila[0] = filasContacto.getId();
            fila[1] = filasContacto.getNombre();
            fila[2] = filasContacto.getEdad();
            fila[3] = filasContacto.getEmail();
            fila[4] = filasContacto.getNumeroDeTelefono();
            
            modeloTabla.addRow(fila);
        }
    }

    private void limpiarComponentes(){
        for(int i = 0; i < tblContactos.getRowCount(); i++){
            modeloTabla.removeRow(i);
            i -= 1;
        }

        txtNombre.setText("");
        txtEdad.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
    }

    //Variables globales
    DefaultTableModel  modeloTabla;
    public boolean isSeleccionado = false;
    public int id = 0;

    //Componentes
    private JPanel panel;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblEmail;
    private JLabel lblTelefono;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JButton btnInsertar;
    private JButton btnSeleccionar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JTable tblContactos;
}
