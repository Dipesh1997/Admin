package dev.edmt.firebasedemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class form extends Activity implements OnClickListener
{
    Spinner spinner1;
    ArrayAdapter<CharSequence> adapter1;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter2;
    Spinner spinner3;
    ArrayAdapter<CharSequence> adapter3;
    Spinner spinner4;
    ArrayAdapter<CharSequence> adapter4;
    Spinner spinner5;
    ArrayAdapter<CharSequence> adapter5;
    Spinner spinner6;
    ArrayAdapter<CharSequence> adapter6;
    static final int TIME_DIALOG_ID = 1111;
    private TextView output;
    public TextView btnClick;

    private int hour;
    private int minute;
    SimpleDateFormat simpledateformat;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";
    public static final String NameP="nameKey";
    public static final String ContactP="contactKey";
    public static final String DateP="dateKey";
    public static final String TimeP="timeKey";
    public static final String BusP="busKey";
    public static final String FromP="fromKey";
    public static final String DesP="desKey";
    public static final String nopP="nopKey";
    public static final String SerP="serKey";
    public static final String SeatP="seatKey";
    public static final String StatusP="statusKey";
    public static final String fareP="fareKey";

    public SharedPreferences sharedpreferences;

    private TextView toDateEtxt;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    TextView Date;
    String displaydate;
    Calendar calander;
    public void onDrop()
    {
        spinner1=(Spinner)findViewById(R.id.busDrop);
        adapter1=ArrayAdapter.createFromResource(this,R.array.buses,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);
        spinner2=(Spinner)findViewById(R.id.fDrop);
        adapter2=ArrayAdapter.createFromResource(this,R.array.states,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);
        spinner3=(Spinner)findViewById(R.id.desDrop);
        adapter3=ArrayAdapter.createFromResource(this,R.array.states,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter3);
        spinner4=(Spinner)findViewById(R.id.pDrop);
        adapter4=ArrayAdapter.createFromResource(this,R.array.persons,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner4.setAdapter(adapter4);
        spinner5=(Spinner)findViewById(R.id.acDrop);
        adapter5=ArrayAdapter.createFromResource(this,R.array.service,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner5.setAdapter(adapter5);
        spinner6=(Spinner)findViewById(R.id.seatDrop);
        adapter6=ArrayAdapter.createFromResource(this,R.array.seat_type,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner6.setAdapter(adapter6);
    }


    private void findViewsById()
    {
        toDateEtxt = (TextView) findViewById(R.id.tDate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
    @Override
    public void onClick(View view)
    {
        if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }
    public void addButtonClickListener()
    {
        btnClick = (TextView) findViewById(R.id.tTime);
        btnClick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
                btnClick.setInputType(InputType.TYPE_NULL);

            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);
        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            // TODO Auto-generated method stub
            hour   = hourOfDay;
            minute = minutes;
            updateTime(hour,minute);
        }
    };

    private static String utilTime(int value) {
        if (value < 10)
            return "0" + String.valueOf(value);
        else
            return String.valueOf(value);
    }
    private void updateTime(int hours, int mins) {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";
        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);
        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();
        output.setText(aTime);
        output.setInputType(InputType.TYPE_NULL);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        onDrop();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
        output = (TextView) findViewById(R.id.tTime);
        final Calendar c = Calendar.getInstance();
        Date=(TextView)findViewById(R.id.tDate);
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        calander = Calendar.getInstance();
        displaydate = simpledateformat.format(calander.getTime());
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        updateTime(hour, minute);
        addButtonClickListener();
        Date.setText(displaydate);
    }
    public void onConfirm(View v)
    {
        EditText nameF = (EditText) findViewById(R.id.user_name);
        EditText mobF = (EditText) findViewById(R.id.user_mob);
        TextView dateF=(TextView)findViewById(R.id.tDate);
        if((nameF.getText().toString().length()==0)||(mobF.getText().toString().length()<10 && mobF.getText().toString().length()>0))
        {
            Toast.makeText(form.this, "Fill all and Proper Detail", Toast.LENGTH_LONG).show();
        }
        else{
            String buspos=String.valueOf(spinner1.getSelectedItemPosition());
            String frompos=String.valueOf(spinner2.getSelectedItemPosition());
            String despos=String.valueOf(spinner3.getSelectedItemPosition());
            String noppos=String.valueOf(spinner4.getSelectedItemPosition());
            String serpos=String.valueOf(spinner5.getSelectedItemPosition());
            String seatpos=String.valueOf(spinner6.getSelectedItemPosition());
            String farecheck=buspos+frompos+despos+noppos+serpos+seatpos;
            String name = nameF.getText().toString();
            String mob = mobF.getText().toString();
            String date = dateF.getText().toString();
            TextView timeF = (TextView) findViewById(R.id.tTime);
            String time = timeF.getText().toString();
            String destinationSp = String.valueOf(spinner3.getSelectedItem());
            String nopSp = String.valueOf(spinner4.getSelectedItem());
            String acSp = String.valueOf(spinner5.getSelectedItem());
            String seatSp = String.valueOf(spinner6.getSelectedItem());
            String busSp = String.valueOf(spinner1.getSelectedItem());
            String fromSp = String.valueOf(spinner2.getSelectedItem());
            String Status = "Payment Stage";
            Intent intent = new Intent(form.this, confirm.class);
            startActivity(intent);
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(NameP, name);
            editor.putString(ContactP, mob);
            editor.putString(DateP, date);
            editor.putString(TimeP, time);
            editor.putString(BusP, busSp);
            editor.putString(FromP, fromSp);
            editor.putString(DesP, destinationSp);
            editor.putString(nopP, nopSp);
            editor.putString(SerP, acSp);
            editor.putString(SeatP, seatSp);
            editor.putString(StatusP, Status);
            editor.putString(fareP,farecheck);
            editor.commit();
        }
    }
}
