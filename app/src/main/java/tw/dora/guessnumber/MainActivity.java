package tw.dora.guessnumber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private View[] btnNumber = new View[10];
    private int[] numberRes = {
            R.id.main_btn_0, R.id.main_btn_1, R.id.main_btn_2,
            R.id.main_btn_3, R.id.main_btn_4, R.id.main_btn_5,
            R.id.main_btn_6,R.id.main_btn_7,  R.id.main_btn_8,
            R.id.main_btn_9
    };

    private TextView[] input = new TextView[4];
    private int[] inputRes = {
            R.id.main_input1, R.id.main_input2,
            R.id.main_input3, R.id.main_input4,
    };

    private int inputPoint = 0;
    private LinkedList<Integer> inputValue = new LinkedList<>();
    private LinkedList<Integer> answer;

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private LinkedList<HashMap<String,String>> hist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initGame();
        initListView();
    }

    private void initView() {
        for(int i=0;i<4;i++){
            inputValue.add(-1);
        }
        for(int i=0;i<inputRes.length;i++){
            input[i] = findViewById(inputRes[i]);
        }
        for(int i=0;i<numberRes.length;i++){
            btnNumber[i] = findViewById(numberRes[i]);
        }
    }

    private void initGame(){
        answer = createAnswer();
        clear(null);
    }

    private void initListView() {
        listView = findViewById(R.id.main_listview);

        hist = new LinkedList<>();
        //simpleAdapter = new SimpleAdapter(this, hist);
        listView.setAdapter(simpleAdapter);
    }

    private LinkedList<Integer> createAnswer() {
        LinkedList<Integer> ret = new LinkedList<>();
        HashSet<Integer> nums = new HashSet<>();
        while(nums.size()<4){
            nums.add((int)(Math.random()*10));
        }
        for(Integer i:nums){
            ret.add(i);
        }

        Collections.shuffle(ret);
        return ret;
    }



    //輸入數字鍵的方法
    public void inputNumber(View view) {
        if(inputPoint == 4) return;

        //比對
        for(int i=0;i<btnNumber.length;i++){
            if(view == btnNumber[i]){

                //處理輸入了i鍵
                inputValue.set(inputPoint, i);
                input[inputPoint].setText("" + i);
                btnNumber[i].setEnabled(false);
                inputPoint++;

                break;
            }
        }
    }

    public void back(View view) {
        if(inputPoint == 0) return;

        inputPoint--;
        input[inputPoint].setText("");
        btnNumber[inputValue.get(inputPoint)].setEnabled(true);
        inputValue.set(inputPoint,-1);

    }

    public void clear(View view) {
        inputPoint = 0;
        inputValue.clear();
        for(int i=0;i<4;i++){
            inputValue.add(-1);
        }
        for(int i=0;i<input.length;i++){
            input[i].setText("");
        }
        for(int i=0;i<btnNumber.length;i++){
            btnNumber[i].setEnabled(true);
        }
    }
    public void send(View view) {

    }
    public void replay(View view) {

    }
}
