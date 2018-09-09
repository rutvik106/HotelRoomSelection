package in.fusionbit.hotelroomselection.views;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

import in.fusionbit.hotelroomselection.Constants;
import in.fusionbit.hotelroomselection.R;
import in.fusionbit.hotelroomselection.models.Adult;
import in.fusionbit.hotelroomselection.models.Child;
import in.fusionbit.hotelroomselection.viewmodels.AdultViewModel;
import in.fusionbit.hotelroomselection.viewmodels.ChildViewModel;

public class AdultView extends LinearLayout {

    private AppCompatSpinner spinSelectedAdults;

    private LinearLayout llAdultAgeContainer;

    private List<Adult> adultList;

    private int previousSelectedChildCount = 0;

    public AdultView(final Context context, final List<Adult> adultList) {
        super(context);

        this.adultList = adultList;

        inflate(context, R.layout.ll_adult_container, this);


        llAdultAgeContainer = findViewById(R.id.ll_adultAgeContainer);

        spinSelectedAdults = findViewById(R.id.spin_selectedAdults);

        spinSelectedAdults.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Constants.NO_OF_ADULTS));

        spinSelectedAdults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int currentSelectedChildCount, long l) {
                if (currentSelectedChildCount > previousSelectedChildCount) {
                    final int adultDetailsToBeAdded = currentSelectedChildCount - llAdultAgeContainer.getChildCount();
                    for (int i = 0; i < adultDetailsToBeAdded; i++) {
                        final Adult adult = new Adult();
                        adultList.add(adult);
                        llAdultAgeContainer.addView(new AdultViewModel(context, adult));
                    }
                } else if (currentSelectedChildCount < previousSelectedChildCount) {
                    for (int i = llAdultAgeContainer.getChildCount() - 1; i >= currentSelectedChildCount; i--) {
                        adultList.remove(i);
                        llAdultAgeContainer.removeViewAt(i);
                    }
                }


                previousSelectedChildCount = currentSelectedChildCount;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinSelectedAdults.setSelection(0);

    }

}
