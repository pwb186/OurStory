package com.pabopwb.ourstory.util;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.room.Room;

import com.pabopwb.ourstory.room.InitDataBase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UtilMethod {

    public static InitDataBase baseRoomDatabase;
    /**
     *用于获取 InitDataBase 数据库的单例实例：
     * 数据库初始化：如果 baseRoomDatabase 为 null（即第一次调用），它会使用 Room.databaseBuilder() 来创建数据库实例，
     * 指定数据库名称为 "ourstory_dataBase.db"，并调用 allowMainThreadQueries() 允许在主线程执行数据库查询（尽管通常不建议这样做，因为可能会导致 UI 卡顿，但在一些简单应用中可以这样做）。
     * 返回数据库实例：方法最终会返回数据库实例，确保整个应用中只会有一个数据库实例。
     **/
    public static InitDataBase getInstance(Context context) {
        if (baseRoomDatabase == null) {
            baseRoomDatabase = Room.databaseBuilder(context, InitDataBase.class, "ourstory_dataBase.db").allowMainThreadQueries().build();
        }
        return baseRoomDatabase;
    }

    /**
     * 一个辅助方法，用来在 Android 中显示一个短时的 Toast 消息：
     * Toast.makeText()：该方法通过上下文 context 和传入的字符串 info 显示一个简短的 Toast 消息。
     * Toast.LENGTH_SHORT：这是 Toast 显示的持续时间，LENGTH_SHORT 表示显示短时间。
     **/
    public static void showToast(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 用于将通过 URI 提供的文件（如图片文件）复制到应用的缓存目录，并返回该文件的路径：
     * 缓存目录：首先，通过 context.getCacheDir() 获取应用的缓存目录，并为文件创建一个新的唯一名称（使用当前时间戳）；
     * 输入流：使用 context.getContentResolver().openInputStream(srcUri) 打开源 URI 对应的文件输入流；
     * 输出流：使用 Files.newOutputStream(Paths.get(path)) 创建一个输出流，将文件存储到指定的路径中；
     * 复制文件：调用 copyStream() 方法将输入流的内容复制到输出流，完成文件存储操作；
     * 返回路径：如果操作成功，返回文件存储的路径；如果有任何错误，则返回 "null"。
     **/
    public static String getPath(Context context, Uri srcUri) {
        String path = context.getCacheDir() + "/" + System.currentTimeMillis() + ".png";//获取本地目录
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(srcUri);//context的方法获取URI文件输入流
            if (inputStream == null) return "null";
            OutputStream outputStream = Files.newOutputStream(Paths.get(path));
            copyStream(inputStream, outputStream);//调用下面的方法存储
            inputStream.close();
            outputStream.close();
            return path;//成功返回路径
        } catch (Exception e) {
            e.printStackTrace();
            return "null";//失败返回路径null
        }
    }
    /**
     * 用于将输入流的数据复制到输出流中，实现文件的存储操作：
     * 缓冲流：使用 BufferedInputStream 和 BufferedOutputStream 来包装输入流和输出流，以提高文件复制效率，尤其是处理大文件时；
     * 读取和写入数据：通过 in.read(buffer) 从输入流中读取数据，并通过 out.write(buffer) 将数据写入输出流；
     * 关闭流：操作完成后，关闭输入流和输出流，确保资源被释放。
     **/
    private static void copyStream(InputStream input, OutputStream output) {//文件存储
        final int BUFFER_SIZE = 1024 * 2;
        byte[] buffer = new byte[BUFFER_SIZE];
        BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
        BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
        int n;
        try {
            while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                out.write(buffer, 0, n);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

