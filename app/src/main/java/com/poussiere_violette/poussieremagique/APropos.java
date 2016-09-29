/*Copyright (C) <2016>  <Nicolas BOUTIN>

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/

        contact us : von.linnasta@gmail.com

*/


package com.poussiere_violette.poussieremagique;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class APropos extends Activity {

    View creativeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_apropos);
        creativeImage=(View)findViewById(R.id.image_apropos);

        creativeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lien=getResources().getString(R.string.creative_lien);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(lien));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(APropos.this, MainActivity.class));}

    @Override
    public void onPause()
    {super.onPause();
    finish();}

}
