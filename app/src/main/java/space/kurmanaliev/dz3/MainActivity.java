package space.kurmanaliev.dz3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static space.kurmanaliev.dz3.MainActivity2.KEY;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    TextView textView;
    final int REQUEST_CODE = 1;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        button1=findViewById(R.id.buttonNext);
        button2=findViewById(R.id.buttonTransition);
        textView=findViewById(R.id.textView1);
        imageView1=findViewById(R.id.imageView1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString()); // текст отправки
                startActivity(Intent.createChooser(intent, "Share with"));
            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_OK&&data!=null){
            String text = data.getStringExtra(MainActivity2.KEY);
            textView.setText(text);

            String image = data.getStringExtra(MainActivity2.IMAGE_KEY);
            Uri uri = Uri.parse(image);
            imageView1.setImageURI(uri);

        }
    }


}