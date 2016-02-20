package com.bk.util;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;
import android.widget.Toast;

import com.bk.activity.MainActivity;
import com.bk.netdel.Ldata;
import com.serven.R;

public class UiUtil<T> {

	public static void removelists(int sum, List<Ldata> list) {
		if (sum > 35) {
			for (int i = 0; i < 10; i++) {
				list.remove(i);
			}
		}
	}

	public static String formatDate() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("MM.dd,yyyy HH:mm");
		Date date = new Date();
		return sDateFormat.format(date);
	}

	public static ImageGetter getNetImage(final Context context) {

		ImageGetter imageGetter = new ImageGetter() {
			@Override
			public Drawable getDrawable(String source) {

				InputStream is = null;
				Drawable drawable = null;
				URL url;
				try {
					url = new URL(source);
					is = (InputStream) url.getContent();
//					drawable = context.getResources().getDrawable(
//							R.drawable.btn_img);
					drawable = Drawable.createFromStream(is,"src"); // »ñÈ¡ÍøÂ·Í¼Æ¬
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight());
					is.close();
					return drawable;
				} catch (Exception e) {
					return null;
				}
			};

		};

		return imageGetter;
	}

}
