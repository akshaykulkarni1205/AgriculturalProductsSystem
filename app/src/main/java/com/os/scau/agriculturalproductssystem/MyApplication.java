package com.os.scau.agriculturalproductssystem;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 * Created by lenovo on 2016/8/12.
 */
public class MyApplication extends Application {
    /**
     * ImageLoader框架缓存
     */
    private static ImageLoader imageLoader;
    public static ImageLoader getImageLoader() {
        return imageLoader;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("application","123");
        initImageLoader();
        imageLoader = ImageLoader.getInstance();
    }

    /**
     * ImageLoader初始化
     */
    public void initImageLoader() {
        File cacheFile = FileHelper.createDirectory(FileManager.getSaveFilePath(FileManager.PATH_CACHE));
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.carousel_node_hightlight)
                .showImageForEmptyUri(R.drawable.carousel_node_hightlight)
                .showImageOnFail(R.drawable.carousel_node_hightlight)
                //启动内存缓存
                .cacheInMemory(true)
                //启动磁盘缓存
                .cacheOnDisk(true)
                //图片解码类型
                .bitmapConfig(Bitmap.Config.RGB_565)
                //图片缩放方式
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                //线程池
                .threadPoolSize(5)
                //线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                //最近最少使用原则内存缓存
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                //设置图片下载和显示的工作队列排序
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                //缓存路径，无限制磁盘缓存
                .diskCache(new LimitedAgeDiskCache(cacheFile, 7 * 24 * 3600))
                //磁盘缓存最大值
                .diskCacheSize(50 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
