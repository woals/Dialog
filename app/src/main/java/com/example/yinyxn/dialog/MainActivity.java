package com.example.yinyxn.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textViewDate;
    TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate = (TextView) findViewById(R.id.textView_date);
        textViewTime = (TextView) findViewById(R.id.textView_time);

        Person person = new Person.Builder("")
                .setAddress("")
                .setEmail("")
                .setGender("")
                .setAge(23)
                .setPhone("")
                .create();
    }

    /**
     * 显示默认对话框
     *
     * @param view
     */
    public void showDialog(View view) {

        DialogListener listener = new DialogListener();
        View v = getLayoutInflater().inflate(R.layout.dialog_demo, null);

        //如果没有v 就会去activity_main.xml中找id
        final EditText editTextNet = (EditText) v.findViewById(R.id.editText);
        EditText editTextPassword = (EditText) findViewById(R.id.editText_password);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);


        /*new AlertDialog.Builder(this).setTitle()......create().show()   简写*/
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("内容")
                .setView(v)
                        //  .setView(R.layout.dialog_demo) 如果用这个，network就取不到数据
                        // 因为相当于实例化了第2套dialog_demo
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String network = editTextNet.getText().toString();
                        showToast(network);
                    }
                })
                .setNegativeButton("取消", null)
//                .setNeutralButton("更多信息", listener)
                .create();
        dialog.show();
    }

    /**
     * 显示带有列表的对话框
     *
     * @param view
     */
    public void showListDialog(View view) {
        final String[] items = {"产品", "研发", "测试", "市场"};

        new AlertDialog.Builder(this)
                .setTitle("职位")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = items[which];
                        showToast(item);
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .create()
                .show();
    }

    String[] items = {"产品", "研发", "测试", "市场"};

    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * 单选对话框
     */
    int checkedItem = -1;

    public void showSingleDialog(View view) {
        final String[] s = {""};
        new AlertDialog.Builder(this)
                .setTitle("职位")
                .setSingleChoiceItems(//3 个参数
                        items,
                        checkedItem,//默认选项
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // showToast(items[which]);
                                checkedItem = which;//把信息(点击的值)保存在checkedItem中
                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(items[checkedItem]);//通过Toast显示信息
                    }
                })
                .setNegativeButton("取消", null)
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     * 多选对话框
     *
     * @param view
     */
    public void showMultiDialog(View view) {
        //多选都需要建立数组
        final boolean[] checkedItems = new boolean[items.length];

        new AlertDialog.Builder(this)//this表示上下文
                .setTitle("选择职位")
                .setMultiChoiceItems(//3 个参数
                        items,
                        checkedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which,//被点击项
                                    boolean isChecked//是否被选中（状态）
                            ) {
                                checkedItems[which] = isChecked;
                            }
                        }
                )
                .setCancelable(false)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    /**
     * 显示时间对话框
     *
     * @param view
     */
    public void showTimeDialog(View view) {

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textViewTime.setText(String.format("%2d%02d",hourOfDay,minute));
                    }
                },
                hour,
                minute,
                false).show();
    }

    /**
     * 显示日期对话框
     *
     * @param view
     */
    public void showDateDialog(View view) {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(
                            DatePicker view, //事件源
                            int year,
                            int monthOfYear,
                            int dayOfMonth) {
                        String text = String.format(//格式化
                                "%d/%02d/%02d",//如果只有一位 前面补 0
                                year,
                                monthOfYear + 1,
                                dayOfMonth);
                        textViewDate.setText(text);
                    }
                },
                year,
                month,
                day);
        dialog.show();
    }

    public void showProgressDialog(View view) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("标题");
        dialog.setMessage("消息");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(9);


        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
        dialog.setProgress(4);
    }


    class DialogListener implements DialogInterface.OnClickListener {

        /**
         * 点击对话框中的按钮
         *
         * @param dialog
         * @param which
         */
        @Override
        public void onClick(DialogInterface dialog, int which) {


            String msg = "";
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    msg = "Positive";
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    msg = "Negative";
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    msg = "Neutral";
                    break;

            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
