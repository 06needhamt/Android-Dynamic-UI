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
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,200);
                buttons.add(new Button(getApplicationContext()));
                buttons.get(i).setText((CharSequence) ("Button " + (i + 1)));
                AddButtonLayout(buttons.get(i),RelativeLayout.CENTER_IN_PARENT);
                if(i != 0)
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
                root.addView(buttons.get(i),buttons.get(i).getLayoutParams());
            }
            this.setContentView(root);
            return root;
        }
        return null;
    }

    private void AddButtonLayout(Button button, int centerInParent, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        // Defining the layout parameters of the Button
        RelativeLayout.LayoutParams buttonLayoutParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add Margin to the LayoutParameters
        buttonLayoutParameters.setMargins(marginLeft, marginTop, marginRight, marginBottom);

        // Add Rule to Layout
        buttonLayoutParameters.addRule(centerInParent);

        // Setting the parameters on the Button
        button.setLayoutParams(buttonLayoutParameters);
    }

    private void AddButtonLayout(Button button, int centerInParent) {
        // Just call the other AddButtonLayout Method with Margin 0
        AddButtonLayout(button, centerInParent, 0 ,0 ,0 ,0);
    }
}
