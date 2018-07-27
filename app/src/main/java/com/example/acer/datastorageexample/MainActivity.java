package com.example.acer.datastorageexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "bvimit.txt";
    private EditText stringEditText;
    private Button getTextButton,setTextButton;
    private TextView outputTextView;
    private static String fileoutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringEditText=(EditText) findViewById(R.id.stringEditText);
        setTextButton=(Button) findViewById(R.id.setTextButton);
        getTextButton=(Button) findViewById(R.id.getTextButton);
        outputTextView=(TextView) findViewById(R.id.outputTextView);

        getTextButton.setEnabled(false);

        setTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    setTextToTheFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Writing into File", Toast.LENGTH_LONG).show();
                getTextButton.setEnabled(true);

            }
        });
        getTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    readTextFromTheFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "Reading From File", Toast.LENGTH_LONG).show();
                outputTextView.setText(fileoutput);
            }
        });

    }

    private void setTextToTheFile() throws IOException {
        String text=stringEditText.getText().toString();
        FileOutputStream fileOutputStream=openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(text);
        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();

    }
    private String readTextFromTheFile() throws IOException{
        FileInputStream fileInputStream=openFileInput(FILE_NAME);
        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        fileoutput=bufferedReader.readLine();
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        return fileoutput;

    }
}
