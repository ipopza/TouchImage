package com.example.touchimage;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.GPUImagePosterizeFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageSobelEdgeDetection;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class GesturesActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RelativeLayout placeHolder = (RelativeLayout) findViewById(R.id.place_holder);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ken);
		
		/*** Filter ***/
		GPUImageSobelEdgeDetection edgeFilter = new GPUImageSobelEdgeDetection();
		edgeFilter.setLineSize(0.3f);
		
		/*** Process **/
		GPUImage imgProcessor = new GPUImage(getApplicationContext());
		imgProcessor.setFilter(edgeFilter);
		imgProcessor.setImage(bitmap);

		bitmap = imgProcessor.getBitmapWithFilterApplied(bitmap);
		imgProcessor.setFilter(new GPUImageColorInvertFilter());
		imgProcessor.setImage(bitmap);

		bitmap = imgProcessor.getBitmapWithFilterApplied(bitmap);
		imgProcessor.setFilter(new GPUImagePosterizeFilter());
		imgProcessor.setImage(bitmap);

		/*bitmap = imgProcessor.getBitmapWithFilterApplied(bitmap);
		imgProcessor.setFilter(new GPUImageSepiaFilter());
		imgProcessor.setImage(bitmap);*/

		placeHolder.addView(new SandboxView(getApplicationContext(), imgProcessor.getBitmapWithFilterApplied()));
	}
}