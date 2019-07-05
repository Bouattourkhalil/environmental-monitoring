package com.example.lenovo;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lenovo.mainwork.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activ extends AppCompatActivity {

    final int MAX_ELEMENT_IN_ARRAY = 1000;

    int show = 0;
    ImageButton BL; //= this.findViewById(R.id.button_ligh);
    ImageButton BH;// = this.findViewById(R.id.button_humd);
    ImageButton BT;// = this.findViewById(R.id.button_temp);

    ArrayList<DataPoint> array_temp = new ArrayList<DataPoint>(MAX_ELEMENT_IN_ARRAY);
    ArrayList<DataPoint> array_humd = new ArrayList<DataPoint>(MAX_ELEMENT_IN_ARRAY);
    ArrayList<DataPoint> array_ligh = new ArrayList<DataPoint>(MAX_ELEMENT_IN_ARRAY);

    BluetoothAdapter mBluetoothAdapter = null;
    BluetoothHelper mBluetooth = new BluetoothHelper();
    private SeekBar mSeekBar;
    private TextView temp;
    private TextView humd;
    private TextView ligh;
    boolean led = false;                                // The status of the ON/OFF led
    private String DEVICE_NAME = "HC-05";            // The name of the remote device (HC-05)
    String detector = "";
    int ID = 0;
    int information = 0;
    int n = 1;
    String regex = "(\\w{4}):(\\d{3}):(\\d{1,4}(?>\\.\\d{0,3})?);";

    public Activ() {

    }

    float minTime = -1;

    @SuppressLint("SimpleDateFormat")
    long  tim=0;
    float getTime() {
        //  if (minTime == -1) {
        // minTime = Float.parseFloat(new SimpleDateFormat("HH.mm").format(Calendar.getInstance())) + Float.parseFloat(new SimpleDateFormat("ss").format(Calendar.getInstance()))*0.001f/60f;
        //  }
        return tim++;
        //return Float.parseFloat(new SimpleDateFormat("HH.mmss").format(Calendar.getInstance().getTimeInMillis()));
        // return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        //return Float.parseFloat(new SimpleDateFormat("HH.mmss").format(Calendar.getInstance().getTime()));
        // return Float.parseFloat(new SimpleDateFormat("ss").format(Calendar.getInstance().getTime()));

    }

    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // force portrait screen
        setContentView(R.layout.activity_);
        //from here
        BL = this.findViewById(R.id.button_ligh);
        BH = this.findViewById(R.id.button_humd);
        BT = this.findViewById(R.id.button_temp);


        try {
            BL.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    show = 3;
                }
            });
            BT.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    show = 1;
                }
            });
            BH.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    show = 2;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        temp = (TextView) findViewById(R.id.temperature);
        humd = (TextView) findViewById(R.id.humidity);
        ligh = (TextView) findViewById(R.id.light);
        //  mSeekBar = (SeekBar) findViewById(R.id.ID_SEEKBAR);
        // Check if Bluetooth is supported by the device
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            finish();
        }

        // Start Bluetooth connection with the paired "RNBT-729D" device (BlueSMIRF Gold)
//        mTextViewStatus.setText("Connecting to " + DEVICE_NAME);
        mBluetooth.Connect(DEVICE_NAME);
        Log.e("hi", "probing connectio ");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

        // activate horizontal scrolling
        graph.getViewport().setScrollable(true);

        // activate horizontal and vertical zooming and scrolling
        graph.getViewport().setScalableY(true);


        //TODO: graph.getViewport().(true);

        // activate vertical scrolling
        graph.getViewport().setScrollableY(true);
        if (show == 0){
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                    new DataPoint(0, 1),
                    new DataPoint(1, 5),
                    new DataPoint(2, 3),
                    new DataPoint(3, 2),
                    new DataPoint(4, 6)

            });
            graph.addSeries(series);
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            GraphView graph = findViewById(R.id.graph);
                            graph.getSeries().clear();
                            if (show == 1) {
                                graph.addSeries(new LineGraphSeries((DataPoint[]) array_temp.toArray(new DataPoint[array_temp.size()])));
                            }
                            if (show == 2) {
                                graph.addSeries(new LineGraphSeries((DataPoint[]) array_humd.toArray(new DataPoint[array_humd.size()])));
                            }
                            if (show == 3) {
                                graph.addSeries(new LineGraphSeries((DataPoint[]) array_ligh.toArray(new DataPoint[array_ligh.size()])));
                            }

                            graph.getViewport().calcCompleteRange();
                            graph.getViewport().scrollToEnd();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }}, 1000, 1000);




        //ArrayList<String> al3 = new ArrayList<String>();


        //add method for integer ArrayList
        /*al2.add(0);
        al2.add(1);
        al2.add(3);
        al2.add(4);
        al2.add(5);
        al2.add(6);
        al2.add(7);
        al2.add(8);
        al2.add(9);
        //add method for integer ArrayList
        al3.add("temperature");
        al3.add("Humidity");
        al3.add("light");
        ArrayList<String> al= new ArrayList<>();*/
       /* if (n<10)
        {
            al.add(temp);
            n++;
        }
        else
        {

            //add method for String ArrayList
            al.add(temp.getText().toString());
            al.remove(0);
        }*/
        /*  try {
          GraphView graph = (GraphView) findViewById(R.id.graph);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 0)
            });
            graph.addSeries(series);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        // Setup listener for SeekBar:
    /*   mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mBluetooth.isConnected()) {
                    // Write the new value to Bluetooth (The String is something like "$PWM,128")
                    mBluetooth.SendMessage("$PWM," + seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not used in this demo app
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mBluetooth.isConnected()) {
                    // Write the new value to Bluetooth
                    mBluetooth.SendMessage("$PWM," + seekBar.getProgress());
                }
            }
        });*/

        // Setup listener for Bluetooth helper;

        mBluetooth.setBluetoothHelperListener(new BluetoothHelper.BluetoothHelperListener() {
            String globalmsg = "";

            @Override
            public void onBluetoothHelperMessageReceived(BluetoothHelper bluetoothhelper, final String message) {
                globalmsg = message;
                // Do your stuff with the message received !!!
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        // Update here your User Interface
                        //    ((TextView) findViewById(R.id.light)).setText(globalmsg);
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(globalmsg);
                        if (matcher.find()) {
                            if (matcher.group(1).equals("temp")) {
                                // (TextView) findViewById(R.id.temperature)).setText(matcher.group(3));
                                temp.setText(matcher.group(3));
                                array_temp.add(new DataPoint(getTime(), Float.parseFloat(matcher.group(3))));
                                // if(array_temp.size()>MAX_ELEMENT_IN_ARRAY) array_temp.remove(0);
                                array_temp.trimToSize();

                                Log.w("WARN/OUTPUT", array_temp.toString());


                            } else if (matcher.group(1).equals("humd")) {
                                humd.setText(matcher.group(3));
                                array_humd.add(new DataPoint(getTime(), Float.parseFloat(matcher.group(3))));
                                if (array_humd.size() > MAX_ELEMENT_IN_ARRAY)
                                    array_humd.remove(0);
                            } else if (matcher.group(1).equals("ligh")) {
                                ligh.setText(matcher.group(3));
                                array_ligh.add(new DataPoint(getTime(), Float.parseFloat(matcher.group(3))));
                                if (array_ligh.size() > MAX_ELEMENT_IN_ARRAY)
                                    array_ligh.remove(0);
                            }

                        }

                    }
                });
            }

            @Override
            public void onBluetoothHelperConnectionStateChanged(BluetoothHelper bluetoothhelper, boolean isConnected) {
                if (isConnected) {

                    // mTextViewStatus.setText("Connected");

                    Log.e("hi", "connecting successfully");
                } else {
                    Log.e("hi", "error in connection");
                    //  mTextViewStatus.setText("Disconnected");
                    // Auto reconnect!
                    mBluetooth.Connect(DEVICE_NAME);
                }
            }
        });
    }

}



