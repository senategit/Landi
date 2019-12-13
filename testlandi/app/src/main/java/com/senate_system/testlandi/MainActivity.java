package com.senate_system.testlandi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.usdk.apiservice.aidl.device.DeviceInfo;
import com.usdk.apiservice.aidl.device.UDeviceManager;
import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.ECLevel;
import com.usdk.apiservice.aidl.printer.OnPrintListener;
import com.usdk.apiservice.aidl.printer.PrintFormat;
import com.usdk.apiservice.aidl.printer.UPrinter;

public class MainActivity extends AppCompatActivity {

    private UDeviceManager deviceManager;
    private UPrinter printer;
    private Button button;
    private ImageView imageView;
    private DrawImage drawImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        button = findViewById(R.id.button);

        drawImage = new DrawImage(this)
                .setPaperWidth(400)
                .setPaperHeight(400)
                .createPaperBitmap()
                .createNewPaint(null,null)
                .setOnDrawImageSuccess(new DrawImage.OnDrawSuccessListener() {
                    @Override
                    public void onImage(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawImage.clearTextBodyList();
                drawImage.addTextBody(new TextBody("ชื่อ-นามสกุล"));
                drawImage.addTextBody(new TextBody("เบอร์โทร"));
                drawImage.addTextBody(new TextBody("ที่อยู่"));
                drawImage.addTextBody(new TextBody("โรคประจำตัว"));
                drawImage
            }
        });

//        register(false);
//        DeviceHelper.me().setServiceListener(new DeviceHelper.ServiceReadyListener() {
//            @Override
//            public void onReady(String version) {
//                System.out.println(version);
//                text.setText(version);
//
////
//
////                deviceManager = DeviceHelper.me().getDeviceManager();
////
////                try {
////                    System.out.println("Serial no: " + getDeviceInfo().getSerialNo());
////                    System.out.println("Hardware serial no: " + getDeviceInfo().getHardWareSn());
////                    System.out.println("Terminal model: " + getDeviceInfo().getModel());
////                    System.out.println("Manufacturer: " + getDeviceInfo().getManufacture());
////                } catch (RemoteException e) {
////                    e.printStackTrace();
////                }
//            }
//        });
//        DeviceHelper.me().register(true);

//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                text.setText("");
//                DeviceHelper.me().register(true);
//
//                int format = PrintFormat.FORMAT_MOREDATAPROC;
//                int value = PrintFormat.VALUE_MOREDATAPROC_PRNTOEND;
//
//                printer = DeviceHelper.me().getPrinter();
//
//                try {
//                    printer.addText(AlignMode.CENTER, "Sheet number " + "Senate");
//                    printer.addText(AlignMode.CENTER, "Sheet number " + "ภาษาไทย");
//                    printer.addText(AlignMode.CENTER, "Sheet number " + "asdasdas");
//                    printer.addText(AlignMode.CENTER, "Sheet number " + "!@!*#^@$*!@$");
//                    printer.addText(AlignMode.CENTER, "Sheet number " + "121312415325");
//                    printer.feedLine(2);
//                    printer.addQrCode(AlignMode.CENTER, 240, ECLevel.ECLEVEL_H, "www.landicorp.com");
//                    printer.addBarCode(AlignMode.CENTER, 2, 48, "1234567");
//                    printer.feedLine(2);
//                    printer.autoCutPaper();
//                    printer.startPrint(new OnPrintListener() {
//                        @Override
//                        public void onFinish() throws RemoteException {
//
//                        }
//
//                        @Override
//                        public void onError(int i) throws RemoteException {
//
//                        }
//
//                        @Override
//                        public IBinder asBinder() {
//                            return null;
//                        }
//                    });
//                } catch (RemoteException e) {
//                    text.setText(e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//        });

//        try {
//            System.out.println("Serial no: " + getDeviceInfo().getSerialNo());
//            System.out.println("Hardware serial no: " + getDeviceInfo().getHardWareSn());
//            System.out.println("Terminal model: " + getDeviceInfo().getModel());
//            System.out.println("Manufacturer: " + getDeviceInfo().getManufacture());
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

    }

//    private void register(boolean useEpayModule) {
//        try {
//            DeviceHelper.me().register(useEpayModule);
////            registerEnabled(false);
//        } catch (IllegalStateException e) {
////            toast("register fail: " + e.getMessage());
//        }
//    }

    public UDeviceManager getDeviceManager() throws RemoteException {
        return deviceManager;
    }


    public DeviceInfo getDeviceInfo() throws RemoteException {
        return deviceManager.getDeviceInfo();
    }

}
