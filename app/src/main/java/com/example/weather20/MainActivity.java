package com.example.weather20;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView time_1, time_2, time_3, time_4, time_5, time_6,time_7,
            Date_1, Date_2, Date_3, Date_4, Date_5, Date_6, Date_7,
            Temperature_1, Temperature_2, Temperature_3, Temperature_4, Temperature_5, Temperature_6, Temperature_7,
            Weather_1, Weather_2, Weather_3, Weather_4, Weather_5, Weather_6, Weather_7,
            Direction_1, Direction_2, Direction_3, Direction_4, Direction_5, Direction_6, Direction_7,
            Power_1, Power_2, Power_3, Power_4, Power_5, Power_6, Power_7, Warn_content;
    JSONObject object_now,
            forecast7_0, forecast7_1, forecast7_2, forecast7_3, forecast7_4, forecast7_5, forecast7_6, warn,
            temperature_0_object, temperature_1_object, temperature_2_object, temperature_3_object, temperature_4_object, temperature_5_object, temperature_6_object;
    private Intent intent;
    private ImageButton StartBtn = null;
    private ImageButton StopBtn = null;
    private StringBuilder respond;
    private String string = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time_1 = findViewById(R.id.day_1);
        time_2 = findViewById(R.id.day_2);
        time_3 = findViewById(R.id.day_3);
        time_4 = findViewById(R.id.day_4);
        time_5 = findViewById(R.id.day_5);
        time_6 = findViewById(R.id.day_6);
        time_7 = findViewById(R.id.day_7);
        Date_2 = findViewById(R.id.date_2);
        Date_3 = findViewById(R.id.date_3);
        Date_4 = findViewById(R.id.date_4);
        Date_5 = findViewById(R.id.date_5);
        Date_6 = findViewById(R.id.date_6);
        Date_7 = findViewById(R.id.date_7);
        Temperature_2 = findViewById(R.id.temperature_2);
        Temperature_3 = findViewById(R.id.temperature_3);
        Temperature_4 = findViewById(R.id.temperature_4);
        Temperature_5 = findViewById(R.id.temperature_5);
        Temperature_6 = findViewById(R.id.temperature_6);
        Temperature_7 = findViewById(R.id.temperature_7);
        Weather_2 = findViewById(R.id.weather_2);
        Weather_3 = findViewById(R.id.weather_3);
        Weather_4 = findViewById(R.id.weather_4);
        Weather_5 = findViewById(R.id.weather_5);
        Weather_6 = findViewById(R.id.weather_6);
        Weather_7 = findViewById(R.id.weather_7);
        Direction_2 = findViewById(R.id.direction_2);
        Direction_3 = findViewById(R.id.direction_3);
        Direction_4 = findViewById(R.id.direction_4);
        Direction_5 = findViewById(R.id.direction_5);
        Direction_6 = findViewById(R.id.direction_6);
        Direction_7 = findViewById(R.id.direction_7);
        Power_2 = findViewById(R.id.power_2);
        Power_3 = findViewById(R.id.power_3);
        Power_4 = findViewById(R.id.power_4);
        Power_5 = findViewById(R.id.power_5);
        Power_6 = findViewById(R.id.power_6);
        Power_7 = findViewById(R.id.power_7);
        Warn_content = findViewById(R.id.warn_content);
        StartBtn = (ImageButton) findViewById(R.id.StartBtn);
        StopBtn = (ImageButton) findViewById(R.id.StopBtn);

        httptest();

        //定义StartBtn为音乐开始按键
        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(v);
            }
        });

        //定义StopBtn为音乐停止按键
        StopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop(v);
            }
        });

    }

    //获取JSON
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {

        @SuppressLint({"SetTextI18n", "ShowToast"})
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    try {
                        //实时数据
                        time_1.setText("降水：" + object_now.getString("now_rain"));
                        time_2.setText("温度：" + object_now.getString("now_feelst"));
                        time_3.setText("空气湿度：" + object_now.getString("now_humidity"));
                        time_4.setText("状态：" + object_now.getString("now_icomfort"));
                        time_5.setText("空气污染：" + object_now.getString("now_rcomfort"));
                        time_6.setText("风力：" + object_now.getString("now_wind_power"));
                        time_7.setText("风向："+object_now.getString("now_wind_direction"));

                        //日期
                        Date_2.setText(forecast7_1.getString("week"));
                        Date_3.setText(forecast7_2.getString("week"));
                        Date_4.setText(forecast7_3.getString("week"));
                        Date_5.setText(forecast7_4.getString("week"));
                        Date_6.setText(forecast7_5.getString("week"));
                        Date_7.setText(forecast7_6.getString("week"));

                        //温度
                        Temperature_2.setText(temperature_1_object.getString("temprature"));
                        Temperature_3.setText(temperature_2_object.getString("temprature"));
                        Temperature_4.setText(temperature_3_object.getString("temprature"));
                        Temperature_5.setText(temperature_4_object.getString("temprature"));
                        Temperature_6.setText(temperature_5_object.getString("temprature"));
                        Temperature_7.setText(temperature_6_object.getString("temprature"));

                        //天气情况
                        Weather_2.setText(temperature_1_object.getString("weather"));
                        Weather_3.setText(temperature_2_object.getString("weather"));
                        Weather_4.setText(temperature_3_object.getString("weather"));
                        Weather_5.setText(temperature_4_object.getString("weather"));
                        Weather_6.setText(temperature_5_object.getString("weather"));
                        Weather_7.setText(temperature_6_object.getString("weather"));

                        //风向
                        Direction_2.setText(temperature_1_object.getString("direction"));
                        Direction_3.setText(temperature_2_object.getString("direction"));
                        Direction_4.setText(temperature_3_object.getString("direction"));
                        Direction_5.setText(temperature_4_object.getString("direction"));
                        Direction_6.setText(temperature_5_object.getString("direction"));
                        Direction_7.setText(temperature_6_object.getString("direction"));

                        //风力
                        Power_2.setText(temperature_1_object.getString("power"));
                        Power_3.setText(temperature_2_object.getString("power"));
                        Power_4.setText(temperature_3_object.getString("power"));
                        Power_5.setText(temperature_4_object.getString("power"));
                        Power_6.setText(temperature_5_object.getString("power"));
                        Power_7.setText(temperature_6_object.getString("power"));

                        //警告
                        Warn_content.setText(warn.getString("content"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private void httptest() {

        //step 1: 同样的需要创建一个OkHttpClick对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //step 2: 创建  FormBody.Builder
        FormBody formBody = new FormBody.Builder()
                // .add("name", "dsd")
                .build();

        //step 3: 创建请求
        Request request = new Request.Builder()
                .url("https://api.shenjian.io/weather/city?appid=3053f51b6e879903659b47d0d3bd1d76&city_name=%E7%AB%A0%E4%B8%98&only_now=false")
                .get()
                .build();

        //step 4： 建立联系 创建Call对象
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO:  请求失败
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // TODO:  请求成功
                String responseString = response.body().string();

                Log.i("http_ok", responseString);
                try {
                    //1.将json 字符串转换成Json对象
                    JSONObject Json_All = new JSONObject(responseString);

                    //判断返回数据是否错误
                    String error_code = Json_All.getString("error_code");
                    Log.d("Error_Code", "Error_code   === " + error_code);

                    String Data_All = Json_All.getString("data");
                    JSONObject Data_Object = new JSONObject(Data_All);

                    //获取实时数据
                    JSONArray Data_now = Data_Object.getJSONArray("now");
                    object_now = Data_now.getJSONObject(0);

                    //获取日期
                    JSONArray Data_Forecast7 = Data_Object.getJSONArray("forecast7");
                    forecast7_0 = Data_Forecast7.getJSONObject(0);
                    forecast7_1 = Data_Forecast7.getJSONObject(1);
                    forecast7_2 = Data_Forecast7.getJSONObject(2);
                    forecast7_3 = Data_Forecast7.getJSONObject(3);
                    forecast7_4 = Data_Forecast7.getJSONObject(4);
                    forecast7_5 = Data_Forecast7.getJSONObject(5);
                    forecast7_6 = Data_Forecast7.getJSONObject(6);

                    //获取警告
                    String forecast7_0_String = forecast7_0.toString();
                    JSONObject forecast7_0_object = new JSONObject(forecast7_0_String);
                    JSONArray forecast7_0_Worn = forecast7_0_object.getJSONArray("warn");
                    warn = forecast7_0_Worn.getJSONObject(0);
                    //获取天气情况
                    String forecast7_1_String = forecast7_1.toString();
                    JSONObject forecast7_1_object = new JSONObject(forecast7_1_String);
                    JSONArray Forecast7_Day_1 = forecast7_1_object.getJSONArray("day");
                    temperature_1_object = Forecast7_Day_1.getJSONObject(0);

                    String forecast7_2_String = forecast7_2.toString();
                    JSONObject forecast7_2_object = new JSONObject(forecast7_2_String);
                    JSONArray Forecast7_Day_2 = forecast7_2_object.getJSONArray("day");
                    temperature_2_object = Forecast7_Day_2.getJSONObject(0);

                    String forecast7_3_String = forecast7_3.toString();
                    JSONObject forecast7_3_object = new JSONObject(forecast7_3_String);
                    JSONArray Forecast7_Day_3 = forecast7_3_object.getJSONArray("day");
                    temperature_3_object = Forecast7_Day_3.getJSONObject(0);

                    String forecast7_4_String = forecast7_4.toString();
                    JSONObject forecast7_4_object = new JSONObject(forecast7_4_String);
                    JSONArray Forecast7_Day_4 = forecast7_4_object.getJSONArray("day");
                    temperature_4_object = Forecast7_Day_4.getJSONObject(0);

                    String forecast7_5_String = forecast7_5.toString();
                    JSONObject forecast7_5_object = new JSONObject(forecast7_5_String);
                    JSONArray Forecast7_Day_5 = forecast7_5_object.getJSONArray("day");
                    temperature_5_object = Forecast7_Day_5.getJSONObject(0);

                    String forecast7_6_String = forecast7_6.toString();
                    JSONObject forecast7_6_object = new JSONObject(forecast7_6_String);
                    JSONArray Forecast7_Day_6 = forecast7_6_object.getJSONArray("day");
                    temperature_6_object = Forecast7_Day_6.getJSONObject(0);


                    handler.sendEmptyMessage(1);
                } catch (Exception e) {
                    handler.sendEmptyMessage(2);
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 背景音乐
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            stopService(intent);
        }
    }

    //开始播放音乐
    public void start(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }

    //停止音乐
    public void stop(View v) {
        Intent intent = new Intent(MainActivity.this, MyService.class);
        stopService(intent);
    }
}
