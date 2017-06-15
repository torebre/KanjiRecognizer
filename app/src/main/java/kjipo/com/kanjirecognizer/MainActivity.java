package kjipo.com.kanjirecognizer;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private InputView inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = (InputView)findViewById(R.id.input);
    }

    public void findKanji(View view) {
        int[] bitmap = inputView.getKanjiBitmap();

        Log.i("test", "Bitmap length: " + bitmap);

        inputView.clearInput();


    }
}
