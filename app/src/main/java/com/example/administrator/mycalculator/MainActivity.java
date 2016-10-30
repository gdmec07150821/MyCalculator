package com.example.administrator.mycalculator;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calculatorButton;
    private EditText weightEditText;
    private CheckBox manCheckBox;
    private CheckBox womanCheckBox;
    private TextView resultTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorButton = (Button) findViewById(R.id.calculator);
        weightEditText = (EditText) findViewById(R.id.weight);
        manCheckBox = (CheckBox) findViewById(R.id.man);
        womanCheckBox = (CheckBox) findViewById(R.id.women);
        resultTextView = (TextView) findViewById(R.id.result);
    }
    private void showmessage(String message) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height =170-(62-weight)/0.6;
        }else{
            height =158-(52-weight)/0.5;
        }
        return height;
    }
    private void registerEvent(){
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!weightEditText.getText().toString().trim().equals("")){
                    if(manCheckBox.isChecked()||womanCheckBox.isChecked()){
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb =new StringBuffer();
                        sb.append("------评估结果--------\n");
                        if(manCheckBox.isChecked()){
                            sb.append("男性标准身高：");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"（厘米）");
                        }
                        if(womanCheckBox.isChecked()){
                            sb.append("女性标准身高：");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result+"（厘米）");
                        }
                        resultTextView.setText(sb.toString());
                    }else{
                        showmessage("请选择性别");
                    }
                }else{
                    showmessage("请输入体重哦！！！");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}



