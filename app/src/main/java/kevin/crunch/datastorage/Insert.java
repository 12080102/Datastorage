package kevin.crunch.datastorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Insert extends AppCompatActivity {

    private EditText name,desc,price,review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        name=(EditText)findViewById(R.id.editText);
        desc=(EditText)findViewById(R.id.editText2);
        price=(EditText)findViewById(R.id.editText3);
        review=(EditText)findViewById(R.id.editText4);
    }

    protected void Add(View view){
        String n = name.getText().toString();
        String d = desc.getText().toString();
        String p = price.getText().toString();
        String r = review.getText().toString();
        if(n!=null && !"".equals(n)){
            DataController dataController=new DataController(getBaseContext());
            dataController.open();
            long retValue= dataController.insert(n,d,p,r);
            dataController.close();
            Toast.makeText(this, "SQLite insert "+ retValue, Toast.LENGTH_SHORT).show();
            if(retValue!=-1){
                Toast.makeText(this, "Insert success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Insert error", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Insert error, please at least have an Item Name", Toast.LENGTH_LONG).show();
        }
    }

    protected void Cancel(View view){
        String n = name.getText().toString();
        String d = desc.getText().toString();
        String p = price.getText().toString();
        String r = review.getText().toString();
        if(!"".equals(n) ||!"".equals(d) ||!"".equals(p) ||!"".equals(r)){
            name.setText("");
            desc.setText("");
            price.setText("");
            review.setText("");
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
