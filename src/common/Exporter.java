package common;
import common.JSON.JSONObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Exporter extends javax.swing.JFrame{
    BufferedImage image = null;
    String str = null;
    Export export = null;
    private String extension = null;
    private String extDesc = "All Files";
    public static Exporter export(Export e){
        Exporter exp = new Exporter().exp(e);
        exp.setVisible(true);
        return exp;
    }
    public static Exporter export(BufferedImage i){
        Exporter exp = new Exporter().exp(i);
        exp.setVisible(true);
        return exp;
    }
    public static Exporter export(JSONObject obj){
        Exporter exp = new Exporter().exp(obj);
        exp.setVisible(true);
        return exp;
    }
    public static Exporter export(Object obj){
        Exporter exp = new Exporter().exp(obj);
        exp.setVisible(true);
        return exp;
    }
    private Exporter exp(Export obj){
        if(obj==null)dispose();
        export = obj;
        return this;
    }
    private Exporter exp(BufferedImage obj){
        if(obj==null)dispose();
        image = (BufferedImage)obj;
        JPanel imagePanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(Color.white);
                float sizeFac = 16;
                sizeFac = Math.min(sizeFac, getWidth()/(float)image.getWidth());
                sizeFac = Math.min(sizeFac, getHeight()/(float)image.getHeight());
                sizeFac = Math.max(1,sizeFac);
                int width = (int) (image.getWidth()*sizeFac);
                int height = (int) (image.getHeight()*sizeFac);
                g.drawImage(image, 0,0,width,height,getBackground(),null);
            }
        };
        outputPanel.add(imagePanel);
        repaint();
        extension = "png";
        extDesc = "PNG Image File";
        return this;
    }
    private Exporter exp(JSONObject obj){
        extension = "json";
        extDesc = "JSON File";
        return exp((Object)obj);
    }
    private Exporter exp(Object obj){
        if(obj==null)dispose();
        str = Objects.toString(obj);
        JTextArea area = new JTextArea(str);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        outputPanel.add(area);
        repaint();
        return this;
    }
    public Exporter setExtension(String extension, String description){
        this.extension = extension;
        this.extDesc = description;
        return this;
    }
    public Exporter(){
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonSaveFile = new javax.swing.JButton();
        buttonClose = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Export Window");

        buttonSaveFile.setText("Save File");
        buttonSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveFileActionPerformed(evt);
            }
        });

        buttonClose.setText("Close Window");
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        outputPanel.setLayout(new java.awt.GridLayout(1, 0));
        jScrollPane1.setViewportView(outputPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 188, Short.MAX_VALUE)
                        .addComponent(buttonSaveFile)
                        .addGap(18, 18, 18)
                        .addComponent(buttonClose))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSaveFile)
                    .addComponent(buttonClose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed
    private void buttonSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveFileActionPerformed
        JFileChooser chooser = new JFileChooser();
        if(extension!=null){
            FileNameExtensionFilter filter = new FileNameExtensionFilter(extDesc+" (."+extension+")", extension);
            chooser.setFileFilter(filter);
        }
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if(chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            if(!f.getName().endsWith("."+extension)){
                f = new File(f.getAbsolutePath()+"."+extension);
            }
            try{
                if(export!=null){
                    export.export(f);
                }else if(image!=null){
                    ImageIO.write(image, extension, f);
                }else{
                    try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)))) {
                        writer.write(str);
                    }
                }
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, "Caught "+ex.getClass().getName()+": "+ex.getMessage()+"!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_buttonSaveFileActionPerformed
    public static void main(String args[]){
        try{
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if("Windows".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException|InstantiationException|IllegalAccessException|javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(Exporter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new Exporter().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonSaveFile;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel outputPanel;
    // End of variables declaration//GEN-END:variables
}