import model.downloader.AbstractDownloader;
import model.DownloadFabric;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class Controller {
private View view;
private Map<String,String> mapOfCurrentFiles;
private AbstractDownloader downloader;

    public Controller(View view) {
        this.view = view;
        view.setConfirmButtonListener(new SaveListener());
        view.setClearUrlButtonListener(new ClearURLListener());
        view.setFindButtonListener(new FindFilesListener());
        view.setSaveAllButtonListener(new SaveAllListener());
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser directoryChooser = new JFileChooser();
            directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            directoryChooser.setCurrentDirectory(new File(AbstractDownloader.directory));
            directoryChooser.setDialogTitle("Choose directory");

            // disable the "All files" option.
            directoryChooser.setAcceptAllFileFilterUsed(false);

            if (directoryChooser.showOpenDialog(directoryChooser) == JFileChooser.APPROVE_OPTION) {
                AbstractDownloader.directory = directoryChooser.getSelectedFile().getAbsolutePath()+"\\";

            }
            else {
                AbstractDownloader.directory =AbstractDownloader.DEFAULT_DIRECTORY;
            }
            //показываем обновленную надпись в label
            view.getPathTextField().setText(AbstractDownloader.directory);
        }
    }

    private class ClearURLListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.getUrlField().setText("");
        }
    }

    private class FindFilesListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String boxIndicator = view.getBox().getSelectedItem().toString();
            checkURL();
            downloader = DownloadFabric.getDownloader(boxIndicator);
            try {
                view.getConditionLabel().setText("Searching...");
                StringBuilder builder = new StringBuilder();
                mapOfCurrentFiles = downloader.parse(AbstractDownloader.url);
                mapOfCurrentFiles.values().stream().forEach(value-> builder.append(" ").append(value).append("\n"));
                view.getTextArea().setText(builder.toString());
                makeFont();
                view.getListSize().setText("Files quantity: "+ mapOfCurrentFiles.size());
                view.getConditionLabel().setText("Searching complete");

            } catch (IOException e1) {
                view.showError("Wrong Url");
                e1.printStackTrace();
            }
        }
        private void checkURL(){
            if (view.getUrlField().getText().length()!=0){
                AbstractDownloader.url = view.getUrlField().getText();
            }else{
                view.showError("Please check your url");
            }
        }
        private void makeFont(){
            if (mapOfCurrentFiles.size()>25){
                view.getTextArea().setFont(new Font(null,Font.BOLD,8));
            }else view.getTextArea().setFont(new Font(null,Font.BOLD,10));
        }
    }

    private class SaveAllListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("downloading files to " + AbstractDownloader.directory);
            makeDownload();
            view.getConditionLabel().setText("Download complete");

        }
        private void makeDownload() {
            try {
                downloader.download(AbstractDownloader.directory, mapOfCurrentFiles);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
