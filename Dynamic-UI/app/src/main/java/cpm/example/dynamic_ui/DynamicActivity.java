package cpm.example.dynamic_ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class DynamicActivity extends ActionBarActivity {

    int amount;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        b = getIntent().getExtras();
        amount = b.getInt("Amount",4);
        this.CreateFragment(savedInstanceState,amount);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dynamic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public RelativeLayout CreateFragment(Bundle state, int amount)
    {
        RelativeLayout root = new RelativeLayout(getBaseContext());
        if (state == null)
        {
            ArrayList<Button> buttons = new ArrayList<Button>(0);
            for(int i = 0; i < amount; i++)
            {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                buttons.add(new Button(getApplicationContext()));
                buttons.get(i).setText((CharSequence) ("Button " + (i + 1)));
                if(i > 0)
                {
                    buttons.get(i).setId(Integer.MAX_VALUE - i);
                    params.addRule(RelativeLayout.BELOW,buttons.get(i - 1).getId());
                    buttons.get(i).setLayoutParams(params);
                }
                else
                {
                    buttons.get(i).setId(Integer.MAX_VALUE - i);
                    buttons.get(i).setLayoutParams(params);
                }
                root.addView(buttons.get(i));
            }
            this.setContentView(root);
            return root;
        }
        return null;
    }
}
