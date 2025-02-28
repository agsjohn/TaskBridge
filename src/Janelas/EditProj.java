package Janelas;

import Classes.Projetos;
import Classes.Usuarios;
import Classes.VinculoProjetos;
import Classes.VinculoProjetosUsuario;
import Classes.limiteTexto;
import DAO.ProjetosDAO;
import DAO.UsuariosDAO;
import DAO.VinculoProjetosDAO;
import DAO.VinculoProjetosUsuarioDAO;
import java.awt.CardLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author agsjohn
 */
public class EditProj extends javax.swing.JPanel {

    /**
     * Creates new form EditForm
     */
    
    String layoutAnterior;
    int idProjeto;
    Geral geral;
    ArrayList<VinculoProjetosUsuario> vinculoUsuariosProjeto = new ArrayList<VinculoProjetosUsuario>();
    ArrayList<Usuarios> usuariosProjeto = new ArrayList<Usuarios>();
    ArrayList<Usuarios> todosUsuarios = new ArrayList<Usuarios>();
    ArrayList<Usuarios> usuariosDisponiveis = new ArrayList<Usuarios>();
    
    public EditProj() {
        initComponents();
    }
    
    public EditProj(Geral geral, int idProjeto, String layoutAnterior) {
        initComponents();
        
        this.geral = geral;
        this.layoutAnterior = layoutAnterior;
        this.idProjeto = idProjeto;
        
        jTextField1.setDocument(new limiteTexto(50));
        jTextArea1.setDocument(new limiteTexto(500));
        
        load();
    }

    public void load(){
        ProjetosDAO projDAO = new ProjetosDAO();
        Projetos projeto = new Projetos();
        projeto = projDAO.procurar(idProjeto);
        
        jTextField1.setText(projeto.getNomeProjeto());
        jTextArea1.setText(projeto.getDescricaoProjeto());
        
        //Caldendario inicio
        Date dataCriacao = projeto.getDataCriacaoProjeto();
        java.util.Date date = null;
        try {
            String texto = dataCriacao.toString();
            date = new SimpleDateFormat("yyyy-MM-dd").parse(texto);
        } catch (ParseException ex) {
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateChooserPanel2.setSelectedDate(cal);
        
        //Calendario conclusao
        Date dataConclusao = projeto.getDataConclusaoProjeto();
        if(dataConclusao == null){
            dateChooserPanel1.setSelectedDate(null);
        } else{
            date = null;
            try {
                String texto = dataConclusao.toString();
                date = new SimpleDateFormat("yyyy-MM-dd").parse(texto);
            } catch (ParseException ex) {
            }
            cal = Calendar.getInstance();
            cal.setTime(date);
            dateChooserPanel1.setSelectedDate(cal);
        }
        
        //Usuarios no projeto
        ArrayList<VinculoProjetosUsuario> vup = new ArrayList<VinculoProjetosUsuario>();
        VinculoProjetosUsuarioDAO vupDAO = new VinculoProjetosUsuarioDAO();
        vup = vupDAO.listar();
        for(int x=0; x<vup.size(); x++){
            if(vup.get(x).getIdProjeto() == idProjeto){
                vinculoUsuariosProjeto.add(vup.get(x));
            }
        }
        
        //Todos usuários parte adicionar
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        todosUsuarios = usuariosDAO.listar();
        for(int x=0; x<todosUsuarios.size(); x++){
            int nenhum = 0;
            for(int y=0; y<vinculoUsuariosProjeto.size();y++){
                if(vinculoUsuariosProjeto.get(y).getIdUsuario() == todosUsuarios.get(x).getIdUsuario()){
                    nenhum++;
                }
            }
            if(nenhum == 0){
                usuariosDisponiveis.add(todosUsuarios.get(x));
            }
        }
        String [] nomesUsuariosDisponiveis = new String[usuariosDisponiveis.size()];
        for(int x=0; x<usuariosDisponiveis.size(); x++){
            nomesUsuariosDisponiveis[x] = usuariosDisponiveis.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosDisponiveis));
        
        //Parte de mostrar os usuarios no projeto
        for (int x = 0; x < vinculoUsuariosProjeto.size(); x++) {
            for(int y=0; y<todosUsuarios.size();y++){
                if(vinculoUsuariosProjeto.get(x).getIdUsuario() == todosUsuarios.get(y).getIdUsuario()){
                    usuariosProjeto.add(todosUsuarios.get(y));
                }
            }
        }
        String [] string = new String[usuariosProjeto.size()];
        for(int x=0; x < usuariosProjeto.size(); x++){
            string[x] = usuariosProjeto.get(x).getNomeUsuario();
        }
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return string.length; }
            public String getElementAt(int i) { return string[i]; }
        });
        
        //Limpar
        if(jComboBox1.getModel().getSize() == 0){
            jButton3.setEnabled(false);
        }
    }
    
    public void reload(){
        String [] nomesUsuariosDisponiveis = new String[usuariosDisponiveis.size()];
        for(int x=0; x<usuariosDisponiveis.size(); x++){
            nomesUsuariosDisponiveis[x] = usuariosDisponiveis.get(x).getNomeUsuario();
        }
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(nomesUsuariosDisponiveis));
        
        String [] string = new String[usuariosProjeto.size()];
        for(int x=0; x < usuariosProjeto.size(); x++){
            string[x] = usuariosProjeto.get(x).getNomeUsuario();
        }
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return string.length; }
            public String getElementAt(int i) { return string[i]; }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        dateChooserPanel2 = new datechooser.beans.DateChooserPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        dateChooserPanel1 = new datechooser.beans.DateChooserPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(640, 504));
        setPreferredSize(new java.awt.Dimension(640, 360));

        jScrollPane3.setBorder(null);

        jLabel1.setText("Nome projeto");

        jLabel2.setText("Descrição projeto");

        jLabel3.setText("Data de criação do projeto");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        dateChooserPanel2.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooserPanel2.setNothingAllowed(false);
    dateChooserPanel2.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    jLabel4.setText("Usuários ligados ao projeto");

    jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jList1MouseClicked(evt);
        }
    });
    jScrollPane2.setViewportView(jList1);

    jLabel5.setText("Data de conclusão do projeto");

    dateChooserPanel1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    jButton1.setText("Cancelar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton2.setText("Salvar");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jButton3.setText("Adicionar");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jLabel6.setText("Vincular usuário");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(jButton1))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addComponent(jLabel4))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3)
                .addComponent(dateChooserPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5)
                .addComponent(dateChooserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(47, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(7, 7, 7)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(dateChooserPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel5)
                    .addGap(2, 2, 2)
                    .addComponent(dateChooserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addGap(3, 3, 3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)))
                    .addGap(96, 96, 96)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))))
            .addContainerGap(37, Short.MAX_VALUE))
    );

    jScrollPane3.setViewportView(jPanel1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        geral.desativarBotoes();
        CardLayout layout = (CardLayout) getParent().getLayout();
        layout.show(getParent(), layoutAnterior);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        geral.limpar();
        
        if(jTextField1.getText().equals("") || jTextArea1.getText().equals("") || jList1.getModel().getSize()==0){
            String mensagem = "Preencha os campos de nome, descrição e usuários ligado ao projeto";
            JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ProjetosDAO projDAO = new ProjetosDAO();
        
        String nomeProj = jTextField1.getText();
        String descProj = jTextArea1.getText();
        
        Calendar cal = Calendar.getInstance();
                
        cal = dateChooserPanel2.getSelectedDate();
        Date dataCriacao = cal.getTime();
        
        Date dataConclusao = null;
        Boolean finalizado = false;
        cal = dateChooserPanel1.getSelectedDate();
        if(cal!=null){
            dataConclusao = cal.getTime();
            finalizado = true;
        } else{
            finalizado = false;
        }
        
        Projetos projeto = new Projetos(idProjeto, nomeProj, descProj, dataCriacao, dataConclusao, finalizado);
        projDAO.atualizar(projeto);
        
        VinculoProjetosDAO vpDAO = new VinculoProjetosDAO();
        ArrayList<VinculoProjetos> todosVinculos = new ArrayList<VinculoProjetos>();
        ArrayList<VinculoProjetos> filtroVinculos = new ArrayList<VinculoProjetos>();
        todosVinculos = vpDAO.listar();
        vpDAO = new VinculoProjetosDAO();
        for(int x=0; x<todosVinculos.size();x++){
            if(todosVinculos.get(x).getIdProjeto() == idProjeto){
                filtroVinculos.add(todosVinculos.get(x));
            }
        }
        for(int x=0; x<filtroVinculos.size();x++){
            boolean achou = false;
            for(int y=0; y<usuariosProjeto.size();y++){
                if(filtroVinculos.get(x).getIdUsuario() == usuariosProjeto.get(y).getIdUsuario()){
                    achou = true;
                }
            }
            if(achou == false){
                vpDAO.excluir(filtroVinculos.get(x));
                vpDAO = new VinculoProjetosDAO();
            }
        }
        for(int x=0; x<usuariosProjeto.size();x++){
            boolean achou = false;
            for(int y=0; y<filtroVinculos.size();y++){
                if(usuariosProjeto.get(x).getIdUsuario() == filtroVinculos.get(y).getIdUsuario()){
                    achou = true;
                }
            }
            if(achou == false){
                vpDAO.inserir(new VinculoProjetos(idProjeto, usuariosProjeto.get(x).getIdUsuario()));
                vpDAO = new VinculoProjetosDAO();
            }
        }
        
        //Final
        CardLayout layout = (CardLayout) getParent().getLayout();
        geral.desativarBotoes();
        geral.reload();
        layout.show(getParent(), layoutAnterior);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int cliques = evt.getClickCount();
        if(jList1.getSelectedIndex() != -1){
            if(cliques > 1){
                Object[] options = { "Sim", "Não"};
                int opcao = JOptionPane.showOptionDialog(null, "Deseja desvincular usuário do projeto?", "Remover vínculo", 1, 2, null, options, options[0]);
                if(opcao == 0){
                    usuariosDisponiveis.add(usuariosProjeto.get(jList1.getSelectedIndex()));
                    usuariosProjeto.remove(jList1.getSelectedIndex());
                    this.reload();
                    if(jComboBox1.getItemCount() != 0){
                        jButton3.setEnabled(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        usuariosProjeto.add(usuariosDisponiveis.get(jComboBox1.getSelectedIndex()));
        usuariosDisponiveis.remove(jComboBox1.getSelectedIndex());
        this.reload();
        if(jComboBox1.getItemCount() == 0){
            jButton3.setEnabled(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserPanel dateChooserPanel1;
    private datechooser.beans.DateChooserPanel dateChooserPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
