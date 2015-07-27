package com.example.tourguide;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	public TourGuide mTutorialHandler, mTutorialHandler2;
	public Activity mActivity;
	
	private TextView first;
	private Button second;
	private ImageView third;
	private Button fourth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		mActivity = this;

		/* Get 3 buttons from layout */
		first = (TextView) findViewById(R.id.first);
		second = (Button) findViewById(R.id.second);
		third = (ImageView) findViewById(R.id.third);
		fourth = (Button) findViewById(R.id.fourth);

		/* setup enter and exit animation */
		Animation enterAnimation = new AlphaAnimation(0f, 1f);
		enterAnimation.setDuration(600);
		enterAnimation.setFillAfter(true);

		Animation exitAnimation = new AlphaAnimation(1f, 0f);
		exitAnimation.setDuration(600);
		exitAnimation.setFillAfter(true);

		/* initialize TourGuide without playOn() */
		mTutorialHandler = TourGuide
				.init(this)
				.with(TourGuide.Technique.Click)
				.setPointer(new Pointer())
				.setToolTip(
						new ToolTip().setTitle("Hey!")
								.setDescription("I'm the First")
								.setGravity(Gravity.RIGHT)
								.setBackgroundColor(Color.parseColor("#1E90FF")))
				.setOverlay(
						new Overlay().setEnterAnimation(enterAnimation)
								.setExitAnimation(exitAnimation));

		/*
		 * setup 1st button, when clicked, cleanUp() and re-run TourGuide on
		 * button2
		 */
		first.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mTutorialHandler.cleanUp();
				mTutorialHandler.setToolTip(
						new ToolTip().setTitle("Hey there!")
								.setDescription("Just the second!")
								.setGravity(Gravity.BOTTOM | Gravity.LEFT))
						.playOn(second);
			}
		});

		/*
		 * setup 2nd button, when clicked, cleanUp() and re-run TourGuide on
		 * button3
		 */
		second.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mTutorialHandler.cleanUp();
				mTutorialHandler.setToolTip(
						new ToolTip().setTitle("Aloha")
								.setDescription("Third~~")
								.setGravity(Gravity.TOP | Gravity.RIGHT))
						.playOn(third);
			}
		});

		/* setup 3rd button, when clicked, run cleanUp() */
		third.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mTutorialHandler.cleanUp();
				mTutorialHandler.setToolTip(
						new ToolTip().setTitle("你好")
								.setDescription("最后一个")
								.setGravity(Gravity.TOP | Gravity.RIGHT))
						.playOn(fourth);
//				mTutorialHandler.cleanUp();
			}
		});
		
		/* setup 3rd button, when clicked, run cleanUp() */
		fourth.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mTutorialHandler.cleanUp();
			}
		});
		
		mTutorialHandler.playOn(first);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}