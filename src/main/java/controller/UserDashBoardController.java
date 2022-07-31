package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import db.InMemoryDb;
import db.User;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import util.Navigations;
import util.Route;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserDashBoardController {
    public AnchorPane pneUserProfile;
    public Label lblName;
    public Label lblNIC;
    public Label lblAddress;
    public Label lblQuota;
    public ImageView imgQR;
    public Button btnDownload;
    public Button btnPrint;
    public Button btnLogout;

    public void initialize(){
        Platform.runLater(pneUserProfile::requestFocus);
    }

    public void setData(User user) throws WriterException {
        lblName.setText(user.getFirstName()+" "+user.getLastName());
        lblNIC.setText(user.getNic());
        lblAddress.setText(user.getAddress());
        lblQuota.setText(user.getQuota()+"L");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String plainScrete = user.getNic()+"-"+user.getFirstName();
        BitMatrix encode=qrCodeWriter.encode(plainScrete, BarcodeFormat.QR_CODE, 185,187);
        WritableImage image = new WritableImage(185, 187);
        PixelWriter pixelWriter=image.getPixelWriter();

        for (int y = 0; y < encode.getHeight(); y++) {
            for (int x = 0; x < encode.getWidth(); x++) {
                if (encode.get(x,y)){
                    pixelWriter.setColor(x,y,Color.BLACK);
                } else{
                    pixelWriter.setColor(x,y,Color.WHITE);

                }
            }

        }

        imgQR.setImage(image);

    }
    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigations.navigate(Route.WELCOME);
    }

    public void btnDownloadOnAction(ActionEvent actionEvent) throws IOException {
        // For more details about this please follow the dep9/phase1/basics/env - more details about the environment variables.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("QR download");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName("qr-code.png");
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("JPEG file (.png)", "*.png"));
        File saveLocation = fileChooser.showSaveDialog(btnLogout.getScene().getWindow());
        BufferedImage bufferedQRImage = SwingFXUtils.fromFXImage(imgQR.getImage(), null);
        boolean isSaved = ImageIO.write(bufferedQRImage, "png", saveLocation);
        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "QR successfully downloaded!").show();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Download failed, Try again.");
        }
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        if (Printer.getDefaultPrinter() == null) {
            new Alert(Alert.AlertType.ERROR, "Couldn't find a printer in your computer, Please connect a printer" +
                    "and try again").showAndWait();
            return;
        }

        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null){
            printerJob.showPageSetupDialog(btnLogout.getScene().getWindow());
            boolean isSuccess = printerJob.printPage(imgQR);
            if (isSuccess){
                printerJob.endJob();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to print, Try again.").showAndWait();
            }

        } else{
            new Alert(Alert.AlertType.ERROR, "Fail to initialize a new printer job").showAndWait();
        }
    }

}
