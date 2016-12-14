package mj.android.cowsaysmoo;

import java.io.IOException;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;


public class CowSaysMoo extends Activity {
	
	SoundPool mSoundPool;
	AssetManager assets;
	int catSound, chickenSound, cowSound, dogSound, duckSound, sheepSound;
	int countLoadedSound;
	Context mContext;
	ProgressDialog dialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mContext = this;
		
		mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		assets = getAssets();
		
		catSound = loadSound("cat.ogg");
		chickenSound = loadSound("chicken.ogg");
		cowSound = loadSound("cow.ogg");
		dogSound = loadSound("dog.ogg");
		duckSound = loadSound("duck.ogg");
		sheepSound = loadSound("sheep.ogg");
		
		/*mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				Log.d("MY", "Complete load sampleId = " + sampleId + " status = " + status);
				if (status == 0)
					countLoadedSound++;
				if (countLoadedSound == 6) {
					Toast.makeText(mContext, "All files sucessfully loaded", Toast.LENGTH_SHORT).show();
				}
			}
		});*/
		
		ImageButton cow = (ImageButton)this.findViewById(R.id.cow);
		cow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(cowSound);
				Log.d("MY", "COW");
			}
		});
		
		ImageButton chicken = (ImageButton)this.findViewById(R.id.chicken);
		chicken.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(chickenSound);
			}
		});
		
		ImageButton cat = (ImageButton)this.findViewById(R.id.cat);
		cat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(catSound);
			}
		});
		
		ImageButton duck = (ImageButton)this.findViewById(R.id.duck);
		duck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(duckSound);
			}
		});
		
		ImageButton sheep = (ImageButton)this.findViewById(R.id.sheep);
		sheep.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(sheepSound);
			}
		});
		
		ImageButton dog = (ImageButton)this.findViewById(R.id.dog);
		dog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playSound(dogSound);
			}
		});
		
	}

	protected void playSound(int sound) {
		if (sound > 0)
			mSoundPool.play(sound, 1, 1, 1, 0, 1);
	}

	private int loadSound(String fileName) {
		AssetFileDescriptor afd = null;
		try {
			afd = assets.openFd(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(this, "Couldn't load file '" + fileName + "'", Toast.LENGTH_SHORT).show();
			return -1;
		}
		
		return mSoundPool.load(afd, 1);
	}

}
