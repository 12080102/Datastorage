package kevin.crunch.datastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Search extends AppCompatActivity {

    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        name=(EditText)findViewById(R.id.editText5);
    }

    protected void Search(View view){
        String n = name.getText().toString();
        if(n!=null && !"".equals(n)){
            DataController dataController=new DataController(getBaseContext());
            dataController.open();
            //query by single name
        }
    }

    protected void Cancel(View view){
        String n = name.getText().toString();
        if(!"".equals(n)){
            name.setText("");
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
