package in.fusionbit.hotelroomselection;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.fusionbit.hotelroomselection.models.Room;
import in.fusionbit.hotelroomselection.views.ChildView;
import in.fusionbit.hotelroomselection.views.RoomView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ROOM";

    private AppCompatSpinner spinSelectedRooms;

    private LinearLayout llRoomDetailsContainer;

    private int previousSelectedRoomCount = 0;

    private List<Room> roomList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llRoomDetailsContainer = findViewById(R.id.ll_roomDetailsContainer);

        findViewById(R.id.btn_submit).setOnClickListener(this);

        spinSelectedRooms = ((AppCompatSpinner) findViewById(R.id.spin_selectedRooms));
        spinSelectedRooms.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Constants.NO_OF_ROOMS));

        spinSelectedRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int currentSelectedRoomCount, long l) {

                Log.i(TAG, "PREVIOUS SELECTION: " + previousSelectedRoomCount + " CURRENT SELECTION: " + currentSelectedRoomCount);

                if (currentSelectedRoomCount > previousSelectedRoomCount) {
                    //add room
                    final int roomDetailsToBeAdded = currentSelectedRoomCount - llRoomDetailsContainer.getChildCount();
                    Log.i(TAG, "CHILD COUNT: " + llRoomDetailsContainer.getChildCount());
                    Log.i(TAG, "ROOM DETAILS TO BE ADDED: " + roomDetailsToBeAdded);
                    for (int i = 0; i < roomDetailsToBeAdded; i++) {
                        Log.i(TAG, "ADDING VIEW");
                        final Room room = new Room();
                        roomList.add(room);
                        llRoomDetailsContainer.addView(new RoomView(MainActivity.this, room));
                    }
                } else if (currentSelectedRoomCount < previousSelectedRoomCount) {
                    for (int i = llRoomDetailsContainer.getChildCount() - 1; i >= currentSelectedRoomCount; i--) {
                        roomList.remove(i);
                        llRoomDetailsContainer.removeViewAt(i);
                    }
                }


                previousSelectedRoomCount = currentSelectedRoomCount;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinSelectedRooms.setSelection(0);

    }


    @Override
    public void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        String details = "";
        for (Room room : roomList) {
            sb.append("Room Details:");
            sb.append("\n");
            sb.append(room.getDetails());
            sb.append("\n");
        }
        details = details + sb;

        new AlertDialog.Builder(this)
                .setTitle("Room Details")
                .setMessage(details)
                .show();

    }
}
