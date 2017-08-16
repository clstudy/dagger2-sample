package com.example.basement.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.basement.R;
import com.example.basement.utils.UIUtils;

import retrofit2.http.Url;

/**
 * Created by jacky on 2017/2/9.
 * banker develper
 * <p>加载图片帮助类</p>
 */
public class ImageLoader {

    private static int mHolderResId = R.mipmap.huiyuan_yidenglu;
    private static int mErrorResId = R.mipmap.huiyuan_yidenglu;


    private static DrawableRequestBuilder getBuild(Context context, @Url String resUrl) {
        return Glide.with(context)
                .load(resUrl)
                .placeholder(mHolderResId)
                .error(mErrorResId);
    }

    private static DrawableRequestBuilder getBuild(Context context, @DrawableRes int resId) {
        return Glide.with(context)
                .load(resId)
                .placeholder(mHolderResId)
                .error(mErrorResId);
    }

    public static void displayCircle(ImageView view, @DrawableRes int resId) {
        getBuild(UIUtils.getContext(), resId)
                .bitmapTransform(new CenterCrop(UIUtils.getContext()), new CropCircleTransformation(UIUtils.getContext()))
                .into(view);
    }

    public static void displayCircle(ImageView view, @Url String url) {
        getBuild(UIUtils.getContext(), url)
                .bitmapTransform(new CenterCrop(UIUtils.getContext()), new CropCircleTransformation(UIUtils.getContext()))
                .into(view);
    }

    public static void displayCircleCenterCrop(ImageView view, @DrawableRes int resId) {
        getBuild(UIUtils.getContext(), resId)
                .bitmapTransform(new CenterCrop(UIUtils.getContext()), new CropCircleTransformation(UIUtils.getContext()))
                .centerCrop()
                .into(view);
    }

    public static void displayCircleFitCenter(ImageView view, @DrawableRes int resId) {
        getBuild(UIUtils.getContext(), resId)
                .bitmapTransform(new CenterCrop(UIUtils.getContext()), new CropCircleTransformation(UIUtils.getContext()))
                .fitCenter()
                .into(view);
    }

    public static void display(ImageView view, @DrawableRes int resId) {
        getBuild(UIUtils.getContext(), resId)
                .into(view);
    }

    public static void display(ImageView view, @Url String url) {
        getBuild(UIUtils.getContext(), url)
                .into(view);
    }

    public static void displayCenterCrop(ImageView view, @DrawableRes int resId) {
        getBuild(UIUtils.getContext(), resId)
                .centerCrop()
                .into(view);
    }

    public static void displayFitCenter(ImageView view, @DrawableRes int resId) {
        getBuild(UIUtils.getContext(), resId)
                .fitCenter()
                .into(view);
    }

    public static void loadImageSimpleTarget(final ImageView view, String url) {
        Glide.with(UIUtils.getContext()) // could be an issue!
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new CenterCrop(UIUtils.getContext()), new CropCircleTransformation(UIUtils.getContext()))
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        view.setImageBitmap(bitmap);
                    }
                });
    }

    public static void displayRoundedCorners(@DrawableRes int resId, RoundedCornersTransformation.CornerType cornerType, ImageView view) {
        getBuild(UIUtils.getContext(), resId)
                .bitmapTransform(new RoundedCornersTransformation(UIUtils.getContext(), 30, 0,
                        cornerType))
                .into(view);
    }

    public static void displayRoundedCorners(String url, RoundedCornersTransformation.CornerType cornerType, ImageView view) {
        getBuild(UIUtils.getContext(), url)
                .bitmapTransform(new RoundedCornersTransformation(UIUtils.getContext(), 30, 0,
                        cornerType))
                .into(view);
    }

    public static void displayRoundedCornersTop(String url, ImageView view) {
        getBuild(UIUtils.getContext(), url)
                .bitmapTransform(new RoundedCornersTransformation(UIUtils.getContext(), 7, 0,
                        RoundedCornersTransformation.CornerType.TOP))
                .into(view);
    }

    public static void displayRoundedCornersBottom(String url, ImageView view) {
        getBuild(UIUtils.getContext(), url)
                .bitmapTransform(new RoundedCornersTransformation(UIUtils.getContext(), 7, 0,
                        RoundedCornersTransformation.CornerType.BOTTOM))
                .into(view);
    }


    public static void displayAllRoundedCornersBottom(String url, int radius, ImageView view) {
        getBuild(UIUtils.getContext(), url)
                .bitmapTransform(new RoundedCornersTransformation(UIUtils.getContext(), radius, 0,
                        RoundedCornersTransformation.CornerType.ALL))
                .into(view);
    }

    private void helper() {
        /**
         * crossFade()	淡入淡出动画   强制淡入淡出		//动画仅仅用于不从缓存中加载的情况。如果图片被缓存过了，它的显示是非常快的，因此动画是没有必要的，并且不显示的
         * dontAnimate() 禁用动画
         * override(horizontalSize, verticalSize) 重设图片大小  这将在图片显示到 ImageView之前重新改变图片大小
         * asGif()   如果这个Url不是一个 Gif，Glide 将会把这个 load 当成失败处理。.error() 回调被调用并且错误占位符被显示
         * asBitmap()  如果你仅仅想要显示 Gif 的第一帧，你可以调用 asBitmap() 去保证其作为一个常规的图片显示
         * 显示本地视频  只支持本地视频！！！
         * skipMemoryCache(true) 跳过内存缓存
         * diskCacheStrategy(DiskCacheStrategy.NONE)	跳过磁盘缓存
         * DiskCacheStrategy.NONE 什么都不缓存，就像刚讨论的那样
         * DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。在我们上面的例子中，将会只有一个 1000x1000 像素的图片
         * DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）
         * DiskCacheStrategy.ALL 缓存所有版本的图像（默认行为）
         * priority(Priority.HIGH)	加载优先级
         *      Priority.LOW
         *      Priority.NORMAL
         *      Priority.HIGH
         *      Priority.IMMEDIATE
         * thumbnail(0.1f)	传入0.1f,Glide将会显示原始图像的10%的大小(1000*1000-100*100)。因为这个图像将会明显比 ImageView 小很多，你需要确保它的 ScaleType 的设置是正确的
         * thumbnail(DrawableRequestBuilder) 以另一个url加载缩略图
         * listener(requestListener)  设置图片加载失败的回调
         * Transformations	可以图片的任意属性：置灰，尺寸，范围，颜色，像素位置等等
         *      glide-transformationsGlide 转换集合
         *      旋转图片利用Transformations和android.graphics.Matrix
         * animate(android.R.anim.slide_in_left) 动画
         *
         * 缩放图像
         * CenterCrop
         * CenterCrop()是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分。ImageView 可能会完全填充，但图像可能不会完整显示。
         *
         * FitCenter
         * fitCenter() 是裁剪技术，即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView。
         *
         * 还支持的功能
         * Targets 返回加载后的Bitmap，即只拿结果，不填充imageview
         * ViewTarget 将图片设置在自定义view中
         * NotificationTarget	加载到通知栏
         * AppWidgetProvider	加载到小控件
         * ViewPropertyAnimation.Animator 自定义动画
         * GlideModule 自定义网络请求库，也可以通过gradle直接导入相应的包,自动集成
         * 还能自定义各种glide配置，如图片质量
         * String requestCustomSizeUrl(int width, int height)
         * 利用GlideModule可以直接改变url，设置请求的图片大小！！！
         */
    }


}
