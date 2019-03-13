package com.ndanda.bigcommerce.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.CircularProgressDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.ndanda.bigcommerce.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Bindings {


    /**
     * Date will be formatted to 'EEE, dd MMM yyyy hh:mm aaa'
     *
     * @param textView : Formatted date will be set to this textView
     * @param dateIn with format 'yyyy-MM-dd'T'HH:mm:ss
     */
    @BindingAdapter("date")
    public static void setDate(TextView textView, String dateIn) {
        if (dateIn != null && !dateIn.isEmpty()) {
            Locale locale = new Locale("US");
            SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                    locale); // 2018-04-21T19:05:00 example incoming date
            SimpleDateFormat formatToDisplay = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm aaa "
                    , locale); // Mon, 13 Jun 2016 07:05 PM example displaying format
            try {
                Date date = incomingFormat.parse(dateIn);
                textView.setText(formatToDisplay.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * Sets a default image if url is null or onLoadFailed.
     *
     * @param imageView where url will be loaded into
     * @param url of the image
     * @param radius for setting the corner radius to the image
     */
    @BindingAdapter({"app:url","app:radius"})
    public static void setImage(ImageView imageView, String url, int radius) {

        if (url != null) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions()
                            .transform(new RoundedCorners(radius))
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .placeholder(new CircularProgressDrawable(imageView.getContext())))
                    .listener(new RequestListener<Drawable>() {

                        @Override
                        public boolean onLoadFailed(@android.support.annotation.Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                            imageView.setImageDrawable(imageView.getContext().getResources()
                                            .getDrawable(R.drawable.ic_launcher_background));
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model,
                                                       com.bumptech.glide.request.target.Target<Drawable> target,
                                                       DataSource dataSource,
                                                       boolean isFirstResource) {
                            imageView.setImageDrawable(resource);
                            return false;
                        }
                    })
                    .into(imageView);
        }else {
            imageView.setImageDrawable(imageView.getContext().getResources().
                    getDrawable(R.drawable.ic_launcher_background));
        }
    }

}
